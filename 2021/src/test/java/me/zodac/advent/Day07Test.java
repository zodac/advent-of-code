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

import static me.zodac.advent.pojo.MoveCostType.CONSTANT;
import static me.zodac.advent.pojo.MoveCostType.VARIABLE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day07}.
 */
class Day07Test {

    private static final String INPUT_FILENAME = "day07.txt";

    @Test
    void example() {
        final List<Long> verticalLocations = InputReader
            .forExample(INPUT_FILENAME)
            .asLinesOfSeparatedNumbers()
            .readFirstLine();

        final long part1Result = Day07.minimumMovesNeededToAlignVertically(verticalLocations, CONSTANT);
        assertThat(part1Result)
            .isEqualTo(37L);

        final long part2Result = Day07.minimumMovesNeededToAlignVertically(verticalLocations, VARIABLE);
        assertThat(part2Result)
            .isEqualTo(168L);
    }

    @Test
    void part1() {
        final List<Long> verticalLocations = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asLinesOfSeparatedNumbers()
            .readFirstLine();

        final long part1Result = Day07.minimumMovesNeededToAlignVertically(verticalLocations, CONSTANT);
        assertThat(part1Result)
            .isEqualTo(341_558L);
    }

    @Test
    void part2() {
        final List<Long> verticalLocations = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asLinesOfSeparatedNumbers()
            .readFirstLine();

        final long part2Result = Day07.minimumMovesNeededToAlignVertically(verticalLocations, VARIABLE);
        assertThat(part2Result)
            .isEqualTo(93_214_037L);
    }
}
