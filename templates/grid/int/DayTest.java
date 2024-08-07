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
import me.zodac.advent.pojo.grid.IntegerGrid;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day%DAY_LONG%}.
 */
class Day%DAY_LONG%Test {

    private static final String INPUT_FILENAME = "day%DAY_LONG%.txt";

    @Test
    void example() {
        final IntegerGrid integerGrid = InputReader
            .forExample(INPUT_FILENAME)
            .asGrid()
            .ofIntegers();

        final long part1Result = Day17.part1(integerGrid);
        assertThat(part1Result)
            .isEqualTo(0L);

        final long part2Result = Day17.part2(integerGrid);
        assertThat(part2Result)
            .isEqualTo(0L);
    }

    @Test
    void part1() {
        final IntegerGrid integerGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofIntegers();

        final long part1Result = Day17.part1(integerGrid);
        assertThat(part1Result)
            .isEqualTo(0L);
    }

    @Test
    void part2() {
        final IntegerGrid integerGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofIntegers();

        final long part2Result = Day17.part2(integerGrid);
        assertThat(part2Result)
            .isEqualTo(0L);
    }
}
