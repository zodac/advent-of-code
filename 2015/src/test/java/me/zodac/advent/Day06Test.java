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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.Triple;
import me.zodac.advent.pojo.grid.GridInstruction;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day06}.
 */
class Day06Test {

    private static final String INPUT_FILENAME = "day06.txt";
    private static final Pattern INSTRUCTION_AND_POINTS_PATTERN = Pattern.compile("(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

    @Test
    void part1() {
        final List<Triple<GridInstruction, Point, Point>> values = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .map(Day06Test::convertToInstructionAndPoints)
            .toList();

        final long numberOfLitLights = Day06.countSwitchedOnLights(values);
        assertThat(numberOfLitLights)
            .isEqualTo(377_891L);
    }

    @Test
    void part2() {
        final List<Triple<GridInstruction, Point, Point>> values = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .map(Day06Test::convertToInstructionAndPoints)
            .toList();

        final long brightnessOfLights = Day06.calculateBrightness(values);
        assertThat(brightnessOfLights)
            .isEqualTo(14_110_788L);
    }

    private static Triple<GridInstruction, Point, Point> convertToInstructionAndPoints(final String input) {
        final Matcher matcher = INSTRUCTION_AND_POINTS_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input string: " + input);
        }

        final String instruction = matcher.group(1);
        final GridInstruction gridInstruction = GridInstruction.match(instruction);

        if (gridInstruction == GridInstruction.INVALID) {
            throw new IllegalStateException("Invalid instruction found: " + input);
        }

        final int x1 = Integer.parseInt(matcher.group(2));
        final int y1 = Integer.parseInt(matcher.group(3));
        final int x2 = Integer.parseInt(matcher.group(4));
        final int y2 = Integer.parseInt(matcher.group(5));

        if (x1 > x2 || y1 > y2) {
            throw new IllegalStateException(String.format("Expected (x1, y1) to be less than (x2, y2), found: (%s, %s) (%s, %s)", x1, y1, x2, y2));
        }

        return Triple.of(gridInstruction, Point.of(x1, y1), Point.of(x2, y2));
    }
}
