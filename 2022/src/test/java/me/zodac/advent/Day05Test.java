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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.Range;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day05}.
 */
class Day05Test {

    private static final String INPUT_FILENAME = "day05.txt";
    private static final Pattern INPUT_PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    @Test
    void part1() {
        final List<String> values = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .filter(s -> s.startsWith("move"))
            .toList();

        final Stack<String> stack1 = new Stack<>();
        stack1.push("H");
        stack1.push("T");
        stack1.push("Z");
        stack1.push("D");

        final Stack<String> stack2 = new Stack<>();
        stack2.push("Q");
        stack2.push("R");
        stack2.push("W");
        stack2.push("T");
        stack2.push("G");
        stack2.push("C");
        stack2.push("S");

        final Stack<String> stack3 = new Stack<>();
        stack3.push("P");
        stack3.push("B");
        stack3.push("F");
        stack3.push("Q");
        stack3.push("N");
        stack3.push("R");
        stack3.push("C");
        stack3.push("H");

        final Stack<String> stack4 = new Stack<>();
        stack4.push("L");
        stack4.push("C");
        stack4.push("N");
        stack4.push("F");
        stack4.push("H");
        stack4.push("Z");

        final Stack<String> stack5 = new Stack<>();
        stack5.push("G");
        stack5.push("L");
        stack5.push("F");
        stack5.push("Q");
        stack5.push("S");

        final Stack<String> stack6 = new Stack<>();
        stack6.push("V");
        stack6.push("P");
        stack6.push("W");
        stack6.push("Z");
        stack6.push("B");
        stack6.push("R");
        stack6.push("C");
        stack6.push("S");

        final Stack<String> stack7 = new Stack<>();
        stack7.push("Z");
        stack7.push("F");
        stack7.push("J");

        final Stack<String> stack8 = new Stack<>();
        stack8.push("D");
        stack8.push("L");
        stack8.push("V");
        stack8.push("Z");
        stack8.push("R");
        stack8.push("H");
        stack8.push("Q");

        final Stack<String> stack9 = new Stack<>();
        stack9.push("B");
        stack9.push("H");
        stack9.push("G");
        stack9.push("N");
        stack9.push("F");
        stack9.push("Z");
        stack9.push("L");
        stack9.push("D");

        final Map<Integer, Stack<String>> stacksById = Map.of(
            1, stack1,
            2, stack2,
            3, stack3,
            4, stack4,
            5, stack5,
            6, stack6,
            7, stack7,
            8, stack8,
            9, stack9
        );

        final String result = Day05.part1(stacksById, values);
        assertThat(result)
            .isEqualTo("RFFFWBPNS");
    }

    @Test
    void part2() {
        final List<String> values = FileUtils.readLines(INPUT_FILENAME)
            .stream()
            .filter(s -> s.startsWith("move"))
            .toList();

        final Stack<String> stack1 = new Stack<>();
        stack1.push("H");
        stack1.push("T");
        stack1.push("Z");
        stack1.push("D");

        final Stack<String> stack2 = new Stack<>();
        stack2.push("Q");
        stack2.push("R");
        stack2.push("W");
        stack2.push("T");
        stack2.push("G");
        stack2.push("C");
        stack2.push("S");

        final Stack<String> stack3 = new Stack<>();
        stack3.push("P");
        stack3.push("B");
        stack3.push("F");
        stack3.push("Q");
        stack3.push("N");
        stack3.push("R");
        stack3.push("C");
        stack3.push("H");

        final Stack<String> stack4 = new Stack<>();
        stack4.push("L");
        stack4.push("C");
        stack4.push("N");
        stack4.push("F");
        stack4.push("H");
        stack4.push("Z");

        final Stack<String> stack5 = new Stack<>();
        stack5.push("G");
        stack5.push("L");
        stack5.push("F");
        stack5.push("Q");
        stack5.push("S");

        final Stack<String> stack6 = new Stack<>();
        stack6.push("V");
        stack6.push("P");
        stack6.push("W");
        stack6.push("Z");
        stack6.push("B");
        stack6.push("R");
        stack6.push("C");
        stack6.push("S");

        final Stack<String> stack7 = new Stack<>();
        stack7.push("Z");
        stack7.push("F");
        stack7.push("J");

        final Stack<String> stack8 = new Stack<>();
        stack8.push("D");
        stack8.push("L");
        stack8.push("V");
        stack8.push("Z");
        stack8.push("R");
        stack8.push("H");
        stack8.push("Q");

        final Stack<String> stack9 = new Stack<>();
        stack9.push("B");
        stack9.push("H");
        stack9.push("G");
        stack9.push("N");
        stack9.push("F");
        stack9.push("Z");
        stack9.push("L");
        stack9.push("D");

        final Map<Integer, Stack<String>> stacksById = Map.of(
            1, stack1,
            2, stack2,
            3, stack3,
            4, stack4,
            5, stack5,
            6, stack6,
            7, stack7,
            8, stack8,
            9, stack9
        );

        final String result = Day05.part2(stacksById, values);
        assertThat(result)
            .isEqualTo("CQQBBJFCS");
    }
}
