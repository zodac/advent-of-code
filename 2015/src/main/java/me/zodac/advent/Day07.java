/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.zodac.advent;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.BitwiseOperator;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.NumberUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2015, Day 7.
 *
 * @see <a href="https://adventofcode.com/2015/day/7">AoC 2015, Day 7</a>
 */
public final class Day07 {

    private static final Pattern OUTPUT_PATTERN = Pattern.compile(" -> ");
    private static final int BIT_MASK = 0xFFFF;
    private static final String EMPTY_OVERRIDE_LABEL = "";
    private static final String EMPTY_OVERRIDE_VALUE = "";

    private final Map<String, Integer> calculatedValuesByLabel;
    private final Map<? super String, Pair<BitwiseOperator, String>> commandByLabel;

    private Day07(final Map<? super String, Pair<BitwiseOperator, String>> commandByLabel) {
        calculatedValuesByLabel = new HashMap<>();
        this.commandByLabel = new HashMap<>(commandByLabel);
    }

    /**
     * Constructor for {@link Day07}.
     *
     * @param values the input {@link String} values
     * @return the created {@link Day07} instance
     */
    public static Day07 create(final Collection<String> values) {
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
    public int evaluate(final String wantedLabel) {
        return evaluateWithOverride(wantedLabel, EMPTY_OVERRIDE_LABEL, EMPTY_OVERRIDE_VALUE);
    }

    /**
     * Evaluate the value of the wanted label, where a known label can have its value overridden.
     *
     * @param wantedLabel   the label of the value to find
     * @param overrideLabel the label of the value to be overridden
     * @param overrideValue the value to override
     * @return the evaluated value
     */
    public int evaluateWithOverride(final String wantedLabel, final String overrideLabel, final String overrideValue) {
        if (!EMPTY_OVERRIDE_LABEL.equals(overrideLabel)) {
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
        if (NumberUtils.isInteger(wantedLabel)) {
            return Integer.parseInt(wantedLabel);
        }

        if (!commandByLabel.containsKey(wantedLabel)) {
            throw new IllegalStateException(String.format("Expected to find command with label '%s', found nothing", wantedLabel));
        }

        final Pair<BitwiseOperator, String> operatorAndCommand = commandByLabel.get(wantedLabel);
        final BitwiseOperator bitwiseOperator = operatorAndCommand.first();
        final String command = operatorAndCommand.second();
        final String[] commandTokens = StringUtils.splitOnWhitespace(command);

        return switch (bitwiseOperator) {
            case NOT -> bitwiseOperator.calculate(List.of(getValue(commandTokens[1])));
            case AND, OR, LSHIFT, RSHIFT -> bitwiseOperator.calculate(List.of(getValue(commandTokens[0]), getValue(commandTokens[2])));
            default -> NumberUtils.isInteger(commandTokens[0]) ? Integer.parseInt(commandTokens[0]) : getValue(commandTokens[0]);
        };
    }
}
