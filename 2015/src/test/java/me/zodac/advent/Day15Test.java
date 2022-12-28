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
import me.zodac.advent.input.PuzzleInput;
import me.zodac.advent.pojo.Ingredient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day15}.
 */
class Day15Test {

    private static final String INPUT_FILENAME = "day15.txt";
    private static final int PART_2_WANTED_CALORIE_COUNT = 500;

    @Disabled("TODO: Solution only works with 4 inputs, will fail with 2 from the example")
    void example() {

    }

    @Test
    void part1() {
        final List<Ingredient> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Ingredient::parse)
            .toList();

        final long score = Day15.scoreOfBestIngredients(values);
        assertThat(score)
            .isEqualTo(21_367_368L);
    }

    @Test
    void part2() {
        final List<Ingredient> values = PuzzleInput.readLines(INPUT_FILENAME)
            .stream()
            .map(Ingredient::parse)
            .toList();

        final long score = Day15.scoreOfBestIngredients(values, PART_2_WANTED_CALORIE_COUNT);
        assertThat(score)
            .isEqualTo(1_766_400L);
    }
}
