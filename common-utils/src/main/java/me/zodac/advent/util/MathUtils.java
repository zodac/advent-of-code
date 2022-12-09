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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility class with {@link Math}-based functions.
 */
public final class MathUtils {

    private static final double TRIANGULAR_NUMBER_DENOMINATOR = 2.0D;

    private MathUtils() {

    }

    /**
     * Checks if any of the input {@code values} are less than the provided {@code lessThanValue}.
     *
     * @param lessThanValue the value to compare against
     * @param values        the values to check
     * @return {@code true} if any of the input {@code values} are less than the provided {@code lessThanValue}
     */
    public static boolean areAnyLessThan(final long lessThanValue, final long... values) {
        for (final long value : values) {
            if (value < lessThanValue) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sums up the number of elements needed to traverse an infinite 2D array diagonally to reach the wanted {@code row} and {@code column}. For
     * example:
     *
     * <pre>
     *        | 1   2   3   4   5   6
     *     ---+---+---+---+---+---+---+
     *      1 |  1   3   6  10  15  21
     *      2 |  2   5   9  14  20
     *      3 |  4   8  13  19
     *      4 |  7  12  18
     *      5 | 11  17
     *      6 | 16
     * </pre>
     *
     * <p>
     * To get to the value <b>14</b>, at (2, 4) would take 13 steps.
     *
     * <p>
     * <b>NOTE:</b> the infinite diagonal array starts at (1, 1), not (0, 0).
     *
     * @param row    the row
     * @param column the column
     * @return the number of elements to get to the wanted {@code row} and {@code column}
     * @throws IllegalArgumentException thrown if the input {@code row} or {@code column} is less than <b>1</b>
     */
    public static long diagonalSum(final int row, final int column) {
        if (row < 1 || column < 1) {
            throw new IllegalArgumentException(String.format("Both row %s and column %s must be at least 1", row, column));
        }

        long total = column;

        for (int i = 0; i < row + column - 1; i++) {
            total += i;
        }

        return total - 1;
    }

    /**
     * Checks if the given {@link Integer} is between two other {@link Integer}s.
     *
     * @param start the start of the {@link Integer} range
     * @param end   the end of the {@link Integer} range
     * @param input the value to check
     * @return {@code true} if the input is within the {@link Integer} range
     * @throws IllegalArgumentException thrown if the {@code end} value is less than the {@code start} value
     */
    public static boolean isBetween(final int start, final int end, final int input) {
        if (end < start) {
            throw new IllegalArgumentException(String.format("Cannot have end %s less than start %s", end, start));
        }

        return input >= start && input <= end;
    }

    /**
     * Checks if the input {@code long} is even.
     *
     * @param value the {@code long} to check
     * @return {@code true} if the {@code long} is even
     */
    public static boolean isEven(final long value) {
        return value % 2 == 0;
    }

    /**
     * Checks if the input {@code long} is odd.
     *
     * @param value the {@code long} to check
     * @return {@code true} if the {@code long} is odd
     * @see #isEven(long)
     */
    public static boolean isOdd(final long value) {
        return !isEven(value);
    }

    /**
     * Returns the maximum value of the provided {@code int} values.
     *
     * @param firstValue the first {@code int}, so at least one value is provided
     * @param values     the {@link int}s to check
     * @return the largest value
     */
    public static int max(final int firstValue, final int... values) {
        final Collection<Integer> valuesAsCollection = new ArrayList<>(Arrays.stream(values).boxed().toList());
        valuesAsCollection.add(firstValue);
        return Collections.max(valuesAsCollection);
    }

    /**
     * Given two {@link List}s, the elements in both lists at the same index are multiplied, and the total values are summed.
     *
     * <p>
     * For example, take the {@link List}s {@code [1, 2, 3]} and {@code [4, 5, 6]}. The values at index 0, 1 and 2 are multiplied to give
     * {@code [4, 10, 18]}. These values are then summed to give the result {@code 32}.
     *
     * @param first  the first {@link List} of elements
     * @param second the second {@link List} of elements
     * @return the sum of the multiplied elements
     * @throws IllegalArgumentException if the two {@link List}s don't have an equal size
     */
    public static long multiplyElementsThenSum(final List<Integer> first, final List<Integer> second) {
        if (first.size() != second.size()) {
            throw new IllegalArgumentException(String.format("Inputs must be of same length, found: %s and %s", first.size(), second.size()));
        }

        long total = 0L;
        for (int i = 0; i < first.size(); i++) {
            total += ((long) first.get(i) * second.get(i));
        }

        return total;
    }

    /**
     * Similar to a factorial, but using addition instead of multiplication. The equation 1 + 2 + 3 ... + n can be simplified to:
     * <pre>
     *     n*(n+1)/2
     * </pre>
     * We then round to the nearest whole number.
     *
     * @param value the value whose triangular number is to be found
     * @return the triangular number for the input
     * @see <a href="https://en.wikipedia.org/wiki/Triangular_number">Triangular Number</a>
     */
    public static long triangularNumber(final int value) {
        return Math.round(value * (value + 1) / TRIANGULAR_NUMBER_DENOMINATOR);
    }
}
