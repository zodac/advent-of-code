/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
