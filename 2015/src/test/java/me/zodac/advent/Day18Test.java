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

import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import me.zodac.advent.pojo.grid.BooleanGrid;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day18}.
 */
class Day18Test {

    private static final String INPUT_FILENAME = "day18.txt";
    private static final char SYMBOL_SIGNIFYING_TRUE = '#';

    @Test
    void example() {
        final List<String> values = ExampleInput.readLines(INPUT_FILENAME);
        final BooleanGrid booleanGrid = BooleanGrid.parse(values, SYMBOL_SIGNIFYING_TRUE);

        final long numberOfCombinations1 = Day18.playGameOfLife(booleanGrid, 4);
        assertThat(numberOfCombinations1)
            .isEqualTo(4L);

        final long numberOfCombinations2 = Day18.playGameOfLifeWithCornersAlwaysOn(booleanGrid, 5);
        assertThat(numberOfCombinations2)
            .isEqualTo(17L);
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final BooleanGrid booleanGrid = BooleanGrid.parse(values, SYMBOL_SIGNIFYING_TRUE);
        final long numberOfCombinations = Day18.playGameOfLife(booleanGrid, 100);
        assertThat(numberOfCombinations)
            .isEqualTo(814L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME);

        final BooleanGrid booleanGrid = BooleanGrid.parse(values, SYMBOL_SIGNIFYING_TRUE);
        final long numberOfCombinations = Day18.playGameOfLifeWithCornersAlwaysOn(booleanGrid, 100);
        assertThat(numberOfCombinations)
            .isEqualTo(924L);
    }
}
