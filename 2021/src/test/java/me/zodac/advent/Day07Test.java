/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2022 zodac.me
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

import static me.zodac.advent.pojo.MoveCostType.CONSTANT;
import static me.zodac.advent.pojo.MoveCostType.VARIABLE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day07}.
 */
class Day07Test {

    private static final String INPUT_FILENAME = "day07.txt";

    @Test
    void example() {
        final List<Integer> verticalLocations = ExampleInput.readSingleLineOfCommaSeparatedIntegers(INPUT_FILENAME)
            .stream()
            .flatMap(List::stream)
            .toList();

        final long minimumMoves1 = Day07.minimumMovesNeededToAlignVertically(verticalLocations, CONSTANT);
        assertThat(minimumMoves1)
            .isEqualTo(37L);

        final long minimumMoves2 = Day07.minimumMovesNeededToAlignVertically(verticalLocations, VARIABLE);
        assertThat(minimumMoves2)
            .isEqualTo(168L);
    }

    @Test
    void part1() {
        final List<Integer> verticalLocations = PuzzleInput.readSingleLineOfCommaSeparatedIntegers(INPUT_FILENAME)
            .stream()
            .flatMap(List::stream)
            .toList();

        final long minimumMoves = Day07.minimumMovesNeededToAlignVertically(verticalLocations, CONSTANT);
        assertThat(minimumMoves)
            .isEqualTo(341_558L);
    }

    @Test
    void part2() {
        final List<Integer> verticalLocations = PuzzleInput.readSingleLineOfCommaSeparatedIntegers(INPUT_FILENAME)
            .stream()
            .flatMap(List::stream)
            .toList();

        final long minimumMoves = Day07.minimumMovesNeededToAlignVertically(verticalLocations, VARIABLE);
        assertThat(minimumMoves)
            .isEqualTo(93_214_037L);
    }
}
