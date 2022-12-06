/*
 * BSD Zero Clause License
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

package me.zodac.advent.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Utility functions for arrays.
 */
public final class ArrayUtils {

    private static final Boolean[] EMPTY_BOOLEAN_ARRAY = new Boolean[0];
    private static final char[][] EMPTY_2D_CHAR_ARRAY = new char[0][0];

    private ArrayUtils() {

    }

    /**
     * For a 2D char array, checks whether that the length of each of the inner arrays are different.
     *
     * @param input the input 2D char array to check
     * @return {@code true} if any of the inner array lengths differ
     */
    public static boolean areColumnLengthsDifferent(final char[][] input) {
        if (input.length == 0) {
            return false;
        }

        final int firstLength = input[0].length;

        for (int i = 1; i < input.length; i++) {
            final char[] element = input[i];

            if (element.length != firstLength) {
                return true;
            }
        }

        return false;
    }

    /**
     * Converts the provided {@link Collection} of {@link List}s of {@link Boolean}s to a 2D {@link Boolean} array.
     *
     * @param input the input {@link Collection} of {@link List}s
     * @return the 2D {@link Boolean} array
     */
    public static Boolean[][] convertToArrayOfBooleanArrays(final Collection<? extends List<Boolean>> input) {
        final Boolean[][] array = new Boolean[input.size()][];

        int i = 0;
        for (final List<Boolean> row : input) {
            array[i++] = row.toArray(EMPTY_BOOLEAN_ARRAY);
        }

        return array;
    }

    /**
     * Converts the provided {@link List} of {@link String}s into a 2D char array.
     *
     * <p>
     * Given a {@link List} as follows:
     * <pre>
     *     [
     *      "abcd",
     *      "efg",
     *      "hi",
     *      "jkl"
     *     ]
     * </pre>
     *
     * <p>
     * The result will be:
     * <pre>
     *     [
     *      ['a', 'b', 'c', 'd'],
     *      ['e', 'f', 'g'],
     *      ['h', 'i'],
     *      ['j, 'k', 'l']
     *     ]
     * </pre>
     *
     * @param input the input {@link List} of {@link String}s
     * @return the 2D char array
     */
    public static char[][] convertToArrayOfCharArrays(final List<String> input) {
        if (input.isEmpty() || input.get(0).isEmpty()) {
            return EMPTY_2D_CHAR_ARRAY.clone();
        }

        final int outerLength = input.size();
        final int innerLength = input
            .stream()
            .mapToInt(String::length)
            .max()
            .orElse(outerLength);

        final char[][] arrayOfCharArrays = new char[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfCharArrays[i] = line.toCharArray();
        }

        return arrayOfCharArrays;
    }

    /**
     * Given an unsorted {@code int} array, finds the smallest index that has a value greater than the {@code thresholdValue}.
     *
     * @param input          the input to check
     * @param thresholdValue the threshold value that any array entry must be greater than
     * @return the smallest index in the {@code int} array
     * @throws IllegalArgumentException thrown if the input is {@code null}, empty or does not have any value greater than the {@code thresholdValue}
     */
    public static int findSmallestIndexGreaterThanThreshold(final int[] input, final int thresholdValue) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        for (int i = 0; i < input.length; i++) {
            final int houseValue = input[i];

            if (houseValue > thresholdValue) {
                return i;
            }
        }

        throw new IllegalArgumentException(String.format("No value in input is greater than %s", thresholdValue));
    }

    /**
     * Given a 2D char array, reversed the order of the rows. For example, given the 2D array:
     * <pre>
     *     [
     *      ['a', 'b', 'c'],
     *      ['d', 'e', 'f'],
     *      ['g', 'h', 'i'],
     *      ['j', 'k', 'l']
     *     ]
     * </pre>
     *
     * <p>
     * We would then return:
     * <pre>
     *     [
     *      ['j', 'k', 'l'],
     *      ['g', 'h', 'i'],
     *      ['d', 'e', 'f'],
     *      ['a', 'b', 'c']
     *     ]
     * </pre>
     *
     * @param input the input 2D char array
     * @return the reversed 2D array
     * @throws IllegalArgumentException if any of the columns are of different lengths
     */
    public static char[][] reverseRows(final char[][] input) {
        if (input.length == 0 || input[0].length == 0) {
            return input;
        }

        if (areColumnLengthsDifferent(input)) {
            throw new IllegalArgumentException("Column lengths must be the same in all rows, found: " + Arrays.deepToString(input));
        }

        final int outerLength = input.length;
        final int innerLength = input[0].length;
        final char[][] output = new char[outerLength][innerLength];

        for (int i = 0; i < outerLength; i++) {
            output[i] = input[outerLength - 1 - i];
        }
        return output;
    }

    /**
     * Tranposes the provided 2D char array. A transpose 'flips' the 2D array along the diagonal axis. For example, given the 2D array:
     * <pre>
     *     [
     *      ['a', 'b', 'c'],
     *      ['d', 'e', 'f'],
     *      ['g', 'h', 'i'],
     *      ['j', 'k', 'l']
     *     ]
     * </pre>
     *
     * <p>
     * We would then return:
     * <pre>
     *     [
     *      ['a', 'd', 'g', 'j'],
     *      ['b', 'e', 'h', 'k'],
     *      ['c', 'f', 'i', 'l']
     *     ]
     * </pre>
     *
     * @param input the input 2D char array
     * @return the transposed 2D array
     * @throws IllegalArgumentException if any of the columns are of different lengths
     */
    public static char[][] transpose(final char[][] input) {
        if (input.length == 0 || input[0].length == 0) {
            return input;
        }

        if (areColumnLengthsDifferent(input)) {
            throw new IllegalArgumentException("Column lengths must be the same in all rows, found: " + Arrays.deepToString(input));
        }

        final int outerLength = input.length;
        final int innerLength = input[0].length;

        final char[][] transposedArray = new char[innerLength][outerLength];
        for (int i = 0; i < innerLength; i++) {
            for (int j = 0; j < outerLength; j++) {
                transposedArray[i][j] = input[j][i];
            }
        }

        return transposedArray;
    }
}
