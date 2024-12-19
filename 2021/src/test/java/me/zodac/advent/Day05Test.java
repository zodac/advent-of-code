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
import me.zodac.advent.grid.Point;
import me.zodac.advent.input.InputReader;
import me.zodac.advent.pojo.Line;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day05}.
 */
class Day05Test {

    private static final String INPUT_FILENAME = "day05.txt";

    @Test
    void example() {
        final List<Line> coordinateLines = InputReader
            .forExample(INPUT_FILENAME)
            .as(Day05Test::convertToLine)
            .readAllLines();

        final long part1Result = Day05.addHorizontalAndVerticalLinesAndReturnOverlap(coordinateLines);
        assertThat(part1Result)
            .isEqualTo(5L);

        final long part2Result = Day05.addAllLinesAndReturnOverlap(coordinateLines);
        assertThat(part2Result)
            .isEqualTo(12L);
    }

    @Test
    void part1() {
        final List<Line> coordinateLines = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Day05Test::convertToLine)
            .readAllLines();

        final long part1Result = Day05.addHorizontalAndVerticalLinesAndReturnOverlap(coordinateLines);
        assertThat(part1Result)
            .isEqualTo(6_113L);
    }

    @Test
    void part2() {
        final List<Line> coordinateLines = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Day05Test::convertToLine)
            .readAllLines();

        final long part2Result = Day05.addAllLinesAndReturnOverlap(coordinateLines);
        assertThat(part2Result)
            .isEqualTo(20_373L);
    }

    private static Line convertToLine(final String input) {
        final List<Point> points = Point.ofMany(input);
        return Line.of(points.get(0), points.get(1));
    }
}
