/*
 * Zero-Clause BSD License
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

import java.util.List;
import me.zodac.advent.util.MathUtils;

/**
 * POJO defining a line on a coordinate gird, defined by two {@link Point}s.
 *
 * @param first  the first {@link Point}
 * @param second the second {@link Point}
 */
public record Line(Point first, Point second) {

    /**
     * Defines the number of coordinates per {@link Line}, which is <b>4</b>: (x1, y1) and (x2, y2).
     */
    public static final int NUMBER_OF_COORDINATES_PER_LINE = 4;

    /**
     * Creates a {@link Line} based off the input {@code coordinates}.
     *
     * <p>
     * Note that the input {@link List} of {@link Integer}s will fill the coordinates in the order:
     * <ol>
     *     <li>x1</li>
     *     <li>y1</li>
     *     <li>x2</li>
     *     <li>y2</li>
     * </ol>
     *
     * @param coordinates the {@link Line} coordinates
     * @return the created {@link Line}
     * @throws IllegalArgumentException thrown if the input {@link List} does not have {@link #NUMBER_OF_COORDINATES_PER_LINE} entries
     */
    public static Line of(final List<Integer> coordinates) {
        if (coordinates.size() != NUMBER_OF_COORDINATES_PER_LINE) {
            throw new IllegalArgumentException(
                String.format("Expected %d elements, found %d", NUMBER_OF_COORDINATES_PER_LINE, coordinates.size()));
        }

        final Point firstPoint = Point.of(coordinates.get(0), coordinates.get(1));
        final Point secondPoint = Point.of(coordinates.get(2), coordinates.get(3));
        return new Line(firstPoint, secondPoint);
    }

    /**
     * Returns the maximum value of the x1, x2, y1, y2 coordinates.
     *
     * @return the largest value of the coordinates
     * @see MathUtils#max(int, int...)
     */
    public int maxCoordinateValue() {
        return MathUtils.max(first.x(), second.x(), first.y(), second.y());
    }

    /**
     * Checks if the line is horizontal.
     *
     * @return {@code true} if the line is horizontal
     */
    public boolean isHorizontal() {
        return first.x() == second.x();
    }

    /**
     * Checks if the line is vertical.
     *
     * @return {@code true} if the line is vertical
     */
    public boolean isVertical() {
        return first.y() == second.y();
    }

    /**
     * Checks if the line is a 'perfect' diagonal (meaning a 45-degree angle).
     *
     * @return {@code true} if the line is a 45-degree diagonal
     */
    public boolean isPerfectDiagonal() {
        return Math.abs(first.x() - second.x()) == Math.abs(first.y() - second.y());
    }
}
