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

package me.zodac.advent;

import java.util.Collection;
import java.util.HashSet;
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.Grid;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Solution for 2024, Day 6.
 *
 * @see <a href="https://adventofcode.com/2024/day/6">[2024: 06] Guard Gallivant</a>
 */
public final class Day06 {

    private Day06() {

    }

    /**
     * Part 1.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 1 result
     */
    public static long part1(final Grid<Character> characterGrid) {
        final Point startPoint = characterGrid.findValue(c -> c == '^').toList().getFirst();
        final Collection<Point> pointsInPath = new HashSet<>();

        Point currentPoint = startPoint;
        Direction currentDirection = Direction.UP;

        do {
            pointsInPath.add(currentPoint);
            final Point nextPoint = currentPoint.move(currentDirection);

            if (!characterGrid.exists(nextPoint)) {
                break;
            }

            if (characterGrid.at(nextPoint) == '#') {
                currentDirection = rotate(currentDirection);
            }

            currentPoint = currentPoint.move(currentDirection);
        } while (true);

        return pointsInPath.size();
    }

    private static Direction rotate(final Direction direction) {
        return switch (direction) {
            case DOWN -> Direction.LEFT;
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT, INVALID -> throw new RuntimeException();
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
            case UP -> Direction.RIGHT;
        };
    }

    /**
     * Part 2.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 2 result
     */
    public static long part2(final Grid<Character> characterGrid) {
        final Point startPoint = characterGrid.findValue(c -> c == '^').toList().getFirst();
        final Collection<Point> pointsInPath = new HashSet<>();

        Point currentPoint = startPoint;
        Direction currentDirection = Direction.UP;

        do {
            pointsInPath.add(currentPoint);
            final Point nextPoint = currentPoint.move(currentDirection);

            if (!characterGrid.exists(nextPoint)) {
                break;
            }

            if (characterGrid.at(nextPoint) == '#') {
                currentDirection = rotate(currentDirection);
            }

            currentPoint = currentPoint.move(currentDirection);
        } while (characterGrid.exists(currentPoint));
        pointsInPath.remove(startPoint);

        long count = 0L;
        for (final Point point : pointsInPath) {
            final Grid<Character> updatedGrid = characterGrid.updateAt(point, '#');
            if (hasLoop(updatedGrid, startPoint)) {
                count++;
            }
        }

        return count;
    }

    private static boolean hasLoop(final Grid<Character> grid, final Point startPoint) {
        Point currentPoint = startPoint;
        Direction currentDirection = Direction.UP;
        final Collection<Pair<Point, Direction>> visitedPoints = new HashSet<>();

        do {
            if (!visitedPoints.add(Pair.of(currentPoint, currentDirection))) {
                return true;
            }

            final Point nextPoint = currentPoint.move(currentDirection);
            if (!grid.exists(nextPoint)) {
                return false;
            }

            if (grid.at(nextPoint) == '#') {
                currentDirection = rotate(currentDirection);

                final Point nextPoint2 = currentPoint.move(currentDirection);
                if (grid.at(nextPoint2) == '#') {
                    currentDirection = rotate(currentDirection);
                }
            }

            currentPoint = currentPoint.move(currentDirection);
        } while (true);
    }
}
