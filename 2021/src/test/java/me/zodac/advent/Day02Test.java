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
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Movement;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";

    @Test
    void part1() {
        final List<Movement> movements = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .map(Day02Test::convertToMovement)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovements(movements);
        assertThat(magnitude)
            .isEqualTo(1_813_801L);
    }

    @Test
    void part2() {
        final List<Movement> movements = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .map(Day02Test::convertToMovement)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovementsWithAim(movements);
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
