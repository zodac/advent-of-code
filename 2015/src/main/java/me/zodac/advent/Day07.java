/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zodac.advent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.BitwiseOperator;
import me.zodac.advent.pojo.Pair;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2015, Day 7.
 *
 * @see <a href="https://adventofcode.com/2015/day/7">AoC 2015, Day 7</a>
 */
public final class Day07 {

    private static final Pattern OUTPUT_PATTERN = Pattern.compile(" -> ");
    private static final int BIT_MASK = 0xFFFF;

    private final Map<String, Integer> calculatedValuesByLabel;
    private final Map<? super String, Pair<BitwiseOperator, String>> commandByLabel;

    private Day07(final Map<? super String, Pair<BitwiseOperator, String>> commandByLabel) {
        calculatedValuesByLabel = new HashMap<>();
        this.commandByLabel = commandByLabel;
    }

    /**
     * Constructor for {@link Day07}.
     *
     * @param values the input {@link String} values
     * @return the created {@link Day07} instance
     */
    public static Day07 create(final Iterable<String> values) {
        final Map<String, Pair<BitwiseOperator, String>> commandByLabel = new HashMap<>();
        for (final String value : values) {
            final String[] commandAndOutputLabel = OUTPUT_PATTERN.split(value, 2);
            final String command = commandAndOutputLabel[0];

            final BitwiseOperator bitwiseOperator = StringUtils.findFirstFullyUpperCaseWord(command)
                .map(BitwiseOperator::get)
                .orElse(BitwiseOperator.SET);
            commandByLabel.put(commandAndOutputLabel[1], Pair.of(bitwiseOperator, command));
        }

        return new Day07(commandByLabel);
    }

    /**
     * Evaluate the value of the wanted label.
     *
     * @param wantedLabel the label of the value to find
     * @return the evaluated value
     */
    public int evaulate(final String wantedLabel) {
        return evaulateWithOverride(wantedLabel, null, null);
    }

    /**
     * Evaluate the value of the wanted label, where a known label can have its value overridden.
     *
     * @param wantedLabel   the label of the value to find
     * @param overrideLabel the label of the value to be overridden
     * @param overrideValue the value to override
     * @return the evaluated value
     */
    public int evaulateWithOverride(final String wantedLabel, final String overrideLabel, final String overrideValue) {
        if (overrideLabel != null) {
            commandByLabel.put(overrideLabel, Pair.of(BitwiseOperator.SET, overrideValue));
        }

        return getValue(wantedLabel);
    }

    private int getValue(final String wantedLabel) {
        if (!calculatedValuesByLabel.containsKey(wantedLabel)) {
            final int evaluatedCommand = evaluateCommand(wantedLabel);
            final int valueWithBitMask = evaluatedCommand & BIT_MASK;
            calculatedValuesByLabel.put(wantedLabel, valueWithBitMask);
        }

        return calculatedValuesByLabel.get(wantedLabel);
    }

    private int evaluateCommand(final String wantedLabel) {
        if (StringUtils.isInteger(wantedLabel)) {
            return Integer.parseInt(wantedLabel);
        }

        final Pair<BitwiseOperator, String> operatorAndCommand = commandByLabel.get(wantedLabel);
        final BitwiseOperator bitwiseOperator = operatorAndCommand.first();
        final String command = operatorAndCommand.second();
        final String[] commandTokens = StringUtils.splitOnWhitespace(command);

        return switch (bitwiseOperator) {
            case NOT -> bitwiseOperator.calculate(List.of(getValue(commandTokens[1])));
            case AND, OR, LSHIFT, RSHIFT -> bitwiseOperator.calculate(List.of(getValue(commandTokens[0]), getValue(commandTokens[2])));
            default -> StringUtils.isInteger(commandTokens[0]) ? Integer.parseInt(commandTokens[0]) : getValue(commandTokens[0]);
        };
    }
}
