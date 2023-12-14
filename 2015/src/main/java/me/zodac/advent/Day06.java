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

import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.BooleanGrid;
import me.zodac.advent.pojo.grid.GridInstruction;
import me.zodac.advent.pojo.grid.IntegerGrid;
import me.zodac.advent.pojo.tuple.Triple;

/**
 * Solution for 2015, Day 6.
 *
 * @see <a href="https://adventofcode.com/2015/day/6">AoC 2015, Day 6</a>
 */
public final class Day06 {

    private static final int GRID_SIZE = 1_000;

    private Day06() {

    }

    /**
     * Creates a {@link BooleanGrid} and sets {@link Point} based on the input {@link GridInstruction}.
     *
     * @param instructionsAndPoints the {@link Point}s defining a box on the {@link BooleanGrid}, with the {@link GridInstruction}
     * @return the number of {@link BooleanGrid} {@link Point}s that have been turned on
     */
    public static long countSwitchedOnLights(final Iterable<Triple<GridInstruction, Point, Point>> instructionsAndPoints) {
        final BooleanGrid booleanGrid = BooleanGrid.ofSize(GRID_SIZE);

        for (final Triple<GridInstruction, Point, Point> instructionAndPoints : instructionsAndPoints) {
            final Point first = instructionAndPoints.second();
            final Point second = instructionAndPoints.third();

            booleanGrid.drawBox(first.x(), first.y(), second.x(), second.y(), instructionAndPoints.first());
        }

        return booleanGrid.sumValues(BooleanGrid.EVALUATOR);
    }

    /**
     * Creates an {@link IntegerGrid} and sets {@link Point} based on the input {@link GridInstruction}.
     *
     * @param instructionsAndPoints the {@link Point}s defining a box on the {@link IntegerGrid}, with the {@link GridInstruction}
     * @return the total value of all {@link IntegerGrid} {@link Point}s
     */
    public static long calculateBrightness(final Iterable<Triple<GridInstruction, Point, Point>> instructionsAndPoints) {
        final IntegerGrid integerGrid = IntegerGrid.ofSize(GRID_SIZE);

        for (final Triple<GridInstruction, Point, Point> instructionAndPoints : instructionsAndPoints) {
            final Point first = instructionAndPoints.second();
            final Point second = instructionAndPoints.third();

            integerGrid.drawBox(first.x(), first.y(), second.x(), second.y(), instructionAndPoints.first());
        }

        return integerGrid.sumValues(IntegerGrid.EVALUATOR);
    }
}
