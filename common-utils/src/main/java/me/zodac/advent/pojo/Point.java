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

/**
 * Simple POJO defining a point on a coordinate system.
 *
 * @param x the X coordinate
 * @param y the Y coordinate
 */
public record Point(int x, int y) {

    /**
     * Creates a {@link Point} starting at the origin (0, 0).
     *
     * @return the created {@link Point}
     */
    public static Point atOrigin() {
        return new Point(0, 0);
    }

    /**
     * Creates a {@link Point} starting at the provided (x, y) coordinates.
     *
     * @return the created {@link Point}
     */
    public static Point of(final int x, final int y) {
        return new Point(x, y);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} one unit of space in the {@link Direction} specified.
     *
     * @param direction the {@link Direction} to move
     * @return the moved {@link Point}
     */
    public Point move(final Direction direction) {
        return switch (direction) {
            case BACKWARDS -> moveLeft();
            case DOWN -> moveDown();
            case FORWARD -> moveRight();
            case UP -> moveUp();
            case INVALID -> throw new IllegalStateException(String.format("Cannot move for direction: %s", direction));
        };
    }

    private Point moveUp() {
        return new Point(x, y + 1);
    }

    private Point moveDown() {
        return new Point(x, y - 1);
    }

    private Point moveLeft() {
        return new Point(x - 1, y);
    }

    private Point moveRight() {
        return new Point(x + 1, y);
    }
}
