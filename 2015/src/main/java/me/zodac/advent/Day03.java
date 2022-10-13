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

package me.zodac.advent;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.util.MathUtils;

/**
 * Solution for 2015, Day 3.
 *
 * @see <a href="https://adventofcode.com/2015/day/3">AoC 2015, Day 3</a>
 */
public final class Day03 {

    private Day03() {

    }

    /**
     * For the given {@link Direction}s, a {@link Point} moves to a new house. Counts the number of unique houses.
     *
     * @param values the {@link Direction}s
     * @return the number of unique houses
     */
    public static int countUniqueHouses(final Iterable<Direction> values) {
        final Collection<Point> visitedHouses = new HashSet<>();
        Point currentPoint = Point.create();

        visitedHouses.add(currentPoint);

        for (final Direction direction : values) {
            currentPoint = currentPoint.move(direction);
            visitedHouses.add(currentPoint);
        }

        return visitedHouses.size();
    }

    /**
     * For the given {@link Direction}s, a {@link Point} moves to a new house for each even index, and another {@link Point} moves to a new house for
     * each odd index. Counts the number of unique houses.
     *
     * @param values the {@link Direction}s
     * @return the number of unique houses
     */
    public static int countUniqueHousesTwoUsers(final List<Direction> values) {
        final Collection<Point> visitedHouses = new HashSet<>();
        Point currentPointUserOne = Point.create();
        Point currentPointUserTwo = Point.create();

        visitedHouses.add(currentPointUserOne);
        visitedHouses.add(currentPointUserTwo);

        final int numberOfEntries = values.size();
        for (int i = 0; i < numberOfEntries; i++) {
            final Direction direction = values.get(i);

            if (MathUtils.isEven(i)) {
                currentPointUserOne = currentPointUserOne.move(direction);
                visitedHouses.add(currentPointUserOne);
            } else {
                currentPointUserTwo = currentPointUserTwo.move(direction);
                visitedHouses.add(currentPointUserTwo);
            }
        }

        return visitedHouses.size();
    }
}
