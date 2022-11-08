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
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day17}.
 */
class Day17Test {

    private static final String INPUT_FILENAME = "day17.txt";
    private static final int WANTED_VALUE = 150;

    @Test
    void part1() {
        final List<Integer> values =
            FileUtils.readLines(INPUT_FILENAME)
                .stream()
                .map(Integer::parseInt)
                .toList();

        final long numberOfCombinations = Day17.numberOfCombinationsMatchingWantedValue(values, WANTED_VALUE);
        assertThat(numberOfCombinations)
            .isEqualTo(4_372L);
    }

    @Test
    void part2() {
        final List<Integer> values =
            FileUtils.readLines(INPUT_FILENAME)
                .stream()
                .map(Integer::parseInt)
                .toList();

        final long numberOfCombinations = Day17.numberOfSmallestSizeCombinationsMatchingWantedValue(values, WANTED_VALUE);
        assertThat(numberOfCombinations)
            .isEqualTo(4L);
    }
}
