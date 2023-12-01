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
 * Tests to verify answers for {@link Day01}.
 */
public class Day01Test {

    private static final String INPUT_FILENAME = "day01.txt";
    private static final String INPUT_FILENAME_PART_2 = "day01_2.txt";

    @Test
    void example() {
        final List<String> values = ExampleInput.readLines(INPUT_FILENAME);

        final long sumOfFrequencies = Day01.sumAllCalibrationValues(values, false);
        assertThat(sumOfFrequencies)
            .isEqualTo(142L);

        final List<String> values2 = ExampleInput.readLines(INPUT_FILENAME_PART_2);
        final long result = Day01.sumAllCalibrationValues(values2, true);
        assertThat(result)
            .isEqualTo(281L);
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long sumOfFrequencies = Day01.sumAllCalibrationValues(values, false);
        assertThat(sumOfFrequencies)
            .isEqualTo(54_450L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long result = Day01.sumAllCalibrationValues(values, true);
        assertThat(result)
            .isEqualTo(54_265L);
    }
}
