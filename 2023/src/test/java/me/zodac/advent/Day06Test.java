/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
 * Tests to verify answers for {@link Day06}.
 */
public class Day06Test {

    private static final String INPUT_FILENAME = "day06.txt";

    @Test
    void example() {
        final List<String> values = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long waysToWinMultipleRaces = Day06.countNumberOfWaysToWin(values, true);
        assertThat(waysToWinMultipleRaces)
            .isEqualTo(288L);

        final long waysToWinSingleRace = Day06.countNumberOfWaysToWin(values, false);
        assertThat(waysToWinSingleRace)
            .isEqualTo(71_503L);
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long waysToWinMultipleRaces = Day06.countNumberOfWaysToWin(values, true);
        assertThat(waysToWinMultipleRaces)
            .isEqualTo(771_628L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        final long waysToWinSingleRace = Day06.countNumberOfWaysToWin(values, false);
        assertThat(waysToWinSingleRace)
            .isEqualTo(27_363_861L);
    }
}
