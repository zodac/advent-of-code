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
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import me.zodac.advent.pojo.Point;

/**
 * Class defining a {@link Grid} of {@link Boolean}s, where any point can have its value turned 'on' or 'off'.
 */
public final class BooleanGrid extends Grid<Boolean> {

    /**
     * {@link ToIntFunction} that is used to convert a specific point on the {@link BooleanGrid} to an {@link Integer} value.
     */
    public static final ToIntFunction<Boolean> EVALUATOR = booleanValue -> booleanValue ? 1 : 0;

    private static final Set<Integer> NEIGHBOUR_VALUES_FOR_OFF_POINT = Set.of(3);
    private static final Set<Integer> NEIGHBOUR_VALUES_FOR_ON_POINT = Set.of(2, 3);

    private BooleanGrid(final Boolean[][] grid) {
        super(grid);
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
        final int numberOfRows = numberOfRows();
        final int numberOfColumns = numberOfColumns();
        final Boolean[][] newGrid = new Boolean[numberOfRows][numberOfColumns];

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                if (cornersAlwaysOn && isCorner(row, column)) {
                    newGrid[row][column] = true;
                    continue;
                }

                final Point currentPoint = Point.of(row, column);
                final boolean isSet = at(currentPoint);

                final AdjacentPointsSelector adjacentPointsSelector = AdjacentPointsSelector.bounded(false, AdjacentDirection.ALL, gridSize);
                final int valueOfSetNeighbours = currentPoint.getAdjacentPoints(adjacentPointsSelector)
                    .mapToInt(point -> EVALUATOR.applyAsInt(at(point)))
                    .sum();

                if (isSet) {
                    newGrid[row][column] = NEIGHBOUR_VALUES_FOR_ON_POINT.contains(valueOfSetNeighbours);
                } else {
                    newGrid[row][column] = NEIGHBOUR_VALUES_FOR_OFF_POINT.contains(valueOfSetNeighbours);
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
}
