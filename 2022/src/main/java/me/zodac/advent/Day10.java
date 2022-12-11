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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import me.zodac.advent.pojo.AssemblyInstruction;
import me.zodac.advent.pojo.SegmentedDisplay;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Solution for 2022, Day 10.
 *
 * @see <a href="https://adventofcode.com/2022/day/10">AoC 2022, Day 10</a>
 */
public final class Day10 {

    private static final int START_CYCLE_FOR_SIGNAL_CHECK = 20;
    private static final int CYCLE_INTERVAL_FOR_SIGNAL_CHECK = 40;
    private static final Set<String> NOOP_INSTRUCTIONS = Set.of("noop");

    private Day10() {

    }

    /**
     * Part 2.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static Pair<Long, String> solve(final Collection<AssemblyInstruction> values) {
        int xValue = 1;
        long totalAtSpecificCyles = 0;

        int i = 0;
        int cycle = 1;

        final SegmentedDisplay segmentedDisplay = SegmentedDisplay.create(8);
        final int segmentedDisplayLength = segmentedDisplay.length();

        final Map<Integer, Integer> offsetByTargetCycle = new HashMap<>();

        while (i < values.size()) {
            final AssemblyInstruction assemblyInstruction = new ArrayList<>(values).get(i);

            if (isCycleToConsiderSignal(cycle)) {
                totalAtSpecificCyles += ((long) xValue * cycle);
            }

            final int row = (cycle - 1) / segmentedDisplayLength;
            final int col = (cycle - 1) % segmentedDisplayLength;

            if (xValue == col || xValue == (col - 1) || xValue == (col + 1)) {
                segmentedDisplay.turnOn(row, col);
            }

            if (offsetByTargetCycle.containsKey(cycle)) {
                xValue += offsetByTargetCycle.get(cycle);
                i++;
            } else {
                if (NOOP_INSTRUCTIONS.contains(assemblyInstruction.instruction())) {
                    i++;
                } else {
                    offsetByTargetCycle.put(cycle + 1, assemblyInstruction.offset());
                }
            }

            cycle++;
        }

        final String res = segmentedDisplay.getLetters();

        return Pair.of(totalAtSpecificCyles, res);
    }

    // We check at cycle #20, and every subseqent 40 cycles
    private static boolean isCycleToConsiderSignal(final int cycle) {
        return cycle % CYCLE_INTERVAL_FOR_SIGNAL_CHECK == START_CYCLE_FOR_SIGNAL_CHECK;
    }
}
