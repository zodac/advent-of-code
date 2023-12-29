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
import me.zodac.advent.input.InputReader;
import me.zodac.advent.pojo.Direction;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day03}.
 */
class Day03Test {

    private static final String INPUT_FILENAME = "day03.txt";

    @Test
    void example() {
        final List<Direction> values = InputReader
            .forExample(INPUT_FILENAME)
            .asStrings()
            .readStream()
            .flatMapToInt(String::chars)
            .mapToObj(i -> Direction.get((char) i))
            .toList();

        final int uniqueHouses1 = Day03.countUniqueHouses(values);
        assertThat(uniqueHouses1)
            .isEqualTo(2);

        final int uniqueHouses2 = Day03.countUniqueHousesTwoUsers(values);
        assertThat(uniqueHouses2)
            .isEqualTo(11);
    }

    @Test
    void part1() {
        final List<Direction> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readStream()
            .flatMapToInt(String::chars)
            .mapToObj(i -> Direction.get((char) i))
            .toList();

        final int uniqueHouses = Day03.countUniqueHouses(values);
        assertThat(uniqueHouses)
            .isEqualTo(2_081);
    }

    @Test
    void part2() {
        final List<Direction> values = InputReader
            .forPuzzle(INPUT_FILENAME)
            .asStrings()
            .readStream()
            .flatMapToInt(String::chars)
            .mapToObj(i -> Direction.get((char) i))
            .toList();

        final int uniqueHouses = Day03.countUniqueHousesTwoUsers(values);
        assertThat(uniqueHouses)
            .isEqualTo(2_341);
    }
}
