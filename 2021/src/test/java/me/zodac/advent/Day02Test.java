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
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import me.zodac.advent.pojo.Movement;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";

    @Test
    void example() {
        final List<Movement> movements = ExampleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Movement::parse)
            .toList();

        final long magnitude1 = Day02.magnitudeOfAllMovements(movements);
        assertThat(magnitude1)
            .isEqualTo(150L);

        final long magnitude2 = Day02.magnitudeOfAllMovementsWithAim(movements);
        assertThat(magnitude2)
            .isEqualTo(900L);
    }

    @Test
    void part1() {
        final List<Movement> movements = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Movement::parse)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovements(movements);
        assertThat(magnitude)
            .isEqualTo(1_813_801L);
    }

    @Test
    void part2() {
        final List<Movement> movements = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Movement::parse)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovementsWithAim(movements);
        assertThat(magnitude)
            .isEqualTo(1_960_569_556L);
    }
}
