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

package me.zodac.advent.util;

import static me.zodac.advent.util.CollectionUtils.getFirst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.zodac.advent.pojo.Route;

/**
 * Calculator that can calculate the shortest or longest path across a {@link List} of {@link Route}s, while visiting all locations once.
 */
public final class DistanceCalculator {

    private final Map<String, Integer> distancesByConcatLocations;
    private final List<? extends List<String>> permutations;
    private final boolean isDistanceUniDirectional;

    private DistanceCalculator(final Map<String, Integer> distancesByConcatLocations,
                               final List<? extends List<String>> permutations,
                               final boolean isDistanceUniDirectional) {
        this.distancesByConcatLocations = distancesByConcatLocations;
        this.permutations = permutations;
        this.isDistanceUniDirectional = isDistanceUniDirectional;
    }

    /**
     * Creates an instance of {@link DistanceCalculator}. We use the provided {@link Route}s to create a map of all distances by location, and also
     * all available permutations of the locations.
     *
     * <p>
     * Does not allow for a reverse mapping when calculating distance (a -> b != b -> a).
     *
     * @param routes the {@link Route}s
     * @return the created {@link DistanceCalculator}
     * @see CollectionUtils#generatePermutations(List)
     */
    public static DistanceCalculator createUniDirectional(final Collection<Route> routes) {
        return create(routes, true);
    }

    /**
     * Creates an instance of {@link DistanceCalculator}. We use the provided {@link Route}s to create a map of all distances by location, and also
     * all available permutations of the locations.
     *
     * <p>
     * Allows for a reverse mapping when calculating distance (a -> b == b -> a).
     *
     * @param routes the {@link Route}s
     * @return the created {@link DistanceCalculator}
     * @see CollectionUtils#generatePermutations(List)
     */
    public static DistanceCalculator createBiDirectional(final Collection<Route> routes) {
        return create(routes, false);
    }

    /**
     * Creates an instance of {@link DistanceCalculator}. We use the provided {@link Route}s to create a map of all distances by location, and also
     * all available permutations of the locations.
     *
     * @param routes                   the {@link Route}s
     * @param isDistanceUniDirectional distance value between source and destination is one-way only (a -> b != b -> a)
     * @return the created {@link DistanceCalculator}
     * @see CollectionUtils#generatePermutations(List)
     */
    public static DistanceCalculator create(final Collection<Route> routes, final boolean isDistanceUniDirectional) {
        final Map<String, Integer> distancesByConcatLocations = HashMap.newHashMap(routes.size());

        final Set<String> locations = new HashSet<>();
        for (final Route route : routes) {
            locations.add(route.from());
            locations.add(route.to());

            final String key = route.from() + route.to();
            distancesByConcatLocations.put(key, route.value());
        }

        final List<List<String>> permutations = CollectionUtils.generatePermutations(new ArrayList<>(locations))
            .stream()
            .toList();

        return new DistanceCalculator(distancesByConcatLocations, permutations, isDistanceUniDirectional);
    }

    /**
     * Returns the distance of the shortest path that visits all locations in the provided {@link Route}s.
     *
     * @return the distance of the shortest path
     */
    public int distanceOfShortestPath() {
        int minimumDistance = calculateDistance(getFirst(permutations));

        // First entry is used as 'baseline' distance, compare remaining entries in the subList to this value
        for (final List<String> permutation : permutations.subList(1, permutations.size())) {
            final int distanceOfPath = calculateDistance(permutation);
            minimumDistance = Math.min(minimumDistance, distanceOfPath);
        }

        return minimumDistance;
    }

    /**
     * Returns the distance of the longest path that visits all locations in the provided {@link Route}s.
     *
     * @return the distance of the longest path
     */
    public int distanceOfLongestPath() {
        int maximumDistance = calculateDistance(getFirst(permutations));

        // First entry is used as 'baseline' distance, compare remaining entries in the subList to this value
        for (final List<String> permutation : permutations.subList(1, permutations.size())) {
            final int distanceOfPath = calculateDistance(permutation);
            maximumDistance = Math.max(maximumDistance, distanceOfPath);
        }

        return maximumDistance;
    }

    private int calculateDistance(final List<String> permutation) {
        int totalDistance = 0;
        for (int i = 0; i < permutation.size() - 1; i++) {
            final String from = permutation.get(i);
            final String to = permutation.get(i + 1);
            totalDistance += getValue(from, to);
        }

        return totalDistance;
    }

    private int getValue(final String from, final String to) {
        final String key = from + to;
        if (distancesByConcatLocations.containsKey(key)) {
            return distancesByConcatLocations.get(key);
        }

        if (isDistanceUniDirectional) {
            throw new IllegalArgumentException(String.format("Unable to find a value for %s in saved values %s", key, distancesByConcatLocations));
        }

        final String reverseKey = to + from;
        if (distancesByConcatLocations.containsKey(reverseKey)) {
            return distancesByConcatLocations.get(reverseKey);
        }

        throw new IllegalArgumentException(
            String.format("Unable to find a value for %s or %s in saved values %s", key, reverseKey, distancesByConcatLocations));
    }
}
