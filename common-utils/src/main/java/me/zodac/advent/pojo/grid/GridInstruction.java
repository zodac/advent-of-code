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

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Enum defining an instruction on how to handle a point in a coordinate grid.
 */
public enum GridInstruction {

    /**
     * Switch the grid point on.
     */
    ON,

    /**
     * Switch the grid point off.
     */
    OFF,

    /**
     * Toggle the value of the grid point (differs depending on grid type).
     */
    TOGGLE,

    /**
     * An invalid {@link GridInstruction}.
     */
    INVALID;

    private static final Collection<GridInstruction> ALL_VALUES = Stream.of(values())
        .filter(value -> value != INVALID)
        .toList();

    /**
     * Retrieve a {@link GridInstruction} that best matches the input {@link String}. The search is case-insensitive.
     *
     * <p>
     * We check that the input {@link String} {@link String#contains(CharSequence)} one of the {@link GridInstruction}s.
     *
     * @param input the {@link GridInstruction} as a {@link String}
     * @return the matching {@link GridInstruction}, or {@link GridInstruction#INVALID} if none is found
     */
    public static GridInstruction match(final String input) {
        return ALL_VALUES
            .stream()
            .filter(gridInstruction -> input.toUpperCase(Locale.UK).contains(gridInstruction.toString()))
            .findAny()
            .orElse(INVALID);
    }
}
