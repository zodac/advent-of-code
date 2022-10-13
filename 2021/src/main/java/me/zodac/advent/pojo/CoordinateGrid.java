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

package me.zodac.advent.pojo;

/**
 * Class defining a coordinate grid of points, which can have lines drawn on it.
 *
 * @param grid            the coordinate grid as a 2D array of {@code ints}
 * @param allowedLineType the types of lines allowed to be drawn on the {@link CoordinateGrid}
 */
public record CoordinateGrid(int[][] grid, AllowedLineType allowedLineType) {

    /**
     * Simple enum defining whether the {@link CoordinateGrid} supports diagonal lines or not.
     */
    public enum AllowedLineType {

        /**
         * All lines (horizontal, vertical and diagonal) are supported.
         */
        ALL_LINES,

        /**
         * Only horizontal and vertical lines as supported.
         */
        NO_DIAGONAL_LINES
    }

    private static final int MINIMUM_NUMBER_SIGNIFYING_OVERLAP = 2;

    /**
     * Creates a {@link CoordinateGrid} with the dimensions {@code gridSize}x{@code gridSize}.
     *
     * @param gridSize        the length and width of the {@link CoordinateGrid}
     * @param allowedLineType the types of lines allowed to be drawn on the {@link CoordinateGrid}
     * @return the created {@link CoordinateGrid}
     */
    public static CoordinateGrid ofSize(final int gridSize, final AllowedLineType allowedLineType) {
        return new CoordinateGrid(new int[gridSize][gridSize], allowedLineType);
    }

    /**
     * Adds a {@link Line} to the {@link CoordinateGrid}.
     *
     * <p>
     * Will only allow diagonal lines if the {@code allowedLineType} is {@link AllowedLineType#ALL_LINES}.
     *
     * @param line the {@link Line} to add to the {@link CoordinateGrid}
     */
    public void addLine(final Line line) {
        if (line.isHorizontal()) {
            addHorizontalLine(line);
        } else if (line.isVertical()) {
            addVerticalLine(line);
        } else if (line.isPerfectDiagonal() && allowedLineType == AllowedLineType.ALL_LINES) {
            addDiagonalLine(line);
        }
    }

    private void addHorizontalLine(final Line line) {
        final int start = Math.min(line.first().y(), line.second().y());
        final int end = Math.max(line.first().y(), line.second().y());

        for (int i = start; i <= end; i++) {
            grid[i][line.first().x()] = grid[i][line.first().x()] + 1;
        }
    }

    private void addVerticalLine(final Line line) {
        final int start = Math.min(line.first().x(), line.second().x());
        final int end = Math.max(line.first().x(), line.second().x());

        for (int i = start; i <= end; i++) {
            grid[line.first().y()][i] = grid[line.first().y()][i] + 1;
        }
    }

    private void addDiagonalLine(final Line line) {
        final int incrementForX = diagonalIncrement(line.second().x() - line.first().x());
        final int incrementForY = diagonalIncrement(line.second().y() - line.first().y());

        final int x2 = line.second().x();
        final int y2 = line.second().y();

        int currX = line.first().x();
        int currY = line.first().y();

        while (currX != x2 && currY != y2) {
            grid[currY][currX] = grid[currY][currX] + 1;

            currX += incrementForX;
            currY += incrementForY;
        }

        // Get the end coordinate, as it is missed in the while loop
        grid[line.second().y()][line.second().x()] = grid[line.second().y()][line.second().x()] + 1;
    }

    private static int diagonalIncrement(final int i) {
        return Integer.compare(i, 0);
    }

    /**
     * Returns the number of points on the {@link CoordinateGrid} that have had an overlap of lines added by {@link #addLine(Line)}.
     *
     * <p>
     * As a {@link Line} is drawn, each point has a counter increments if a {@link Line} passes through it. If that value is over
     * {@link #MINIMUM_NUMBER_SIGNIFYING_OVERLAP}, the point has an overlap.
     *
     * @return the number of points with overlapping {@link Line}s
     */
    public int numberOfOverlaps() {
        final int gridLength = grid[0].length;
        int count = 0;

        for (final int[] rows : grid) {
            for (int column = 0; column < gridLength; column++) {
                if (rows[column] >= MINIMUM_NUMBER_SIGNIFYING_OVERLAP) {
                    count++;
                }
            }
        }
        return count;
    }
}
