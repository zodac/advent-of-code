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
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";
    private static final String INPUT_FILENAME_PART_2 = "day02_2.txt";

    @Test
    void example() {
        final List<String> valuesPart1 = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long checksumOfBoxIds = Day02.checksumOfBoxIds(valuesPart1);
        assertThat(checksumOfBoxIds)
            .isEqualTo(12L);

        final List<String> valuesPart2 = InputReader
            .forExample(INPUT_FILENAME_PART_2)
            .asStrings()
            .readAllLines();

        final String commonLetters = Day02.findCommonCharactersForValidBoxIds(valuesPart2);
        assertThat(commonLetters)
            .isEqualTo("fgij");
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long checksumOfBoxIds = Day02.checksumOfBoxIds(values);
        assertThat(checksumOfBoxIds)
            .isEqualTo(5_880L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final String commonLetters = Day02.findCommonCharactersForValidBoxIds(values);
        assertThat(commonLetters)
            .isEqualTo("tiwcdpbseqhxryfmgkvjujvza");
    }
}
