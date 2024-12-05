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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.AdjacentDirection;
import me.zodac.advent.pojo.grid.AdjacentPointsSelector;
import me.zodac.advent.pojo.grid.Grid;

/**
 * Solution for 2024, Day 4.
 *
 * @see <a href="https://adventofcode.com/2024/day/4">[2024: 04] Ceres Search</a>
 */
public final class Day04 {

    private Day04() {

    }

    /**
     * Part 1.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 1 result
     */
    public static long countOccurancesOfXmas(final Grid<Character> characterGrid) {
        final List<Point> starts = new ArrayList<>();
        final Character[][] grid = characterGrid.getInternalGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                final Point firstPoint = Point.of(i, j);
                final char firstValue = characterGrid.at(firstPoint);

                if (firstValue != 'X') {
                    continue;
                }

                final AdjacentPointsSelector adjacentPointsSelector = AdjacentPointsSelector.bounded(false, AdjacentDirection.ALL, grid.length);
                final List<Point> secondPoints = firstPoint.getAdjacentPoints(adjacentPointsSelector).toList();

                for (final Point secondPoint : secondPoints) {
                    try {
                        final char secondValue = characterGrid.at(secondPoint);

                        if (secondValue == 'M') {
                            int xDiff = secondPoint.x() - firstPoint.x();
                            int yDiff = secondPoint.y() - firstPoint.y();

                            final Point thirdPoint = Point.of(secondPoint.x() + xDiff, secondPoint.y() + yDiff);
                            final char thirdValue = characterGrid.at(thirdPoint);

                            if (thirdValue == 'A') {
                                final Point fourthPoint = Point.of(thirdPoint.x() + xDiff, thirdPoint.y() + yDiff);

                                final char fourthValue = characterGrid.at(fourthPoint);

                                if (fourthValue == 'S') {
                                    starts.add(firstPoint);
                                }
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }

            }
        }

        return starts.size();
    }

    /**
     * Part 2.
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the part 2 result
     */
    public static long countOccurancesOfMasAsX(final Grid<Character> characterGrid) {
        final List<Point> starts = new ArrayList<>();
        final Character[][] grid = characterGrid.getInternalGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                final Point firstPoint = Point.of(i, j);
                final char firstValue = characterGrid.at(firstPoint);

                if (firstValue != 'A') {
                    continue;
                }

                final Point upLeft = firstPoint.moveUpLeft();
                final Point downLeft = firstPoint.moveDownLeft();
                final Point upRight = firstPoint.moveUpRight();
                final Point downRight = firstPoint.moveDownRight();

                try {
                    final char upLeftVal = characterGrid.at(upLeft);
                    final char downLeftVal = characterGrid.at(downLeft);
                    final char upRightVal = characterGrid.at(upRight);
                    final char downRightVal = characterGrid.at(downRight);

                    final String combination = "" + upLeftVal + downRightVal + upRightVal + downLeftVal;

                    final Set<String> possibleCombinations = Set.of("SMSM", "MSMS", "MSSM", "SMMS");
                    if (possibleCombinations.contains(combination)) {
                        starts.add(firstPoint);
                    }


//                    if ((upLeftVal == 'S' && downRightVal == 'M' && upRightVal == 'S' && downLeftVal == 'M')
//                        || (upLeftVal == 'M' && downRightVal == 'S' && upRightVal == 'M' && downLeftVal == 'S')
//                        || (upLeftVal == 'M' && downRightVal == 'S' && upRightVal == 'S' && downLeftVal == 'M')
//                        || (upLeftVal == 'S' && downRightVal == 'M' && upRightVal == 'M' && downLeftVal == 'S')) {
//                        starts.add(firstPoint);
//                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return starts.size();
    }
}
