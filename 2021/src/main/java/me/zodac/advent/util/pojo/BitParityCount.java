/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
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

package me.zodac.advent.util.pojo;

import java.util.List;

/**
 * Simple POJO keeping track of the count of the bit parity (the '0's and '1's) of a binary {@link String}.
 */
public record BitParityCount(int zeros, int ones) {

    private static final char ZERO_BIT = '0';
    private static final char ONE_BIT = '1';

    /**
     * Given a {@link List} of {@link String} binary values, we calculate the {@link BitParityCount} for a specific {@code index} of all values.
     *
     * <p>
     * For example, take the input:
     * <pre>
     *     01101
     *     10010
     *     10000
     *     11000
     *     00010
     * </pre>
     *
     * <p>
     * If the index <b>0</b> is provided, the number of zero bits is <b>2</b> and the number of one bits is <b>3</b>.
     * Similarly, give an index <b>4</b>, the number of zero bits is <b>4</b> and the number of one bits is <b>1</b>.
     *
     * @param binaryValues the binary values to be checked
     * @param index        the index of the bit we are interested in
     * @return the {@link BitParityCount} for the index of the provided binary {@link String}s.
     */
    public static BitParityCount createForIndexOfBinaryValues(final List<String> binaryValues, final int index) {
        int zeros = 0;
        int ones = 0;

        for (final String value : binaryValues) {
            final char charAtIndex = value.charAt(index);

            if (charAtIndex == ZERO_BIT) {
                zeros++;
            } else if (charAtIndex == ONE_BIT) {
                ones++;
            } else {
                throw new IllegalStateException(String.format("Cannot handle 'binary' input of: '%s'", charAtIndex));
            }
        }

        return new BitParityCount(zeros, ones);
    }

    /**
     * Finds the most common bit in the {@link BitParityCount}.
     *
     * <p>
     * Note that if the number of 0s and 1s are equal, we will return '1'.
     *
     * @return the most common bit
     */
    public char mostCommonBit() {
        return ones >= zeros ? ONE_BIT : ZERO_BIT;
    }

    /**
     * Finds the least common bit in the {@link BitParityCount}.
     *
     * <p>
     * Note that if the number of 0s and 1s are equal, we will return '0'.
     *
     * @return the least common bit
     */
    public char leastCommonBit() {
        return zeros <= ones ? ZERO_BIT : ONE_BIT;
    }
}
