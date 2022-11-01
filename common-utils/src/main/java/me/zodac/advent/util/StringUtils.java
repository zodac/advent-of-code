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

package me.zodac.advent.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class with {@link String}-based functions.
 */
public final class StringUtils {

    /**
     * Pattern defining a valid positive or genative {@link Integer}.
     */
    public static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\b(\\d{1,10})\\b"); // 10 for Integer.MAX_VALUE

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    private static final Pattern FULLY_UPPERCASE_WORDS_PATTERN = Pattern.compile("(\\b[A-Z][A-Z]+\\b)");
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private StringUtils() {

    }

    /**
     * Sorts the individual characters in the given {@link String} alphabetically.
     *
     * <p>
     * For example, {@code foobar} will be returned as {@code abfoor}
     *
     * @param input the {@link String} to sort
     * @return the sorted {@link String}
     */
    public static String sort(final String input) {
        final char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Splits the given {@link String} on any whitespaces. Will {@link String#trim()} before splitting.
     *
     * @param input the {@link String} to split
     * @return the array of split {@link String}s
     */
    public static String[] splitOnWhitespace(final String input) {
        if (input == null) {
            return EMPTY_STRING_ARRAY;
        }
        return WHITESPACE_PATTERN.split(input.trim());
    }

    /**
     * Checks if the characters in the provided {@code superString} is a superset for all provided {@code subStrings}.
     *
     * @param superString the {@link String} that should contain all characters in all {@code subStrings}
     * @param subStrings  the {@link String}s that should be subsets of {@code superString}
     * @return {@code false} if any character in any {@code subString} is not in the {@code superString}
     */
    public static boolean containsAllCharacters(final String superString, final String... subStrings) {
        for (final String subString : subStrings) {
            for (final char charToCheck : subString.toCharArray()) {
                if (!superString.contains(Character.toString(charToCheck))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the provided {@code subStrings} are indeed contained in the provided {@code superString}.
     *
     * @param superString the {@link String} that should contain all characters in all {@code subStrings}
     * @param subStrings  the {@link String}s that should be subsets of {@code superString}
     * @return {@code true} if any {@code subString} in contained in the {@code superString}
     */
    public static boolean containsAny(final String superString, final String... subStrings) {
        for (final String subString : subStrings) {
            if (superString.contains(subString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of vowel characters ('a', 'e', 'i', 'o', 'u') in the input {@link String}.
     *
     * @param input the {@link String} to check
     * @return the number of vowels in the {@link String}
     */
    public static long countVowels(final String input) {
        if (input == null || input.isBlank()) {
            return 0L;
        }

        int numberOfVowels = 0;
        for (final char c : input.toLowerCase(Locale.UK).toCharArray()) {
            if (VOWELS.contains(c)) {
                numberOfVowels++;
            }
        }

        return numberOfVowels;
    }

    /**
     * Checks if the provided {@link String} has at least one character repeated, in order. For example, <b>aabc</b> will return {@code true},
     * while <b>abac</b> will return {@code false}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if there is at least one character repeated in order
     */
    public static boolean hasRepeatedCharacterInOrder(final String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        final int stringLength = input.length();
        for (int i = 0; i < stringLength - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the input {@link String} has any 'sandwich' characters, meaning a character that repeats in the {@link String}, with a single
     * character in between (this middle character can be the same as the outer character). For example, both <b>aba</b> and <b>aaa</b> would return
     * {@code true}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if at least one set of 'sandwich' characters exist in the {@link String}
     */
    public static boolean hasSandwichCharacters(final String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        final int stringLength = input.length();
        for (int i = 2; i < stringLength; i++) {
            if (input.charAt(i - 2) == input.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the input {@link String} has any pair of characters that repeats in the {@link String}, but which does not have an overlap. For
     * example, both <b>abab</b> and <b>aabcdefaa</b> would return {@code true}, while <b>aaa</b> would return {@code false} due to the last character
     * of one pair overlapping with the first character of the other pair.
     *
     * @param input the {@link String} to check
     * @return {@code true} if at least one pair of characters repeats without overlap in the {@link String}
     */
    public static boolean hasCharacterPairRepeatWithNoOverlap(final String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        final int stringLength = input.length();
        for (int i = 1; i < stringLength; i++) {
            final String remainderOfInput = input.substring(i + 1);
            final String pair = String.format("%c%c", input.charAt(i - 1), input.charAt(i));

            if (remainderOfInput.contains(pair)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the first fully uppercase word (a substring surrounded by whitespace) in the input {@link String}.
     *
     * @param input the {@link String} to check
     * @return the first fully uppercase word
     * @throws IllegalArgumentException thrown if the {@code input} has no uppercase word
     */
    public static Optional<String> findFirstFullyUpperCaseWord(final String input) {
        if (input == null || input.isBlank()) {
            return Optional.empty();
        }

        final Matcher matcher = FULLY_UPPERCASE_WORDS_PATTERN.matcher(input);

        if (!matcher.find()) {
            return Optional.empty();
        }

        return Optional.of(matcher.group());
    }

    /**
     * Checks if the input {@link String} is a valid {@link Integer} or {@link Long}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if the input is an {@link Integer} or {@link Long}
     */
    public static boolean isInteger(final String input) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return INTEGER_PATTERN.matcher(input).matches();
    }

    /**
     * Parses the input {@link String} and returns any {@link Integer} values in the order provided.
     *
     * @param input the {@link String} to check
     * @return the found {@link Integer}s
     */
    public static List<Integer> collectIntegersInOrder(final String input) {
        if (input == null || input.isBlank()) {
            return Collections.emptyList();
        }

        final Matcher matcher = INTEGER_PATTERN.matcher(input);

        final List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        return numbers;
    }

    /**
     * Performs the 'lookAndSay' sequence on the input {@link String} of numbers.
     *
     * <p>
     * The 'lookAndSay' sequence 'reads' the input aloud and creates a new output based on runs of similar numbers. For example, given the input
     * <b>132211</b>, we can break this down as:
     * <pre>
     *     one one
     *     one three
     *     two two(s)
     *     two one(s)
     * </pre>
     *
     * <p>
     * This would give us an output of <b>11132221</b>.
     *
     * @param input the {@link String} input
     * @return the output of the 'lookAndSay' sequence, or the input if input is <b>null</b>
     */
    public static String lookAndSay(final String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        final StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            int count = 1;
            final char c = input.charAt(i);

            while (i + 1 < input.length() && input.charAt(i + 1) == c) {
                i++; // If next char is same as current, move the pointer to the next char
                count++;
            }

            output.append(count);
            output.append(c);
        }
        return output.toString();
    }
}
