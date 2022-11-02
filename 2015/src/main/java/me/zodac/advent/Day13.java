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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import me.zodac.advent.pojo.Route;
import me.zodac.advent.util.DistanceCalculator;

/**
 * Solution for 2015, Day 13.
 *
 * @see <a href="https://adventofcode.com/2015/day/13">AoC 2015, Day 13</a>
 */
public final class Day13 {

    private static final String SELF_SOURCE_NAME = "me";
    private static final int SELF_DISTANCE_VALUE = 0;

    private Day13() {

    }

    /**
     * Calculates the longest path along all locations defined by the provided {@link Route}s.
     *
     * @param routes the {@link Route}s
     * @return the distance of the longest path between all locations
     * @see DistanceCalculator
     */
    public static long greatestChangeInHappiness(final Collection<Route> routes) {
        final DistanceCalculator distanceCalculator = DistanceCalculator.createWithAllOptions(routes);
        return distanceCalculator.distanceOfLongestPath();
    }

    /**
     * Calculates the longest path along all locations defined by the provided {@link Route}s. For all {@link Route}s, we also add ourselves
     * (default user) to each of the sources.
     *
     * @param routes the {@link Route}s
     * @return the distance of the longest path between all locations
     * @see DistanceCalculator
     */
    public static long greatestChangeInHappinessIncludingSelf(final Collection<Route> routes) {
        final Collection<Route> routesIncludingSelf = updateRoutesWithSelf(routes);
        final DistanceCalculator distanceCalculator = DistanceCalculator.createWithAllOptions(routesIncludingSelf);
        return distanceCalculator.distanceOfLongestPath();
    }

    private static Collection<Route> updateRoutesWithSelf(final Collection<Route> routes) {
        final Collection<Route> routesIncludingSelf = new ArrayList<>(routes);
        final Set<String> sources = getAllSources(routes);

        for (final String source : sources) {
            routesIncludingSelf.add(Route.create(SELF_SOURCE_NAME, source, SELF_DISTANCE_VALUE));
            routesIncludingSelf.add(Route.create(source, SELF_SOURCE_NAME, SELF_DISTANCE_VALUE));
        }

        return routesIncludingSelf;
    }

    private static Set<String> getAllSources(final Iterable<Route> routes) {
        final Set<String> sources = new HashSet<>();
        for (final Route route : routes) {
            sources.add(route.from());
            sources.add(route.to());
        }
        return sources;
    }
}
