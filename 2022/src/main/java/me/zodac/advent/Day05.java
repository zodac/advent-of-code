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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import me.zodac.advent.pojo.StackInstruction;

/**
 * Solution for 2022, Day 5.
 *
 * @see <a href="https://adventofcode.com/2022/day/5">AoC 2022, Day 5</a>
 */
public final class Day05 {

    private Day05() {

    }

    /**
     * Moves elements between stacks according to the provided {@link StackInstruction}s. The stacks are provided as a {@link Map} where the key is
     * the ID of the stack. When multiple elements are moved, they are moved one-by-one in standard 'Last-In, First-Out' order. For example, given the
     * stacks {@code 1, 2 and 3}:
     *
     * <pre>
     *     1    2    3
     *    [A]  [B]  [C]
     *    [D]  [G]  [F]
     *    [E]       [H]
     *              [I]
     * </pre>
     *
     * <p>
     * Given the {@link StackInstruction} to move <b>3</b> elements from {@code 3} to {@code 1}, our result is:
     * <pre>
     *     1    2    3
     *    [A]  [B]  [C]
     *    [D]  [G]
     *    [E]
     *    [I]
     *    [H]
     *    [F]
     * </pre>
     *
     * <p>
     * When all {@link StackInstruction}s have been applied, we take the top {@link String} from each stack (in ascending order of IDs), then combine
     * them into a new {@link String} code which is returned.
     *
     * @param stacksById        the {@link Deque} stacked, keyed by ID
     * @param stackInstructions the {@link StackInstruction}s to be applied
     * @return the final constructed {@link String} code
     */
    public static String moveElementsBetweenStacksLastInFirstOutOrderAndCreateCode(final Map<Integer, ? extends Deque<String>> stacksById,
                                                                                   final Iterable<StackInstruction> stackInstructions) {
        return moveElementsBetweenStacks(stacksById, stackInstructions, false);
    }

    /**
     * Moves elements between stacks according to the provided {@link StackInstruction}s. The stacks are provided as a {@link Map} where the key is
     * the ID of the stack. When multiple elements are moved, they are moved as a whole, retaining their order from their original stack. For example,
     * given the stacks {@code 1, 2 and 3}:
     *
     * <pre>
     *     1    2    3
     *    [A]  [B]  [C]
     *    [D]  [G]  [F]
     *    [E]       [H]
     *              [I]
     * </pre>
     *
     * <p>
     * Given the {@link StackInstruction} to move <b>3</b> elements from {@code 3} to {@code 1}, our result is:
     * <pre>
     *     1    2    3
     *    [A]  [B]  [C]
     *    [D]  [G]
     *    [E]
     *    [F]
     *    [H]
     *    [I]
     * </pre>
     *
     * <p>
     * When all {@link StackInstruction}s have been applied, we take the top {@link String} from each stack (in ascending order of IDs), then combine
     * them into a new {@link String} code which is returned.
     *
     * @param stacksById        the {@link Deque} stacked, keyed by ID
     * @param stackInstructions the {@link StackInstruction}s to be applied
     * @return the final constructed {@link String} code
     */
    public static String moveElementsBetweenStacksRetainingOrderAndCreateCode(final Map<Integer, ? extends Deque<String>> stacksById,
                                                                              final Iterable<StackInstruction> stackInstructions) {
        return moveElementsBetweenStacks(stacksById, stackInstructions, true);
    }

    private static String moveElementsBetweenStacks(final Map<Integer, ? extends Deque<String>> stacksById,
                                                    final Iterable<StackInstruction> stackInstructions,
                                                    final boolean retainOrderOfMultipleElements
    ) {
        for (final StackInstruction stackInstruction : stackInstructions) {
            final Deque<String> src = stacksById.get(stackInstruction.sourceStackId());
            final Deque<String> des = stacksById.get(stackInstruction.destinationStackId());
            final int numberOfElementsToMove = stackInstruction.numberOfElementsToMove();

            if (retainOrderOfMultipleElements) {
                final Deque<String> buffer = new ArrayDeque<>();
                for (int i = 0; i < numberOfElementsToMove; i++) {
                    buffer.push(src.pop());
                }

                for (int i = 0; i < numberOfElementsToMove; i++) {
                    des.push(buffer.pop());
                }
            } else {
                for (int i = 0; i < numberOfElementsToMove; i++) {
                    des.push(src.pop());
                }
            }
        }

        return buildStringFromFirstElements(stacksById);
    }

    private static String buildStringFromFirstElements(final Map<Integer, ? extends Deque<String>> stacksById) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= stacksById.size(); i++) {
            final Deque<String> stack = stacksById.get(i);
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();
    }
}
