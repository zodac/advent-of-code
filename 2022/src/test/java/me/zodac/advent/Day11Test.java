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

import java.math.BigDecimal;
import java.util.List;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day11}.
 */
class Day11Test {

    private static final String INPUT_FILENAME = "day11.txt";

    @Test
    void part1() {
        final List<String> values = FileUtils.readLines(INPUT_FILENAME);

        final BigDecimal part1 = Day11.solve(values, 20, true);
        assertThat(part1)
            .isEqualTo(new BigDecimal("151312"));
    }

    @Test
    void part2() {
        final List<String> values = FileUtils.readLines(INPUT_FILENAME);

        final BigDecimal part2 = Day11.solve(values, 10_000, false);
        assertThat(part2)
            .isEqualTo(new BigDecimal("51382025916"));
    }
}
