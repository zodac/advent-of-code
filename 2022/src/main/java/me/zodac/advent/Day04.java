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

import java.util.Collection;
import java.util.Objects;
import me.zodac.advent.pojo.Quadruple;

/**
 * Solution for 2022, Day 4.
 *
 * @see <a href="https://adventofcode.com/2022/day/4">AoC 2022, Day 4</a>
 */
public final class Day04 {

    private Day04() {

    }

    public static long part1(final Iterable<Quadruple<Integer, Integer, Integer, Integer>> values) {
        int count = 0;

        for (final Quadruple<Integer, Integer, Integer, Integer> value : values) {
            if (value.first() >= value.third() && value.second() <= value.fourth()
                || value.third() >= value.first() && value.fourth() <= value.second()) {
                count++;
            }
        }

        return count;
    }

    public static long part2(final Iterable<Quadruple<Integer, Integer, Integer, Integer>> values) {
        int count = 0;

        for (final Quadruple<Integer, Integer, Integer, Integer> value : values) {
            if (isBetween(value.first(), value.second(), value.third())
                || isBetween(value.first(), value.second(), value.fourth())
                || isBetween(value.third(), value.fourth(), value.first())
                || isBetween(value.third(), value.fourth(), value.second())) {
                count++;
            }
        }

        return count;
    }

    public static boolean isBetween(final int first, final int second, final int input) {
        return input >= first && input <= second;
    }
}
