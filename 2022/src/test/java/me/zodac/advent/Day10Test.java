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
import me.zodac.advent.pojo.AssemblyInstruction;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day10}.
 */
class Day10Test {

    private static final String INPUT_FILENAME = "day10.txt";

    @Test
    void example() {
        final List<AssemblyInstruction> values = ExampleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(AssemblyInstruction::parse)
            .toList();

        final long sumOfSignalValues = Day10.sumOfSignalValues(values);
        assertThat(sumOfSignalValues)
            .isEqualTo(13_140L);
    }

    @Test
    void part1() {
        final List<AssemblyInstruction> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(AssemblyInstruction::parse)
            .toList();

        final long sumOfSignalValues = Day10.sumOfSignalValues(values);
        assertThat(sumOfSignalValues)
            .isEqualTo(16_480L);
    }

    @Test
    void part2() {
        final List<AssemblyInstruction> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(AssemblyInstruction::parse)
            .toList();

        final String charactersOnSegmentedDisplay = Day10.charactersOnSegmentedDisplay(values);
        assertThat(charactersOnSegmentedDisplay)
            .isEqualTo("PLEFULPB");
    }
}
