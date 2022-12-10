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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Solution for 2022, Day 10.
 *
 * @see <a href="https://adventofcode.com/2022/day/10">AoC 2022, Day 10</a>
 */
public final class Day10 {

    private Day10() {

    }

    /**
     * Part 2.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static long part2(final Collection<String> values) {
        int xValue = 1;
        long totalAtSpecificCyles = 0;

        int i = 0;
        int cycle = 1;

        final char[][] output = new char[6][40];

        Map<Integer, String> offsetByTargetCycle = new HashMap<>();

        while (i < values.size()) {
            String command = new ArrayList<>(values).get(i);
            if (Set.of(20, 60, 100, 140, 180, 220).contains(cycle)) {
                totalAtSpecificCyles += ((long) xValue * cycle);
            }

            final int row = (cycle - 1) / 40;
            final int col = (cycle - 1) % 40;


            if (xValue == col || xValue == (col - 1) || xValue == (col + 1)) {
                output[row][col] = '#';
            } else {
                output[row][col] = '.';
            }

            if (offsetByTargetCycle.containsKey(cycle)) {
                String s = offsetByTargetCycle.get(cycle);
                xValue += Integer.parseInt(s.split("\\s+")[1]);
                i++;
            } else {
                if (command.equalsIgnoreCase("noop")) {
                    i++;
                } else {
                    offsetByTargetCycle.put(cycle + 1, command);
                }
            }

            cycle++;
        }

        for (final char[] o : output) {
            for (final char c : o) {
                System.out.print(c + " ");
            }
            System.out.println();
        }


        return totalAtSpecificCyles;
    }
}
