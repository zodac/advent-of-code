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
import me.zodac.advent.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day01}.
 */
class Day01Test {

    private static final String INPUT_FILENAME = "day01.txt";

    @Test
    void example() {
        final List<Character> values = InputReader
            .forExample(INPUT_FILENAME)
            .asLinesOfCharacters()
            .readFirstLine();

        final int floor1 = Day01.findResultFloor(values);
        assertThat(floor1)
            .isEqualTo(-1);

        final int floor2 = Day01.findIndexOfSpecificFloor(values, -1);
        assertThat(floor2)
            .isEqualTo(5);
    }

    @Test
    void part1() {
        final List<Character> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asLinesOfCharacters()
            .readFirstLine();

        final int floor = Day01.findResultFloor(values);
        assertThat(floor)
            .isEqualTo(74);
    }

    @Test
    void part2() {
        final List<Character> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asLinesOfCharacters()
            .readFirstLine();

        final int floor = Day01.findIndexOfSpecificFloor(values, -1);
        assertThat(floor)
            .isEqualTo(1_795);
    }
}
