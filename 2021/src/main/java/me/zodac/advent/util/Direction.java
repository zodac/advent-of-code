/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
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

package me.zodac.advent.util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * ENUM defining the directions that the submarine can move in.
 */
public enum Direction {

    /**
     * The submarine is making a positive vertical movement.
     */
    DOWN,

    /**
     * The submarine is making a positive horizontal movement.
     */
    FORWARD,

    /**
     * The submarine is making a negative vertical movement.
     */
    UP,

    /**
     * An invalid direction.
     */
    INVALID;

    private static final Collection<Direction> ALL_VALUES = Stream.of(values())
        .filter(value -> value != INVALID)
        .toList();

    /**
     * Retrieve all available {@link Direction}s (excluding {@link Direction#INVALID}.
     *
     * <p>
     * Should be used instead of {@link Direction#values()}, as that recalculates the array for each call,
     * while this method uses a static {@link Collection}.
     *
     * @return a {@link Collection} of all {@link Direction}s
     */
    public static Collection<Direction> getAllValues() {
        return ALL_VALUES;
    }

    /**
     * Retrieve a {@link Direction} based on the input {@link String}. The search is case-insensitive.
     *
     * @param input the {@link Direction} as a {@link String}
     * @return the matching {@link Direction}, or {@link Direction#INVALID} if none is found
     */
    public static Direction get(final String input) {
        return getAllValues()
            .stream()
            .filter(direction -> direction.toString().equalsIgnoreCase(input))
            .findAny()
            .orElse(Direction.INVALID);
    }
}
