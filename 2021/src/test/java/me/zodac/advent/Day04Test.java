/*
 * Zero-Clause BSD License
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

import static me.zodac.advent.util.CollectionUtils.getFirst;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import me.zodac.advent.util.CollectionUtils;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day04}.
 */
class Day04Test {

    private static final String INPUT_FILENAME = "day04.txt";

    @Test
    void part1() {
        final List<String> bingoInput = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .filter(input -> !input.isBlank())
            .toList();

        final List<Integer> bingoNumbers = Arrays.stream(getFirst(bingoInput).split(","))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        // Remove the first entry
        final List<String> bingoBoardsInput = bingoInput.subList(1, bingoInput.size());

        final long finalScore = Day04.finalScoreOfFirstWinningBingoBoard(bingoNumbers, bingoBoardsInput);
        assertThat(finalScore)
            .isEqualTo(45_031L);
    }

    @Test
    void part2() {
        final List<String> bingoInput = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .filter(input -> !input.isBlank())
            .toList();

        final List<Integer> bingoNumbers = Arrays.stream(getFirst(bingoInput).split(","))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        // Remove the first entry
        final List<String> bingoBoardsInput = bingoInput.subList(1, bingoInput.size());

        final long finalScore = Day04.finalScoreOfLastWinningBingoBoard(bingoNumbers, bingoBoardsInput);
        assertThat(finalScore)
            .isEqualTo(2_568L);
    }
}
