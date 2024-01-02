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
import me.zodac.advent.pojo.Route;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day13}.
 */
class Day13Test {

    private static final String INPUT_FILENAME = "day13.txt";

    @Test
    void example() {
        final List<Route> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(Route::parseSittingNextTo)
            .readAllLines();

        final long changeInHappiness1 = Day13.greatestChangeInHappiness(values);
        assertThat(changeInHappiness1)
            .isEqualTo(330L);
    }

    @Test
    void part1() {
        final List<Route> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Route::parseSittingNextTo)
            .readAllLines();

        final long changeInHappiness = Day13.greatestChangeInHappiness(values);
        assertThat(changeInHappiness)
            .isEqualTo(664L);
    }

    @Test
    void part2() {
        final List<Route> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Route::parseSittingNextTo)
            .readAllLines();

        final long changeInHappiness = Day13.greatestChangeInHappinessIncludingSelf(values);
        assertThat(changeInHappiness)
            .isEqualTo(640L);
    }
}
