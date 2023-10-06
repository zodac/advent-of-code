/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2023 zodac.me
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

package me.zodac.advent.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Utility class with {@link String}-based functions.
 */
public final class StringUtils {

    /**
     * Pattern defining a valid positive or negative number.
     */
    public static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\b(\\d+)\\b");

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    private static final Pattern NEW_LINE_PATTERN = Pattern.compile("\\r?\\n");
    private static final Pattern FULLY_UPPERCASE_WORDS_PATTERN = Pattern.compile("(\\b[A-Z][A-Z]+\\b)");
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String EMPTY_STRING = "";
    private static final int LENGTH_OF_SINGLE_CHARACTER = 1;

    private StringUtils() {

    }

    /**
     * Bisects the given {@link String} and returns both halfs as a {@link Pair} of {@link String}s.
     *
     * @param input the {@link String} to split in half
     * @return the {@link String} halves as a {@link Pair}
     * @throws IllegalArgumentException thrown if the input {@link String} does not have an even length, or if the input is {@code null}
     */
    public static Pair<String, String> bisect(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        if (MathUtils.isOdd(input.length())) {
            throw new IllegalArgumentException(String.format("Cannot bisect input of length: %s", input.length()));
        }

        final int middleIndex = input.length() / 2;
        return Pair.of(input.substring(0, middleIndex), input.substring(middleIndex));
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

        final Matcher matcher = NUMBER_PATTERN.matcher(input);

        final List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            final String value = matcher.group();
            if (isInteger(value)) {
                numbers.add(Integer.parseInt(value));
            }
        }

