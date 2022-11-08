/*
 * Zero-Clause BSD License
 *
 * Copyright (c) 2021-2022 zodac.me
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
