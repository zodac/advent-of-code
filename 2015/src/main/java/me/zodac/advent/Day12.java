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

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2015, Day 12.
 *
 * @see <a href="https://adventofcode.com/2015/day/12">AoC 2015, Day 12</a>
 */
public final class Day12 {

    private static final Pattern JSON_ELEMENT_PATTERN = Pattern.compile("(\\{[-\\w,:\"]*})|(\\[[-\\w,\"]*])");
    private static final Pattern JSON_VALUE_PATTERN = Pattern.compile("\\{[-\\w,:\"]*}");

    private Day12() {

    }

    /**
     * Takes the input {@link String}, finds all {@link Integer} values and sums them up.
     *
     * @param input the input to be parsed
     * @return the sum of all numbers
     */
    public static long sumOfAllNumbers(final String input) {
        return StringUtils.collectIntegersInOrder(input)
            .stream()
            .mapToLong(Integer::intValue)
            .sum();
    }

    /**
     * Takes the input {@link String}, finds all {@link Integer} values and sums them up. The input will be a JSON {@link String}, and if any of the
     * JSON elements have a value matching the {@code invalidLabel} then that JSON element and all of its children will not be included.
     *
     * <p>
     * Non-JSON solution modified from <a href="https://www.reddit.com/r/adventofcode/comments/3wh73d/day_12_solutions/cxwr7q0/">reddit</a>.
     *
     * @param input             the input to be parsed
     * @param invalidLabelValue the value signifying an invalid JSON element
     * @return the sum of all valid numbers
     */
    public static long sumOfAllNumbersWithNoInvalidLabel(final String input, final String invalidLabelValue) {
        final String invalidLabel = String.format(".*:\"%s\".*", invalidLabelValue);
        String output = input;
        Matcher jsonMatcher = JSON_ELEMENT_PATTERN.matcher(output);

        while (jsonMatcher.find()) {
            // Process JSON subString
            int sumOfSubString = 0;
            if (isValidJsonAndDoesNotIncludeInvalidLabel(invalidLabel, jsonMatcher)) {
                final Matcher integerMatcher = StringUtils.INTEGER_PATTERN.matcher(jsonMatcher.group(0));
                while (integerMatcher.find()) {
                    sumOfSubString += Integer.parseInt(integerMatcher.group(0));
                }
            }

            // Strip the String of processed JSON, update matcher to use remainder of the String while including the sum
            output = output.substring(0, jsonMatcher.start()) + sumOfSubString + output.substring(jsonMatcher.end());
            jsonMatcher = JSON_ELEMENT_PATTERN.matcher(output);
        }

        return Long.parseLong(output);
    }

    private static boolean isValidJsonAndDoesNotIncludeInvalidLabel(final String invalidLabel, final MatchResult jsonMatcher) {
        return !(JSON_VALUE_PATTERN.matcher(jsonMatcher.group(0)).matches() && jsonMatcher.group(1).matches(invalidLabel));
    }
}
