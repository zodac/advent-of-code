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
