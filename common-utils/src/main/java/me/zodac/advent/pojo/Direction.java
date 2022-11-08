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

package me.zodac.advent.pojo;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

/**
 * ENUM defining the directions that can be moved in.
 */
public enum Direction {

    /**
     * A negative horizontal movement.
     */
    BACKWARDS("<"),

    /**
     * A negative vertical movement.
     */
    DOWN("v"),

    /**
     * A positive horizontal movement.
     */
    FORWARD(">"),

    /**
     * A positive vertical movement.
     */
    UP("^"),

    /**
     * An invalid {@link Direction}.
     */
    INVALID;

    private static final Collection<Direction> ALL_VALUES = Stream.of(values())
        .filter(value -> value != INVALID)
        .toList();

    private final Set<String> possibleValues;

    Direction(final String... possibleValues) {
        this.possibleValues = possibleValues.length == 0 ? Collections.emptySet() : Set.of(possibleValues);
    }

    /**
     * Retrieve a {@link Direction} based on the input {@link String}. The search is case-insensitive.
     *
     * @param input the {@link Direction} as a {@link String}
     * @return the matching {@link Direction}, or {@link Direction#INVALID} if none is found
     */
    public static Direction get(final String input) {
        return ALL_VALUES
            .stream()
            .filter(direction -> direction.toString().equalsIgnoreCase(input))
            .findAny()
            .orElse(INVALID);
    }

    /**
     * Retrieve a {@link Direction} based on the input {@link String} referring to a possible value of the {@link Direction}. The search is
     * case-insensitive.
     *
     * @param input the {@link Direction} as a {@link String}
     * @return the matching {@link Direction}
     * @throws IllegalStateException thrown if the input {@link String} is not a valid value for any {@link Direction}
     */
    public static Direction getByValue(final String input) {
        return ALL_VALUES
            .stream()
            .filter(direction -> direction.possibleValues.contains(input))
            .findAny()
            .orElseThrow(() -> new IllegalStateException(String.format("Invalid direction: '%s'", input)));
    }
}
