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

import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day11}.
 */
class Day11Test {

    private static final String INPUT_FILENAME = "day11.txt";

    @Test
    void example() {
        final String value = ExampleInput.readSingleLine(INPUT_FILENAME);

        final String newPassword = Day11.findNextValidPassword(value);
        assertThat(newPassword)
            .isEqualTo("abcdffaa");
    }

    @Test
    void part1() {
        final String value = PuzzleInput.readSingleLine(INPUT_FILENAME);

        final String newPassword = Day11.findNextValidPassword(value);
        assertThat(newPassword)
            .isEqualTo("hxbxxyzz");
    }

    @Test
    void part2() {
        final String value = "hxbxxyzz"; // Reusing the answer from part 1 rather than doing it twice

        final String newPassword = Day11.findNextValidPassword(value);
        assertThat(newPassword)
            .isEqualTo("hxcaabcc");
    }
}
