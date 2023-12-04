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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Utility functions for arrays.
 */
public final class ArrayUtils {

    private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    private static final boolean[][] EMPTY_2D_BOOLEAN_ARRAY = new boolean[0][0];
    private static final Boolean[][] EMPTY_2D_BOOLEAN_OBJECT_ARRAY = new Boolean[0][0];
    private static final Character[][] EMPTY_2D_CHARACTER_ARRAY = new Character[0][0];
    private static final Integer[][] EMPTY_2D_INTEGER_ARRAY = new Integer[0][0];

    private ArrayUtils() {

    }

    /**
     * Converts the provided {@link List} of {@link String}s to a 2D {@link Boolean} array. Each {@link String} will be converted to an array of
     * {@link Boolean}s, where each character is compared to {@code symbolSignifyingTrue}.
     *
     * <p>
     * Given a {@link List} as follows, with a {@code symbolSignifyingTrue} of <b>a</b>:
     * <pre>
     *     [
     *      "abbb",
     *      "bab",
     *      "aa",
     *      "bbb"
     *     ]
     * </pre>
     *
     * <p>
     * The result will be:
     * <pre>
     *     [
     *      [TRUE, FALSE, FALSE, FALSE],
     *      [FALSE, TRUE, FALSE],
     *      [TRUE, TRUE],
     *      [FALSE, FALSE, FALSE]
     *     ]
     * </pre>
     *
     * @param input                the input {@link String}s
     * @param symbolSignifyingTrue the symbol in the {@link String} that defines a {@code true} {@link Boolean}
     * @return the 2D {@link Boolean} array
     */
    public static Boolean[][] convertToArrayOfBooleanArrays(final List<String> input, final char symbolSignifyingTrue) {
        if (input.isEmpty() || input.getFirst().isEmpty()) {
            return EMPTY_2D_BOOLEAN_OBJECT_ARRAY.clone();
        }

        final int outerLength = input.size();
        final int innerLength = input
            .stream()
            .mapToInt(String::length)
            .max()
            .orElse(outerLength);

        final Boolean[][] arrayOfBooleanArrays = new Boolean[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfBooleanArrays[i] = convertToArrayOfBooleans(line, symbolSignifyingTrue);
        }

        return arrayOfBooleanArrays;
    }

    private static Boolean[] convertToArrayOfBooleans(final CharSequence input, final char symbolSignifyingTrue) {
        final Boolean[] booleanArray = new Boolean[input.length()];
        for (int i = 0; i < input.length(); i++) {
            booleanArray[i] = input.charAt(i) == symbolSignifyingTrue;
        }
        return booleanArray;
    }

    /**
     * Converts the provided {@link List} of {@link String}s into a 2D {@link Character} array. We assume each character in the {@link String} is a
     * {@link Character}, there is no support for multiple-digit {@link Character}s.
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
     *      [a, b, c, d],
     *      [e, f, g],
     *      [h, i],
     *      [j, k, l]
     *     ]
     * </pre>
     *
     * <p>
     * If any character in an input {@link String} is not a valid {@link Character}, a value of <b>'0''</b> will be set for that character.
     *
     * @param input the input {@link List} of {@link String}s
     * @return the 2D {@link Character} array
     */
    public static Character[][] convertToArrayOfCharacterArrays(final List<String> input) {
        if (input.isEmpty() || input.getFirst().isEmpty()) {
            return EMPTY_2D_CHARACTER_ARRAY.clone();
        }

        final int outerLength = input.size();
        final int innerLength = input
            .stream()
            .mapToInt(String::length)
            .max()
            .orElse(outerLength);

        final Character[][] arrayOfCharacterArrays = new Character[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfCharacterArrays[i] = convertToArrayOfCharacters(line);
        }

        return arrayOfCharacterArrays;
    }

    private static Character[] convertToArrayOfCharacters(final CharSequence input) {
        final Character[] characterArray = new Character[input.length()];
        for (int i = 0; i < input.length(); i++) {
            characterArray[i] = input.charAt(i);
        }
        return characterArray;
    }

    /**
     * Converts the provided {@link List} of {@link String}s into a 2D {@link Integer} array. We assume each character in the {@link String} is a
     * one-digit {@link Integer}, there is no support for multiple-digit {@link Integer}s.
     *
     * <p>
     * Given a {@link List} as follows:
     * <pre>
     *     [
     *      "1234",
     *      "567",
     *      "89",
     *      "012"
     *     ]
     * </pre>
     *
     * <p>
     * The result will be:
     * <pre>
     *     [
     *      [1, 2, 3, 4],
     *      [5, 6, 7],
     *      [8, 9],
     *      [0, 1, 2]
     *     ]
     * </pre>
     *
     * <p>
     * If any character in an input {@link String} is not a valid {@link Integer}, a value of <b>0</b> will be set for that character.
     *
     * @param input the input {@link List} of {@link String}s
     * @return the 2D {@link Integer} array
     */
    public static Integer[][] convertToArrayOfIntegerArrays(final List<String> input) {
        if (input.isEmpty() || input.getFirst().isEmpty()) {
            return EMPTY_2D_INTEGER_ARRAY.clone();
        }

        final int outerLength = input.size();
        final int innerLength = input
            .stream()
            .mapToInt(String::length)
            .max()
            .orElse(outerLength);

        final Integer[][] arrayOfIntegerArrays = new Integer[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfIntegerArrays[i] = convertToArrayOfIntegers(line);
        }

        return arrayOfIntegerArrays;
    }

