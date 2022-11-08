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

import java.time.Duration;
import java.util.List;
import me.zodac.advent.pojo.Reindeer;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day14}.
 */
class Day14Test {

    private static final String INPUT_FILENAME = "day14.txt";
    private static final Duration TRAVEL_TIME = Duration.ofSeconds(2_503);

    @Test
    void part1() {
        final List<Reindeer> values =
            FileUtils.readLines(INPUT_FILENAME)
                .stream()
                .map(Reindeer::parse)
                .toList();

        final long furthestDistance = Day14.distanceOfFurthestTravelledReindeer(values, TRAVEL_TIME);
        assertThat(furthestDistance)
            .isEqualTo(2_660L);
    }

    @Test
    void part2() {
        final List<Reindeer> values =
            FileUtils.readLines(INPUT_FILENAME)
                .stream()
                .map(Reindeer::parse)
                .toList();

        final long highestScore = Day14.calculateTheHighestScore(values, TRAVEL_TIME);
        assertThat(highestScore)
            .isEqualTo(1_256L);
    }
}
