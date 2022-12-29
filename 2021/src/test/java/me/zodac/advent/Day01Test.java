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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day01}.
 */
class Day01Test {

    private static final String INPUT_FILENAME = "day01.txt";
    private static final int WINDOW_SIZE = 3;

    @Test
    void example() {
        final List<Integer> values = ExampleInput.readLinesAsIntegers(INPUT_FILENAME);

        final int count1 = Day01.countValuesHigherThanPreviousValue(values);
        assertThat(count1)
            .isEqualTo(7);

        final int count2 = Day01.countWindowValueHigherThanPreviousValue(WINDOW_SIZE, values);
        assertThat(count2)
            .isEqualTo(5);
    }

    @Test
    void part1() {
        final List<Integer> values = PuzzleInput.readLinesAsIntegers(INPUT_FILENAME);

        final int count = Day01.countValuesHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_766);
    }

    @Test
    void part2() {
        final List<Integer> values = PuzzleInput.readLinesAsIntegers(INPUT_FILENAME);

        final int count = Day01.countWindowValueHigherThanPreviousValue(WINDOW_SIZE, values);
        assertThat(count)
            .isEqualTo(1_797);
    }
}
