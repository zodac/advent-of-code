/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2025 zodac.net
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

package net.zodac.advent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.zodac.advent.input.InputReader;
import net.zodac.advent.json.JsonElement;
import net.zodac.advent.json.JsonParser;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day13}.
 */
class Day13Test {

    private static final String INPUT_FILENAME = "day13.txt";

    @Test
    void example() {
        final List<JsonElement> values = InputReader
            .forExample(INPUT_FILENAME)
            .excludeBlankLines()
            .as(JsonParser::parse)
            .readAllLines();

        final long part1Result = Day13.calculateSumOfValidIndices(values);
        assertThat(part1Result)
            .isEqualTo(13L);

        final long part2Result = Day13.calculateProductOfDistressSignalIndices(values);
        assertThat(part2Result)
            .isEqualTo(140L);
    }

    @Test
    void part1() {
        final List<JsonElement> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .excludeBlankLines()
            .as(JsonParser::parse)
            .readAllLines();

        final long part1Result = Day13.calculateSumOfValidIndices(values);
        assertThat(part1Result)
            .isEqualTo(5_185L);
    }

    @Test
    void part2() {
        final List<JsonElement> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .excludeBlankLines()
            .as(JsonParser::parse)
            .readAllLines();

        final long part2Result = Day13.calculateProductOfDistressSignalIndices(values);
        assertThat(part2Result)
            .isEqualTo(23_751L);
    }
}
