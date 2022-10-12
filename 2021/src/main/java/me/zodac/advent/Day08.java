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

package me.zodac.advent;

import java.util.Collection;
import java.util.List;
import me.zodac.advent.pojo.Signal;
import me.zodac.advent.pojo.SignalDecoder;

/**
 * Solution for 2021, Day 8.
 *
 * @see <a href="https://adventofcode.com/2021/day/8">AoC 2021, Day 8</a>
 */
public final class Day08 {

    private Day08() {

    }

    /**
     * Provided a {@link List} of {@link Signal}s, count the total number of unique output values.
     *
     * @param signals the {@link Signal}s to check
     * @return the sum of unique output values
     * @see SignalDecoder#isUniqueOutputValue(String)
     */
    public static long identifyUniqueOutputValues(final Collection<Signal> signals) {
        return signals
            .stream()
            .map(Signal::outputs)
            .flatMap(List::stream)
            .filter(SignalDecoder::isUniqueOutputValue)
            .count();
    }

    /**
     * Provided a {@link List} of {@link Signal}s, for each {@link Signal} we use the inputs values to generate a {@link SignalDecoder} that can be
     * used to decode the output value for that {@link Signal}. We then sum all the decoded outputs.
     *
     * @param signals the {@link Signal}s to decode
     * @return the sum of the decoded {@link Signal} values
     * @see SignalDecoder#decode(Signal)
     */
    public static long sumOfDecodedOutputs(final Collection<Signal> signals) {
        return signals
            .stream()
            .mapToLong(SignalDecoder::decode)
            .sum();
    }
}
