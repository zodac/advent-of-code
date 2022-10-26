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

package me.zodac.advent.pojo.grid;

import me.zodac.advent.pojo.Line;

/**
 * Class defining a coordinate grid of points, which can have lines drawn on it.
 *
 * @param grid the coordinate grid as a 2D array of {@code int}s
 */
public record IntegerGrid(int[][] grid) {

    private static final int MINIMUM_NUMBER_SIGNIFYING_OVERLAP = 2;

    /**
     * Creates a {@link IntegerGrid} with the dimensions {@code gridSize}x{@code gridSize}.
     *
     * @param gridSize the length and width of the {@link IntegerGrid}
     * @return the created {@link IntegerGrid}
     * @throws IllegalArgumentException thrown if input size is less than 0
     */
    public static IntegerGrid ofSize(final int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("Size must be positive integer, found: " + gridSize);
        }

        return new IntegerGrid(new int[gridSize][gridSize]);
    }

    /**
     * Draws a box on the {@link IntegerGrid}, where each {@link me.zodac.advent.pojo.Point} is updated based according to the
     * {@link GridInstruction}. All values inside the box are also updated.
     *
     * <pre>
     * | {@link GridInstruction}        | Action                                       |
     * |------------------------|----------------------------------------------|
     * | {@link GridInstruction#ON}     | Increment the point by <b>1</b>                     |
     * | {@link GridInstruction#OFF}    | Decrement the point by <b>1</b>, to a minimum of <b>0</b>  |
     * | {@link GridInstruction#TOGGLE} | Increment the point by <b>2</b>                     |
     * </pre>
     *
     * <p>
     * For example, starting with a 10x10 grid:
     * <pre>
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     * </pre>
     *
     * <p>
     * Given the {@link me.zodac.advent.pojo.Point}s (0, 0) and (2, 4) with the instruction {@link GridInstruction#ON}, the updated
     * {@link IntegerGrid} would be:
     * <pre>
     *     1 1 1 1 1 0 0 0 0 0
     *     1 1 1 1 1 0 0 0 0 0
     *     1 1 1 1 1 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     * </pre>
     *
     * <p>
     * Following up with {@link me.zodac.advent.pojo.Point}s (0, 1) and (0, 9) with the instruction {@link GridInstruction#TOGGLE} would give us:
     * <pre>
     *     1 3 3 3 3 2 2 2 2 2
     *     1 1 1 1 1 0 0 0 0 0
     *     1 1 1 1 1 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     * </pre>
     *
     * <p>
     * And finally with {@link me.zodac.advent.pojo.Point}s (0, 2) and (3, 3) with the instruction {@link GridInstruction#OFF} we would get:
     * <pre>
     *     1 3 2 2 3 2 2 2 2 2
     *     1 1 0 0 1 0 0 0 0 0
     *     1 1 0 0 1 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     *     0 0 0 0 0 0 0 0 0 0
     * </pre>
     *
     * @param x1              the first x coordinate
     * @param y1              the first y coordinate
     * @param x2              the second x coordinate
     * @param y2              the second y coordinate
     * @param gridInstruction the {@link GridInstruction}
     */
    public void drawBox(final int x1, final int y1, final int x2, final int y2, final GridInstruction gridInstruction) {
        if (x1 < 0 || y1 < 0) {
            throw new IllegalArgumentException(String.format("x1, y1 must be at least 0, found: (%s, %s)", x1, y1));
        }

        if (x2 > grid.length || y2 > grid.length) {
            throw new IllegalArgumentException(String.format("x2, y2 must be at less than %s, found: (%s, %s)", grid.length, x2, y2));
        }

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                updateGrid(gridInstruction, x, y);
            }
        }
    }

    private void updateGrid(final GridInstruction gridInstruction, final int x, final int y) {
        switch (gridInstruction) {
            case ON -> grid[x][y] = grid[x][y] + 1;
            case OFF -> grid[x][y] = Math.max(0, grid[x][y] - 1); // Value should not be less than 0
            case TOGGLE -> grid[x][y] = grid[x][y] + 2;
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
    }

    /**
     * Sums the values of all {@link me.zodac.advent.pojo.Point}s in the {@link IntegerGrid}.
     *
     * @return the sum of all values in the {@link IntegerGrid}
     */
    public long sumValues() {
        int count = 0;

        final int gridLength = grid[0].length;
        for (final int[] rows : grid) {
            for (int column = 0; column < gridLength; column++) {
                count += rows[column];
            }
        }

        return count;
    }

    /**
     * Adds a {@link Line} to the {@link IntegerGrid}.
     *
     * <p>
     * Will only allow diagonal lines if the {@code allowedLineType} is {@link AllowedLineType#ALL_LINES}.
     *
     * @param line            the {@link Line} to add to the {@link IntegerGrid}
     * @param allowedLineType the types of lines allowed to be drawn on the {@link IntegerGrid}
     */
    public void addLine(final Line line, final AllowedLineType allowedLineType) {
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
     * Returns the number of points on the {@link IntegerGrid} that have had an overlap of lines added by {@link #addLine(Line, AllowedLineType)}.
     *
     * <p>
     * As a {@link Line} is drawn, each point has a counter increments if a {@link Line} passes through it. If that value is over
     * {@value #MINIMUM_NUMBER_SIGNIFYING_OVERLAP}, the point has an overlap.
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
