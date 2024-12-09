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

package me.zodac.advent;

import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.MathOperation;
import me.zodac.advent.util.PermutationUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2024, Day 7.
 *
 * @see <a href="https://adventofcode.com/2024/day/7">[2024: 07] Bridge Repair</a>
 */
public final class Day07 {

    private Day07() {

    }

    // TODO
    /**
     * Part 1.
     *
     * @param mathOperations the operations
     * @param values the input values
     * @return the part 1 result
     */
    public static long sumValidResults(final List<String> values, final Set<MathOperation> mathOperations) {
        long total = 0L;

        for (final String value : values) {
            final List<Long> nums = StringUtils.collectNumbersInOrder(value);
            final long result = nums.getFirst();
            final List<String> inputs = nums.subList(1, nums.size()).stream().map(String::valueOf).toList();

            final List<List<String>> allOptions = PermutationUtils.generateWithSeparators(inputs, mathOperations);

            for (final List<String> anOption : allOptions) {
                final long evaluatedResult = evaluate(anOption);
                if (evaluatedResult == result) {
                    total += result;
                    break;
                }
            }
        }
        return total;
    }

    private static long evaluate(final List<String> input) {
        long currentTotal = Long.parseLong(input.getFirst());

        MathOperation mathOperation = MathOperation.ADD;
        for (int i = 1; i < input.size(); i++) {
            final String nextSymbol = input.get(i);

            switch (nextSymbol) {
                case "+" -> mathOperation = MathOperation.ADD;
                case "*" -> mathOperation = MathOperation.MULTIPLY;
                case "||" -> mathOperation = MathOperation.CONCATENATE;
                default -> {
                    final long nextValue = Long.parseLong(nextSymbol);
                    currentTotal = mathOperation.apply(currentTotal, nextValue);
                }
            }
        }

        return currentTotal;
    }
}