        return numbers;
    }

    /**
     * Compares the provided {@link String}s and returns the common {@link Character}s in all {@link String}s.
     *
     * @param first  the first {@link String}
     * @param others any additional {@link String}s
     * @return a {@link Set} of the common {@link Character}s in all {@link String}s
     * @throws IllegalArgumentException thrown if any input {@link String} is null or blank, or no vararg {@link String}s are provided
     */
    public static Set<Character> commonChars(final String first, final String... others) {
        if (others.length == 0) {
            throw new IllegalArgumentException("Must have at least two strings to compare");
        }

        if (first == null || first.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or blank");
        }

        for (final String other : others) {
            if (other == null || other.isBlank()) {
                throw new IllegalArgumentException("Input cannot be null or blank");
            }
        }

        final Set<Character> firstChars = new HashSet<>();
        for (final char firstChar : first.toCharArray()) {
            firstChars.add(firstChar);
        }

        final Collection<Set<Character>> allOtherChars = new HashSet<>();
        for (final String other : others) {
            final Set<Character> otherChars = new HashSet<>();
            for (final char otherChar : other.toCharArray()) {
                otherChars.add(otherChar);
            }
            allOtherChars.add(otherChars);
        }

        for (final Set<Character> otherChars : allOtherChars) {
            firstChars.retainAll(otherChars);
        }

        return firstChars;
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
     * Checks whether the input {@link String} has any duplicate characters.
     *
     * @param input the input {@link String} to check
     * @return {@code true} if the {@link String} contains at least one duplicate character
     */
    public static boolean containsDuplicates(final CharSequence input) {
        return input != null && input.length() != input.chars().distinct().count();
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
     * Checks if the input {@link String} has any pair of characters that repeats in the {@link String}, but which does not have an overlap. For
     * example, both <b>abab</b> and <b>aabcdefaa</b> would return {@code true}, while <b>aaa</b> would return {@code false} due to the last character
     * of one pair overlapping with the first character of the other pair.
     *
     * @param input the {@link String} to check
     * @return {@code true} if at least one pair of characters repeats without overlap in the {@link String}
     */
    public static boolean hasRepeatedCharacterPairWithNoOverlap(final String input) {
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
     * Checks if the input {@link String} is a valid {@link Integer}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if the input is an {@link Integer}
     */
    public static boolean isInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
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
            return EMPTY_STRING;
        }

        final StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            int count = 1;
            final char c = input.charAt(i);

            while (i + 1 < input.length() && input.charAt(i + 1) == c) {
                i++; // If next char is same as current, move the pointer to the next char
                count++;
            }

            output.append(count).append(c);
        }
        return output.toString();
    }

    /**
     * Iterates through both input {@link String}s index by index, and if the character at any index is different, that character is removed.
     *
     * <p>
     * For example, given {@code foobar} and {@code fuubar}, the returned value will be:
     * <pre>
     *   {@code fbar}
     * </pre>
     *
     * @param first  the first {@link String}
     * @param second the second {@link String}
     * @return the {@link String} with any differences removed
     * @throws IllegalArgumentException thrown if the two input {@link String}s do not have the same length, or if either input is {@code null}
     */
    public static String removeDifferentCharacters(final String first, final String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }

        if (first.length() != second.length()) {
            throw new IllegalArgumentException(
                String.format("Expected inputs of equal length, found %s (%s) and %s (%s)", first, first.length(), second, second.length()));
        }

        final int stringLength = first.length();

        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            final char charFromFirst = first.charAt(i);
            final char charFromSecond = second.charAt(i);

            if (charFromFirst == charFromSecond) {
                stringBuilder.append(charFromFirst);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Removes the last character in the {@link String}.
     *
     * <p>
     * For example, given {@code foobar}, the returned value will be:
     * <pre>
     *   {@code fooba}
     * </pre>
     *
     * @param input the input {@link String}
     * @return the updated {@link String}
     */
    public static String removeLastCharacter(final String input) {
        return removeLastCharacters(input, 1);
    }

    /**
     * Removes the last {@code numberOfCharactersToRemove} characters in the {@link String}.
     *
     * <p>
     * For example, given {@code foobar} and {@code numberOfCharactersToRemove} set to <b>3</b>, the returned value will be:
     * <pre>
     *   {@code foo}
     * </pre>
     *
     * @param input                      the input {@link String}
     * @param numberOfCharactersToRemove the number of characters to remove
     * @return the updated {@link String}
     * @throws IllegalArgumentException if the {@code numberOfCharactersToRemove} is less than 1, or is greater than the length of the {@code input}
     */
    public static String removeLastCharacters(final String input, final int numberOfCharactersToRemove) {
        if (input == null || input.isEmpty()) {
            return EMPTY_STRING;
        }

        if (numberOfCharactersToRemove < LENGTH_OF_SINGLE_CHARACTER) {
            throw new IllegalArgumentException("Must remove at least 1 character, found: " + numberOfCharactersToRemove);
        }

        if (numberOfCharactersToRemove > input.length()) {
            throw new IllegalArgumentException(
                String.format("Cannot remove %s characters from input of length: %s", numberOfCharactersToRemove, input.length()));
        }

        return input.substring(0, input.length() - numberOfCharactersToRemove);
    }

    /**
     * Replaces a sub-string in the input {@link String}, where the position of the sub-string is known. This is useful for the scenario where we only
     * want to replace a specific sub-string where multiples of that sub-string may exist in the input.
     *
     * @param input            the input {@link String}
     * @param subString        the sub-string to find
     * @param replacement      the sub-string replacement
     * @param indexOfSubString the known index of the sub-string
     * @return the replaced {@link String}
     * @throws IllegalArgumentException thrown if the index is invalid or the sub-string does not exist at that index
     */
    public static String replaceAtIndex(final String input, final CharSequence subString, final String replacement, final int indexOfSubString) {
        if (input == null || input.isBlank()) {
            return EMPTY_STRING;
        }

        final int maxIndexPosition = input.length() - subString.length();
        if (indexOfSubString < 0 || indexOfSubString > maxIndexPosition) {
            throw new IllegalArgumentException(String.format("Expected index to be between 0 and %s, found: %s", maxIndexPosition, indexOfSubString));
        }

        final String actualSubString = input.substring(indexOfSubString, indexOfSubString + subString.length());
        if (!actualSubString.contentEquals(subString)) {
            throw new IllegalArgumentException(
                String.format("Expected to find subString '%s' at index %s, instead found: '%s'", subString, indexOfSubString, actualSubString));
        }

        return input.substring(0, indexOfSubString) + replacement + input.substring(indexOfSubString + subString.length());
    }

    /**
     * Sorts the individual characters in the given {@link String} alphabetically.
     *
     * <p>
     * For example, given {@code foobar}, the returned value will be:
     * <pre>
     *   {@code abfoor}
     * </pre>
     *
     * @param input the {@link String} to sort
     * @return the sorted {@link String}
     */
    public static String sort(final String input) {
        final char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return String.copyValueOf(chars);
    }

    /**
     * Splits the given {@link String} on any new lines.
     *
     * @param input the {@link String} to split
     * @return the array of split {@link String}s
     */
    public static String[] splitOnNewLines(final CharSequence input) {
        if (input == null) {
            return EMPTY_STRING_ARRAY;
        }
        return NEW_LINE_PATTERN.split(input);
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
}
