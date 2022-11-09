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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.CollectionUtils;

/**
 * Class defining a {@link CoordinateGrid} of {@link Boolean}s, where any point can have its value turned 'on' or 'off'.
 */
public final class BooleanGrid extends CoordinateGrid<Boolean> {

    private static final int NUMBER_SIGNIFYING_OVERLAP = 1;

    private BooleanGrid(final int gridSize) {
        super(gridSize, new Boolean[gridSize][gridSize], false, NUMBER_SIGNIFYING_OVERLAP);
    }

    private BooleanGrid(final Boolean[][] grid) {
        super(grid, NUMBER_SIGNIFYING_OVERLAP);
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
     * Given a {@link Collection} of {@link List}s of {@link Boolean}s that represents a {@link BooleanGrid}'s internal boolean 2D-array, we convert
     * the {@link Collection} to a 2D array and create a new instance of {@link BooleanGrid}.
     *
     * @param grid the {@link Collection} representation of a 2D array
     * @return the created {@link BooleanGrid}
     * @throws IllegalArgumentException thrown if input is empty, or the input {@link Collection} size does not match the size of the first element
     */
    public static BooleanGrid parse(final Collection<? extends List<Boolean>> grid) {
        if (grid.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        final List<Boolean> firstElement = getFirst(grid);
        if (grid.size() != firstElement.size()) {
            throw new IllegalArgumentException(
                String.format("Outer size must match inner size, found outer: %s, inner: %s", grid.size(), firstElement.size()));
        }

        return new BooleanGrid(CollectionUtils.convertToArrayOfArrays(grid));
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
     * @see BooleanGrid#getNeighbours(int, int)
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
     * @see BooleanGrid#getNeighbours(int, int)
     */
    public BooleanGrid playGameOfLife(final boolean cornersAlwaysOn) {
        final Boolean[][] newGrid = new Boolean[gridSize][gridSize];

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (cornersAlwaysOn && isCorner(x, y)) {
                    newGrid[x][y] = true;
                    continue;
                }

                final boolean isSet = grid[x][y];
                final Set<Point> neighbours = getNeighbours(x, y);
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
     * If the value of the {@link me.zodac.advent.pojo.Point} is {@code true} returns <b>1</b>, else returns <b>0</b>.
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