    private static Integer[] convertToArrayOfIntegers(final CharSequence input) {
        final Integer[] integerArray = new Integer[input.length()];
        for (int i = 0; i < input.length(); i++) {
            try {
                integerArray[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
            } catch (final NumberFormatException e) {
                integerArray[i] = 0;
            }
        }
        return integerArray;
    }

    /**
     * Counts the number of elements along the end of a 2D array. This is calculated as:
     * <pre>
     *     (length - 1) * 4
     * </pre>
     *
     * @param input the array to check
     * @param <E>   the type of the array
     * @return the number of elements on the perimeter
     */
    public static <E> int countPerimeterElements(final E[][] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        final int outerLength = input.length;
        final int innerLength = input[0].length;

        if (outerLength != innerLength) {
            throw new IllegalArgumentException(
                String.format("Outer size must match inner size, found outer: %s, inner: %s", outerLength, innerLength));
        }

        return (input.length - 1) << 2;
    }

    /**
     * Given a 2D array, we create a new array and fill it with the provided {@code value}. The input array is unmodified.
     *
     * @param input the input array
     * @param value the value to set in the array
     * @return the filled array
     */
    public static boolean[][] deepFill(final boolean[][] input, final boolean value) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            return EMPTY_2D_BOOLEAN_ARRAY.clone();
        }

        final boolean[][] array = deepCopy(input);
        for (final boolean[] innerArray : array) {
            for (int j = 0; j < array[0].length; j++) {
                innerArray[j] = value;
            }
        }

        return array;
    }

    private static boolean[][] deepCopy(final boolean[][] input) {
        return Arrays.stream(input)
            .map(boolean[]::clone)
            .toArray(array -> input.clone());
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
     * Flattens a 2D array into a 1D array. For example, given the input 2D array:
     * <pre>
     *     [
     *       [true, true, true],
     *       [false, false, false],
     *       [true, false, true],
     *     ]
     * </pre>
     *
     * <p>
     * Then the output 1D array would be:
     * <pre>
     *     [true, true, true, false, false, false, true, false, true]
     * </pre>
     *
     * @param input the input 2D array to flatten
     * @return the flattened 1D array, or an empty array if input is {@code null} or empty
     */
    public static boolean[] flatten(final boolean[][] input) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            return EMPTY_BOOLEAN_ARRAY.clone();
        }

        final int size = size(input);
        final boolean[] output = new boolean[size];

        int i = 0;
        for (final boolean[] row : input) {
            for (final boolean column : row) {
                output[i] = column;
                i++;
            }
        }

        return output;
    }

    /**
     * Given a 2D char array, returns the length of the longest inner char array.
     *
     * @param input the input 2D char array
     * @param <E>   the type of the {@code input}
     * @return the length of the longest inner array
     * @throws IllegalArgumentException thrown if the input is {@code null} or an empty array
     */
    public static <E> int maxInnerLength(final E[][] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return Arrays.stream(input)
            .mapToInt(array -> array.length)
            .max()
            .orElseThrow(() -> new IllegalArgumentException("Cannot find max length of input: " + Arrays.deepToString(input)));
    }

    /**
     * Given a 2D char array, reverses the order of the rows. For example, given the 2D array:
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
     * @param <E>   the type of the {@code input}
     * @return the reversed 2D array
     * @throws IllegalArgumentException thrown if the input is {@code null} or empty
     */
    @SuppressWarnings("unchecked")
    public static <E> E[][] reverseRows(final E[][] input) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        final int outerLength = input.length;
        final int innerLength = maxInnerLength(input);

        final E[][] output = (E[][]) Array.newInstance(input[0][0].getClass(), outerLength, innerLength);
        for (int i = 0; i < outerLength; i++) {
            output[i] = input[outerLength - 1 - i];
        }
        return output;
    }

    /**
     * Counts the number of elements in a 2D array. Used when there is no guarantee that the rows of a 2D array are a consistent length.
     *
     * @param input the array to check
     * @return the number of elements in the array, or <b>0</b> if the input is {@code null}
     */
    public static int size(final boolean[][] input) {
        if (input == null) {
            return 0;
        }

        int size = 0;
        for (final boolean[] row : input) {
            size += row.length;
        }

        return size;
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
     * <p>
     * <b>NOTE:</b> If the inner arrays are of different lengths, the shorter arrays will be filled with blank characters to fill them up.
     *
     * @param input the input 2D char array
     * @return the transposed 2D array
     */
    public static Character[][] transpose(final Character[][] input) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            return EMPTY_2D_CHARACTER_ARRAY.clone();
        }

        final int outerLength = input.length;
        final int innerLength = Arrays.stream(input)
            .mapToInt(array -> array.length)
            .max()
            .orElse(0);

        final Character[][] transposedArray = new Character[innerLength][outerLength];
        for (int i = 0; i < innerLength; i++) {
            for (int j = 0; j < outerLength; j++) {
                if (i >= input[j].length) {
                    transposedArray[i][j] = ' ';
                } else {
                    transposedArray[i][j] = input[j][i];
                }
            }
        }

        return transposedArray;
    }
}
