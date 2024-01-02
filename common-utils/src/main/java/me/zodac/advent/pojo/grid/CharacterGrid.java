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

/**
 * Class defining a {@link Grid} of {@link Character}s, where any point can have a {@link Character} value.
 */
// TODO: Is this class really needed? Convert into a GridFactory?
public final class CharacterGrid extends Grid<Character> {

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
     * @see Grid#parseGrid(List, Function)
     */
    public static CharacterGrid parse(final List<String> gridValues) {
        final Grid<Character> characterGrid = parseGrid(gridValues, character -> character);
        return new CharacterGrid(characterGrid.getInternalGrid());
    }
}
