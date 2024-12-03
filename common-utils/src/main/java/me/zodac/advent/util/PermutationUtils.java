/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import java.util.List;

/**
 * Utility class for permutations of {@link java.util.Collection}s.
 */
public final class PermutationUtils {

    private PermutationUtils() {

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
    public static <E> List<List<E>> generateAll(final List<? extends E> input) {
        if (input.isEmpty()) {
            return List.of(List.of());
        }

        final List<? extends E> modifiableInput = new ArrayList<>(input);
        final E firstElement = modifiableInput.removeFirst();
        final List<List<E>> returnValue = new ArrayList<>();
        final List<List<E>> permutations = generateAll(modifiableInput);

        for (final List<E> smallerPermutation : permutations) {
            final int sizeOfSmallerPermutation = smallerPermutation.size();

            for (int index = 0; index <= sizeOfSmallerPermutation; index++) {
                final List<E> temp = new ArrayList<>(smallerPermutation);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }

        return returnValue;
    }

    /**
     * Generates permutations based on the input {@link List}. It will return all permutations of the original {@link List} where a single element has
     * been removed.
     *
     * <p>
     * Given an input {@link List} of {@link String}s:
     * <pre>
     *      England
     *      Ireland
     *      Sweden
     *      New Zealand
     * </pre>
     *
     * <p>
     * We would have 4 permutations:
     * <pre>
     *     England, Ireland,      Sweden
     *     England, Ireland, New Zealand
     *     England,  Sweden, New Zealand
     *     Ireland,  Sweden, New Zealand
     * </pre>
     *
     * @param input the {@link List} from which permutations should be generated
     * @param <E>   the type of the input {@link List}
     * @return the {@link List} of permutations
     */
    public static <E> List<List<E>> generateAllWithOneRemovedEntry(final List<? extends E> input) {
        final List<List<E>> combinations = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            final List<E> combination = new ArrayList<>(input);
            combination.remove(i);
            combinations.add(combination);
        }

        return combinations;
    }
}
