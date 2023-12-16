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
import me.zodac.advent.pojo.SequenceElement;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day15}.
 */
public class Day15Test {

    private static final String INPUT_FILENAME = "day15.txt";

    @Test
    void example() {
        final List<SequenceElement> values = ExampleInput.readSingleLineOfCommaSeparatedStrings(INPUT_FILENAME)
            .stream()
            .map(SequenceElement::parse)
            .toList();

        final long sumOfHashAlgorithms = Day15.calculateSumOfHashAlgorithms(values);
        assertThat(sumOfHashAlgorithms)
            .isEqualTo(1_320L);

        final long totalFocusPower = Day15.calculateTotalFocusPower(values);
        assertThat(totalFocusPower)
            .isEqualTo(145L);
    }

    @Test
    void part1() {
        final List<SequenceElement> values = PuzzleInput.readSingleLineOfCommaSeparatedStrings(INPUT_FILENAME)
            .stream()
            .map(SequenceElement::parse)
            .toList();

        final long sumOfHashAlgorithms = Day15.calculateSumOfHashAlgorithms(values);
        assertThat(sumOfHashAlgorithms)
            .isEqualTo(512_797L);
    }

    @Test
    void part2() {
        final List<SequenceElement> values = PuzzleInput.readSingleLineOfCommaSeparatedStrings(INPUT_FILENAME)
            .stream()
            .map(SequenceElement::parse)
            .toList();

        final long totalFocusPower = Day15.calculateTotalFocusPower(values);
        assertThat(totalFocusPower)
            .isEqualTo(262_454L);
    }
}
