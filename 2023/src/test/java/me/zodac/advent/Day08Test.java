/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2023 zodac.me
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
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day08}.
 */
public class Day08Test {

    private static final String INPUT_FILENAME = "day08.txt";
    private static final String INPUT_FILENAME_PART_2 = "day08_2.txt";

    @Test
    void example() {
        final List<String> valuesPart1 = ExampleInput.readLines(INPUT_FILENAME);

        final long part1Result = Day08.part1(valuesPart1);
        assertThat(part1Result)
            .isEqualTo(6L);

        final List<String> valuesPart2 = ExampleInput.readLines(INPUT_FILENAME_PART_2);
        final long part2Result = Day08.part2(valuesPart2);
        assertThat(part2Result)
            .isEqualTo(6L);
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long part1Result = Day08.part1(values);
        assertThat(part1Result)
            .isEqualTo(14_893L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final long part2Result = Day08.part2(values);
        assertThat(part2Result)
            .isEqualTo(10_241_191_004_509L);
    }
}
