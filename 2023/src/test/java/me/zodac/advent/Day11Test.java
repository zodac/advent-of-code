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
 * Tests to verify answers for {@link Day11}.
 */
public class Day11Test {

    private static final String INPUT_FILENAME = "day11.txt";

    @Test
    void example() {
        final List<String> values = ExampleInput.readLines(INPUT_FILENAME);

        final long sumOfDistancesPart1 = Day11.sumOfDistancesBetweenGalaxies(values, 2);
        assertThat(sumOfDistancesPart1)
            .isEqualTo(374L);

        final long sumOfDistancesPart2Result1 = Day11.sumOfDistancesBetweenGalaxies(values, 10);
        assertThat(sumOfDistancesPart2Result1)
            .isEqualTo(1_030L);

        final long sumOfDistancesPart2Result2 = Day11.sumOfDistancesBetweenGalaxies(values, 100);
        assertThat(sumOfDistancesPart2Result2)
            .isEqualTo(8_410L);
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long sumOfDistances = Day11.sumOfDistancesBetweenGalaxies(values, 2);
        assertThat(sumOfDistances)
            .isEqualTo(10_289_334L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long sumOfDistances = Day11.sumOfDistancesBetweenGalaxies(values, 1_000_000);
        assertThat(sumOfDistances)
            .isEqualTo(649_862_989_626L);
    }
}