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

import me.zodac.advent.pojo.Line;
import me.zodac.advent.pojo.grid.AllowedLineType;
import me.zodac.advent.pojo.grid.IntegerGrid;

/**
 * Solution for 2021, Day 5.
 *
 * @see <a href="https://adventofcode.com/2021/day/5">AoC 2021, Day 5</a>
 */
public final class Day05 {

    private Day05() {

    }

    /**
     * Draws only the horizontal and vertical {@link Line}s in the supplied {@code coordinateLines} onto a {@link IntegerGrid} and counts the
     * number of points that have overlapping {@link Line}s.
     *
     * @param coordinateLines the {@link Line}s to be drawn on the {@link IntegerGrid}
     * @return the number of overlapping points
     * @see Line#isHorizontal()
     * @see Line#isVertical()
     */
    public static int addHorizontalAndVerticalLinesAndReturnOverlap(final Iterable<Line> coordinateLines) {
        final int maxGridSize = getMaxGridSize(coordinateLines);
        final IntegerGrid integerGrid = IntegerGrid.ofSize(maxGridSize);

        for (final Line coordinateLine : coordinateLines) {
            integerGrid.addLine(coordinateLine, AllowedLineType.NO_DIAGONAL_LINES);
        }

        return integerGrid.numberOfOverlaps();
    }

    /**
     * Draws the horizontal, vertical and 'perfect' diagonal {@link Line}s in the supplied {@code coordinateLines} onto a {@link IntegerGrid} and
     * counts the number of points that have overlapping {@link Line}s.
     *
     * @param coordinateLines the {@link Line}s to be drawn on the {@link IntegerGrid}
     * @return the number of overlapping points
     * @see Line#isHorizontal()
     * @see Line#isVertical()
     * @see Line#isPerfectDiagonal()
     */
    public static int addAllLinesAndReturnOverlap(final Iterable<Line> coordinateLines) {
        final int maxGridSize = getMaxGridSize(coordinateLines);
        final IntegerGrid integerGrid = IntegerGrid.ofSize(maxGridSize);

        for (final Line coordinateLine : coordinateLines) {
            integerGrid.addLine(coordinateLine, AllowedLineType.ALL_LINES);
        }

        return integerGrid.numberOfOverlaps();
    }

    private static int getMaxGridSize(final Iterable<Line> coordinateLines) {
        int max = 0;
        for (final Line coordinateLine : coordinateLines) {
            if (coordinateLine.maxCoordinateValue() > max) {
                max = coordinateLine.maxCoordinateValue();
            }
        }

        // Coordinates (0,0) would need a grid of size 1, not 0, so make sure to add 1
        return max + 1;
    }
}
