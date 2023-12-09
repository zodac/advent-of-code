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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import me.zodac.advent.util.MathUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2023, Day 09.
 *
 * @see <a href="https://adventofcode.com/2023/day/09">[2023: 09] Mirage Maintenance</a>
 */
public final class Day09 {

    private Day09() {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final Iterable<String> values) {
        long total = 0;

        for (final String input : values) {
            final List<Long> value = StringUtils.collectNumbersInOrder(input);
            final List<List<Long>> allDiffs = new LinkedList<>();

            List<Long> previousList = value;

            do {
                final List<Long> newDiffs = new ArrayList<>();
                for (int i = 0; i < previousList.size() - 1; i++) {
                    newDiffs.add(previousList.get(i + 1) - previousList.get(i));
                }

                previousList = newDiffs;
                allDiffs.add(newDiffs);
            } while (MathUtils.containsAnyNotAllowedValue(allDiffs.getLast(), 0L));

            final List<Long> lastDiffs = allDiffs.getLast();
            lastDiffs.add(0L);

            for (int i = allDiffs.size() - 1; i > 0; i--) {
                final List<Long> lastRemainingDiffs = allDiffs.get(i);
                final List<Long> secondLastRemainingDiffs = allDiffs.get(i - 1);

                final long currentLastRemainingDiff = lastRemainingDiffs.getLast();
                final long currentSecondLastRemainingDiff = secondLastRemainingDiffs.getLast();

                final long newDiff = currentSecondLastRemainingDiff + currentLastRemainingDiff;
                secondLastRemainingDiffs.add(newDiff);
            }

            final List<Long> lastRemainingDiffs = allDiffs.getFirst();

            final long currentLastRemainingDiff = lastRemainingDiffs.getLast();
            final long currentLastValue = value.getLast();

            final long newDiff = currentLastValue + currentLastRemainingDiff;
            value.add(newDiff);

            total += newDiff;
        }

        return total;
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final Iterable<String> values) {
        long total = 0;

        for (final String input : values) {
            final List<Long> value = StringUtils.collectNumbersInOrder(input);
            final List<List<Long>> allDiffs = new LinkedList<>();

            List<Long> previousList = value;

            do {
                final List<Long> newDiffs = new ArrayList<>();
                for (int i = 0; i < previousList.size() - 1; i++) {
                    newDiffs.add(previousList.get(i + 1) - previousList.get(i));
                }

                previousList = newDiffs;
                allDiffs.add(newDiffs);
            } while (MathUtils.containsAnyNotAllowedValue(allDiffs.getLast(), 0L));

            final List<Long> lastDiffs = allDiffs.getLast();
            lastDiffs.addFirst(0L);

            for (int i = allDiffs.size() - 1; i > 0; i--) {
                final List<Long> lastRemainingDiffs = allDiffs.get(i);
                final List<Long> secondLastRemainingDiffs = allDiffs.get(i - 1);

                final long currentLastRemainingDiff = lastRemainingDiffs.getFirst();
                final long currentSecondLastRemainingDiff = secondLastRemainingDiffs.getFirst();

                final long newDiff = currentSecondLastRemainingDiff - currentLastRemainingDiff;
                secondLastRemainingDiffs.addFirst(newDiff);
            }

            final List<Long> lastRemainingDiffs = allDiffs.getFirst();

            final long currentLastRemainingDiff = lastRemainingDiffs.getFirst();
            final long currentLastValue = value.getFirst();

            final long newDiff = currentLastValue - currentLastRemainingDiff;
            value.addFirst(newDiff);

            total += newDiff;
        }

        return total;
    }
}
