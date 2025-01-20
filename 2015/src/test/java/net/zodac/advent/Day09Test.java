/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2025 zodac.net
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

package net.zodac.advent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.zodac.advent.input.InputReader;
import net.zodac.advent.pojo.Route;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day09}.
 */
class Day09Test {

    private static final String INPUT_FILENAME = "day09.txt";

    @Test
    void example() {
        final List<Route> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(Route::parseSourceDestination)
            .readAllLines();

        final long part1Result = Day09.distanceOfShortestPath(values);
        assertThat(part1Result)
            .isEqualTo(605L);

        final long part2Result = Day09.distanceOfLongestPath(values);
        assertThat(part2Result)
            .isEqualTo(982L);
    }

    @Test
    void part1() {
        final List<Route> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Route::parseSourceDestination)
            .readAllLines();

        final long part1Result = Day09.distanceOfShortestPath(values);
        assertThat(part1Result)
            .isEqualTo(141L);
    }

    @Test
    void part2() {
        final List<Route> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Route::parseSourceDestination)
            .readAllLines();

        final long part2Result = Day09.distanceOfLongestPath(values);
        assertThat(part2Result)
            .isEqualTo(736L);
    }
}
