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
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day21}.
 */
class Day21Test {

    private static final String INPUT_FILENAME = "day21.txt";

    @Test
    void example() {
        final int[] startPositions = InputReader
            .forExample(INPUT_FILENAME)
            .as(input -> input.split(":", 2)[1].trim())
            .readStream()
            .mapToInt(Integer::parseInt)
            .toArray();

        final long result = Day21.getLosingScoreTimesNumberOfRolls(startPositions, 3, 100, 10, 1_000L);
        assertThat(result)
            .isEqualTo(739_785L);
    }

    @Test
    void part1() {
        final int[] startPositions = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(input -> input.split(":", 2)[1].trim())
            .readStream()
            .mapToInt(Integer::parseInt)
            .toArray();

        final long result = Day21.getLosingScoreTimesNumberOfRolls(startPositions, 3, 100, 10, 1_000L);
        assertThat(result)
            .isEqualTo(797_160L);
    }
}
