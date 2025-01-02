/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2025 zodac.me
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

import me.zodac.advent.grid.Grid;
import me.zodac.advent.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day20}.
 */
class Day20Test {

    private static final String INPUT_FILENAME = "day20.txt";

    @Test
    void example() {
        final Grid<Character> characterGrid = InputReader
            .forExample(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long part1Result = Day20.findNumberOfCheats(characterGrid, 2, 1);
        assertThat(part1Result)
            .isEqualTo(44L);

        final long part2Result = Day20.findNumberOfCheats(characterGrid, 20, 50);
        assertThat(part2Result)
            .isEqualTo(285L);
    }

    @Test
    void part1() {
        final Grid<Character> characterGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long part1Result = Day20.findNumberOfCheats(characterGrid, 2, 100);
        assertThat(part1Result)
            .isEqualTo(1_524L);
    }

    @Test
    void part2() {
        final Grid<Character> characterGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long part2Result = Day20.findNumberOfCheats(characterGrid, 20, 100);
        assertThat(part2Result)
            .isEqualTo(1_033_746L);
    }
}
