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
import java.util.Set;
import me.zodac.advent.pojo.Point;

/**
 * Solution for 2015, Day 3.
 *
 * @see <a href="https://adventofcode.com/2015/day/3">AoC 2015, Day 3</a>
 */
public final class Day03 {

    private static final char UP_CHARACTER = '^';
    private static final char DOWN_CHARACTER = 'v';
    private static final char LEFT_CHARACTER = '<';
    private static final char RIGHT_CHARACTER = '>';

    private Day03() {

    }


    public static int countUniqueHouses(final Iterable<Character> values) {
        final Collection<Point> visitedHouses = new HashSet<>();
        Point currentPoint = new Point(0, 0);

        visitedHouses.add(currentPoint);

        for (final char character : values) {
            switch (character) {
                case UP_CHARACTER -> currentPoint = new Point(currentPoint.x(), currentPoint.y() + 1);
                case DOWN_CHARACTER -> currentPoint = new Point(currentPoint.x(), currentPoint.y() - 1);
                case LEFT_CHARACTER -> currentPoint = new Point(currentPoint.x() - 1, currentPoint.y());
                case RIGHT_CHARACTER -> currentPoint = new Point(currentPoint.x() + 1, currentPoint.y());
                default -> throw new IllegalStateException(String.format("Invalid character: '%s'", character));
            }

            visitedHouses.add(currentPoint);
        }

        return visitedHouses.size();
    }

    public static int countUniqueHousesTwoUsers(final List<Character> values) {
        final Collection<Point> visitedHouses = new HashSet<>();
        Point currentPointUserOne = new Point(0, 0);
        Point currentPointUserTwo = new Point(0, 0);

        visitedHouses.add(currentPointUserOne);
        visitedHouses.add(currentPointUserTwo);

        final int numberOfEntries = values.size();
        for (int i = 0; i < numberOfEntries; i++) {
            final char character = values.get(i);

            if (i % 2 == 0) {
                switch (character) {
                    case UP_CHARACTER -> currentPointUserOne = new Point(currentPointUserOne.x(), currentPointUserOne.y() + 1);
                    case DOWN_CHARACTER -> currentPointUserOne = new Point(currentPointUserOne.x(), currentPointUserOne.y() - 1);
                    case LEFT_CHARACTER -> currentPointUserOne = new Point(currentPointUserOne.x() - 1, currentPointUserOne.y());
                    case RIGHT_CHARACTER -> currentPointUserOne = new Point(currentPointUserOne.x() + 1, currentPointUserOne.y());
                    default -> throw new IllegalStateException(String.format("Invalid character: '%s'", character));
                }

                visitedHouses.add(currentPointUserOne);
            } else {
                switch (character) {
                    case UP_CHARACTER -> currentPointUserTwo = new Point(currentPointUserTwo.x(), currentPointUserTwo.y() + 1);
                    case DOWN_CHARACTER -> currentPointUserTwo = new Point(currentPointUserTwo.x(), currentPointUserTwo.y() - 1);
                    case LEFT_CHARACTER -> currentPointUserTwo = new Point(currentPointUserTwo.x() - 1, currentPointUserTwo.y());
                    case RIGHT_CHARACTER -> currentPointUserTwo = new Point(currentPointUserTwo.x() + 1, currentPointUserTwo.y());
                    default -> throw new IllegalStateException(String.format("Invalid character: '%s'", character));
                }

                visitedHouses.add(currentPointUserTwo);
            }
        }

        return visitedHouses.size();
    }
}
