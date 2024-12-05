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

/**
 * Defines the directions in which to search for adjacent {@link me.zodac.advent.pojo.Point}s.
 */
public enum AdjacentDirection {

    /**
     * Search all directions (cardinal and diagonal).
     */
    ALL,

    /**
     * Search the cardinal directions (up, down, left, right) only.
     */
    CARDINAL_ONLY,

    /**
     * Search the diagonals (up-left, up-right, down-left, down-right) only.
     */
    DIAGONAL_ONLY;

    /**
     * Check if the {@link AdjacentDirection} is meant to find adjacent points in the cardinal directions.
     *
     * @param adjacentDirection the {@link AdjacentDirection} to check
     * @return {@code true} if the {@link AdjacentDirection} is {@link #ALL} or {@link #CARDINAL_ONLY}
     */
    public static boolean isCardinal(final AdjacentDirection adjacentDirection) {
        return adjacentDirection == ALL || adjacentDirection == CARDINAL_ONLY;
    }

    /**
     * Check if the {@link AdjacentDirection} is meant to find adjacent points in the diagonal directions.
     *
     * @param adjacentDirection the {@link AdjacentDirection} to check
     * @return {@code true} if the {@link AdjacentDirection} is {@link #ALL} or {@link #DIAGONAL_ONLY}
     */
    public static boolean isDiagonal(final AdjacentDirection adjacentDirection) {
        return adjacentDirection == ALL || adjacentDirection == DIAGONAL_ONLY;
    }
}
