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

import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
public class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";
    private static final String INPUT_FILENAME_PART_2 = "day02_2.txt";

    @Test
    void example() {
        final List<String> values = ExampleInput.readLines(INPUT_FILENAME);

        final long checksumOfBoxIds = Day02.checksumOfBoxIds(values);
        assertThat(checksumOfBoxIds)
            .isEqualTo(12L);

        final List<String> valuesPart2 = ExampleInput.readLines(INPUT_FILENAME_PART_2);

        final String commonLetters = Day02.findCommonCharactersForValidBoxIds(valuesPart2);
        assertThat(commonLetters)
            .isEqualTo("fgij");
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long checksumOfBoxIds = Day02.checksumOfBoxIds(values);
        assertThat(checksumOfBoxIds)
            .isEqualTo(5_880L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final String commonLetters = Day02.findCommonCharactersForValidBoxIds(values);
        assertThat(commonLetters)
            .isEqualTo("tiwcdpbseqhxryfmgkvjujvza");
    }
}
