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
import java.util.Set;
import me.zodac.advent.pojo.MathOperation;
import org.jspecify.annotations.Nullable;

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

    // TODO: Horrible naming all round, fix this.
    public static List<List<String>> generateCombinations(List<String> numbers, final Set<MathOperation> operations) {
        List<List<String>> result = new ArrayList<>();
        if (numbers == null || numbers.isEmpty()) {
            return result;
        }
        // Start recursive generation
        generate(numbers, 0, new ArrayList<>(), result, operations);
        return result;
    }

    private static void generate(List<String> numbers, int index, List<String> current, List<List<String>> result, final Set<MathOperation> operations) {
        // Base case: when index reaches the last number
        if (index == numbers.size() - 1) {
            current.add(numbers.get(index));
            result.add(new ArrayList<>(current));
            current.remove(current.size() - 1); // Backtrack
            return;
        }

        // Add current number
        current.add(numbers.get(index));

        // Recursively add combinations with operators
        for (final MathOperation operator : operations) {
            current.add(operator.symbol()); // Add operator
            generate(numbers, index + 1, current, result, operations); // Move to the next number
            current.remove(current.size() - 1); // Remove operator (backtrack)
        }

        current.remove(current.size() - 1); // Remove number (backtrack)
    }

//    public static List<List<String>> generateCombinations(List<String> numbers, Set<String> operators) {
//        List<List<String>> result = new ArrayList<>();
//        if (numbers == null || numbers.isEmpty() || operators == null || operators.isEmpty()) {
//            return result;
//        }
//
//        List<String> operatorList = new ArrayList<>(operators); // Convert Set to List for faster access
//        int operatorCount = operatorList.size();
//        int numberCount = numbers.size();
//        int totalCombinations = (int) Math.pow(operatorCount, numberCount - 1);
//
//        for (int i = 0; i < totalCombinations; i++) {
//            List<String> combination = new ArrayList<>(2 * numberCount - 1); // Preallocate size
//            combination.add(numbers.get(0)); // Add the first number
//
//            int tempIndex = i;
//            for (int j = 1; j < numberCount; j++) {
//                // Determine operator based on current combination index
//                combination.add(operatorList.get(tempIndex % operatorCount));
//                tempIndex /= operatorCount;
//
//                // Add the next number
//                combination.add(numbers.get(j));
//            }
//
//            result.add(combination);
//        }
//
//        return result;
//    }
//    public static List<List<String>> generateCombinations(List<String> numbers, Set<String> operators) {
//        List<List<String>> result = new ArrayList<>();
//        if (numbers == null || numbers.isEmpty() || operators == null || operators.isEmpty()) {
//            return result;
//        }
//
//        int n = numbers.size();
//        int combinations = (int) Math.pow(operators.size(), n - 1); // Total combinations of operators
//
//        for (int i = 0; i < combinations; i++) {
//            List<String> combination = new ArrayList<>();
//            combination.add(numbers.get(0)); // Always start with the first number
//
//            int operatorIndex = i;
//            for (int j = 1; j < n; j++) {
//                // Pick the operator corresponding to the current combination
//                combination.add(getOperatorByIndex(operators, operatorIndex % operators.size()));
//                operatorIndex /= operators.size();
//
//                // Add the next number
//                combination.add(numbers.get(j));
//            }
//
//            result.add(combination);
//        }
//
//        return result;
//    }
//
//    private static @Nullable String getOperatorByIndex(Set<String> operators, int index) {
//        int currentIndex = 0;
//        for (String operator : operators) {
//            if (currentIndex == index) {
//                return operator;
//            }
//            currentIndex++;
//        }
//        return null; // Should not happen if index is valid
//    }
}
