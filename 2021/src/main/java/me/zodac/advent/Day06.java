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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://adventofcode.com/2021/day/6">AoC 2021, Day 6</a>
 */
public final class Day06 {

    private static final int NUMBER_OF_TIMERS_FOR_LANTERNFISH = 9; // 0-8

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
    public static BigDecimal countLanternFishAfterDays(final List<Integer> lanternFish, final int numberOfDays) {
        // Rather than storing the lanternfish themselves (which explodes in size), we group the fish by their timers and simply increment/decrement
        final BigDecimal[] lanternFishByTimer = new BigDecimal[NUMBER_OF_TIMERS_FOR_LANTERNFISH];
        Arrays.fill(lanternFishByTimer, BigDecimal.ZERO);

        for (final int singleLanternFish : lanternFish) {
            lanternFishByTimer[singleLanternFish] = lanternFishByTimer[singleLanternFish].add(BigDecimal.ONE);
        }

        for (int day = 0; day < numberOfDays; day++) {
            // This is more flexible code, that can take a variable number of timers for each lanternfish.
            // Not really necessary, and makes the logic harder to follow, so I'm not using it, but keeping for reference
            //
            //// We need to extract the current values for each timer, so we can then use them later
            //// This allows us to (for example), update the value for timer 8, while still using the value at the start of the day for timer 7
            //final Map<Integer, BigDecimal> lanternfishCountByTimer = getLanternfishCountAtStartOfDay(lanternFishByTimer, day);
            //
            //// Each timer takes the previous timer's value
            //// So if there were 5 fish at 3-timer at the start of the day, we set 2-timer to 5, and so on
            //// Special cases exist for 8 (which uses 0-timer) and 6 (which uses 7-timer and 0-timer, to count the 'new' fish)
            //for (int timer = 0; timer < NUMBER_OF_TIMERS_FOR_LANTERNFISH; timer++) {
            //    if (timer == TIMER_FOR_NEW_FISH) { // TIMER_FOR_NEW_FISH = 6
            //        // Technically we want all 0-timer fish to be reset to timer 6, and a new one at timer 8
            //        // However, it is simpler to wrap timer 8 to use the value at timer 0,
            //        // and simply update timer with the values from timer 7 and 'new' fish for timer 0
            //        lanternFishByTimer[timer] = lanternfishCountByTimer.get(timer + 1).add(lanternfishCountByTimer.get(0));
            //    } else {
            //        // We want timer 8 to wrap around to timer 0
            //        final int nextTimer = (timer + 1) % 9;
            //        lanternFishByTimer[timer] = lanternfishCountByTimer.get(nextTimer);
            //    }
            //}

            // Keeping the number of lanternfish for each timer at the start of the day
            final BigDecimal zero = lanternFishByTimer[0];
            final BigDecimal one = lanternFishByTimer[1];
            final BigDecimal two = lanternFishByTimer[2];
            final BigDecimal three = lanternFishByTimer[3];
            final BigDecimal four = lanternFishByTimer[4];
            final BigDecimal five = lanternFishByTimer[5];
            final BigDecimal six = lanternFishByTimer[6];
            final BigDecimal seven = lanternFishByTimer[7];
            final BigDecimal eight = lanternFishByTimer[8];

            // Note that due to the way BigDecimal works, there is no need to check for any overflow
            // BigDecimal should
            // use all available memory on the system, so we will get an OutOfMemoryError instead of any overflow

            lanternFishByTimer[0] = one;
            lanternFishByTimer[1] = two;
            lanternFishByTimer[2] = three;
            lanternFishByTimer[3] = four;
            lanternFishByTimer[4] = five;
            lanternFishByTimer[5] = six;
            lanternFishByTimer[6] = seven.add(zero); // Timer 6 has all fish from timer 7, and also the fish from timer 0 that just spawned new fish
            lanternFishByTimer[7] = eight;
            lanternFishByTimer[8] = zero;
        }

        return sumOfAll(lanternFishByTimer);
    }

    //private static Map<Integer, BigDecimal> getLanternfishCountAtStartOfDay(final BigDecimal[] lanternFishByTimer, final int day) {
    //    final Map<Integer, BigDecimal> lanternfishValues = new HashMap<>();
    //    for (int i = 0; i < 9; i++) {
    //        // Note that due to the way BigDecimal works, there is no need to check for any overflow
    //        // BigDecimal should use all available memory on the system, so we will get an OutOfMemoryError instead of any overflow
    //        final BigDecimal lanternfishCountAtTimer = lanternFishByTimer[i];
    //        lanternfishValues.put(i, lanternfishCountAtTimer);
    //    }
    //    return lanternfishValues;
    //}

    private static BigDecimal sumOfAll(final BigDecimal... values) {
        BigDecimal count = BigDecimal.ZERO;
        for (final BigDecimal value : values) {
            count = count.add(value);
        }
        return count;
    }
}
