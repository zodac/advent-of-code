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
        final List<Line> coordinateLines = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .map(Day05Test::convertToLine)
            .toList();

        final int numberOfOverlaps = Day05.addHorizontalAndVerticalLinesAndReturnOverlap(coordinateLines);
        assertThat(numberOfOverlaps)
            .isEqualTo(6_113);
    }

    @Test
    void part2() {
        final List<Line> coordinateLines = FileUtils.readLines(INPUT_FILENAME)
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
