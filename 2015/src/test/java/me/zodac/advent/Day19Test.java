/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import me.zodac.advent.input.InputReader;
import me.zodac.advent.pojo.Replacement;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day19}.
 */
class Day19Test {

    private static final String INPUT_FILENAME = "day19.txt";

    @Test
    void example() {
        final List<String> values = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        // Ignore 2 last lines with molecule and blank line
        final List<Replacement<String>> replacements = values.subList(0, values.size() - 2)
            .stream()
            .map(Replacement::parse)
            .toList();

        final String molecule = values.getLast();

        final long numberOfDistinctMolecules = Day19.numberOfDistinctReplacementMolecules(molecule, replacements);
        assertThat(numberOfDistinctMolecules)
            .isEqualTo(4L);
    }

    @Test
    void part1() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        // Ignore 2 last lines with molecule and blank line
        final List<Replacement<String>> replacements = values.subList(0, values.size() - 2)
            .stream()
            .map(Replacement::parse)
            .toList();

        final String molecule = values.getLast();

        final long numberOfDistinctMolecules = Day19.numberOfDistinctReplacementMolecules(molecule, replacements);
        assertThat(numberOfDistinctMolecules)
            .isEqualTo(518L);
    }

    @Test
    void part2() {
        final List<String> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readAllLines();

        // Ignore 2 last lines with molecule and blank line
        final List<Replacement<String>> replacements = values.subList(0, values.size() - 2)
            .stream()
            .map(Replacement::parse)
            .toList();

        final String molecule = values.getLast();

        final long numberOfDistinctMolecules = Day19.minimumStepsToCreateOutputFromInput("e", molecule, replacements);
        assertThat(numberOfDistinctMolecules)
            .isEqualTo(200L);
    }
}
