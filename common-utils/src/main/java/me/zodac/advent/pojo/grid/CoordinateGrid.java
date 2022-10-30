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
import me.zodac.advent.pojo.Point;

/**
 * Abstract class defining a coordinate grid of {@link Point}s.
 *
 * @param <E> the type of the {@link Point}s on the {@link CoordinateGrid}
 */
abstract class CoordinateGrid<E> {

    /**
     * The length (or width) of the {@link CoordinateGrid}.
     */
    protected final int gridSize;

    /**
     * The values of the {@link CoordinateGrid}.
     */
    protected final E[][] grid;

    /**
     * The minimum value required to be considered an overlap.
     */
    protected final int valueSignifyingOverlap;

    /**
     * Default constructor, to be called by subclasses.
     *
     * @param gridSize               the size of the {@link CoordinateGrid}.
     * @param grid                   the actual {@link CoordinateGrid} represented as a 2D array.
     * @param initialValue           the initial value for each {@link Point} in the {@link CoordinateGrid}
     * @param valueSignifyingOverlap when counting how many overlaps has occurred, this is the minimum value required to be considered an overlap
     */
    CoordinateGrid(final int gridSize, final E[][] grid, final E initialValue, final int valueSignifyingOverlap) {
        this.gridSize = gridSize;
        this.grid = grid.clone();
        this.valueSignifyingOverlap = valueSignifyingOverlap;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                grid[row][column] = initialValue;
            }
        }
    }

    /**
     * Count any {@link Point}s in the {@link CoordinateGrid}. The actual value of each {@link Point} is defined by {@link #valueOf(int, int)}.
     *
     * @return the sum of all {@link Point}s in the {@link CoordinateGrid}
     * @see #valueOf(int, int)
     */
    public long sumValues() {
        int count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                count += valueOf(row, column);
            }
        }

        return count;
    }

    /**
     * Returns the number of points on the {@link CoordinateGrid} that have {@link Point}s that have been set more than a minimum
     * amount {@code valueSignifyingOverlap}.
     *
     * <p>
     * As a {@link Line} is drawn or {@link Point}s are set, each {@link Point} has its value updated. If
     * that value is over {@code valueSignifyingOverlap}, the {@link Point} has an overlap.
     *
     * @return the number of {@link Point} with overlaps
     */
    public int numberOfOverlaps() {
        int count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                if (valueOf(row, column) >= valueSignifyingOverlap) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Draws a box on the {@link CoordinateGrid}, where each {@link Point} is updated based according to the
     * {@link GridInstruction}. All values inside the box are also updated.
     *
     * <p>
     * For example, starting with a 10x10 grid where the values are {@link Integer}s:
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
     * Given the {@link Point}s (0, 0) and (2, 4) with the instruction {@link GridInstruction#ON}, the updated
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
     * Following up with {@link Point}s (0, 1) and (0, 9) with the instruction {@link GridInstruction#TOGGLE} would give us:
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
     * And finally with {@link Point}s (0, 2) and (3, 3) with the instruction {@link GridInstruction#OFF} we would get:
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
     * @see #updateGrid(GridInstruction, int, int)
     */
    public void drawBox(final int x1, final int y1, final int x2, final int y2, final GridInstruction gridInstruction) {
        if (x1 < 0 || y1 < 0) {
            throw new IllegalArgumentException(String.format("x1, y1 must be at least 0, found: (%s, %s)", x1, y1));
        }

        if (x2 > gridSize || y2 > gridSize) {
            throw new IllegalArgumentException(String.format("x2, y2 must be at less than %s, found: (%s, %s)", gridSize, x2, y2));
        }

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                updateGrid(gridInstruction, x, y);
            }
        }
    }

    /**
     * Calculates the value at a specific {@link Point}.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return the value
     */
    protected abstract int valueOf(int row, int column);

    /**
     * Updates the {@link CoordinateGrid} at the provided {@link Point}.
     *
     * @param gridInstruction the {@link GridInstruction}
     * @param x               the x coordinate
     * @param y               the y coordinate
     */
    protected abstract void updateGrid(GridInstruction gridInstruction, int x, int y);
}
