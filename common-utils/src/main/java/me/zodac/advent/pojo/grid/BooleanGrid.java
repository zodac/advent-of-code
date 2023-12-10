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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.ArrayUtils;

/**
 * Class defining a {@link Grid} of {@link Boolean}s, where any point can have its value turned 'on' or 'off'.
 */
public final class BooleanGrid extends Grid<Boolean> {

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
     * @see ArrayUtils#convertToArrayOfBooleanArrays(List, char)
     */
    public static BooleanGrid parse(final List<String> gridValues, final char symbolSignifyingTrue) {
        if (gridValues.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        final int firstElementSize = gridValues.getFirst().length();
        if (gridValues.size() != firstElementSize) {
            throw new IllegalArgumentException(
                String.format("Outer size must match inner size, found outer: %s, inner: %s", gridValues.size(), firstElementSize));
        }

        return new BooleanGrid(ArrayUtils.convertToArrayOfBooleanArrays(gridValues, symbolSignifyingTrue));
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
        final Boolean[][] newGrid = new Boolean[gridSize][gridSize];

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (cornersAlwaysOn && isCorner(x, y)) {
                    newGrid[x][y] = true;
                    continue;
                }

                final Point currentPoint = Point.of(x, y);
                final boolean isSet = grid[x][y];

                final AdjacentPointsSelector adjacentPointsSelector = AdjacentPointsSelector.createForBoundedGrid(false, true, gridSize);
                final Set<Point> neighbours = currentPoint.getAdjacentPoints(adjacentPointsSelector);

                final int numberOfSetNeighbours = countSetPoints(neighbours);

                if (isSet) {
                    newGrid[x][y] = numberOfSetNeighbours == 2 || numberOfSetNeighbours == 3;
                } else {
                    newGrid[x][y] = numberOfSetNeighbours == 3;
                }
            }
        }

        return new BooleanGrid(newGrid);
    }

    private int countSetPoints(final Collection<Point> points) {
        return points
            .stream()
            .mapToInt(this::valueAt)
            .sum();
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
     * <p>
     * If the value at the point is {@code true} returns <b>1</b>, else returns <b>0</b>.
     */
    @Override
    public int valueAt(final int row, final int column) {
        return grid[row][column] ? 1 : 0;
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
            case ON -> grid[row][column] = true;
            case OFF -> grid[row][column] = false;
            case TOGGLE -> grid[row][column] = !grid[row][column];
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
    }
}
