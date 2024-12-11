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

import java.util.ArrayList;
import java.util.List;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.StringUtils;

public final class Day11 {

    private Day11() {

    }

    public static long part1(final String value,int iterations) {
        final List<Long> numbers = StringUtils.collectNumbersInOrder(value);

        long count = 0L;
        for (final Long number : numbers) {
            count += expand(number, iterations);
        }

        return count;
    }

    private static long expand(final long start, final int iterations) {
        List<Long> numbers = List.of(start);
        for (int i = 0; i < iterations; i++) {
            final List<Long> newNumbers = new ArrayList<>();
            for (final long number : numbers) {
                if (number == 0) {
                    newNumbers.add(1L);
                } else if (String.valueOf(number).length() % 2 == 0) {
                    final Pair<String, String> split = StringUtils.bisect(String.valueOf(number));
                    newNumbers.add(Long.parseLong(split.first()));
                    newNumbers.add(Long.parseLong(split.second()));
                } else {
                    newNumbers.add(number * 2_024L);
                }
            }
            numbers = newNumbers;
        }
        return numbers.size();
    }

    public static long part2(final String value) {
        return 0L;
    }
}
