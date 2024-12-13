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
import me.zodac.advent.pojo.Ingredient;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day15}.
 */
class Day15Test {

    private static final String INPUT_FILENAME = "day15.txt";
    private static final int PART_1_WANTED_CALORIE_COUNT = 0;
    private static final int PART_2_WANTED_CALORIE_COUNT = 500;

    @Test
    void example() {
        final List<Ingredient> values = InputReader
            .forExample(INPUT_FILENAME)
            .as(Ingredient::parse)
            .readAllLines();

        final long score1 = Day15.scoreOfBestIngredients(values, PART_1_WANTED_CALORIE_COUNT);
        assertThat(score1)
            .isEqualTo(62_842_880L);

        final long score2 = Day15.scoreOfBestIngredients(values, PART_2_WANTED_CALORIE_COUNT);
        assertThat(score2)
            .isEqualTo(57_600_000L);
    }

    @Test
    void part1() {
        final List<Ingredient> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Ingredient::parse)
            .readAllLines();

        final long score = Day15.scoreOfBestIngredients(values, PART_1_WANTED_CALORIE_COUNT);
        assertThat(score)
            .isEqualTo(21_367_368L);
    }

    @Test
    void part2() {
        final List<Ingredient> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .as(Ingredient::parse)
            .readAllLines();

        final long score = Day15.scoreOfBestIngredients(values, PART_2_WANTED_CALORIE_COUNT);
        assertThat(score)
            .isEqualTo(1_766_400L);
    }
}
