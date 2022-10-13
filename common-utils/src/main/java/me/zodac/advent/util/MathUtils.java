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
import java.util.Collection;
import java.util.Collections;

/**
 * Utility class with {@link Math}-based functions.
 */
public final class MathUtils {

    private static final double TRIANGULAR_NUMBER_DENOMINATOR = 2.0D;

    private MathUtils() {

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

    /**
     * Checks if the input {@code int} is even.
     *
     * @param value the {@code int} to check
     * @return {@code true} if the {@code int} is even
     */
    public static boolean isEven(final int value) {
        return value % 2 == 0;
    }
}
