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
     * An invalid direction.
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
