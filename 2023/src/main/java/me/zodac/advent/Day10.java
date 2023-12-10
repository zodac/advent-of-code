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

package me.zodac.advent;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.CharacterGrid;

/**
 * Solution for 2023, Day 10.
 *
 * @see <a href="https://adventofcode.com/2023/day/10">[2023: 10] Pipe Maze</a>
 */
public final class Day10 {

    private static final char NON_PIPE_SYMBOL = ' ';
    private static final char START_SYMBOL = 'S';
    private static final Pattern START_SYMBOL_PATTERN = Pattern.compile(String.valueOf(START_SYMBOL));

    private Day10() {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final CharacterGrid characterGrid = CharacterGrid.parse(values);
        final Point start = findStartPoint(characterGrid);
        final Set<Point> pipePoints = getPipePoints(start, characterGrid);

        return pipePoints.size() / 2;
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final List<String> values) {
        final CharacterGrid characterGrid = CharacterGrid.parse(values);
        final Point start = findStartPoint(characterGrid);
        final Set<Point> pipePoints = getPipePoints(start, characterGrid);

        final CharacterGrid characterGrid2 = CharacterGrid.parse(values
            .stream()
            .map(s -> START_SYMBOL_PATTERN.matcher(s).replaceAll("L"))
            .toList());

        final Character[][] grid = characterGrid2.getGrid();
        int internalCount = 0;
        for (int i = 0; i < grid.length; i++) {
            final Character[] row = grid[i];
            char currentStart = NON_PIPE_SYMBOL;
            boolean isInsideLoop = false;

            for (int j = 0; j < row.length; j++) {
                final char value = row[j];
                final Point p = Point.of(i, j);

                if (!pipePoints.contains(p)) {
                    if (isInsideLoop) {
                        internalCount++;
                    }
                    continue;
                }

                // Rules:
                // -> | (flip)
                // -> F-J (flip)
                // -> L-7 (flip)
                // -> F-7 (no flip)
                // -> L-J (no flip)

                if (value == '|') {
                    isInsideLoop = !isInsideLoop;
                    currentStart = NON_PIPE_SYMBOL;
                }

                if (value == 'F' || value == 'L') {
                    currentStart = value;
                }

                if (value == 'J') {
                    if (currentStart == 'F') {
                        isInsideLoop = !isInsideLoop;
                    }
                    currentStart = NON_PIPE_SYMBOL;
                }

                if (value == '7') {
                    if (currentStart == 'L') {
                        isInsideLoop = !isInsideLoop;
                    }
                    currentStart = NON_PIPE_SYMBOL;
                }
            }
        }

        return internalCount;
    }

    private static Point findStartPoint(final CharacterGrid characterGrid) {
        final List<Point> startPoints = new ArrayList<>(characterGrid.findValue(START_SYMBOL));
        if (startPoints.size() != 1) {
            throw new IllegalStateException(String.format("Expected 1 '%s' character, found: %d", START_SYMBOL, startPoints.size()));
        }

        return startPoints.getFirst();
    }

    private static Set<Point> getPipePoints(final Point start, final CharacterGrid characterGrid) {
        Point previous = start;
        Point current = start;
        final Set<Point> pipePoints = new LinkedHashSet<>();
        pipePoints.add(start);

        do {
            final char currentChar = get(characterGrid, current);
            final Point upPoint = current.moveUp();
            final Point downPoint = current.moveDown();
            final Point leftPoint = current.moveLeft();
            final Point rightPoint = current.moveRight();
            final Character up = get(characterGrid, upPoint);
            final Character down = get(characterGrid, downPoint);
            final Character left = get(characterGrid, leftPoint);
            final Character right = get(characterGrid, rightPoint);

            if (currentChar == 'S') {
                if (up != ' ' && (up == '|' || up == 'F' || up == '7' || up == 'S') && !upPoint.equals(previous)) {
                    current = upPoint;
                    previous = current.move(1, 0);
                } else if (down != ' ' && (down == '|' || down == 'L' || down == 'J' || down == 'S') && !downPoint.equals(previous)) {
                    current = downPoint;
                    previous = current.move(-1, 0);
                } else if (left != ' ' && (left == '-' || left == 'F' || left == 'L' || left == 'S') && !leftPoint.equals(previous)) {
                    current = leftPoint;
                    previous = current.move(0, 1);
                } else if (right != ' ' && (right == '-' || right == '7' || right == 'J' || right == 'S') && !rightPoint.equals(previous)) {
                    current = rightPoint;
                    previous = current.move(0, -1);
                }
            } else if (currentChar == 'F') {
                if (down != ' ' && (down == '|' || down == 'L' || down == 'J' || down == 'S') && !downPoint.equals(previous)) {
                    current = downPoint;
                    previous = current.move(-1, 0);
                } else if (right != ' ' && (right == '-' || right == '7' || right == 'J' || right == 'S') && !rightPoint.equals(previous)) {
                    current = rightPoint;
                    previous = current.move(0, -1);
                }
            } else if (currentChar == '7') {
                if (down != ' ' && (down == '|' || down == 'L' || down == 'J' || down == 'S') && !downPoint.equals(previous)) {
                    current = downPoint;
                    previous = current.move(-1, 0);
                } else if (left != ' ' && (left == '-' || left == 'F' || left == 'L' || left == 'S') && !leftPoint.equals(previous)) {
                    current = leftPoint;
                    previous = current.move(0, 1);
                }
            } else if (currentChar == '|') {
                if (up != ' ' && (up == '|' || up == 'F' || up == '7' || up == 'S') && !upPoint.equals(previous)) {
                    current = upPoint;
                    previous = current.move(1, 0);
                } else if (down != ' ' && (down == '|' || down == 'L' || down == 'J' || down == 'S') && !downPoint.equals(previous)) {
                    current = downPoint;
                    previous = current.move(-1, 0);
                }
            } else if (currentChar == '-') {
                if (left != ' ' && (left == '-' || left == 'F' || left == 'L' || left == 'S') && !leftPoint.equals(previous)) {
                    current = leftPoint;
                    previous = current.move(0, 1);
                } else if (right != ' ' && (right == '-' || right == '7' || right == 'J' || right == 'S') && !rightPoint.equals(previous)) {
                    current = rightPoint;
                    previous = current.move(0, -1);
                }
            } else if (currentChar == 'L') {
                if (up != ' ' && (up == '|' || up == 'F' || up == '7' || up == 'S') && !upPoint.equals(previous)) {
                    current = upPoint;
                    previous = current.move(1, 0);
                } else if (right != ' ' && (right == '-' || right == '7' || right == 'J' || right == 'S') && !rightPoint.equals(previous)) {
                    current = rightPoint;
                    previous = current.move(0, -1);
                }
            } else if (currentChar == 'J') {
                if (up != ' ' && (up == '|' || up == 'F' || up == '7' || up == 'S') && !upPoint.equals(previous)) {
                    current = upPoint;
                    previous = current.move(1, 0);
                } else if (left != ' ' && (left == '-' || left == 'F' || left == 'L' || left == 'S') && !leftPoint.equals(previous)) {
                    current = leftPoint;
                    previous = current.move(0, 1);
                }
            }

            pipePoints.add(current);

        } while (!current.equals(start));
        return pipePoints;
    }

    private static Character get(final CharacterGrid characterGrid, final Point point) {
        try {
            return characterGrid.at(point);
        } catch (final Exception e) {
            return ' ';
        }
    }
}
