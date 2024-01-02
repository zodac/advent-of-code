/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import java.util.function.Function;
import me.zodac.advent.pojo.Point;

/**
 * Class defining a {@link Grid} of {@link Boolean}s, where any point can have its value turned 'on' or 'off'.
 */
public final class BooleanGrid extends Grid<Boolean> {

    /**
     * {@link Function} that is used to convert a specific point on the {@link BooleanGrid} to an {@link Integer} value.
     */
    public static final Function<Boolean, Integer> EVALUATOR = booleanValue -> booleanValue ? 1 : 0;

    private BooleanGrid(final int gridSize) {
        super(gridSize, new Boolean[gridSize][gridSize], false);
    }

    private BooleanGrid(final Boolean[][] grid) {
        super(grid);
    }

    /**
     * Creates a {@link BooleanGrid} with the dimensions {@code gridSize}x{@code gridSize}.
     *
     * @param gridSize the length and width of the {@link BooleanGrid}
     * @return the created {@link BooleanGrid}
     * @throws IllegalArgumentException thrown if input size is less than 0
     */
    public static BooleanGrid ofSize(final int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("Size must be positive integer, found: " + gridSize);
        }

        return new BooleanGrid(gridSize);
    }

    /**
     * Given a {@link List}s of {@link String}s where each {@link String} represents a 2D array of {@link Boolean}s, we convert to a 2D array and
     * create a new instance of {@link BooleanGrid}.
     *
     * @param gridValues           the {@link String}s representing a 2D array (where each character in the {@link String} is an element in the array)
     * @param symbolSignifyingTrue the symbol in the {@link String} that defines a {@code true} {@link Boolean}
     * @return the created {@link BooleanGrid}
     * @throws IllegalArgumentException thrown if input is empty, or the input {@link List} size does not match the length of the first {@link String}
     * @see Grid#parseGrid(List, Function)
     */
    public static BooleanGrid parse(final List<String> gridValues, final char symbolSignifyingTrue) {
        final Grid<Boolean> booleanGrid = parseGrid(gridValues, character -> character == symbolSignifyingTrue);
        return new BooleanGrid(booleanGrid.getInternalGrid());
    }

    /**
     * Plays Conway's Game of Life on the {@link BooleanGrid}. The rules are as follows.
     *
     * <p>
     * Given a {@link Point} in the {@link BooleanGrid}, there can be 3-8 neighbour {@link Point}s. For each {@link Point}:
     *
     * <ul>
     *     <li>If a point is <b>ON</b> and 2 or 3 neighbours are also <b>ON</b>, the {@link Point} stays <b>ON</b>, else it's turned <b>OFF</b></li>
     *     <li>If a point is <b>OFF</b> and 3 neighbours are <b>ON</b>, the {@link Point} turns <b>ON</b>, else stays <b>OFF</b></li>
     * </ul>
     *
     * <p>
     * <b>NOTE:</b> All {@link Point}s are updated at the same time, so we do not make a change and use the new value for another {@link Point}.
     *
     * @return the updated {@link BooleanGrid}
     * @see Point#getAdjacentPoints(AdjacentPointsSelector)
     */
    public BooleanGrid playGameOfLife() {
        return playGameOfLife(false);
    }

    /**
     * Plays Conway's Game of Life on the {@link BooleanGrid}. The rules are as follows.
     *
     * <p>
     * Given a {@link Point} in the {@link BooleanGrid}, there can be 3-8 neighbour {@link Point}s. For each {@link Point}:
     *
     * <ul>
     *     <li>If a point is <b>ON</b> and 2 or 3 neighbours are also <b>ON</b>, the {@link Point} stays <b>ON</b>, else it's turned <b>OFF</b></li>
     *     <li>If a point is <b>OFF</b> and 3 neighbours are <b>ON</b>, the {@link Point} turns <b>ON</b>, else stays <b>OFF</b></li>
     * </ul>
     *
     * <p>
     * If {@code cornersAlwaysOn} is set to {@code} true, then the corners of the {@link BooleanGrid} are always set to <b>ON</b>, regardless of the
     * Game of Life rules above.
     *
     * <p>
     * <b>NOTE:</b> All {@link Point}s are updated at the same time, so we do not make a change and use the new value for another {@link Point}.
     *
     * @param cornersAlwaysOn the corners of the {@link BooleanGrid} are always <b>ON</b>
     * @return the updated {@link BooleanGrid}
     * @see Point#getAdjacentPoints(AdjacentPointsSelector)
     */
    public BooleanGrid playGameOfLife(final boolean cornersAlwaysOn) {
        final Boolean[][] newGrid = new Boolean[numberOfRows()][numberOfColumns()];

        for (int row = 0; row < numberOfRows(); row++) {
            for (int column = 0; column < numberOfColumns(); column++) {
                if (cornersAlwaysOn && isCorner(row, column)) {
                    newGrid[row][column] = true;
                    continue;
                }

                final Point currentPoint = Point.of(row, column);
                final boolean isSet = at(currentPoint);

                final AdjacentPointsSelector adjacentPointsSelector = AdjacentPointsSelector.createForBoundedGrid(false, true, gridSize);
                final int valueOfSetNeighbours = currentPoint.getAdjacentPoints(adjacentPointsSelector)
                    .mapToInt(point -> EVALUATOR.apply(at(point)))
                    .sum();

                if (isSet) {
                    newGrid[row][column] = valueOfSetNeighbours == 2 || valueOfSetNeighbours == 3;
                } else {
                    newGrid[row][column] = valueOfSetNeighbours == 3;
                }
            }
        }

        return new BooleanGrid(newGrid);
    }

    /**
     * Sets the corners of the {@link BooleanGrid} to the input {@code newValue}.
     *
     * @param newValue the new value for the corners
     * @return the updated {@link BooleanGrid}
     */
    public BooleanGrid updateCorners(final Boolean newValue) {
        return new BooleanGrid(updateCornersToValue(newValue));
    }

    /**
     * {@inheritDoc}
     *
     * <pre>
     * | {@link GridInstruction}        | Action                  |
     * |------------------------|-------------------------|
     * | {@link GridInstruction#ON}     | Set the point to <b>true</b>   |
     * | {@link GridInstruction#OFF}    | Set the point to <b>false</b>  |
     * | {@link GridInstruction#TOGGLE} | Flip the boolean value  |
     * </pre>
     */
    @Override
    protected void updateGrid(final GridInstruction gridInstruction, final int row, final int column) {
        switch (gridInstruction) {
            case ON -> internalGrid[row][column] = true;
            case OFF -> internalGrid[row][column] = false;
            case TOGGLE -> internalGrid[row][column] = !internalGrid[row][column];
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
    }
}
