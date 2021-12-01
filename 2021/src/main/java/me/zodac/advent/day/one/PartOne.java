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

package me.zodac.advent.day.one;

import java.util.List;

/**
 * @see <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1, Part 1</a>
 */
public record PartOne() {

    /**
     * Iterates through the supplied {@code values} and compares each entry to the one before it. If the new value is greater than the previous one,
     * the counter is updated.
     *
     * <p>
     * The supplied {@code values} are parsed to {@link Integer}s in order to be compared, rather than rely on {@link String#compareTo(String)}.
     *
     * @param values the {@link List} of {@link String}s to be checked
     * @return the count of the values higher than their predecessor
     */
    public int countValuesHigherThanPreviousValue(final List<String> values) {
        if (values.isEmpty()) {
            return 0;
        }

        int count = 0;

        // Initialise with first value, rather than assuming the value cannot be negative
        int currentValue = Integer.parseInt(values.get(0));

        for (final String value : values) {
            final int nextValue = Integer.parseInt(value);

            if (nextValue > currentValue) {
                count++;
            }

            currentValue = nextValue;
        }

        return count;
    }
}
