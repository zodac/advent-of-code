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
import net.zodac.advent.grid.Grid;
import net.zodac.advent.input.InputReader;
import net.zodac.advent.util.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day14}.
 */
class Day14Test {

    private static final String INPUT_FILENAME = "day14.txt";
    private static final int GRID_ROWS_EXAMPLE = 7;
    private static final int GRID_COLUMNS_EXAMPLE = 11;
    private static final int GRID_ROWS_PUZZLE = 103;
    private static final int GRID_COLUMNS_PUZZLE = 101;

    @Test
    void example() {
        final List<String> values = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();
        final Integer[][] internalGrid = ArrayUtils.deepFill(new Integer[GRID_ROWS_EXAMPLE][GRID_COLUMNS_EXAMPLE], 0);
        final Grid<Integer> integerGrid = new Grid<>(internalGrid);

        final long part1Result = Day14.calculateSafetyRating(values, integerGrid);
        assertThat(part1Result)
            .isEqualTo(12L);
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();
        final Integer[][] internalGrid = ArrayUtils.deepFill(new Integer[GRID_ROWS_PUZZLE][GRID_COLUMNS_PUZZLE], 0);
        final Grid<Integer> integerGrid = new Grid<>(internalGrid);

        final long part1Result = Day14.calculateSafetyRating(values, integerGrid);
        assertThat(part1Result)
            .isEqualTo(218_965_032L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();
        final Integer[][] internalGrid = ArrayUtils.deepFill(new Integer[GRID_ROWS_PUZZLE][GRID_COLUMNS_PUZZLE], 0);
        final Grid<Integer> integerGrid = new Grid<>(internalGrid);

        final long part2Result = Day14.findSecondsUntilChrismasTree(values, integerGrid);
        assertThat(part2Result)
            .isEqualTo(7_037L);
    }
}
