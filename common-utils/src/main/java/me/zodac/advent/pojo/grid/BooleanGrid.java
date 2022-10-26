/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zodac.advent.pojo.grid;

/**
 * Class defining a coordinate grid of points, where any point can have its value turned 'on' or 'off'.
 *
 * @param grid the coordinate grid as a 2D array of {@code boolean}s
 */
public record BooleanGrid(boolean[][] grid) {

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

        return new BooleanGrid(new boolean[gridSize][gridSize]);
    }

    /**
     * Draws a box on the {@link BooleanGrid}, where each {@link me.zodac.advent.pojo.Point} is updated based according to the
     * {@link GridInstruction}. All values inside the box are also updated.
     *
     * <pre>
     * | {@link GridInstruction}        | Action                  |
     * |------------------------|-------------------------|
     * | {@link GridInstruction#ON}     | Set the point to <b>true</b>   |
     * | {@link GridInstruction#OFF}    | Set the point to <b>false</b>  |
     * | {@link GridInstruction#TOGGLE} | Flip the boolean value  |
     * </pre>
     *
     * <p>
     * For example, starting with a 10x10 grid:
     * <pre>
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     * </pre>
     *
     * <p>
     * Given the {@link me.zodac.advent.pojo.Point}s (0, 0) and (2, 4) with the instruction {@link GridInstruction#ON}, the updated
     * {@link BooleanGrid} would be:
     * <pre>
     *     T T T T T F F F F F
     *     T T T T T F F F F F
     *     T T T T T F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     * </pre>
     *
     * <p>
     * Following up with {@link me.zodac.advent.pojo.Point}s (0, 1) and (0, 9) with the instruction {@link GridInstruction#TOGGLE} would give us:
     * <pre>
     *     T F F F F T T T T T
     *     T T T T T F F F F F
     *     T T T T T F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     * </pre>
     *
     * <p>
     * And finally with {@link me.zodac.advent.pojo.Point}s (0, 2) and (3, 3) with the instruction {@link GridInstruction#OFF} we would get:
     * <pre>
     *     T F F F F T T T T T
     *     T T F F T F F F F F
     *     T T F F T F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     *     F F F F F F F F F F
     * </pre>
     *
     * @param x1              the first x coordinate
     * @param y1              the first y coordinate
     * @param x2              the second x coordinate
     * @param y2              the second y coordinate
     * @param gridInstruction the {@link GridInstruction}
     */
    public void drawBox(final int x1, final int y1, final int x2, final int y2, final GridInstruction gridInstruction) {
        if (x1 < 0 || y1 < 0) {
            throw new IllegalArgumentException(String.format("x1, y1 must be at least 0, found: (%s, %s)", x1, y1));
        }

        if (x2 > grid.length || y2 > grid.length) {
            throw new IllegalArgumentException(String.format("x2, y2 must be at less than %s, found: (%s, %s)", grid.length, x2, y2));
        }

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                updateGrid(gridInstruction, x, y);
            }
        }
    }

    private void updateGrid(final GridInstruction gridInstruction, final int x, final int y) {
        switch (gridInstruction) {
            case ON -> grid[x][y] = true;
            case OFF -> grid[x][y] = false;
            case TOGGLE -> grid[x][y] = !grid[x][y];
            default -> throw new IllegalStateException("Cannot draw a box with instruction: " + gridInstruction);
        }
    }

    /**
     * Count any {@link me.zodac.advent.pojo.Point}s in the {@link BooleanGrid} that has its value set to {@code true}.
     *
     * @return the number of {@code true} {@link me.zodac.advent.pojo.Point}s in the {@link BooleanGrid}
     */
    public long countSwitchedOn() {
        int count = 0;

        final int gridLength = grid[0].length;
        for (final boolean[] rows : grid) {
            for (int column = 0; column < gridLength; column++) {
                if (rows[column]) {
                    count++;
                }
            }
        }

        return count;
    }
}
