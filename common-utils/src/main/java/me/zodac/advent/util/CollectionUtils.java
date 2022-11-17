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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Utility functions for {@link java.util.Collection}s.
 */
public final class CollectionUtils {

    private static final Boolean[] EMPTY_BOOLEAN_ARRAY = new Boolean[0];

    private CollectionUtils() {

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
    public static <E extends Comparable<E>> E getMiddleValueOfList(final List<E> list) {
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
        final E firstElement = modifiableInput.remove(0);
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
     * Returns the first element of the {@link Collection}.
     *
     * @param input the {@link Collection}
     * @param <E>   the type of the {@link Collection}
     * @return the first element
     * @throws NoSuchElementException thrown if the input {@link Collection} is empty
     */
    public static <E> E getFirst(final Collection<? extends E> input) {
        return get(input, 0);
    }

    /**
     * Returns the last element of the {@link Collection}.
     *
     * @param input the {@link Collection}
     * @param <E>   the type of the {@link Collection}
     * @return the last element
     * @throws NoSuchElementException thrown if the input {@link Collection} is empty
     */
    public static <E> E getLast(final Collection<? extends E> input) {
        return get(input, input.size() - 1);
    }

    private static <E> E get(final Collection<? extends E> input, final int index) {
        if (input.isEmpty()) {
            throw new NoSuchElementException();
        }

        final List<E> temp = new ArrayList<>(input);
        return temp.get(index);
    }

    /**
     * Extracts a value from the {@link Collection} of {@code elements}, based on the provided {@link Function}. The {@link Function} will be used to
     * map a new value for each element.
     *
     * @param elements           the {@link Collection} to be mapped
     * @param extractionFunction the {@link Function} used to extract a value
     * @param <I>                the type of the input {@link Collection} of {@code elements}
     * @param <O>                the type of the output values which will be mapped by the {@link Function}
     * @return the updated {@link Collection}
     */
    public static <I, O> List<O> extractValuesAsList(final Collection<I> elements, final Function<? super I, O> extractionFunction) {
        return elements
            .stream()
            .map(extractionFunction)
            .toList();
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
     * Generates the power-list of the input with duplicates. The power-list is a made up term for the {@link Set} that contains all possible
     * combinations of the input {@link List}. Since a {@link Set} cannot contain duplicates, we will use {@link List}s as inputs and
     * outputs instead.
     *
     * <p>
     * For example, given an input of {@code {1, 1, 3}}, the power-set would be:
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
     * @return the power-set of the input, with 2^n entries
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
     * Converts the provided {@link Collection} of {@link List}s of {@link Boolean}s to a 2D array.
     *
     * @param input the input {@link Collection} of {@link List}s
     * @return the 2D {@link Boolean} array
     */
    public static Boolean[][] convertToArrayOfArrays(final Collection<? extends List<Boolean>> input) {
        final Boolean[][] array = new Boolean[input.size()][];

        int i = 0;
        for (final List<Boolean> row : input) {
            array[i++] = row.toArray(EMPTY_BOOLEAN_ARRAY);
        }

        return array;
    }

    /**
     * Given an unsorted {@code int} array, finds the smallest index that has a value greater than the {@code thresholdValue}.
     *
     * @param input          the input to check
     * @param thresholdValue the threshold value that any array entry must be greater than
     * @return the smallest index in the {@code int} array
     * @throws IllegalArgumentException thrown if the input is {@code null}, empty or does not have any value greater than the {@code thresholdValue}
     */
    public static int findSmallestIndexGreaterThanThreshold(final int[] input, final int thresholdValue) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        for (int i = 0; i < input.length; i++) {
            final int houseValue = input[i];

            if (houseValue > thresholdValue) {
                return i;
            }
        }

        throw new IllegalArgumentException(String.format("No value in input is greater than %s", thresholdValue));
    }

    /**
     * Checks whether the input {@link Collection} has any duplicates.
     *
     * @param input the input {@link Collection} to check
     * @param <T>   the type of the {@link Collection}
     * @return {@code true} if the {@link Collection} contains at least one duplicate
     */
    public static <T> boolean containsDuplicates(final Collection<T> input) {
        return new HashSet<>(input).size() != input.size();
    }
}
