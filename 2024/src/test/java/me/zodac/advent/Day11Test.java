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

import me.zodac.advent.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day11}.
 */
class Day11Test {

    private static final String INPUT_FILENAME = "day11.txt";

    @Test
    void example() {
        final String value = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings() // TODO: As list of longs?
            .readAllAsSingleString();

        final long part1Result = Day11.countStonesAfterBlinks(value, 25);
        assertThat(part1Result)
            .isEqualTo(55_312L);

        final long part2Result = Day11.countStonesAfterBlinks(value, 75);
        assertThat(part2Result)
            .isEqualTo(65_601_038_650_482L);
    }

    @Test
    void part1() {
        final String value = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllAsSingleString();

        final long part1Result = Day11.countStonesAfterBlinks(value, 25);
        assertThat(part1Result)
            .isEqualTo(239_714L);
    }

    @Test
    void part2() {
        final String value = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllAsSingleString();

        final long part2Result = Day11.countStonesAfterBlinks(value, 75);
        assertThat(part2Result)
            .isEqualTo(284_973_560_658_514L);
    }
}
