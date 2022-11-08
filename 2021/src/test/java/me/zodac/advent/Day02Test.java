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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Movement;
import me.zodac.advent.util.FileUtils;
import me.zodac.advent.util.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day02}.
 */
class Day02Test {

    private static final String INPUT_FILENAME = "day02.txt";

    @Test
    void part1() {
        final List<Movement> movements = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .map(Day02Test::convertToMovement)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovements(movements);
        assertThat(magnitude)
            .isEqualTo(1_813_801L);
    }

    @Test
    void part2() {
        final List<Movement> movements = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .map(Day02Test::convertToMovement)
            .toList();

        final long magnitude = Day02.magnitudeOfAllMovementsWithAim(movements);
        assertThat(magnitude)
            .isEqualTo(1_960_569_556L);
    }

    /**
     * Input should be in the form:
     * <pre>
     *     forward 1
     * </pre>
     *
     * @param input the input {@link String} to convert
     * @return the converted {@link Movement}
     */
    private static Movement convertToMovement(final String input) {
        final String[] tokens = StringUtils.splitOnWhitespace(input);

        final Direction direction = Direction.get(tokens[0]);
        final int spaces = Integer.parseInt(tokens[1]);

        return new Movement(direction, spaces);
    }
}
