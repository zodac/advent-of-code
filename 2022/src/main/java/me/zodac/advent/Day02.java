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

import java.util.Map;

/**
 * Solution for 2022, Day 2.
 *
 * @see <a href="https://adventofcode.com/2022/day/2">AoC 2022, Day 2</a>
 */
public final class Day02 {

    private Day02() {

    }

    public static long part1(final Iterable<String> values, final Map<String, Integer> codes) {
        long total = 0L;

        for(final String value : values){
            total += codes.getOrDefault(value, 0);
        }

        return total;
    }

    public static long part2(final Iterable<String> values, final Map<String, Integer> codes) {
        long total = 0L;

        for(final String value : values){
            total += codes.getOrDefault(value, 0);
        }

        return total;
    }
}
