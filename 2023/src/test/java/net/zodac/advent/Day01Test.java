/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2025 zodac.net
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

package net.zodac.advent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.zodac.advent.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day01}.
 */
class Day01Test {

    private static final String INPUT_FILENAME = "day01.txt";
    private static final String INPUT_FILENAME_PART_2 = "day01_2.txt";

    @Test
    void example() {
        final List<String> values1 = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long part1Result = Day01.sumAllCalibrationValues(values1, false);
        assertThat(part1Result)
            .isEqualTo(142L);

        final List<String> values2 = InputReader
            .forExample(INPUT_FILENAME_PART_2)
            .asStrings()
            .readAllLines();

        final long part2Result = Day01.sumAllCalibrationValues(values2, true);
        assertThat(part2Result)
            .isEqualTo(281L);
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long part1Result = Day01.sumAllCalibrationValues(values, false);
        assertThat(part1Result)
            .isEqualTo(54_450L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long part2Result = Day01.sumAllCalibrationValues(values, true);
        assertThat(part2Result)
            .isEqualTo(54_265L);
    }
}
