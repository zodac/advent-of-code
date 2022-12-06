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

package me.zodac.advent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.StackInstruction;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.ArrayUtils;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day05}.
 */
class Day05Test {

    private static final String INPUT_FILENAME = "day05.txt";

    @Test
    void part1() {
        final Pair<List<String>, List<String>> values = FileUtils.readLinesAndSplit(INPUT_FILENAME, String::isEmpty);
        final Map<Integer, Deque<String>> stacksById = getStacksById(values);
        final List<StackInstruction> stackInstructions = getStackInstructions(values);

        final String result = Day05.moveElementsBetweenStacksLastInFirstOutOrderAndCreateCode(stacksById, stackInstructions);
        assertThat(result)
            .isEqualTo("RFFFWBPNS");
    }

    @Test
    void part2() {
        final Pair<List<String>, List<String>> values = FileUtils.readLinesAndSplit(INPUT_FILENAME, String::isEmpty);
        final Map<Integer, Deque<String>> stacksById = getStacksById(values);
        final List<StackInstruction> stackInstructions = getStackInstructions(values);

        final String result = Day05.moveElementsBetweenStacksRetainingOrderAndCreateCode(stacksById, stackInstructions);
        assertThat(result)
            .isEqualTo("CQQBBJFCS");
    }

    private static Map<Integer, Deque<String>> getStacksById(final Pair<? extends List<String>, List<String>> values) {
        final List<String> input = values.first();
        final char[][] arrayOfCharArrays = ArrayUtils.convertToArrayOfCharArrays(input);
        final char[][] reversedArrayOfCharArrays = ArrayUtils.reverseRows(arrayOfCharArrays);
        final char[][] transposedArrayOfCharArrays = ArrayUtils.transpose(reversedArrayOfCharArrays);

        // Filter and add to Map, keyed by ID
        final Map<Integer, Deque<String>> stacksById = new HashMap<>();
        for (final char[] transposedCharArray : transposedArrayOfCharArrays) {
            final char firstChar = transposedCharArray[0];

            if (Character.isDigit(firstChar)) {
                final Deque<String> stack = createStack(transposedCharArray);
                stacksById.put(Character.getNumericValue(firstChar), stack);
            }
        }
        return stacksById;
    }

    private static Deque<String> createStack(final char[] transposedCharArray) {
        final Deque<String> stack = new ArrayDeque<>();

        // Skip first element as that is the stack ID
        for (int i = 1; i < transposedCharArray.length; i++) {
            final char c = transposedCharArray[i];
            if (Character.isAlphabetic(c)) {
                stack.push(String.valueOf(c));
            }
        }
        return stack;
    }

    private static List<StackInstruction> getStackInstructions(final Pair<List<String>, ? extends List<String>> values) {
        return values
            .second()
            .stream()
            .map(StackInstruction::parse)
            .toList();
    }
}
