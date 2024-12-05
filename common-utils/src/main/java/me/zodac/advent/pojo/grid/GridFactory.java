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

/**
 * Factory class to create instances of {@link Grid}.
 */
public final class GridFactory {

    private GridFactory() {

    }

    /**
     * Converts the {@link List} of {@link String}s to a {@link BooleanGrid}.
     *
     * @param strings              the input {@link List} of {@link String}s
     * @param symbolSignifyingTrue the symbol in the {@link String} that defines a {@code true} {@link Boolean}
     * @return the {@link BooleanGrid}
     */
    public static BooleanGrid ofBooleans(final List<String> strings, final char symbolSignifyingTrue) {
        return BooleanGrid.parse(strings, symbolSignifyingTrue);
    }

    /**
     * Converts the {@link List} of {@link String}s to a {@link Grid} of {@link Character}s.
     *
     * @param strings the input {@link List} of {@link String}s
     * @return the {@link Grid} of {@link Character}s
     */
    public static Grid<Character> ofCharacters(final List<String> strings) {
        return Grid.parseGrid(strings, character -> character);
    }

    /**
     * Converts the {@link List} of {@link String}s to a {@link IntegerGrid}.
     *
     * @param strings the input {@link List} of {@link String}s
     * @return the {@link IntegerGrid}
     */
    public static IntegerGrid ofIntegers(final List<String> strings) {
        return IntegerGrid.parse(strings);
    }
}
