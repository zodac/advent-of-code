/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.Line;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day05}.
 */
class Day05Test {

    private static final String INPUT_FILENAME = "day05.txt";
    private static final Pattern COORDINATE_PATTERN = Pattern.compile("\\d+,\\d+ -> \\d+,\\d+");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");

    @Test
    void part1() {
        final List<Line> coordinateLines = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .map(Day05Test::convertToLine)
            .toList();

        final int numberOfOverlaps = Day05.addHorizontalAndVerticalLinesAndReturnOverlap(coordinateLines);
        assertThat(numberOfOverlaps)
            .isEqualTo(6_113);
    }

    @Test
    void part2() {
        final List<Line> coordinateLines = FileUtils.readLinesFromFileInResources(INPUT_FILENAME)
            .stream()
            .map(Day05Test::convertToLine)
            .toList();

        final int numberOfOverlaps = Day05.addAllLinesAndReturnOverlap(coordinateLines);
        assertThat(numberOfOverlaps)
            .isEqualTo(20_373);
    }

    private static Line convertToLine(final String input) {
        final Matcher validMatcher = COORDINATE_PATTERN.matcher(input);
        if (!validMatcher.matches()) {
            throw new IllegalStateException(String.format("Invalid input: '%s'", input));
        }

        final Matcher digitMatcher = DIGIT_PATTERN.matcher(input);

        final List<Integer> coordinates = new ArrayList<>(Line.NUMBER_OF_COORDINATES_PER_LINE);

        while (digitMatcher.find()) {
            coordinates.add(Integer.parseInt(digitMatcher.group()));
        }
        return Line.of(coordinates);
    }
}
