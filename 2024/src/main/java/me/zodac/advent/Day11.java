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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.StringUtils;

public final class Day11 {

    private Day11() {

    }

    public static long part1(final CharSequence value, final int iterations) { // TODO: Stop using CharSequence, for God's sake...
        final List<Long> numbers = StringUtils.collectNumbersInOrder(value);
        final Map<String, Long> cache = new HashMap<>(); // Should I make this a variable and stop making everything static? Check performance...

        long count = 0L;
        for (final Long number : numbers) {
            count += expand2Loop(cache, iterations, number);
        }

        return count;
    }

    // TODO: Remove this recursive function if possible, might be nicer iterative?
    private static long expand2Loop(final Map<? super String, Long> cache, final long iterations, final long number) {
        final String cacheKey = String.format("%s-%s", iterations, number);

        // Check cache if value has already been calculated
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        // Base case for recursive call
        if (iterations == 0L) {
            return 1L;
        }

        // Below are the three problem conditions, recursively calling the function and caching the result
        // TODO: Extract cases to functions
        if (number == 0) {
            final long result = expand2Loop(cache, iterations - 1, 1);
            cache.put(cacheKey, result);
            return result;
        }

        if (String.valueOf(number).length() % 2 == 0) {
            final Pair<String, String> split = StringUtils.bisect(String.valueOf(number));
            final long first = Long.parseLong(split.first());
            final long second = Long.parseLong(split.second());

            final long result = expand2Loop(cache, iterations - 1, first) + expand2Loop(cache, iterations - 1, second);
            cache.put(cacheKey, result);
            return result;
        }

        final long result = expand2Loop(cache, iterations - 1, number * 2_024L); // TODO: Constant
        cache.put(cacheKey, result);
        return result;
    }
}
