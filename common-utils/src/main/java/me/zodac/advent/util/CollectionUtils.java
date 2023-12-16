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

package me.zodac.advent.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Utility functions for {@link java.util.Collection}s.
 */
public final class CollectionUtils {

    private CollectionUtils() {

    }

    /**
     * Checks whether the input {@link Collection} has any duplicates.
     *
     * @param input the input {@link Collection} to check
     * @param <T>   the type of the {@link Collection}
     * @return {@code true} if the {@link Collection} contains at least one duplicate
     */
    public static <T> boolean containsDuplicates(final Collection<T> input) {
        return !input.isEmpty() && input.stream().distinct().count() != input.size();
    }

    /**
     * Extracts a value from the {@link Collection} of {@code elements}, based on the provided {@link Function}. The {@link Function} will be used to
     * map a new value for each element.
     *
     * @param elements           the {@link Collection} to be mapped
     * @param extractionFunction the {@link Function} used to extract a value
     * @param <I>                the type of the input {@link Collection} of {@code elements}
     * @param <O>                the type of the output values which will be mapped by the {@link Function}
     * @return the updated {@link Collection}, or an empty {@link Collection} if input is {@link Collection#isEmpty()}
     */
    public static <I, O> List<O> extractValuesAsList(final Collection<I> elements, final Function<? super I, O> extractionFunction) {
        return elements
            .stream()
            .map(extractionFunction)
            .toList();
    }

    /**
     * For the provided {@link Collection}, we return all elements that are less than the provided {@code value}. To support generic types, a
     * {@link Comparator} is provided to do a comparision bewteen elements.
     *
     * @param values     the {@link Collection} to check
     * @param value      the value to compare against
     * @param comparator the {@link Comparator} defining how to compare the elements
     * @param <E>        the type of the elements
     * @return a {@link List} of all elements less than {@code value}
     */
    public static <E> List<E> findValuesLessThan(final Collection<E> values, final E value, final Comparator<? super E> comparator) {
        return values
            .stream()
            .filter(v -> Objects.compare(value, v, comparator) == 1)
            .toList();
    }

    /**
     * Generates permutations based on the input {@link List}.
     *
     * <p>
     * Given an input {@link List} of {@link String}s:
     * <pre>
     *      England
     *      Ireland
     *      Sweden
     * </pre>
     *
     * <p>
     * We would have 6 permutations (3!):
     * <pre>
     *     England, Ireland,  Sweden
     *     England,  Sweden, Ireland
     *     Ireland, England,  Sweden
     *     Ireland,  Sweden, England
     *      Sweden, England, Ireland
     *      Sweden, Ireland, England
     * </pre>
     *
     * @param input the {@link List} from which permutations should be generated
     * @param <E>   the type of the input {@link List}
     * @return the {@link List} of permutations
     */
    public static <E> List<List<E>> generatePermutations(final List<? extends E> input) {
        if (input.isEmpty()) {
            return List.of(List.of());
        }

        final List<? extends E> modifiableInput = new ArrayList<>(input);
        final E firstElement = modifiableInput.removeFirst();
        final List<List<E>> returnValue = new ArrayList<>();
        final List<List<E>> permutations = generatePermutations(modifiableInput);

        for (final List<E> smallerPermutation : permutations) {
            for (int index = 0; index <= smallerPermutation.size(); index++) {
                final List<E> temp = new ArrayList<>(smallerPermutation);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }

        return returnValue;
    }

    /**
     * For cases when the value of a {@link Map} might be known, but the key is not. We iterate over all {@link Map.Entry}s and check the value. If it
     * matches the input, then the key for that {@link Map.Entry} is returned.
     *
     * @param map   the {@link Map} to be searched
     * @param value the value whose key is to be found
     * @param <K>   the type of the key
     * @param <V>   the type of the value
     * @return an {@link Optional} of the found key
     */
    public static <K, V> Optional<K> getKeyByValue(final Map<K, V> map, final V value) {
        for (final Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return Optional.of(entry.getKey());
            }
        }

        return Optional.empty();
    }

    /**
     * Returns the value at the middle index of a {@link List}. The {@link List} is sorted then the middle index is returned.
     *
     * @param list the {@link List} whose middle index is to be found
     * @param <E>  the type of the {@link List}, must extend {@link Comparable}
     * @return the value at the middle of the sorted {@link List}
     * @throws IllegalArgumentException thrown if the {@link List} has an even size
     */
    public static <E extends Comparable<E>> E getMiddleValue(final List<E> list) {
        final int listSize = list.size();
        if (list.isEmpty() || MathUtils.isEven(listSize)) {
            throw new IllegalArgumentException("Expected list with positive odd size, found size: " + listSize);
        }

        final List<E> modifiableList = new ArrayList<>(list);
        Collections.sort(modifiableList);

        final int middleIndex = listSize / 2;
        return modifiableList.get(middleIndex);
    }

    /**
     * Given a {@link Collection}, groups all entries into sub-{@link Collection}s where each group is of size {@code amountPerGroup}.
     *
     * @param values         the input {@link Collection} to group
     * @param amountPerGroup the number of entries wanted per group
     * @param <T>            the type of the input {@link Collection}
     * @return the grouped {@link Collection} of {@link Collection}s
     * @throws IllegalArgumentException thrown if the input {@link Collection} cannot be split into exactly {@code amountPerGroup} groups, or if
     *                                  {@code amountPerGroup} is less than <b>1</b>.
     */
    public static <T> List<List<T>> groupBySize(final Collection<? extends T> values, final int amountPerGroup) {
        if (amountPerGroup <= 0) {
            throw new IllegalArgumentException(String.format("amountPerGroup must be at least 1, found: %s", amountPerGroup));
        }

        if (values.isEmpty()) {
            return List.of(List.of());
        }

        if (values.size() % amountPerGroup != 0) {
            throw new IllegalArgumentException(
                String.format("Expected number of entries to be divisible by %s, found: %s", amountPerGroup, values.size()));
        }

        final List<List<T>> groups = new ArrayList<>();

        List<T> group = new ArrayList<>();
        int counter = 0;
        for (final T value : values) {
            if (counter == amountPerGroup) {
                groups.add(group);
                group = new ArrayList<>();
                counter = 0;
            }

            group.add(value);
            counter++;
        }
        groups.add(group);

        return groups;
    }

    /**
     * Function that returns the index of a value within the provided {@link Collection}. To be used for implementations of {@link Collection} that do
     * not have their own {@code #indexOf()} function, like {@link Set}.
     *
     * @param collection the {@link Collection} to check
     * @param wantedValue      the value to look for
     * @param <E>        the type of the {@link Collection}
     * @return the index of the {@code value}, or <b>-1</b>
     * @see List#indexOf(Object)
     */
    public static <E> int indexOf(final Collection<? extends E> collection, final E wantedValue) {
        return new ArrayList<>(collection).indexOf(wantedValue);
    }

    /**
     * Returns the intersection of a {@link Set} and a {@link Collection}s - the common elements in both.
     *
     * @param first  the first {@link Set}
     * @param second the second {@link Collection}
     * @param <E>    the type of the {@link Set} and {@link Collection}
     * @return the common elements between both {@link Set} and {@link Collection}
     */
    public static <E> Set<E> intersection(final Set<? extends E> first, final Collection<E> second) {
        final Set<E> intersection = new HashSet<>(first);
        intersection.retainAll(second);
        return intersection;
    }
}
