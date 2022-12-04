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

import me.zodac.advent.pojo.tuple.Quadruple;

/**
 * Solution for 2022, Day 4.
 *
 * @see <a href="https://adventofcode.com/2022/day/4">AoC 2022, Day 4</a>
 */
public final class Day04 {

    private Day04() {

    }

    /**
     * Given an input of {@link Quadruple}s, where each {@link Quadruple} defines two numerical ranges, counts how many ranges are completely
     * overlapped by their pair. For example, given:
     * <pre>
     *     1-5,2-6
     *     1-9,2-3
     * </pre>
     *
     * <p>
     * The second value has a complete overlap, where {@code 2-3} is completely overlapped by {@code 1-9}.
     *
     * @param values the {@link Quadruple}s
     * @return the number of complete overlaps
     */
    public static long countCompleteOverlaps(final Iterable<Quadruple<Integer, Integer, Integer, Integer>> values) {
        int count = 0;

        for (final Quadruple<Integer, Integer, Integer, Integer> value : values) {
            if (value.first() >= value.third() && value.second() <= value.fourth()
                || value.third() >= value.first() && value.fourth() <= value.second()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Given an input of {@link Quadruple}s, where each {@link Quadruple} defines two numerical ranges, counts how many ranges are partially
     * overlapped by their pair. For example, given:
     * <pre>
     *     1-5,2-6
     *     1-9,2-3
     * </pre>
     *
     * <p>
     * Both values have a partial overlap, where {@code 1-5} and {@code 2-6} partiall overlap, and where {@code 2-3} is completely overlapped by
     * {@code 1-9}.
     *
     * @param values the {@link Quadruple}s
     * @return the number of partial overlaps
     */
    public static long countPartialOverlaps(final Iterable<Quadruple<Integer, Integer, Integer, Integer>> values) {
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

    private static boolean isBetween(final int first, final int second, final int input) {
        return input >= first && input <= second;
    }
}
