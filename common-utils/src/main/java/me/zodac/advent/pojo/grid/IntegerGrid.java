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

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import me.zodac.advent.pojo.Line;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.NumberUtils;

/**
 * Class defining a {@link Grid} of {@link Integer}s, where any point can have an {@link Integer} value.
 */
public final class IntegerGrid extends Grid<Integer> {

    private static final int NUMBER_SIGNIFYING_OVERLAP = 2;

    private IntegerGrid(final int gridSize) {
        super(gridSize, new Integer[gridSize][gridSize], 0);
    }

    private IntegerGrid(final Integer[][] grid) {
        super(grid);
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
     * @see Grid#parseGrid(List, Function)
     */
    public static IntegerGrid parse(final List<String> gridValues) {
        final Integer[][] internalArray = parseGrid(gridValues, (character -> NumberUtils.toIntOrDefault(character, 0)));
        return new IntegerGrid(internalArray);
    }

    /**
     * Returns the number of points on the {@link Grid} that have {@link Point}s that have been set more than the minimum
     * amount {@link #NUMBER_SIGNIFYING_OVERLAP}.
     *
     * <p>
     * As a {@link Line} is drawn or {@link Point}s are set, each {@link Point} has its value updated. If that value is over
     * {@link #NUMBER_SIGNIFYING_OVERLAP}, the {@link Point} has an overlap.
     *
     * @return the number of {@link Point} with overlaps
     */
    public int numberOfOverlaps() {
        int count = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                if (valueAt(row, column) >= NUMBER_SIGNIFYING_OVERLAP) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Uses the {@link Integer} value at the point.
     */
    @Override
    public int valueAt(final int row, final int column) {
        return internalGrid[row][column];
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
            case ON -> internalGrid[row][column] = internalGrid[row][column] + 1;
            case OFF -> internalGrid[row][column] = Math.max(0, internalGrid[row][column] - 1); // Value should not be less than 0
            case TOGGLE -> internalGrid[row][column] = internalGrid[row][column] + 2;
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
        if (line.isPerfectDiagonal() && allowedLineType != AllowedLineType.ALL_LINES) {
            return;
        }

        final Set<Point> points = line.getPointsInLine();

        for (final Point point : points) {
            internalGrid[point.y()][point.x()]++;
        }
    }
}
