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
import me.zodac.advent.util.ArrayUtils;

/**
 * Class defining a {@link CoordinateGrid} of {@link Character}s, where any point can have a {@link Character} value.
 */
public final class CharacterGrid extends CoordinateGrid<Character> {

    private CharacterGrid(final Character[][] grid) {
        super(grid);
    }

    /**
     * Given a {@link List}s of {@link String}s where each {@link String} represents a 2D array of {@link Character}s, we convert to a 2D array and
     * create a new instance of {@link CharacterGrid}.
     *
     * @param gridValues the {@link String}s representing a 2D array (where each character in the {@link String} is an element in the array)
     * @return the created {@link CharacterGrid}
     * @throws IllegalArgumentException thrown if input is empty, or the input {@link List} size does not match the length of the first {@link String}
     * @see ArrayUtils#convertToArrayOfCharacterArrays(List)
     */
    public static CharacterGrid parse(final List<String> gridValues) {
        if (gridValues.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        final int firstElementLength = gridValues.getFirst().length();
        if (gridValues.size() != firstElementLength) {
            throw new IllegalArgumentException(
                String.format("Outer size must match inner size, found outer: %s, inner: %s", gridValues.size(), firstElementLength));
        }

        return new CharacterGrid(ArrayUtils.convertToArrayOfCharacterArrays(gridValues));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Uses the {@link Integer} value of the {@link Character} at the given point.
     */
    @Override
    public int valueAt(final int row, final int column) {
        return grid[row][column];
    }

    @Override
    protected void updateGrid(final GridInstruction gridInstruction, final int row, final int column) {
        throw new UnsupportedOperationException();
    }
}
