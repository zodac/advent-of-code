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

import java.util.Collection;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2015, Day 5.
 *
 * @see <a href="https://adventofcode.com/2015/day/5">AoC 2015, Day 5</a>
 */
public final class Day05 {

    private static final String[] INVALID_SUBSTRINGS = {"ab", "cd", "pq", "xy"};
    private static final long MINIMUM_NUMBER_OF_VOWELS_REQUIRED = 3L;

    private Day05() {

    }

    /**
     * Takes a {@link Collection} of {@link String}s and validates them based on the following rules:
     * <ul>
     *     <li>Cannot contain any of the substrings: "ab", "cd", "pq", "xy"</li>
     *     <li>Must have at least 3 vowels</li>
     *     <li>Must have at least one character repeating itself</li>
     * </ul>
     *
     * @param strings the {@link String}s to validate
     * @return the number of valid {@link String}s
     */
    public static long countValidStringsPartOne(final Collection<String> strings) {
        return strings
            .stream()
            .filter(Day05::isValidForPartOne)
            .count();
    }

    private static boolean isValidForPartOne(final String input) {
        if (StringUtils.containsAny(input, INVALID_SUBSTRINGS)) {
            return false;
        }

        if (StringUtils.countVowels(input) < MINIMUM_NUMBER_OF_VOWELS_REQUIRED) {
            return false;
        }

        return StringUtils.hasRepeatedCharacterInOrder(input);
    }

    /**
     * Takes a {@link Collection} of {@link String}s and validates them based on the following rules:
     * <ul>
     *     <li>Must have a repeated character, with a character in between</li>
     *     <li>Must have a repeated pair of characters, with no overlap between the pairs</li>
     * </ul>
     *
     * @param strings the {@link String}s to validate
     * @return the number of valid {@link String}s
     */
    public static long countValidStringsPartTwo(final Collection<String> strings) {
        return strings
            .stream()
            .filter(Day05::isValidForPartTwo)
            .count();
    }

    private static boolean isValidForPartTwo(final String input) {
        return StringUtils.hasSandwichCharacters(input) && StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
    }
}
