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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.ArrayUtils;

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
    protected final E[][] internalGrid;

    /**
     * Constructor that creates the internal {@code grid} and initialises the data.
     *
     * @param gridSize     the size of the {@link Grid}.
     * @param internalGrid the actual {@link Grid} represented as a 2D array.
     * @param initialValue the initial value for each {@link Point} in the {@link Grid}
     */
    public Grid(final int gridSize, final E[][] internalGrid, final E initialValue) {
        this.gridSize = gridSize;
        this.internalGrid = internalGrid.clone();

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                this.internalGrid[row][column] = initialValue;
            }
        }
    }

    /**
     * Default constructor.
     *
     * @param internalGrid the actual {@link Grid} represented as a 2D array.
     */
    public Grid(final E[][] internalGrid) {
        gridSize = internalGrid.length;
        this.internalGrid = internalGrid.clone();
    }

    /**
     * Given a {@link List}s of {@link String}s where each {@link String} represents a 2D array of {@link Character}s, we convert to a 2D array and
     * create a new instance of {@link Grid}. Each value in the 2D array is determined by converting the {@link Character} using the {@code converter}
     * {@link Function}.
     *
     * @param gridValues the {@link String}s representing a 2D array (where each character in the {@link String} is an element in the array)
     * @param converter  the {@link Function} to convert a {@link Character} from the input into the correct type for the 2D array
     * @param <E>        the type of the {@link Grid}
     * @return the created {@link Grid}
     * @throws IllegalArgumentException thrown if input is empty
     * @see ArrayUtils#toArrayOfArrays(List, Function)
     */
    public static <E> E[][] parseGrid(final List<String> gridValues, final Function<? super Character, ? extends E> converter) {
        if (gridValues.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        return ArrayUtils.toArrayOfArrays(gridValues, converter);
    }

    /**
     * Count the value of all {@link Point}s in the {@link Grid}. The actual value of each {@link Point} is defined by the input {@code evaluator}.
     *
     * @param evaluator {@link Function} used to convert the value of type {@code E} into an {@link Integer}
     * @return the sum of all {@link Point}s in the {@link Grid}
     */
    public long sumValues(final Function<? super E, Integer> evaluator) {
        int count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                final E value = at(row, column);
                count += evaluator.apply(value);
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
     * Updates the {@link Grid} at the provided {@link Point}.
     *
     * @param gridInstruction the {@link GridInstruction}
     * @param row             the x coordinate
     * @param column          the y coordinate
     */
    protected void updateGrid(final GridInstruction gridInstruction, final int row, final int column) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the corners of the {@link Grid} to the input {@code newValue}.
     *
     * @param newValue the new value for the corners
     * @return the updated {@code grid}
     */
    protected E[][] updateCornersToValue(final E newValue) {
        internalGrid[0][0] = newValue;
        internalGrid[0][gridSize - 1] = newValue;
        internalGrid[gridSize - 1][0] = newValue;
        internalGrid[gridSize - 1][gridSize - 1] = newValue;

        return internalGrid.clone();
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
    public E[][] getInternalGrid() {
        return internalGrid.clone();
    }

    /**
     * Returns the number of rows in the {@link Grid}.
     *
     * @return the number of rows
     */
    public int numberOfRows() {
        return internalGrid.length;
    }

    /**
     * Returns the number of columns in the {@link Grid}.
     *
     * @return the number of columns
     */
    public int numberOfColumns() {
        return internalGrid[0].length;
    }

    /**
     * Returns the value at the given ({@code row}, {@code column}).
     *
     * @param row    the row
     * @param column the column
     * @return the value
     */
    public E at(final int row, final int column) {
        return internalGrid[row][column];
    }

    /**
     * Returns the value at the given {@link Point}.
     *
     * @param point the {@link Point}
     * @return the value
     */
    public E at(final Point point) {
        return internalGrid[point.x()][point.y()];
    }

    /**
     * Returns the row of values at the given {@code row}.
     *
     * @param row the row
     * @return the values
     */
    public E[] getRow(final int row) {
        return internalGrid[row];
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
        for (int i = 0; i < internalGrid.length; i++) {
            final E[] row = internalGrid[i];

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
     * Iterates through all columns of the {@link Grid}, and checks whether they pass the provided {@link Predicate}. If they pass, the index of that
     * column is returned.
     *
     * @param predicate the {@link Predicate} to test all columns against
     * @return a {@link Stream} of indexes of all matching columns
     */
    public Stream<Integer> findColumnsWith(final Predicate<? super E> predicate) {
        final Collection<Integer> matchingColumns = new ArrayList<>();
        for (int i = 0; i < internalGrid[0].length; i++) {
            boolean match = true;

            for (final E[] characters : internalGrid) {
                final E value = characters[i];
                if (!predicate.test(value)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                matchingColumns.add(i);
            }
        }
        return matchingColumns.stream();
    }

    /**
     * Iterates through all rows of the {@link Grid}, and checks whether they pass the provided {@link Predicate}. If they pass, the index of that
     * row is returned.
     *
     * @param predicate the {@link Predicate} to test all rows against
     * @return a {@link Stream} of indexes of all matching rows
     */
    public Stream<Integer> findRowsWith(final Predicate<? super E> predicate) {
        final Collection<Integer> matchingRows = new HashSet<>();
        for (int i = 0; i < internalGrid.length; i++) {
            final E[] row = internalGrid[i];
            boolean match = true;

            for (final E value : row) {
                if (!predicate.test(value)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                matchingRows.add(i);
            }

        }
        return matchingRows.stream();
    }

    /**
     * Prints the content of the {@link Grid}.
     *
     * @param withHeaders whether to also print numeric headers for each column and row
     */
    @SuppressWarnings("unused") // Only used for debugging
    public void print(final boolean withHeaders) {
        if (withHeaders) {
            //noinspection ALL: Printing to stdout for debugging
            System.out.print(" | "); // NOPMD: SystemPrintln - Printing to stdout for debugging
            for (int i = 0; i < internalGrid[0].length; i++) {
                //noinspection ALL
                System.out.print((i + 1) % 10); // NOPMD
            }

            //noinspection ALL: Printing to stdout for debugging
            System.out.println("\n" + "-".repeat(internalGrid[0].length) + "---"); // NOPMD
        }

        for (int i = 0; i < internalGrid.length; i++) {
            final E[] row = internalGrid[i];

            if (withHeaders) {
                //noinspection ALL
                System.out.print((i + 1) % 10 + "| "); // NOPMD
            }

            for (final E val : row) {
                //noinspection ALL
                System.out.print(val); // NOPMD
            }
            //noinspection ALL
            System.out.println(); // NOPMD
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof final Grid<?> otherGrid)) {
            return false;
        }

        return Arrays.deepEquals(internalGrid, otherGrid.internalGrid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(internalGrid);
    }
}
