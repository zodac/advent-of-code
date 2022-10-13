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
