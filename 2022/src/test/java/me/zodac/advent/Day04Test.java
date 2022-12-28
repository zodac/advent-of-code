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

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import me.zodac.advent.pojo.Range;
import me.zodac.advent.pojo.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day04}.
 */
class Day04Test {

    private static final String INPUT_FILENAME = "day04.txt";
    private static final Pattern INPUT_PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    @Test
    void example() {
        final Collection<Pair<Range, Range>> values = ExampleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Day04Test::convert)
            .toList();

        final long count1 = Day04.countCompleteOverlaps(values);
        assertThat(count1)
            .isEqualTo(2L);

        final long count2 = Day04.countPartialOverlaps(values);
        assertThat(count2)
            .isEqualTo(4L);
    }

    @Test
    void part1() {
        final Collection<Pair<Range, Range>> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Day04Test::convert)
            .toList();

        final long count = Day04.countCompleteOverlaps(values);
        assertThat(count)
            .isEqualTo(560L);
    }

    @Test
    void part2() {
        final Collection<Pair<Range, Range>> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Day04Test::convert)
            .toList();

        final long count = Day04.countPartialOverlaps(values);
        assertThat(count)
            .isEqualTo(839L);
    }

    private static Pair<Range, Range> convert(final String input) {
        final Matcher matcher = INPUT_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input: " + input);
        }

        return Pair.of(
            Range.of(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
            ),
            Range.of(
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
            )
        );
    }
}
