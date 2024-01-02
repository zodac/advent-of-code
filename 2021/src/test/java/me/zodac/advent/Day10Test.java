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
import me.zodac.advent.pojo.SyntaxLine;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day10}.
 */
class Day10Test {

    private static final String INPUT_FILENAME = "day10.txt";

    @Test
    void example() {
        final List<SyntaxLine> syntaxLines = InputReader
            .forExample(INPUT_FILENAME)
            .as(SyntaxLine::create)
            .readAllLines();

        final long syntaxErrorScore = Day10.calculateSyntaxErrorScoreForLines(syntaxLines);
        assertThat(syntaxErrorScore)
            .isEqualTo(26_397L);

        final long middleIncompleteLineScore = Day10.calculateMiddleScoreForIncompleteLines(syntaxLines);
        assertThat(middleIncompleteLineScore)
            .isEqualTo(288_957L);
    }

    @Test
    void part1() {
        final List<SyntaxLine> syntaxLines = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(SyntaxLine::create)
            .readAllLines();

        final long syntaxErrorScore = Day10.calculateSyntaxErrorScoreForLines(syntaxLines);
        assertThat(syntaxErrorScore)
            .isEqualTo(278_475L);
    }

    @Test
    void part2() {
        final List<SyntaxLine> syntaxLines = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(SyntaxLine::create)
            .readAllLines();

        final long middleIncompleteLineScore = Day10.calculateMiddleScoreForIncompleteLines(syntaxLines);
        assertThat(middleIncompleteLineScore)
            .isEqualTo(3_015_539_998L);
    }
}
