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

import java.util.Collection;
import me.zodac.advent.pojo.Route;
import me.zodac.advent.util.DistanceCalculator;

/**
 * Solution for 2015, Day 9.
 *
 * @see <a href="https://adventofcode.com/2015/day/9">AoC 2015, Day 9</a>
 */
public final class Day09 {

    private Day09() {

    }

    /**
     * Calculates the shortest path along all locations defined by the provided {@link Route}s.
     *
     * @param routes the {@link Route}s
     * @return the distance of the shortest path between all locations
     * @see DistanceCalculator
     */
    public static long distanceOfShortestPath(final Collection<Route> routes) {
        final DistanceCalculator distanceCalculator = DistanceCalculator.create(routes);
        return distanceCalculator.distanceOfShortestPath();
    }

    /**
     * Calculates the longest path along all locations defined by the provided {@link Route}s.
     *
     * @param routes the {@link Route}s
     * @return the distance of the longest path between all locations
     * @see DistanceCalculator
     */
    public static long distanceOfLongestPath(final Collection<Route> routes) {
        final DistanceCalculator distanceCalculator = DistanceCalculator.create(routes);
        return distanceCalculator.distanceOfLongestPath();
    }
}
