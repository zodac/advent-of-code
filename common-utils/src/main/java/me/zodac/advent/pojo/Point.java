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
    public static Point create() {
        return new Point(0, 0);
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
