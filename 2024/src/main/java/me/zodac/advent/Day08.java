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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.Grid;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Solution for 2024, Day 8.
 *
 * @see <a href="https://adventofcode.com/2024/day/8">[2024: 08] Resonant Collinearity</a>
 */
public final class Day08 {

    private static final Set<Character> CHARACTERS_TO_IGNORE = Set.of('.');

    private Day08() {

    }

    /**
     * Part 1.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 1 result
     */
    public static long part1(final Grid<Character> characterGrid) {
//        characterGrid.print(false);
        final Map<Character, Set<Point>> pointsByCharacter = getPointsByCharacter(characterGrid);

        final Set<Point> antinodes = new HashSet<>();
        for (final Set<Point> points : pointsByCharacter.values()) {
            for (final Point currentPoint : points) {
                for (final Point nextPoint : points) {
                    if (currentPoint.equals(nextPoint)) {
                        continue;
                    }

                    final Pair<Integer, Integer> delta = currentPoint.deltaTo(nextPoint);
                    final Point possibleAntinode = Point.of(nextPoint.x() + delta.first(), nextPoint.y() + delta.second());
                    if (characterGrid.exists(possibleAntinode)) {
                        antinodes.add(possibleAntinode);
                    }
                }
            }
        }

//        Grid<Character> updated = characterGrid;
//        for (final Point p : antinodes) {
//            var curr = updated.at(p);
//            if (curr == '1') {
//                updated = updated.updateAt(p, '2');
//            } else if (curr == '2') {
//                updated = updated.updateAt(p, '3');
//            } else {
//                updated = updated.updateAt(p, '1');
//            }
//        }

//        System.out.println("----------------");
//
//        updated.print(false);

        return antinodes.size();
    }

    private static Map<Character, Set<Point>> getPointsByCharacter(Grid<Character> characterGrid) {
        final Set<Character> allValues = characterGrid
            .allPoints()
            .stream()
            .map(characterGrid::at)
            .collect(Collectors.toSet());
        allValues.removeAll(CHARACTERS_TO_IGNORE); // Remove values that do not signify antennas

        final Map<Character, Set<Point>> pointsByCharacter = new HashMap<>();
        for (final Character character : allValues) {
            pointsByCharacter.put(character, characterGrid.findValue(character1 -> character1.equals(character)).collect(Collectors.toSet()));
        }
        return pointsByCharacter;
    }

    /**
     * Part 2.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 2 result
     */
    public static long part2(final Grid<Character> characterGrid) {
//        characterGrid.print(false);
        final Map<Character, Set<Point>> pointsByCharacter = getPointsByCharacter(characterGrid);

        final Set<Point> antinodes = new HashSet<>();
        for (final Set<Point> points : pointsByCharacter.values()) {
            for (final Point currentPoint : points) {
                for (final Point nextPoint : points) {
                    if (currentPoint.equals(nextPoint)) {
                        continue;
                    }
                    antinodes.add(nextPoint);

                    final Pair<Integer, Integer> delta = currentPoint.deltaTo(nextPoint);
                    Point possibleAntinode = Point.of(nextPoint.x() + delta.first(), nextPoint.y() + delta.second());
                    while (characterGrid.exists(possibleAntinode)) {
                        antinodes.add(possibleAntinode);
                        possibleAntinode = Point.of(possibleAntinode.x() + delta.first(), possibleAntinode.y() + delta.second());
                    }
                }
            }
        }

//        Grid<Character> updated = characterGrid;
//        for (final Point p : antinodes) {
//            var curr = updated.at(p);
//            if (curr == '1') {
//                updated = updated.updateAt(p, '2');
//            } else if (curr == '2') {
//                updated = updated.updateAt(p, '3');
//            } else {
//                updated = updated.updateAt(p, '1');
//            }
//        }
//
//        System.out.println("----------------");
//
//        updated.print(false);

        return antinodes.size();
    }
}
