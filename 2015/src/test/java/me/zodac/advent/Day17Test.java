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
 * Tests to verify answers for {@link Day17}.
 */
class Day17Test {

    private static final String INPUT_FILENAME = "day17.txt";
    private static final int EXAMPLE_WANTED_VALUE = 25;
    private static final int PUZZLE_WANTED_VALUE = 150;

    @Test
    void example() {
        final List<Integer> values = InputReader
            .forExample(INPUT_FILENAME)
            .asIntegers()
            .readAllLines();

        final long numberOfCombinations1 = Day17.numberOfCombinationsMatchingWantedValue(values, EXAMPLE_WANTED_VALUE);
        assertThat(numberOfCombinations1)
            .isEqualTo(4L);

        final long numberOfCombinations2 = Day17.numberOfSmallestSizeCombinationsMatchingWantedValue(values, EXAMPLE_WANTED_VALUE);
        assertThat(numberOfCombinations2)
            .isEqualTo(3L);
    }

    @Test
    void part1() {
        final List<Integer> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asIntegers()
            .readAllLines();

        final long numberOfCombinations = Day17.numberOfCombinationsMatchingWantedValue(values, PUZZLE_WANTED_VALUE);
        assertThat(numberOfCombinations)
            .isEqualTo(4_372L);
    }

    @Test
    void part2() {
        final List<Integer> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asIntegers()
            .readAllLines();

        final long numberOfCombinations = Day17.numberOfSmallestSizeCombinationsMatchingWantedValue(values, PUZZLE_WANTED_VALUE);
        assertThat(numberOfCombinations)
            .isEqualTo(4L);
    }
}
