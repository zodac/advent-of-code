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

import java.util.Collections;
import java.util.List;
import me.zodac.advent.pojo.MoveCostType;

/**
 * @see <a href="https://adventofcode.com/2021/day/7">AoC 2021, Day 7</a>
 */
public final class Day07 {

    private Day07() {

    }

    /**
     * Given an input {@link List} of {@link Integer} values defining a number of vertical locations, we attempt to find the minimum distance for all
     * values to align to a given location.
     *
     * <p>
     * The cost for the moves are defined by {@link MoveCostType}.
     *
     * @param locations    the locations to align vertically
     * @param moveCostType the {@link MoveCostType} per move
     * @return the minimum moves required to align to the ideal vertical location
     */
    public static long minimumMovesNeededToAlignVertically(final List<Integer> locations, final MoveCostType moveCostType) {
        final int minValue = Collections.min(locations);
        final int maxValue = Collections.max(locations);
        long minimumNumberOfMoves = Long.MAX_VALUE;

        for (int possibleAlignmentLocation = minValue; possibleAlignmentLocation < maxValue; possibleAlignmentLocation++) {
            long movedNeeded = 0L;

            for (final int location : locations) {
                movedNeeded += moveCostType.costForMove(location, possibleAlignmentLocation);
            }

            if (movedNeeded < minimumNumberOfMoves) {
                minimumNumberOfMoves = movedNeeded;
            }
        }

        return minimumNumberOfMoves;
    }
}
