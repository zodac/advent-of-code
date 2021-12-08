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
import java.util.Set;
import me.zodac.advent.pojo.Signal;
import me.zodac.advent.pojo.SignalDecoder;

/**
 * @see <a href="https://adventofcode.com/2021/day/8">AoC 2021, Day 8</a>
 */
public final class Day08 {

    private static final Set<Integer> UNIQUE_OUTPUT_VALUES = Set.of(2, 3, 4, 7);

    private Day08() {

    }

    /**
     * Provided a {@link List} of {@link Signal}s, we focus on the available outputs. In a standard 7-segment display, we can identify four values
     * based on the number of segments that are lit:
     * <ol>
     *     <li>2-segments: '1'</li>
     *     <li>3-segments: '7'</li>
     *     <li>4-segments: '4'</li>
     *     <li>7-segments: '8'</li>
     * </ol>
     *
     * <p>
     * For each of the provides {@link Signal}s we will count how many unique values we can find, then return the sum.
     *
     * @param signals the {@link Signal}s to check
     * @return the sum of unique output values
     */
    public static long identifyUniqueOutputValues(final List<Signal> signals) {
        return signals
            .stream()
            .map(Signal::outputs)
            .flatMap(List::stream)
            .filter(outputValue -> UNIQUE_OUTPUT_VALUES.contains(outputValue.length()))
            .count();
    }

    /**
     * Provided a {@link List} of {@link Signal}s, for each {@link Signal} we use the inputs values to generate a {@link SignalDecoder} that can be
     * used to decode the output value for that {@link Signal}. We then sum all the decoded outputs.
     *
     * @param signals the {@link Signal}s to decode
     * @return the sum of the decoded {@link Signal} values
     */
    public static long sumOfDecodedOutputs(final List<Signal> signals) {
        return signals
            .stream()
            .mapToLong(SignalDecoder::decode)
            .sum();
    }
}
