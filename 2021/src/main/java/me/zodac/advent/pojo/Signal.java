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

package me.zodac.advent.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import me.zodac.advent.util.StringUtils;

/**
 * Simple class defining a signal with its input values and output values.
 *
 * @param inputs the input values
 * @param outputs the output values
 */
public record Signal(List<String> inputs, List<String> outputs) {

    private static final Pattern VALID_SIGNAL = Pattern.compile("([a-g]{2,7}\\s?){10}\\|(\\s?[a-g]{2,7}){4}");

    /**
     * Constructs a {@link Signal}, given an input {@link String} in the form:
     * <pre>
     * acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
     * </pre>
     *
     * <p>
     * We split at the delimiter and keep the inputs and outputs. Each input and output value will be ordered alphabetically.
     *
     * @param signalEntry the raw signal input
     * @return the created {@link Signal}
     * @throws IllegalArgumentException thrown if the input is invalid
     */
    public static Signal create(final String signalEntry) {
        if (!VALID_SIGNAL.matcher(signalEntry).matches()) {
            throw new IllegalArgumentException(String.format("Invalid input: '%s'", signalEntry));
        }

        final String[] inputTokens = signalEntry.split("\\|");

        final List<String> inputs = Arrays.stream(StringUtils.splitOnWhitespace(inputTokens[0]))
            .map(StringUtils::sort)
            .toList();

        final List<String> outputs = Arrays.stream(StringUtils.splitOnWhitespace(inputTokens[1]))
            .map(StringUtils::sort)
            .toList();

        return new Signal(inputs, outputs);
    }
}
