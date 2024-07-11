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

import me.zodac.advent.input.InputReader;
import me.zodac.advent.pojo.grid.Grid;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day14}.
 */
class Day14Test {

    private static final String INPUT_FILENAME = "day14.txt";
    private static final int NUMBER_OF_CYCLES = 1_000_000_000;

    @Test
    void example() {
        final Grid<Character> characterGrid = InputReader
            .forExample(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long loadAfterSingleNorthTurn = Day14.countLoadAfterSingleNorthTurn(characterGrid);
        assertThat(loadAfterSingleNorthTurn)
            .isEqualTo(136L);

        final long loadAfterFullCycleOfTurns = Day14.calculateLoadAfterFullCycleOfTurns(characterGrid, NUMBER_OF_CYCLES);
        assertThat(loadAfterFullCycleOfTurns)
            .isEqualTo(64L);
    }

    @Test
    void part1() {
        final Grid<Character> characterGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long loadAfterSingleNorthTurn = Day14.countLoadAfterSingleNorthTurn(characterGrid);
        assertThat(loadAfterSingleNorthTurn)
            .isEqualTo(105_461L);
    }

    @Test
    void part2() {
        final Grid<Character> characterGrid = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asGrid()
            .ofCharacters();

        final long loadAfterFullCycleOfTurns = Day14.calculateLoadAfterFullCycleOfTurns(characterGrid, NUMBER_OF_CYCLES);
        assertThat(loadAfterFullCycleOfTurns)
            .isEqualTo(102_829L);
    }
}
