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
import me.zodac.advent.pojo.BallGame;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
public class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";

    @Test
    void example() {
        final List<BallGame> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(BallGame::parse)
            .readAllLines();

        final long sumOfIds = Day02.sumOfIdsOfPossibleGames(values);
        assertThat(sumOfIds)
            .isEqualTo(8L);

        final long sumOfPowers = Day02.sumOfPowersOfGames(values);
        assertThat(sumOfPowers)
            .isEqualTo(2_286L);
    }

    @Test
    void part1() {
        final List<BallGame> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(BallGame::parse)
            .readAllLines();

        final long sumOfFrequencies = Day02.sumOfIdsOfPossibleGames(values);
        assertThat(sumOfFrequencies)
            .isEqualTo(2_447L);
    }

    @Test
    void part2() {
        final List<BallGame> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(BallGame::parse)
            .readAllLines();

        final long sumOfPowers = Day02.sumOfPowersOfGames(values);
        assertThat(sumOfPowers)
            .isEqualTo(56_322L);
    }
}
