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

package me.zodac.advent.pojo.grid;

import java.util.HashSet;
import java.util.Set;
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
     * Constructor that creates the internal {@code grid} and initialises the data.
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
                this.grid[row][column] = initialValue;
            }
        }
    }

    /**
     * Default constructor.
     *
     * @param grid                   the actual {@link CoordinateGrid} represented as a 2D array.
     * @param valueSignifyingOverlap when counting how many overlaps has occurred, this is the minimum value required to be considered an overlap
     */
    CoordinateGrid(final E[][] grid, final int valueSignifyingOverlap) {
        gridSize = grid.length;
        this.grid = grid.clone();
        this.valueSignifyingOverlap = valueSignifyingOverlap;
    }

    /**
     * Count any {@link Point}s in the {@link CoordinateGrid}. The actual value of each {@link Point} is defined by {@link #valueAt(int, int)}.
     *
     * @return the sum of all {@link Point}s in the {@link CoordinateGrid}
     * @see #valueAt(int, int)
     */
    public long sumValues() {
        int count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                count += valueAt(row, column);
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
                if (valueAt(row, column) >= valueSignifyingOverlap) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns a {@link Set} of the neighbours for a {@link Point}. Can return up to 8 neighbours, depending on where the input is located.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return the neighbouring {@link Point}s
     */
    protected Set<Point> getNeighbours(final int row, final int column) {
        final Set<Point> neighbours = new HashSet<>();
        neighbours.add(Point.of(next(row), column));
        neighbours.add(Point.of(previous(row), column));
        neighbours.add(Point.of(row, next(column)));
        neighbours.add(Point.of(row, previous(column)));
        neighbours.add(Point.of(next(row), next(column)));
        neighbours.add(Point.of(previous(row), previous(column)));
        neighbours.add(Point.of(next(row), previous(column)));
        neighbours.add(Point.of(previous(row), next(column)));

        // Remove current point, in case it was added in above calculations
        neighbours.remove(Point.of(row, column));
        return neighbours;
    }

    private int next(final int rowOrColumn) {
        return Math.min(rowOrColumn + 1, (gridSize - 1));
    }

    private static int previous(final int rowOrColumn) {
        return Math.max(rowOrColumn - 1, 0);
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
     * @param point the {@link Point}
     * @return the value
     * @see #valueAt(int, int)
     */
    protected int valueAt(final Point point) {
        return valueAt(point.x(), point.y());
    }

    /**
     * Calculates the value at a specific {@link Point}.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return the value
     */
    protected abstract int valueAt(int row, int column);

    /**
     * Updates the {@link CoordinateGrid} at the provided {@link Point}.
     *
     * @param gridInstruction the {@link GridInstruction}
     * @param row             the x coordinate
     * @param column          the y coordinate
     */
    protected abstract void updateGrid(GridInstruction gridInstruction, int row, int column);

    /**
     * Sets the corners of the {@link CoordinateGrid} to the input {@code newValue}.
     *
     * @param newValue the new value for the corners
     * @return the updated {@code grid}
     */
    protected E[][] updateCornersToValue(final E newValue) {
        grid[0][0] = newValue;
        grid[0][gridSize - 1] = newValue;
        grid[gridSize - 1][0] = newValue;
        grid[gridSize - 1][gridSize - 1] = newValue;

        return grid.clone();
    }

    /**
     * Checks if the input {@link Point} is one of the corners of the {@link CoordinateGrid}.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return {@code true} if the input {@link Point} is a corner of the {@link CoordinateGrid}
     */
    protected boolean isCorner(final int row, final int column) {
        return (row == 0 && column == 0)
            || (row == gridSize - 1 && column == gridSize - 1)
            || (row == 0 && column == gridSize - 1)
            || (row == gridSize - 1 && column == 0);
    }

    /**
     * Returns the backing 2D array of the {@link CoordinateGrid}.
     *
     * @return the {@link CoordinateGrid} as a 2D array
     */
    public E[][] getGrid() {
        return grid.clone();
    }
}
