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

package me.zodac.advent.day;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.day.one.PartOne;
import me.zodac.advent.day.one.PartTwo;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1</a>.
 */
class DayOneTest {

    private static final String INPUT_FILE_PATH = "day1/input.txt";

    @Test
    void partOne() {
        final PartOne partOne = new PartOne();
        final List<Integer> values = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH)
            .stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        final int count = partOne.countValuesHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_766);
    }

    @Test
    void partTwo() {
        final PartTwo partTwo = new PartTwo();
        final List<Integer> values = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH)
            .stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        final int count = partTwo.countThreeValueWindowHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_797);
    }
}
