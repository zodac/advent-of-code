/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zodac.advent;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import me.zodac.advent.pojo.Reindeer;

/**
 * Solution for 2015, Day 14.
 *
 * @see <a href="https://adventofcode.com/2015/day/14">AoC 2015, Day 14</a>
 */
public final class Day14 {

    private static final int STARTING_POINTS = 0;
    private static final int POINTS_FOR_BEING_IN_THE_LEAD = 1;

    private Day14() {

    }

    /**
     * Given a {@link Collection} of {@link Reindeer}, it calculates the distance each one travels in the provided {@link Duration}, then returns the
     * furthest distance travelled.
     *
     * @param reindeers      the travelling {@link Reindeer}
     * @param travelDuration the {@link Duration} of travel
     * @return the distance of the furthest-travelled {@link Reindeer}
     */
    public static long distanceOfFurthestTravelledReindeer(final Collection<Reindeer> reindeers, final Duration travelDuration) {
        return reindeers
            .stream()
            .map(reindeer -> reindeer.calculateDistance(travelDuration))
            .mapToLong(Long::longValue)
            .max()
            .orElseThrow(() -> new IllegalStateException("Could not find a max value for input: " + reindeers));
    }

    /**
     * Given {@link Collection} of {@link Reindeer}, it calculates the distance each one travels for every second of the provided {@link Duration}. At
     * the end of each second, the {@link Reindeer} that has travelled the furthest is given {@value #POINTS_FOR_BEING_IN_THE_LEAD} points. The value
     * returned is the highest score out of all the {@link Reindeer} after the {@code travelDuration} is complete.
     *
     * @param reindeers      the travelling {@link Reindeer}
     * @param travelDuration the {@link Duration} of travel
     * @return the score of the highest-scoring {@link Reindeer}
     */
    public static long calculateTheHighestScore(final Collection<Reindeer> reindeers, final Duration travelDuration) {
        final long secondsToTravel = travelDuration.toSeconds();

        final Map<Reindeer, Integer> scoresByReindeer = HashMap.newHashMap(reindeers.size());
        for (final Reindeer reindeer : reindeers) {
            scoresByReindeer.put(reindeer, STARTING_POINTS);
        }

        for (int travelTime = 1; travelTime <= secondsToTravel; travelTime++) {
            final Collection<Reindeer> furthestReindeers = getFurthestReindeerForTime(reindeers, travelTime);

            for (final Reindeer furthestReindeer : furthestReindeers) {
                scoresByReindeer.computeIfPresent(furthestReindeer, (key, value) -> value + POINTS_FOR_BEING_IN_THE_LEAD);
            }
        }

        return Collections.max(scoresByReindeer.values());
    }

    private static Collection<Reindeer> getFurthestReindeerForTime(final Iterable<Reindeer> reindeers, final int travelTime) {
        final Collection<Reindeer> furthestReindeers = new HashSet<>();
        long distanceOfFurthestReindeer = Long.MIN_VALUE;

        for (final Reindeer reindeer : reindeers) {
            final long distanceAtTime = reindeer.calculateDistance(Duration.ofSeconds(travelTime));

            if (distanceAtTime > distanceOfFurthestReindeer) {
                distanceOfFurthestReindeer = distanceAtTime;
                furthestReindeers.clear();
                furthestReindeers.add(reindeer);
            } else if (distanceAtTime == distanceOfFurthestReindeer) {
                furthestReindeers.add(reindeer);
            }
        }
        return furthestReindeers;
    }
}
