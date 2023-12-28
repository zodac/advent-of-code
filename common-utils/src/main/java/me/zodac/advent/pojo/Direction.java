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

package me.zodac.advent.pojo;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

/**
 * ENUM defining the directions that can be moved in.
 */
public enum Direction {

    /**
     * A negative vertical movement.
     */
    DOWN("V", "D", "DOWN"),

    /**
     * A negative horizontal movement.
     */
    LEFT("<", "L", "LEFT", "BACKWARDS"),

    /**
     * A positive horizontal movement.
     */
    RIGHT(">", "R", "RIGHT", "FORWARD"),

    /**
     * A positive vertical movement.
     */
    UP("^", "U", "UP"),

    /**
     * An invalid {@link Direction}.
     */
    INVALID;

    private static final Collection<Direction> ALL_VALUES = Stream.of(values())
        .filter(value -> value != INVALID)
        .toList();

    // Should all be uppercase values to avoid issues with Direction#get(String)
    private final Set<String> possibleValues;

    Direction(final String... possibleValues) {
        this.possibleValues = possibleValues.length == 0 ? Collections.emptySet() : Set.of(possibleValues);
    }

    /**
     * Retrieve a {@link Direction} based on the input {@code char} referring to a possible value of the {@link Direction}. The search is
     * case-insensitive.
     *
     * @param input the {@link Direction} as a {@code char}
     * @return the matching {@link Direction}
     * @throws IllegalArgumentException thrown if the input {@link String} is not a valid value for any {@link Direction}
     * @see #get(String)
     */
    public static Direction get(final char input) {
        return get(String.valueOf(input));
    }

    /**
     * Retrieve a {@link Direction} based on the input {@link String} referring to a possible value of the {@link Direction}. The search is
     * case-insensitive.
     *
     * @param input the {@link Direction} as a {@link String}
     * @return the matching {@link Direction}
     * @throws IllegalArgumentException thrown if the input {@link String} is not a valid value for any {@link Direction}
     */
    public static Direction get(final String input) {
        return ALL_VALUES
            .stream()
            .filter(direction -> direction.possibleValues.contains(input.toUpperCase(Locale.UK)))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid %s: '%s'", Direction.class.getSimpleName(), input)));
    }

    /**
     * Returns the opposite {@link Direction} for the current {@link Direction}.
     *
     * @return the opposite {@link Direction}
     */
    public Direction getOpposite() {
        return switch (this) {
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case UP -> DOWN;
            case INVALID -> INVALID;
        };
    }
}
