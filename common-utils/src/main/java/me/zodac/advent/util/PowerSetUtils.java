/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2022 zodac.me
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

package me.zodac.advent.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for generating the power-set of an input {@link java.util.Collection}.
 */
public final class PowerSetUtils {

    private PowerSetUtils() {

    }

    /**
     * Generates the power-set of the input {@link Set}. The power-set is the {@link Set} that contains all possible combinations of the input
     * {@link Set}.
     *
     * <p>
     * For example, given an input of {@code {1, 2, 3}}, the power-set would be:
     * <pre>
     *     {
     *       {},
     *       {1},
     *       {2},
     *       {3},
     *       {1, 2},
     *       {1, 3},
     *       {2, 3},
     *       {1, 2, 3}
     *     }
     * </pre>
     *
     * @param input the input {@link Set}
     * @param <T>   the type of the {@link Set}
     * @return the power-set of the input, with 2^n entries
     * @see #getPowerList(List)
     */
    public static <T> Set<Set<T>> getPowerSet(final Set<? extends T> input) {
        final Set<Set<T>> powerSet = new HashSet<>();
        if (input.isEmpty()) {
            powerSet.add(new HashSet<>(0));
            return powerSet;
        }

        final List<T> list = new ArrayList<>(input);
        final T head = list.remove(0);
        final Set<T> remainder = new HashSet<>(list);

        for (final Set<T> subSet : getPowerSet(remainder)) {
            final Set<T> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(subSet);
            powerSet.add(newSet);
            powerSet.add(subSet);
        }
        return powerSet;
    }

    /**
     * Generates a power-set of the input, then filters based on the provided {@link PowerSetFilter}s.
     *
     * @param input           the input {@link Set}
     * @param powerSetFilters the {@link PowerSetFilter}s to filter the available combinations
     * @param <T>             the type of the {@link Set}
     * @return the filtered power-set of the input
     * @see #getPowerSet(Set)
     */
    public static <T> Set<Set<T>> getFilteredPowerSet(final Set<T> input, final Iterable<? extends PowerSetFilter<? extends T>> powerSetFilters) {
        return getPowerSet(input)
            .stream()
            .filter(combinations -> isValidCombination(combinations, powerSetFilters))
            .collect(Collectors.toSet());
    }

    /**
     * Generates the power-list of the input with duplicates. The power-list is a made up term for the {@link Set} that contains all possible
     * combinations of the input {@link List}. Since a {@link Set} cannot contain duplicates, we will use {@link List}s as inputs and
     * outputs instead.
     *
     * <p>
     * For example, given an input of {@code {1, 1, 3}}, the power-list would be:
     * <pre>
     *     {
     *       {},
     *       {1},
     *       {1},
     *       {3},
     *       {1, 1},
     *       {1, 3},
     *       {1, 3},
     *       {1, 1, 3}
     *     }
     * </pre>
     *
     * @param input the input {@link List}
     * @param <T>   the type of the {@link List}
     * @return the power-list of the input, with 2^n entries
     * @see #getPowerSet(Set)
     */
    public static <T> List<List<T>> getPowerList(final List<? extends T> input) {
        final List<List<T>> powerList = new ArrayList<>();
        if (input.isEmpty()) {
            powerList.add(new ArrayList<>(0));
            return powerList;
        }

        final List<T> list = new ArrayList<>(input);
        final T head = list.remove(0);
        final List<T> remainder = new ArrayList<>(list);

        for (final List<T> subList : getPowerList(remainder)) {
            final List<T> newList = new ArrayList<>();
            newList.add(head);
            newList.addAll(subList);
            powerList.add(newList);
            powerList.add(subList);
        }
        return powerList;
    }

    /**
     * Generates a power-list of the input with duplicates, then filters based on the provided {@link PowerSetFilter}s.
     *
     * @param input           the input {@link List}
     * @param powerSetFilters the {@link PowerSetFilter}s to filter the available combinations
     * @param <T>             the type of the {@link List}
     * @return the filtered power-list of the input
     * @see #getPowerList(List)
     */
    public static <T> List<List<T>> getFilteredPowerList(final List<T> input, final Iterable<? extends PowerSetFilter<? extends T>> powerSetFilters) {
        return getPowerList(input)
            .stream()
            .filter(combinations -> isValidCombination(combinations, powerSetFilters))
            .toList();
    }

    private static <T> boolean isValidCombination(final Collection<? extends T> powerSetCombinations,
                                                  final Iterable<? extends PowerSetFilter<? extends T>> powerSetRules) {
        final Map<Class<?>, List<T>> combinationsByClass = powerSetCombinations
            .stream()
            .collect(Collectors.groupingBy(T::getClass));

        for (final PowerSetFilter<? extends T> powerSetFilter : powerSetRules) {
            final Class<? extends T> powerSetRuleClass = powerSetFilter.subClass();
            final int count = safeCountByKey(combinationsByClass, powerSetRuleClass);

            if (count < powerSetFilter.minimumEntriesRequired() || count > powerSetFilter.maximumEntriesRequired()) {
                return false;
            }

            if (!powerSetFilter.allowDuplicates() && CollectionUtils.containsDuplicates(combinationsByClass.get(powerSetRuleClass))) {
                return false;
            }
        }

        return true;
    }

    private static <K, V> int safeCountByKey(final Map<K, ? extends Collection<V>> map, final K key) {
        if (!map.containsKey(key)) {
            return 0;
        }

        return map.get(key).size();
    }
}
