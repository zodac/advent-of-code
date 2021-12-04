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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1</a>.
 */
class Day01Test {

    private static final String INPUT_FILENAME = "day01.txt";
    private static final int WINDOW_SIZE = 3;

    @Test
    void day2() {
        final List<Integer> values = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        final int count = Day01.countValuesHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_766);
    }

    @Test
    void partTwo() {
        final List<Integer> values = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        final int count = Day01.countWindowValueHigherThanPreviousValue(WINDOW_SIZE, values);
        assertThat(count)
            .isEqualTo(1_797);
    }
}
