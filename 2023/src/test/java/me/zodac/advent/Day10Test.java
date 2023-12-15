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

import java.util.ArrayList;
import java.util.List;
import me.zodac.advent.input.ExampleInput;
import me.zodac.advent.input.PuzzleInput;
import me.zodac.advent.pojo.Pipe;
import me.zodac.advent.pojo.grid.Grid;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day10}.
 */
public class Day10Test {

    private static final String INPUT_FILENAME_PART_1 = "day10.txt";
    private static final String INPUT_FILENAME_PART_2 = "day10_2.txt";

    @Test
    void example() {
        final List<String> valuesPart1 = ExampleInput.readLines(INPUT_FILENAME_PART_1);
        final Grid<Pipe> pipeGridPart1 = Grid.parseGrid(valuesPart1, Pipe::get);

        final long numberOfSteps = Day10.numberOfStepsToFurthestPartOfLoop(pipeGridPart1);
        assertThat(numberOfSteps)
            .isEqualTo(8L);

        final List<String> valuesPart2 = new ArrayList<>(ExampleInput.readLines(INPUT_FILENAME_PART_2));
        final Grid<Pipe> pipeGridPart2 = Grid.parseGrid(valuesPart2, Pipe::get);

        final long numberOfPointsInsidePipeLoop = Day10.numberOfPointsInsidePipeLoop(pipeGridPart2);
        assertThat(numberOfPointsInsidePipeLoop)
            .isEqualTo(10L);
    }

    @Test
    void part1() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME_PART_1);
        final Grid<Pipe> pipeGrid = Grid.parseGrid(values, Pipe::get);

        final long numberOfSteps = Day10.numberOfStepsToFurthestPartOfLoop(pipeGrid);
        assertThat(numberOfSteps)
            .isEqualTo(6_812L);
    }

    @Test
    void part2() {
        final List<String> values = PuzzleInput.readLines(INPUT_FILENAME_PART_1);
        final Grid<Pipe> pipeGrid = Grid.parseGrid(values, Pipe::get);

        final long numberOfPointsInsidePipeLoop = Day10.numberOfPointsInsidePipeLoop(pipeGrid);
        assertThat(numberOfPointsInsidePipeLoop)
            .isEqualTo(527L);
    }
}
