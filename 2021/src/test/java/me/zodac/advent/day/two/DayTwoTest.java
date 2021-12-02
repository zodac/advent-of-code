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

package me.zodac.advent.day.two;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.util.Direction;
import me.zodac.advent.util.FileUtils;
import me.zodac.advent.util.Movement;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for <a href="https://adventofcode.com/2021/day/2">AoC 2021, Day 2</a>.
 */
class DayTwoTest {

    private static final String INPUT_FILE_PATH = "day2/input.txt";

    @Test
    void partOne() {
        final PartOne partOne = new PartOne();
        final List<Movement> movements = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH)
            .stream()
            .map(DayTwoTest::convertToMovement)
            .toList();

        final long magnitude = partOne.magnitudeOfAllMovements(movements);
        assertThat(magnitude)
            .isEqualTo(1_813_801L);
    }

    @Test
    void partTwo() {
        final PartTwo partTwo = new PartTwo();
        final List<Movement> movements = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH)
            .stream()
            .map(DayTwoTest::convertToMovement)
            .toList();

        final long magnitude = partTwo.magnitudeOfAllMovementsWithAim(movements);
        assertThat(magnitude)
            .isEqualTo(1_960_569_556L);
    }

    /**
     * Input should be in the form:
     * <pre>
     *     forward 1
     * </pre>
     *
     * @param input the input {@link String} to convert
     * @return the converted {@link Movement}
     */
    private static Movement convertToMovement(final String input) {
        final String[] tokens = input.split("\s+");

        final Direction direction = Direction.get(tokens[0]);
        final int spaces = Integer.parseInt(tokens[1]);

        return new Movement(direction, spaces);
    }
}
