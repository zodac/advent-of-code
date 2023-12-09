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
import java.util.Collection;
import java.util.List;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2023, Day 6.
 *
 * @see <a href="https://adventofcode.com/2023/day/6">[2023: 06] Wait For It</a>
 */
public final class Day06 {

    private Day06() {

    }

    private record Race(long time, long distance) {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final Collection<Race> races = new ArrayList<>();

        final List<Long> times = StringUtils.collectNumbersInOrder(values.getFirst());
        final List<Long> distances = StringUtils.collectNumbersInOrder(values.getLast());

        for (int i = 0; i < times.size(); i++) {
            races.add(new Race(times.get(i), distances.get(i)));
        }

        long total = 1;
        for (final Race race : races) {
            final long val = countWinningOptions(race);
            total *= val;
        }
        return total;
    }

    private static long countWinningOptions(final Race race) {
        int count = 0;
        for (int timeToHold = 1; timeToHold < race.time - 1; timeToHold++) {
            final long remainingTime = race.time - timeToHold;
            final long travelledDistance = timeToHold * remainingTime;

            if (travelledDistance > race.distance) {
                count++;
            }
        }
        return count;
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final List<String> values) {
        final List<Long> times = StringUtils.collectNumbersInOrder(values.getFirst());
        final List<Long> distances = StringUtils.collectNumbersInOrder(values.getLast());

        final StringBuilder timeBuilder = new StringBuilder();
        final StringBuilder distanceBuilder = new StringBuilder();
        for (int i = 0; i < times.size(); i++) {
            timeBuilder.append(times.get(i));
            distanceBuilder.append(distances.get(i));
        }

        final Race race = new Race(Long.parseLong(timeBuilder.toString()), Long.parseLong(distanceBuilder.toString()));
        return countWinningOptions(race);
    }
}
