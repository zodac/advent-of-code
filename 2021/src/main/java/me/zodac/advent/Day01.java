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

package me.zodac.advent;

import java.util.List;

/**
 * @see <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1</a>
 */
public final class Day01 {

    private Day01() {

    }

    /**
     * Iterates through the supplied {@code values} and compares each entry to the one before it. If the new value is greater than the previous one,
     * the counter is updated.
     *
     * @param values the {@link List} of {@link Integer}s to be checked
     * @return the count of the values higher than their predecessor
     */
    public static int countValuesHigherThanPreviousValue(final List<Integer> values) {
        if (values.isEmpty()) {
            return 0;
        }

        int count = 0;

        // Initialise with first value, rather than assuming the value cannot be negative
        int currentValue = values.get(0);

        for (final int nextValue : values) {
            if (nextValue > currentValue) {
                count++;
            }

            currentValue = nextValue;
        }

        return count;
    }

    /**
     * Iterates through the supplied {@code values} and groups each {@code windowSize} entries into a 'window' for comparison. These values are summed
     * together as the first window. Then we iterate down one value (for a {@code windowSize} of <b>three</b>, for example) the second and third
     * values of the first window are reused) to calculate the second window, and so on. If the new windows summed value is greater than the previous
     * one, the counter is updated.
     *
     * @param windowSize the size of the 'window' to be calculated
     * @param values     the {@link List} of {@link Integer}s to be checked
     * @return the count of the windows with a higher summed value than their predecessor
     */
    public static int countWindowValueHigherThanPreviousValue(final int windowSize, final List<Integer> values) {
        if (values.size() < windowSize) {
            return 0;
        }

        int count = 0;

        // Initialise with first value, rather than assuming the value cannot be negative
        int currentValue = 0;
        for (int i = 0; i < windowSize; i++) {
            currentValue += values.get(i);
        }

        // We want to stop iterating 'windowSize - 1' entries before the end of the values, to avoid an IndexOutOfBoundsException
        final int iterationBoundary = values.size() - (windowSize - 1);

        for (int i = 0; i < iterationBoundary; i++) {
            int nextValue = 0;
            for (int j = 0; j < windowSize; j++) {
                nextValue += values.get(i + j);
            }

            if (nextValue > currentValue) {
                count++;
            }

            currentValue = nextValue;
        }

        return count;
    }
}
