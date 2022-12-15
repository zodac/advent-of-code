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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Simple POJO defining a point on a coordinate system.
 *
 * @param x the X coordinate
 * @param y the Y coordinate
 */
public record Point(int x, int y) {

    /**
     * Defines the 2D grid that the {@link Point} is on as having infinite bounds. For example, this would allow us to consider all neighbours, not
     * just ones within a range.
     */
    public static final int INFINITE_GRID_SIZE = -1;

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
        return move(0, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#DOWN}.
     *
     * @return the moved {@link Point}
     */
    public Point moveDown() {
        return move(0, -DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#LEFT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveLeft() {
        return move(-DEFAULT_MOVE_DISTANCE, 0);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#RIGHT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveRight() {
        return move(DEFAULT_MOVE_DISTANCE, 0);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#UP} and
     * {@link Direction#LEFT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveUpLeft() {
        return move(-DEFAULT_MOVE_DISTANCE, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a new {@link Point} which has moved the current {@link Point} {@value DEFAULT_MOVE_DISTANCE} spaces {@link Direction#UP} and
     * {@link Direction#RIGHT}.
     *
     * @return the moved {@link Point}
     */
    public Point moveUpRight() {
        return move(DEFAULT_MOVE_DISTANCE, DEFAULT_MOVE_DISTANCE);
    }

    /**
     * Returns a {@link Set} of the direct neighbours for a {@link Point}. Can return up to 4 neighbours and itself, depending on where the input is
     * located. Assumes the {@link Point}s are on a bounded 2D array, with a size of {@code gridSize}.
     *
     * @param includeSelf whether to include the input itself as a 'neighbour'
     * @param gridSize    the limits of the 2D array for the {@link Point}s
     * @return the neighbouring {@link Point}s (and self if chosen)
     */
    public Set<Point> getDirectNeighbours(final boolean includeSelf, final int gridSize) {
        final int row = x;
        final int column = y;

        final Set<Point> neighbours = new HashSet<>();
        neighbours.add(of(next(row, gridSize), column));
        neighbours.add(of(previous(row, gridSize), column));
        neighbours.add(of(row, next(column, gridSize)));
        neighbours.add(of(row, previous(column, gridSize)));

        if (includeSelf) {
            neighbours.add(this);
        } else {
            // Remove current point, in case it was added in above calculations
            neighbours.remove(this);
        }
        return neighbours;
    }

    /**
     * Returns a {@link Set} of the neighbours for a {@link Point}. Can return up to 8 neighbours and itself, depending on where the input is located.
     * Assumes the {@link Point}s are on a bounded 2D array, with a size of {@code gridSize}.
     *
     * @param includeSelf whether to include the input itself as a 'neighbour'
     * @param gridSize    the limits of the 2D array for the {@link Point}s
     * @return the neighbouring {@link Point}s (and self if chosen)
     */
    public Set<Point> getNeighbours(final boolean includeSelf, final int gridSize) {
        final int row = x;
        final int column = y;

        final Set<Point> neighbours = new HashSet<>();
        neighbours.add(of(next(row, gridSize), column));
        neighbours.add(of(previous(row, gridSize), column));
        neighbours.add(of(row, next(column, gridSize)));
        neighbours.add(of(row, previous(column, gridSize)));
        neighbours.add(of(next(row, gridSize), next(column, gridSize)));
        neighbours.add(of(previous(row, gridSize), previous(column, gridSize)));
        neighbours.add(of(next(row, gridSize), previous(column, gridSize)));
        neighbours.add(of(previous(row, gridSize), next(column, gridSize)));

        if (includeSelf) {
            neighbours.add(this);
        } else {
            // Remove current point, in case it was added in above calculations
            neighbours.remove(this);
        }
        return neighbours;
    }

    private static int next(final int rowOrColumn, final int gridSize) {
        if (gridSize == INFINITE_GRID_SIZE) {
            return rowOrColumn + 1;
        }

        return Math.min(rowOrColumn + 1, (gridSize - 1));
    }

    private static int previous(final int rowOrColumn, final int gridSize) {
        if (gridSize == INFINITE_GRID_SIZE) {
            return rowOrColumn - 1;
        }

        return Math.max(rowOrColumn - 1, 0);
    }
}
