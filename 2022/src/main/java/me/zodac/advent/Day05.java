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

import java.util.Collection;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.Range;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Solution for 2022, Day 5.
 *
 * @see <a href="https://adventofcode.com/2022/day/5">AoC 2022, Day 5</a>
 */
public final class Day05 {

    private Day05() {

    }

    private static final Pattern PATTERN =Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public static String part1(final Map<Integer, Stack<String>> stackById, final Collection<String> instructions) {
        for(final String instruction : instructions){
            final Matcher matcher = PATTERN.matcher(instruction);

            if (!matcher.find()) {
                throw new IllegalStateException("Unable to find match in input: " + instruction);
            }

            final Stack<String> src = stackById.get(Integer.parseInt(matcher.group(2)));
            final Stack<String> des = stackById.get(Integer.parseInt(matcher.group(3)));
            final int amount = Integer.parseInt(matcher.group(1));

            for(int i = 0; i < amount; i++){
                des.push(src.pop());
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for(int i = 1; i <= stackById.size(); i++){
            stringBuilder.append(stackById.get(i).pop());
        }

        return stringBuilder.toString();
    }

    public static String part2(final Map<Integer, Stack<String>> stackById, final Collection<String> instructions) {
        for(final String instruction : instructions){
            final Matcher matcher = PATTERN.matcher(instruction);

            if (!matcher.find()) {
                throw new IllegalStateException("Unable to find match in input: " + instruction);
            }

            final Stack<String> src = stackById.get(Integer.parseInt(matcher.group(2)));
            final Stack<String> des = stackById.get(Integer.parseInt(matcher.group(3)));
            final int amount = Integer.parseInt(matcher.group(1));

            final Stack<String> buffer = new Stack<>();

            for(int i = 0; i < amount; i++){
                buffer.push(src.pop());
            }

            for(int i = 0; i < amount; i++){
                des.push(buffer.pop());
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for(int i = 1; i <= stackById.size(); i++){
            stringBuilder.append(stackById.get(i).pop());
        }

        return stringBuilder.toString();
    }
}
