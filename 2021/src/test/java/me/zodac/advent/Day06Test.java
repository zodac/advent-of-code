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

import java.math.BigDecimal;
import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day06}.
 */
class Day06Test {

    private static final String INPUT_FILENAME = "day06.txt";

    @Test
    void example() {
        final List<Integer> lanternValues = ExampleInput.readLinesOfCommaSeparatedIntegers(INPUT_FILENAME)
            .getFirst();

        final BigDecimal finalNumberOfLanterns1 = Day06.countLanternFishAfterDays(lanternValues, 80);
        assertThat(finalNumberOfLanterns1)
            .isEqualTo(BigDecimal.valueOf(5_934L));

        final BigDecimal finalNumberOfLanterns2 = Day06.countLanternFishAfterDays(lanternValues, 256);
        assertThat(finalNumberOfLanterns2)
            .isEqualTo(BigDecimal.valueOf(26_984_457_539L));
    }

    @Test
    void part1() {
        final List<Integer> lanternValues = PuzzleInput.readSingleLineOfCommaSeparatedIntegers(INPUT_FILENAME)
            .getFirst();

        final BigDecimal finalNumberOfLanterns = Day06.countLanternFishAfterDays(lanternValues, 80);
        assertThat(finalNumberOfLanterns)
            .isEqualTo(BigDecimal.valueOf(362_346L));
    }

    @Test
    void part2() {
        final List<Integer> lanternValues = PuzzleInput.readSingleLineOfCommaSeparatedIntegers(INPUT_FILENAME)
            .getFirst();

        final BigDecimal finalNumberOfLanterns = Day06.countLanternFishAfterDays(lanternValues, 256);
        assertThat(finalNumberOfLanterns)
            .isEqualTo(BigDecimal.valueOf(1_639_643_057_051L));
    }
}
