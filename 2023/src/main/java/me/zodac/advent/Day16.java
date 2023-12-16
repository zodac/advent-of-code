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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.Grid;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Solution for 2023, Day 16.
 *
 * @see <a href="https://adventofcode.com/2023/day/16">[2023: 16] The Floor Will Be Lava</a>
 */
public final class Day16 {

    private Day16() {

    }

    public record Beam(Point point, char value, Direction direction) {

    }

    /**
     * Part 1.
     *
     * @param grid the input values
     * @return the part 1 result
     */
    public static long part1(final Grid<Character> grid) {
        final Point start = Point.of(0, -1);
        final Direction startDirection = Direction.RIGHT;
        final Set<Point> energizedPoints = findEnergizedPoints(grid, start, startDirection);
        return energizedPoints.size();
    }

    private static Set<Point> findEnergizedPoints(final Grid<Character> grid, final Point start, final Direction startDirection) {
        final Set<Point> energizedPoints = new HashSet<>();
        final Set<Beam> doneBeams = new HashSet<>();

        List<Beam> beams = new ArrayList<>();
        beams.add(new Beam(start, '.', startDirection));

        int count = 0;
        while (!beams.isEmpty()) {
            energizedPoints.addAll(beams.stream().map(Beam::point).filter(p -> p.x() >= 0 && p.y() >= 0).collect(Collectors.toSet()));

            List<Beam> newBeams = new ArrayList<>();
            for (final Beam beam : beams) {
                final Direction nextDirection;
                if (beam.value == '.') {
                    nextDirection = beam.direction();
                } else if (beam.value == '/') {
                    nextDirection = switch (beam.direction()) {
                        case DOWN -> Direction.LEFT;
                        case UP -> Direction.RIGHT;
                        case RIGHT -> Direction.UP;
                        case LEFT -> Direction.DOWN;
                        default -> throw new RuntimeException();
                    };
                } else if (beam.value == '\\') {
                    nextDirection = switch (beam.direction()) {
                        case DOWN -> Direction.RIGHT;
                        case UP -> Direction.LEFT;
                        case RIGHT -> Direction.DOWN;
                        case LEFT -> Direction.UP;
                        default -> throw new RuntimeException();
                    };
                } else if (beam.value == '-' && !Set.of(Direction.LEFT, Direction.RIGHT).contains(beam.direction())) {
                    nextDirection = Direction.RIGHT;

                    try {
                        final Point splitPoint = beam.point.move(Direction.LEFT);
                        final Beam newBeam = new Beam(splitPoint, grid.at(splitPoint), Direction.LEFT);
                        newBeams.add(newBeam);
                    } catch (final ArrayIndexOutOfBoundsException e) {

                    }
                } else if (beam.value == '|' && !Set.of(Direction.UP, Direction.DOWN).contains(beam.direction())) {
                    nextDirection = Direction.UP;

                    try {
                        final Point splitPoint = beam.point.move(Direction.DOWN);
                        final Beam newBeam = new Beam(splitPoint, grid.at(splitPoint), Direction.DOWN);
                        newBeams.add(newBeam);
                    } catch (final ArrayIndexOutOfBoundsException e) {

                    }
                } else {
                    nextDirection = beam.direction;
                }

                final Point nextPoint = beam.point.move(nextDirection);
                try {
                    final Beam newBeam = new Beam(nextPoint, grid.at(nextPoint), nextDirection);
                    newBeams.add(newBeam);
                } catch (final ArrayIndexOutOfBoundsException e) {

                }

                doneBeams.add(beam);
            }

            if (beams.equals(newBeams)) {
                break;
            }

            newBeams.removeAll(doneBeams);
            beams.clear();
            beams.addAll(newBeams);
        }

        return energizedPoints;
    }

    /**
     * Part 2.
     *
     * @param grid the input values
     * @return the part 2 result
     */
    public static long part2(final Grid<Character> grid) {
        final Set<Pair<Point, Direction>> startPointsAndDirection = new HashSet<>();

        // Not counting corners
        for (int j = 0; j < grid.numberOfColumns(); j++) {
            final Point startPoint = Point.of(0, j);
            startPointsAndDirection.add(Pair.of(startPoint, Direction.DOWN));
        }

        for (int j = 0; j < grid.numberOfColumns(); j++) {
            final Point startPoint = Point.of(grid.numberOfRows() - 1, j);
            startPointsAndDirection.add(Pair.of(startPoint, Direction.UP));
        }

        for (int i = 0; i < grid.numberOfRows(); i++) {
            final Point startPoint = Point.of(i, 0);
            startPointsAndDirection.add(Pair.of(startPoint, Direction.RIGHT));
        }

        for (int i = 0; i < grid.numberOfRows(); i++) {
            final Point startPoint = Point.of(i, grid.numberOfColumns() - 1);
            startPointsAndDirection.add(Pair.of(startPoint, Direction.LEFT));
        }

        return startPointsAndDirection
            .stream()
            .mapToInt(pair -> findEnergizedPoints(grid, pair.first(), pair.second()).size())
            .max()
            .orElse(0);
    }
}
