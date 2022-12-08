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

import static me.zodac.advent.util.CollectionUtils.getFirst;

import java.util.List;
import me.zodac.advent.pojo.Line;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.ArrayUtils;

/**
 * Class defining a {@link CoordinateGrid} of {@link Integer}s, where any point can have an {@link Integer} value.
 */
public final class IntegerGrid extends CoordinateGrid<Integer> {

    private static final int NUMBER_SIGNIFYING_OVERLAP = 2;

    private IntegerGrid(final int gridSize) {
        super(gridSize, new Integer[gridSize][gridSize], 0, NUMBER_SIGNIFYING_OVERLAP);
    }

    private IntegerGrid(final Integer[][] grid) {
        super(grid, NUMBER_SIGNIFYING_OVERLAP);
    }

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

        return new IntegerGrid(gridSize);
    }

    /**
     * Given a {@link List}s of {@link String}s where each {@link String} represents a 2D array of {@link Integer}s, we convert to a 2D array and
     * create a new instance of {@link IntegerGrid}.
     *
     * @param gridValues the {@link String}s representing a 2D array (where each character in the {@link String} is an element in the array)
     * @return the created {@link IntegerGrid}
     * @throws IllegalArgumentException thrown if input is empty, or the input {@link List} size does not match the length of the first {@link String}
     * @see ArrayUtils#convertToArrayOfIntegerArrays(List)
     */
    public static IntegerGrid parse(final List<String> gridValues) {
        if (gridValues.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        final int firstElementLength = getFirst(gridValues).length();
        if (gridValues.size() != firstElementLength) {
            throw new IllegalArgumentException(
                String.format("Outer size must match inner size, found outer: %s, inner: %s", gridValues.size(), firstElementLength));
        }

        return new IntegerGrid(ArrayUtils.convertToArrayOfIntegerArrays(gridValues));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Uses the {@link Integer} value of the {@link Point}.
     */
    @Override
    public int valueAt(final int row, final int column) {
        return grid[row][column];
    }

    /**
     * {@inheritDoc}
     *
     * <pre>
     * | {@link GridInstruction}        | Action                                       |
     * |------------------------|----------------------------------------------|
     * | {@link GridInstruction#ON}     | Increment the point by <b>1</b>                     |
     * | {@link GridInstruction#OFF}    | Decrement the point by <b>1</b>, to a minimum of <b>0</b>  |
     * | {@link GridInstruction#TOGGLE} | Increment the point by <b>2</b>                     |
     * </pre>
     */
    @Override
    protected void updateGrid(final GridInstruction gridInstruction, final int row, final int column) {
        switch (gridInstruction) {
            case ON -> grid[row][column] = grid[row][column] + 1;
            case OFF -> grid[row][column] = Math.max(0, grid[row][column] - 1); // Value should not be less than 0
            case TOGGLE -> grid[row][column] = grid[row][column] + 2;
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
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
}
