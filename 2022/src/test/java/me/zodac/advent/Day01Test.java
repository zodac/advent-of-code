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
        final List<String> values = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long count1 = Day01.valueOfLargestGroupOfCalories(values);
        assertThat(count1)
            .isEqualTo(24_000L);

        final long count2 = Day01.valueOfLargestGroupsOfCalories(values, 3);
        assertThat(count2)
            .isEqualTo(45_000L);
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long count = Day01.valueOfLargestGroupOfCalories(values);
        assertThat(count)
            .isEqualTo(68_802L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long count = Day01.valueOfLargestGroupsOfCalories(values, 3);
        assertThat(count)
            .isEqualTo(205_370L);
    }
}
