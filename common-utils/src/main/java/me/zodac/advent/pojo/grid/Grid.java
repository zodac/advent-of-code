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

package me.zodac.advent.pojo.grid;

import java.util.HashSet;
import java.util.Set;
import me.zodac.advent.pojo.Point;

/**
 * Abstract class defining a grid of {@link Point}s.
 *
 * @param <E> the type of the {@link Point}s on the {@link Grid}
 */
public class Grid<E> {

    /**
     * The length (or width) of the {@link Grid}.
     */
    protected final int gridSize;

    /**
     * The values of the {@link Grid}.
     */
    protected final E[][] grid;

    /**
     * Constructor that creates the internal {@code grid} and initialises the data.
     *
     * @param gridSize     the size of the {@link Grid}.
     * @param grid         the actual {@link Grid} represented as a 2D array.
     * @param initialValue the initial value for each {@link Point} in the {@link Grid}
     */
    Grid(final int gridSize, final E[][] grid, final E initialValue) {
        this.gridSize = gridSize;
        this.grid = grid.clone();

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                this.grid[row][column] = initialValue;
            }
        }
    }

    /**
     * Default constructor.
     *
     * @param grid the actual {@link Grid} represented as a 2D array.
     */
    Grid(final E[][] grid) {
        gridSize = grid.length;
        this.grid = grid.clone();
    }

    /**
     * Count any {@link Point}s in the {@link Grid}. The actual value of each {@link Point} is defined by {@link #valueAt(int, int)}.
     *
     * @return the sum of all {@link Point}s in the {@link Grid}
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
     * Draws a box on the {@link Grid}, where each {@link Point} is updated based according to the
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
     * Calculates the numeric value at a specific point.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return the value
     */
    protected int valueAt(final int row, final int column) {
        return 0;
    }

    /**
     * Updates the {@link Grid} at the provided {@link Point}.
     *
     * @param gridInstruction the {@link GridInstruction}
     * @param row             the x coordinate
     * @param column          the y coordinate
     */
    protected void updateGrid(final GridInstruction gridInstruction, final int row, final int column) {

    }

    /**
     * Sets the corners of the {@link Grid} to the input {@code newValue}.
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
     * Checks if the input point is one of the corners of the {@link Grid}.
     *
     * @param row    the x coordinate
     * @param column the y coordinate
     * @return {@code true} if the input {@link Point} is a corner of the {@link Grid}
     */
    protected boolean isCorner(final int row, final int column) {
        return (row == 0 && column == 0)
            || (row == gridSize - 1 && column == gridSize - 1)
            || (row == 0 && column == gridSize - 1)
            || (row == gridSize - 1 && column == 0);
    }

    /**
     * Returns the backing 2D array of the {@link Grid}.
     *
     * @return the {@link Grid} as a 2D array
     */
    public E[][] getGrid() {
        return grid.clone();
    }

    /**
     * Returns the number of rows in the {@link Grid}.
     *
     * @return the number of rows
     */
    public int numberOfRows() {
        return grid.length;
    }

    /**
     * Returns the number of columns in the {@link Grid}.
     *
     * @return the number of columns
     */
    public int numberOfColumns() {
        return grid[0].length;
    }

    /**
     * Returns the value at the given ({@code row}, {@code column}).
     *
     * @param row    the row
     * @param column the column
     * @return the value
     */
    public E at(final int row, final int column) {
        return grid[row][column];
    }

    /**
     * Returns the value at the given {@link Point}.
     *
     * @param point the {@link Point}
     * @return the value
     */
    public E at(final Point point) {
        return grid[point.x()][point.y()];
    }

    /**
     * Returns the row of values at the given {@code row}.
     *
     * @param row the row
     * @return the values
     */
    public E[] getRow(final int row) {
        return grid[row];
    }

    /**
     * Searches through all values in the {@link Grid} looking for the {@code wantedValue}. Returns all {@link Point}s which contain the
     * {@code wantedValue}.
     *
     * @param wantedValue the value to search for
     * @return a {@link Set} of the {@link Point}s with the {@code wantedValue}
     */
    public Set<Point> findValue(final E wantedValue) {
        final Set<Point> points = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            final E[] row = grid[i];

            for (int j = 0; j < row.length; j++) {
                final E value = row[j];

                if (value.equals(wantedValue)) {
                    points.add(Point.of(i, j));
                }
            }
        }
        return points;
    }

    /**
     * Prints the content of the {@link Grid}.
     */
    @SuppressWarnings("unused") // Only used for debugging
    public void print() {
        for (final E[] row : grid) {
            for (final E val : row) {
                //noinspection ALL: Printing to stdout for debugging
                System.out.print(val);
            }
            //noinspection ALL: Printing to stdout for debugging
            System.out.println();
        }
    }
}
