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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.Interval;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2023, Day 5.
 *
 * @see <a href="https://adventofcode.com/2023/day/5">[2023: 05] If You Give A Seed A Fertilizer</a>
 */
public final class Day05 {

    private Day05() {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final List<Long> seedDefinitions = StringUtils.collectNumbersInOrder(values.getFirst());
        final Collection<Interval> seedIntervals = new ArrayList<>();

        for (final Long seedDefinition : seedDefinitions) {
            seedIntervals.add(Interval.singular(seedDefinition));
        }

        final Map<String, List<String>> mappingsByName = loadMappings(values);
        return solve(mappingsByName, seedIntervals);
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final List<String> values) {
        final List<Long> seedDefinitions = StringUtils.collectNumbersInOrder(values.getFirst());
        final Collection<Interval> seedIntervals = new ArrayList<>();

        for (int i = 0; i <= seedDefinitions.size() / 2; i += 2) {
            seedIntervals.add(Interval.openInterval(seedDefinitions.get(i), seedDefinitions.get(i) + seedDefinitions.get(i + 1)));
        }

        final Map<String, List<String>> mappingsByName = loadMappings(values);
        return solve(mappingsByName, seedIntervals);
    }

    private static long solve(final Map<String, List<String>> mappingsByName, final Iterable<Interval> seedIntervals) {
        long minNumber = Long.MAX_VALUE;

        for (final Interval seedInterval : seedIntervals) {
            final Interval soil = find(seedInterval, mappingsByName.getOrDefault("seed-to-soil map:", new ArrayList<>()));
            final Interval fertilizer = find(soil, mappingsByName.getOrDefault("soil-to-fertilizer map:", new ArrayList<>()));
            final Interval water = find(fertilizer, mappingsByName.getOrDefault("fertilizer-to-water map:", new ArrayList<>()));
            final Interval light = find(water, mappingsByName.getOrDefault("water-to-light map:", new ArrayList<>()));
            final Interval temperature = find(light, mappingsByName.getOrDefault("light-to-temperature map:", new ArrayList<>()));
            final Interval humidity = find(temperature, mappingsByName.getOrDefault("temperature-to-humidity map:", new ArrayList<>()));
            final Interval location = find(humidity, mappingsByName.getOrDefault("humidity-to-location map:", new ArrayList<>()));

            if (location.start() < minNumber) {
                minNumber = location.start();
            }
        }

        return minNumber;
    }

    private static Map<String, List<String>> loadMappings(final List<String> values) {
        final Map<String, List<String>> mappingsByName = new HashMap<>();
        String currentKey = "";

        for (final String value : values.subList(2, values.size())) {
            if (value.isBlank()) {
                continue;
            }

            if (value.contains("map")) {
                currentKey = value;
                continue;
            }

            final List<String> currentMappings = mappingsByName.getOrDefault(currentKey, new ArrayList<>());
            currentMappings.add(value);
            mappingsByName.put(currentKey, currentMappings);
        }
        return mappingsByName;
    }

    private static Interval find(final Interval interval, final Iterable<String> possibleMappings) {
        for (final String possibleMapping : possibleMappings) {
            final List<Long> mappingValues = StringUtils.collectNumbersInOrder(possibleMapping);
            final Interval mappingInterval = new Interval(mappingValues.get(1), mappingValues.get(1) + mappingValues.get(2));
            final Interval intersection = interval.intersect(mappingInterval);

            if (!intersection.isEmpty()) {
                final long offset = mappingValues.get(0) - mappingValues.get(1);
                return Interval.openInterval(intersection.start() + offset, intersection.end() + offset);
            }
        }
        return interval;
    }
}

