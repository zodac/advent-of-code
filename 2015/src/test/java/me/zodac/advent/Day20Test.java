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

import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day20}.
 */
class Day20Test {

    private static final int PUZZLE_INPUT = 36_000_000;

    @Test
    void part1() {
        final long firstVisitedHouseWithExpectedNumberOfGifts = Day20.deliverToHouses(PUZZLE_INPUT, 10);
        assertThat(firstVisitedHouseWithExpectedNumberOfGifts)
            .isEqualTo(831_600L);
    }

    @Test
    void part2() {
        final long firstVisitedHouseWithExpectedNumberOfGifts = Day20.deliverToHouses(PUZZLE_INPUT, 11, 50);
        assertThat(firstVisitedHouseWithExpectedNumberOfGifts)
            .isEqualTo(884_520L);
    }
}