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

package me.zodac.advent;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.Point;

/**
 * Solution for 2022, Day 9.
 *
 * @see <a href="https://adventofcode.com/2022/day/9">AoC 2022, Day 9</a>
 */
public final class Day09 {

    private Day09() {

    }

    /**
     * Part 1.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static long part1(final Collection<String> values) {
        Point currentH = Point.of(0, 0);
        Point currentT = Point.of(0, 0);

        Set<Point> visitedByTail = new LinkedHashSet<>();
        visitedByTail.add(currentT);

        for (final String value : values) {
            final String[] tokens = value.split("\\s+");
            final String dir = tokens[0];
            final int num = Integer.parseInt(tokens[1]);


//            System.out.println();
            System.out.println(value);
            for(int i = 0; i < num; i++){
                System.out.println(i);

                switch (dir){
                    case "U" -> currentH = Point.of(currentH.x() + 1, currentH.y());
                    case "D" -> currentH = Point.of(currentH.x() - 1, currentH.y());
                    case "L" -> currentH = Point.of(currentH.x(), currentH.y() - 1);
                    case "R" -> currentH = Point.of(currentH.x(), currentH.y() + 1);
                }

                if(!areTouching(currentH, currentT)){
//                    System.out.println("not touching");

                    if(currentH.x() == currentT.x()) {
                        if (currentH.y() > currentT.y()) {
                            currentT = Point.of(currentT.x(), currentT.y() + 1);
                        } else {
                            currentT = Point.of(currentT.x(), currentT.y() - 1);
                        }
                    } else if(currentH.y() == currentT.y()) {
                        if (currentH.x() > currentT.x()) {
                            currentT = Point.of(currentT.x() + 1, currentT.y());
                        } else {
                            currentT = Point.of(currentT.x() - 1, currentT.y());
                        }
                    } else {
                        if(currentH.x() < currentT.x() && currentH.y() > currentT.y()){
                            // move down-right
                            currentT = Point.of(currentT.x() - 1, currentT.y() + 1);
                        } else if(currentH.x() < currentT.x() && currentH.y() < currentT.y()){
                            // move down-left
                            currentT = Point.of(currentT.x() - 1, currentT.y() - 1);
                        } else if(currentH.x() > currentT.x() && currentH.y() > currentT.y()){
                            // move up-right
                            currentT = Point.of(currentT.x() + 1, currentT.y() + 1);
                        } else {
                            // move up-left
                            currentT = Point.of(currentT.x() + 1, currentT.y() - 1);
                        }
                    }

                }


                visitedByTail.add(currentT);

                System.out.println("head: " + currentH);
                System.out.println("tail: " + currentT);
                System.out.println();
            }
        }

        visitedByTail.forEach(System.out::println);

        return visitedByTail.size();
    }

    private static boolean areTouching(final Point h, final Point t){
        final Set<Point> n = getNeighbours(h.x(), h.y());
        n.add(h);
        return n.contains(t);
    }

    private static Set<Point> getNeighbours(final int row, final int column) {
        final Set<Point> neighbours = new HashSet<>();
        neighbours.add(Point.of(next(row), column));
        neighbours.add(Point.of(previous(row), column));
        neighbours.add(Point.of(row, next(column)));
        neighbours.add(Point.of(row, previous(column)));
        neighbours.add(Point.of(next(row), next(column)));
        neighbours.add(Point.of(previous(row), previous(column)));
        neighbours.add(Point.of(next(row), previous(column)));
        neighbours.add(Point.of(previous(row), next(column)));

        // Remove current point, in case it was added in above calculations
        neighbours.remove(Point.of(row, column));
        return neighbours;
    }

    private static int next(final int rowOrColumn) {
        return rowOrColumn + 1;
    }

    private static int previous(final int rowOrColumn) {
        return rowOrColumn - 1;
    }



    /**
     * Part 2.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static long part2(final Collection<String> values) {
        Point currentH = Point.of(0, 0);

        final Point[] tails = {
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0),
            Point.of(0, 0)
        };

        Set<Point> visitedByTail = new LinkedHashSet<>();
        visitedByTail.add(tails[tails.length - 1]);

        for (final String value : values) {
            final String[] tokens = value.split("\\s+");
            final String dir = tokens[0];
            final int num = Integer.parseInt(tokens[1]);


//            System.out.println();
//            System.out.println(value);
            for(int i = 0; i < num; i++){
//                System.out.println(i);

                switch (dir){
                    case "U" -> currentH = Point.of(currentH.x() + 1, currentH.y());
                    case "D" -> currentH = Point.of(currentH.x() - 1, currentH.y());
                    case "L" -> currentH = Point.of(currentH.x(), currentH.y() - 1);
                    case "R" -> currentH = Point.of(currentH.x(), currentH.y() + 1);
                }

                for(int tail = 0; tail < tails.length; tail++) {
                    final Point target = tail == 0 ? currentH : tails[tail -1];
                    Point currentT = tails[tail];

                    if (!areTouching(target, currentT)) {
//                    System.out.println("not touching");

                        if (target.x() == currentT.x()) {
                            if (target.y() > currentT.y()) {
                                currentT = Point.of(currentT.x(), currentT.y() + 1);
                            } else {
                                currentT = Point.of(currentT.x(), currentT.y() - 1);
                            }
                        } else if (target.y() == currentT.y()) {
                            if (target.x() > currentT.x()) {
                                currentT = Point.of(currentT.x() + 1, currentT.y());
                            } else {
                                currentT = Point.of(currentT.x() - 1, currentT.y());
                            }
                        } else {
                            if (target.x() < currentT.x() && target.y() > currentT.y()) {
                                // move down-right
                                currentT = Point.of(currentT.x() - 1, currentT.y() + 1);
                            } else if (target.x() < currentT.x() && target.y() < currentT.y()) {
                                // move down-left
                                currentT = Point.of(currentT.x() - 1, currentT.y() - 1);
                            } else if (target.x() > currentT.x() && target.y() > currentT.y()) {
                                // move up-right
                                currentT = Point.of(currentT.x() + 1, currentT.y() + 1);
                            } else {
                                // move up-left
                                currentT = Point.of(currentT.x() + 1, currentT.y() - 1);
                            }
                        }
                    }

                    tails[tail] = currentT;
                }


                visitedByTail.add(tails[tails.length - 1]);

//                System.out.println("head: " + currentH);
//                for(int j = 0; j < tails.length; j++) {
//                    System.out.println("tail" + j + ": " + tails[j]);
//                }
//                System.out.println();
            }
        }

        return visitedByTail.size();
    }
}
