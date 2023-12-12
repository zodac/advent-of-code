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
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import me.zodac.advent.pojo.Point;

/**
 * Solution for 2023, Day 11.
 *
 * @see <a href="https://adventofcode.com/2023/day/11">[2023: 11] Cosmic Expansion</a>
 */
public final class Day11 {

    private Day11() {

    }

    private static <E> List<E> findValuesLessThan(final Collection<E> values, final E value, final Comparator<? super E> comparator) {
        return values
            .stream()
            .filter(v -> Objects.compare(value, v, comparator) == 1)
            .toList();
    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values, final int expansionSize) {
        final List<List<Character>> grid = new ArrayList<>();
        final List<Integer> emptyRows = new ArrayList<>();
        final List<Integer> emptyColumns = new ArrayList<>();

        for (final String value : values) {
            final List<Character> chars = new ArrayList<>();
            for (final char ch : value.toCharArray()) {
                chars.add(ch);
            }

            grid.add(chars);
        }

        for (int i = 0; i < grid.size(); i++) {
            final List<Character> row = grid.get(i);
            boolean hasNoGalaxies = true;

            for (final Character value : row) {
                if (value == '#') {
                    hasNoGalaxies = false;
                    break;
                }
            }

            if (hasNoGalaxies) {
                emptyRows.add(i);
            }
        }

        for (int j = 0; j < grid.getFirst().size(); j++) {
            boolean hasNoGalaxies = true;

            for (final List<Character> characters : grid) {
                final char value = characters.get(j);
                if (value == '#') {
                    hasNoGalaxies = false;
                    break;
                }
            }

            if (hasNoGalaxies) {
                emptyColumns.add(j);
            }
        }

        final List<Point> galaxies = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            final List<Character> row = grid.get(i);

            for (int j = 0; j < row.size(); j++) {
                final char val = row.get(j);
                if (val == '#') {
                    final int extraColumns = (findValuesLessThan(emptyColumns, j, Integer::compareTo).size() * (expansionSize-1));
                    final int extraRows = (findValuesLessThan(emptyRows, i, Integer::compareTo).size() * (expansionSize-1));
                    galaxies.add(Point.of(i + extraRows, j + extraColumns));
                }
            }
        }

        long total = 0L;

        for (int i = 0; i < galaxies.size(); i++) {
            final Point currentGalaxy = galaxies.get(i);

            for (int j = i + 1; j < galaxies.size(); j++) {
                // final long val = pathExists(expandedGrid, currentGalaxy, galaxies.get(j));
                final long val = calculateDistance(currentGalaxy, galaxies.get(j));
                // System.out.printf("Galaxy #%d -> #%d: %d%n", (i + 1), (j + 1), val);
                if (val != -1) {
                    total += val;
                }
            }
        }

        return total;
    }

    private static long calculateDistance(final Point first, final Point second) {
        return Math.abs(first.x() - second.x()) + Math.abs(first.y() - second.y());
    }

    private static Character[][] deepCopy(final Character[][] input) {
        return Arrays.stream(input)
            .map(Character[]::clone)
            .toArray(array -> input.clone());
    }

    private static int pathExists(Character[][] input, final Point start, final Point target) {
        final Character[][] matrix = deepCopy(input);
        Node source = new Node(start.x(), start.y(), 0);
        Queue<Node> queue = new LinkedList<Node>();

        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;

        queue.add(source);

        while (!queue.isEmpty()) {
            Node popped = queue.poll();

            if (popped.x == target.x() && popped.y == target.y()) {
                return popped.distanceFromSource;
            } else {
                matrix[popped.x][popped.y] = '0';

                List<Node> neighbourList = addNeighbours(popped, matrix, numOfRows, numOfColumns);
                queue.addAll(neighbourList);
            }
        }
        return -1;
    }

    private static List<Node> addNeighbours(Node poped, Character[][] matrix, final int numOfRows, final int numOfColumns) {

        List<Node> list = new LinkedList<Node>();

        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && matrix[poped.x - 1][poped.y] != '0') {
            list.add(new Node(poped.x - 1, poped.y, poped.distanceFromSource + 1));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && matrix[poped.x + 1][poped.y] != '0') {
            list.add(new Node(poped.x + 1, poped.y, poped.distanceFromSource + 1));
        }
        if ((poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && matrix[poped.x][poped.y - 1] != '0') {
            list.add(new Node(poped.x, poped.y - 1, poped.distanceFromSource + 1));
        }
        if ((poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && matrix[poped.x][poped.y + 1] != '0') {
            list.add(new Node(poped.x, poped.y + 1, poped.distanceFromSource + 1));
        }
        return list;
    }

    static class Node {
        int x;
        int y;
        int distanceFromSource;

        Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.distanceFromSource = dis;
        }
    }
}
