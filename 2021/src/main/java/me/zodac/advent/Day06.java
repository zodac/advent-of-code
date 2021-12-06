/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.util.MathUtils;

/**
 * @see <a href="https://adventofcode.com/2021/day/6">AoC 2021, Day 6</a>
 */
public final class Day06 {

    private static final int NUMBER_OF_TIMERS_FOR_LANTERNFISH = 9; // 0-8
    private static final int TIMER_FOR_NEW_FISH = 6; // See where this is used for an explanation on why we are adding new fish to timer 6 and not 8

    private Day06() {

    }

    /**
     * Provided a {@link List} of {@link Integer}s, each number will be assumed to  define a lanternfish's internal timer. As each day increments, the
     * lanternfish timer will decrement. A lanternfish with a timer of <b>0</b> will be reset to <b>6</b> the next day, and also spawn a new
     * lanternfish with an internal timer of <b>8</b>.
     *
     * @param lanternFish  the initial number of lanternfish and their timers
     * @param numberOfDays the number of days to simulate
     * @return the number of lanternfish after the given number of days
     * @throws IllegalStateException possible to cause a long overflow if the number of {@code lanternfish} or {@code numberOfDays} is too high
     *                               (exponential growth, don't you know?)
     */
    public static long countLanternFishAfterDays(final List<Integer> lanternFish, final int numberOfDays) {
        // Since the number of lanternfish will rise exponentially, we need to use longs to avoid an Integer overflow
        // Could potentially look at using BigDecimal if the expected numberOfDays is very large, but unnecessary for now
        final long[] lanternFishByTimer = new long[NUMBER_OF_TIMERS_FOR_LANTERNFISH];

        for (final int singleLanternFish : lanternFish) {
            lanternFishByTimer[singleLanternFish] = lanternFishByTimer[singleLanternFish] + 1;
        }

        for (int day = 0; day < numberOfDays; day++) {
            // We need to extract the current values for each timer, so we can then use them later
            // This allows us to (for example), update the value for timer 8, while still using the value at the start of the day for timer 7
            final Map<Integer, Long> lanternfishCountByTimer = getLanternfishCountAtStartOfDay(lanternFishByTimer, day);

            // Each timer takes the previous timers value
            // So if there were 5 fish with a 3-timer at the start of the day, we set 2-timer to 5, and so on
            // Special cases exist for 8 (which uses 0-timer) and 6 (which uses 7-timer and 0-timer, to count the 'new' fish)
            for (int timer = 0; timer < NUMBER_OF_TIMERS_FOR_LANTERNFISH; timer++) {
                if (timer == TIMER_FOR_NEW_FISH) {
                    // Technically we want all 0-timer fish to be reset to timer 6, and a new one at timer 8
                    // However, it is simpler to wrap timer 8 to use the value at timer 0,
                    // and simply update timer with the values from timer 7 and 'new' fish for timer 0
                    lanternFishByTimer[timer] = lanternfishCountByTimer.get(timer + 1) + lanternfishCountByTimer.get(0);
                } else {
                    // We want timer 8 to wrap around to timer 0
                    final int nextTimer = (timer + 1) % 9;
                    lanternFishByTimer[timer] = lanternfishCountByTimer.get(nextTimer);
                }
            }
        }

        return MathUtils.sumOfAll(lanternFishByTimer);
    }

    private static Map<Integer, Long> getLanternfishCountAtStartOfDay(final long[] lanternFishByTimer, final int day) {
        final Map<Integer, Long> lanternfishValues = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            final long lanternfishCountAtTimer = lanternFishByTimer[i];

            if (lanternfishCountAtTimer < 0) {
                throw new IllegalStateException(
                    String.format("Day %d, overflow has been achieved, code needs to be updated to use BigDecimal: %s", day,
                        Arrays.toString(lanternFishByTimer)));
            }

            lanternfishValues.put(i, lanternfishCountAtTimer);
        }
        return lanternfishValues;
    }
}
