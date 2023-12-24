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

import java.security.NoSuchAlgorithmException;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day04}.
 */
class Day04Test {

    private static final String INPUT_FILENAME = "day04.txt";

    @Test
    void example() throws NoSuchAlgorithmException {
        final String value = ExampleInput.readLinesAsSingleString(INPUT_FILENAME);
        final long indexPart1 = Day04.indexOfHashWithPrefix(value, "00000");
        assertThat(indexPart1)
            .isEqualTo(609_043L);

        final long indexPart2 = Day04.indexOfHashWithPrefix(value, "000000");
        assertThat(indexPart2)
            .isEqualTo(6_742_839L);
    }

    @Test
    void part1() throws NoSuchAlgorithmException {
        final String value = PuzzleInput.readLinesAsSingleString(INPUT_FILENAME);
        final long index = Day04.indexOfHashWithPrefix(value, "00000");
        assertThat(index)
            .isEqualTo(282_749L);
    }

    @Test
    void part2() throws NoSuchAlgorithmException {
        final String value = PuzzleInput.readLinesAsSingleString(INPUT_FILENAME);
        final long index = Day04.indexOfHashWithPrefix(value, "000000");
        assertThat(index)
            .isEqualTo(9_962_624L);
    }
}
