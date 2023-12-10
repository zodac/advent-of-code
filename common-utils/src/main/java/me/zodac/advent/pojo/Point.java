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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.grid.AdjacentPointsSelector;

/**
 * Simple POJO defining a point on a coordinate system.
 *
 * @param x the X coordinate, or row
 * @param y the Y coordinate, or column
 */
public record Point(int x, int y) {

    private static final Pattern POINT_DELIMITER_PATTERN = Pattern.compile(" -> ");
    private static final int DEFAULT_MOVE_DISTANCE = 1;

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
     * Creates a {@link Point} from an input in the format:
     * <pre>
     *     [x],[y]
     * </pre>
     *
     * @param input the input to parse
     * @return the {@link Point}
     */
    public static Point parse(final String input) {
        final String[] pairOfCoordinates = input.split(",");
        return of(Integer.parseInt(pairOfCoordinates[0]), Integer.parseInt(pairOfCoordinates[1]));
    }

    /**
     * Creates a {@link List} of {@link Point}s from an input in the format:
     * <pre>
     *     [x1],[y1] -> [x2],[y2] -> [x3],[y3]... -> [xn],[yn]
     * </pre>
     *
     * @param input the input to parse
     * @return the {@link List} of {@link Point}s
     */
    public static List<Point> ofMany(final CharSequence input) {
        final List<Point> points = new ArrayList<>();

        final String[] coordinates = POINT_DELIMITER_PATTERN.split(input);

        for (final String coordinate : coordinates) {
            points.add(parse(coordinate));
        }

        return points;
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces in the {@link Direction} specified.
     *
     * @param direction the {@link Direction} to move
     * @return the moved {@link Point}
     */
    public Point move(final Direction direction) {
        return switch (direction) {
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case INVALID -> throw new IllegalStateException(String.format("Cannot move for direction: %s", direction));
        };
    }

    /**
     * Returns a new {@link Point} which has moved {@code deltaX} spaces for the X coordinate, and {@code deltaY} spaces for the Y coordinate.
     *
     * @param deltaX the change in the X coordinate
     * @param deltaY the change in the Y coordinate
     * @return the moved {@link Point}
     */
    public Point move(final int deltaX, final int deltaY) {
        return of(x + deltaX, y + deltaY);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#UP}.
     *
     * @return the moved {@link Point}
     */
    public Point moveUp() {
        return move(-DEFAULT_MOVE_DISTANCE, 0);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#DOWN}.
     *
     * @return the moved {@link Point}
     */
    public Point moveDown() {
        return move(DEFAULT_MOVE_DISTANCE, 0);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#LEFT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveLeft() {
        return move(0, -DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#RIGHT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveRight() {
        return move(0, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#UP} and
     * {@link Direction#LEFT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveUpLeft() {
        return move(-DEFAULT_MOVE_DISTANCE, -DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#UP} and
     * {@link Direction#RIGHT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveUpRight() {
        return move(-DEFAULT_MOVE_DISTANCE, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#DOWN} and
     * {@link Direction#LEFT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveDownLeft() {
        return move(DEFAULT_MOVE_DISTANCE, -DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#DOWN} and
     * {@link Direction#RIGHT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveDownRight() {
        return move(DEFAULT_MOVE_DISTANCE, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a {@link Set} of the adjacent {@link Point}s for the current {@link Point}.
     *
     * @param adjacentPointsSelector definition of what to consider an adjacent {@link Point}
     * @return the adjacent {@link Point}s according to the {@link AdjacentPointsSelector}
     */
    public Set<Point> getAdjacentPoints(final AdjacentPointsSelector adjacentPointsSelector) {
        final Set<Point> adjacentPoints = new HashSet<>();

        // Current point
        if (adjacentPointsSelector.includeSelf()) {
            adjacentPoints.add(this);
        }

        // 'Directly' adjacent points (left, right, up, down)
        final Set<Point> directAdjacentPoints = getDirectAdjacentPoints(adjacentPointsSelector);
        adjacentPoints.addAll(directAdjacentPoints);

        // Diagonally adjacent points
        if (adjacentPointsSelector.includeDiagonals()) {
            final Set<Point> diagonalAdjacentPoints = getDiagonalAdjacentPoints(adjacentPointsSelector);
            adjacentPoints.addAll(diagonalAdjacentPoints);
        }

        return adjacentPoints;
    }

    private Set<Point> getDirectAdjacentPoints(final AdjacentPointsSelector adjacentPointsSelector) {
        final Set<Point> adjacentPoints = new HashSet<>();

        if (adjacentPointsSelector.allowOutOfBounds() || x - DEFAULT_MOVE_DISTANCE >= 0) {
            adjacentPoints.add(moveUp());
        }

        if (adjacentPointsSelector.allowOutOfBounds() || x + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize()) {
            adjacentPoints.add(moveDown());
        }

        if (adjacentPointsSelector.allowOutOfBounds() || y + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize()) {
            adjacentPoints.add(moveRight());
        }

        if (adjacentPointsSelector.allowOutOfBounds() || y - DEFAULT_MOVE_DISTANCE >= 0) {
            adjacentPoints.add(moveLeft());
        }

        return adjacentPoints;
    }

    private Set<Point> getDiagonalAdjacentPoints(final AdjacentPointsSelector adjacentPointsSelector) {
        final Set<Point> adjacentPoints = new HashSet<>();

        if (adjacentPointsSelector.allowOutOfBounds()
            || (x - DEFAULT_MOVE_DISTANCE >= 0 && y + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize())) {
            adjacentPoints.add(moveUpRight());
        }

        if (adjacentPointsSelector.allowOutOfBounds()
            || (x + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize() && y - DEFAULT_MOVE_DISTANCE >= 0)) {
            adjacentPoints.add(moveDownLeft());
        }

        if (adjacentPointsSelector.allowOutOfBounds()
            || (x - DEFAULT_MOVE_DISTANCE >= 0 && y - DEFAULT_MOVE_DISTANCE >= 0)) {
            adjacentPoints.add(moveUpLeft());
        }

        if (adjacentPointsSelector.allowOutOfBounds()
            || (x + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize() && y + DEFAULT_MOVE_DISTANCE < adjacentPointsSelector.gridSize())) {
            adjacentPoints.add(moveDownRight());
        }

        return adjacentPoints;
    }
}
