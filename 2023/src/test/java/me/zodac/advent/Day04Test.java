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
import me.zodac.advent.pojo.ScratchCard;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day04}.
 */
public class Day04Test {

    private static final String INPUT_FILENAME = "day04.txt";

    @Test
    void example() {
        final List<ScratchCard> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(ScratchCard::parse)
            .readAllLines();

        final long totalPoints = Day04.totalPointsForScratchCards(values);
        assertThat(totalPoints)
            .isEqualTo(13L);

        final long numberOfScratchCards = Day04.countTotalNumberOfScratchCards(values);
        assertThat(numberOfScratchCards)
            .isEqualTo(30L);
    }

    @Test
    void part1() {
        final List<ScratchCard> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(ScratchCard::parse)
            .readAllLines();

        final long totalPoints = Day04.totalPointsForScratchCards(values);
        assertThat(totalPoints)
            .isEqualTo(18_519L);
    }

    @Test
    void part2() {
        final List<ScratchCard> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(ScratchCard::parse)
            .readAllLines();

        final long numberOfScratchCards = Day04.countTotalNumberOfScratchCards(values);
        assertThat(numberOfScratchCards)
            .isEqualTo(11_787_590L);
    }
}
