/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zodac.advent;

import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.Triple;
import me.zodac.advent.pojo.grid.BooleanGrid;
import me.zodac.advent.pojo.grid.GridInstruction;
import me.zodac.advent.pojo.grid.IntegerGrid;

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

        return booleanGrid.countSwitchedOn();
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

        return integerGrid.sumValues();
    }
}
