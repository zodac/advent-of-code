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

package me.zodac.advent.day.three;

import java.util.List;
import me.zodac.advent.util.BinaryConverter;
import me.zodac.advent.util.BitParityCount;

/**
 * @see <a href="https://adventofcode.com/2021/day/3">AoC 2021, Day 3, Part 1</a>
 */
public record PartOne() {

    /**
     * Given an input {@link List} of binary values, we calculate the most common bit and least common bit per index. We then reconstitute these
     * most/least common bits to calculate the gamma (most common bits) and epsilon (least common bits) rates of the input.
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
     * We can calculate the gamma rate as:
     * <pre>
     *     10000
     * </pre>
     *
     * <p>
     * And the epsilon rate as:
     * <pre>
     *     01111
     * </pre>
     *
     * <p>
     * We can then convert these binary values to decimal values, and multiply them to determine the power consumption.
     *
     * @param binaryValues the values to be checked
     * @return the power consumption
     * @see BinaryConverter
     * @see BitParityCount#createForIndexOfBinaryValues(List, int)
     */
    public long calculatePowerConsumption(final List<String> binaryValues) {
        if (binaryValues.isEmpty()) {
            return 0L;
        }

        // Input should have 12 digits, but no harm being a bit flexible
        final int lengthOfBinaryValue = binaryValues.get(0).length();

        final StringBuilder gammaRate = new StringBuilder();
        final StringBuilder epsilonRate = new StringBuilder();

        for (int i = 0; i < lengthOfBinaryValue; i++) {
            final BitParityCount bitParityCount = BitParityCount.createForIndexOfBinaryValues(binaryValues, i);

            final char gammaValue = bitParityCount.mostCommonBit();
            final char epsilonValue = bitParityCount.leastCommonBit();

            gammaRate.append(gammaValue);
            epsilonRate.append(epsilonValue);
        }

        final long gamma = BinaryConverter.toDecimal(gammaRate.toString());
        final long epsilon = BinaryConverter.toDecimal(epsilonRate.toString());

        return gamma * epsilon;
    }
}


