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

import static me.zodac.advent.pojo.MoveCostType.CONSTANT;
import static me.zodac.advent.pojo.MoveCostType.VARIABLE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day07}.
 */
class Day07Test {

    private static final String INPUT_FILENAME = "day07.txt";

    @Test
    void part1() {
        final List<Integer> verticalLocations = FileUtils.readCommaSeparatedIntegers(INPUT_FILENAME)
            .stream()
            .flatMap(List::stream)
            .toList();

        final long minimumMoves = Day07.minimumMovesNeededToAlignVertically(verticalLocations, CONSTANT);
        assertThat(minimumMoves)
            .isEqualTo(341_558L);
    }

    @Test
    void part2() {
        final List<Integer> verticalLocations = FileUtils.readCommaSeparatedIntegers(INPUT_FILENAME)
            .stream()
            .flatMap(List::stream)
            .toList();

        final long minimumMoves = Day07.minimumMovesNeededToAlignVertically(verticalLocations, VARIABLE);
        assertThat(minimumMoves)
            .isEqualTo(93_214_037L);
    }
}
