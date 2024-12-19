/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.zodac.advent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.input.InputReader;
import me.zodac.advent.grid.Point;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day18}.
 */
class Day18Test {

    private static final String INPUT_FILENAME = "day18.txt";
    private static final int GRID_SIZE_EXAMPLE = 7;
    private static final int GRID_SIZE_PUZZLE = 71;
    private static final int POINTS_TO_CHECK_EXAMPLE = 12;
    private static final int POINTS_TO_CHECK_PUZZLE = 1024;

    @Test
    void example() {
        final List<Point> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(Point::parse)
            .readAllLines();

        final long part1Result = Day18.smallestStepsBetweenStartAndEnd(values, GRID_SIZE_EXAMPLE, POINTS_TO_CHECK_EXAMPLE);
        assertThat(part1Result)
            .isEqualTo(22L);

        final String part2Result = Day18.findPointWhichBlocksPath(values, GRID_SIZE_EXAMPLE, POINTS_TO_CHECK_EXAMPLE);
        assertThat(part2Result)
            .isEqualTo("6,1");
    }

    @Test
    void part1() {
        final List<Point> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Point::parse)
            .readAllLines();

        final long part1Result = Day18.smallestStepsBetweenStartAndEnd(values, GRID_SIZE_PUZZLE, POINTS_TO_CHECK_PUZZLE);
        assertThat(part1Result)
            .isEqualTo(278L);
    }

    @Test
    void part2() {
        final List<Point> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Point::parse)
            .readAllLines();

        final String part2Result = Day18.findPointWhichBlocksPath(values, GRID_SIZE_PUZZLE, POINTS_TO_CHECK_PUZZLE);
        assertThat(part2Result)
            .isEqualTo("43,12");
    }
}
