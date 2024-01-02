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
import me.zodac.advent.pojo.Sue;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day16}.
 */
class Day16Test {

    private static final String INPUT_FILENAME = "day16.txt";

    @Test
    void part1() {
        final List<Sue> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Sue::parseThreeAttributes)
            .readAllLines();

        final int matchingId = Day16.findIdOfMatchingSue(values);
        assertThat(matchingId)
            .isEqualTo(373);
    }

    @Test
    void part2() {
        final List<Sue> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Sue::parseThreeAttributes)
            .readAllLines();

        final int matchingId = Day16.findIdOfMatchingSueWithRanges(values);
        assertThat(matchingId)
            .isEqualTo(260);
    }
}
