/*
 * Zero-Clause BSD License
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

/**
 * Class defining a {@link CoordinateGrid} of {@link Boolean}s, where any point can have its value turned 'on' or 'off'.
 */
public final class BooleanGrid extends CoordinateGrid<Boolean> {

    private static final int NUMBER_SIGNIFYING_OVERLAP = 1;

    private BooleanGrid(final int gridSize) {
        super(gridSize, new Boolean[gridSize][gridSize], false, NUMBER_SIGNIFYING_OVERLAP);
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
     * {@inheritDoc}
     *
     * <p>
     * If the value of the {@link me.zodac.advent.pojo.Point} is {@code true} returns <b>1</b>, else returns <b>0</b>.
     */
    @Override
    public int valueOf(final int row, final int column) {
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
    protected void updateGrid(final GridInstruction gridInstruction, final int x, final int y) {
        switch (gridInstruction) {
            case ON -> grid[x][y] = true;
            case OFF -> grid[x][y] = false;
            case TOGGLE -> grid[x][y] = !grid[x][y];
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
    }
}
