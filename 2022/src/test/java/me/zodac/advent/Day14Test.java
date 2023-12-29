/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2023 zodac.me
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
import me.zodac.advent.pojo.Point;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day14}.
 */
class Day14Test {

    private static final String INPUT_FILENAME = "day14.txt";

    @Test
    void example() {
        final List<List<Point>> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(Point::ofMany)
            .readAllLines();

        final long grainsOfSand1 = Day14.countGrainsOfSandBeforeFallingPastTheFloor(values);
        assertThat(grainsOfSand1)
            .isEqualTo(24L);

        final long grainsOfSand2 = Day14.countGrainsOfSandBeforeReachingSandSpawnPoint(values);
        assertThat(grainsOfSand2)
            .isEqualTo(93L);
    }

    @Test
    void part1() {
        final List<List<Point>> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Point::ofMany)
            .readAllLines();

        final long grainsOfSand = Day14.countGrainsOfSandBeforeFallingPastTheFloor(values);
        assertThat(grainsOfSand)
            .isEqualTo(843L);
    }

    @Test
    void part2() {
        final List<List<Point>> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Point::ofMany)
            .readAllLines();

        final long grainsOfSand = Day14.countGrainsOfSandBeforeReachingSandSpawnPoint(values);
        assertThat(grainsOfSand)
            .isEqualTo(27_625L);
    }
}
