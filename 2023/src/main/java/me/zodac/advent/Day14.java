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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.grid.CharacterGrid;
import me.zodac.advent.util.ArrayUtils;

/**
 * Solution for 2023, Day 14.
 *
 * @see <a href="https://adventofcode.com/2023/day/14">[2023: 14] Parabolic Reflector Dish</a>
 */
public final class Day14 {

//    private Day14() {
//
//    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final Character[][] grid = CharacterGrid.parse(values).getInternalGrid();
        final Character[][] northTurn = new Day14().sendNorth(grid);
        return calculateLoad(northTurn);
    }

    int norths = 0;
    int souths = 0;
    int wests = 0;
    int easts = 0;

    private class Final {

        final Character[][] grid;

        public Final(final Character[][] grid) {
            this.grid = ArrayUtils.deepCopy(grid);
        }

        @Override
        public boolean equals(final Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof final Final aFinal)) {
                return false;
            }
            return Arrays.deepEquals(grid, aFinal.grid);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(grid);
        }

        public Character[][] getGrid() {
            return grid;
        }
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public long part2(final List<String> values) {
        final Character[][] grid = CharacterGrid.parse(values).getInternalGrid();
        Character[][] finalGrid = ArrayUtils.deepCopy(grid);
        Final f = new Final(finalGrid);
        final int cycles = 1_000_000_000;
//        final int cycles = 1000;
//        final int cycles = 3;

        final List<Final> seen = new ArrayList<>();
        int cycleStart = 0;

        while(true) {
            final Character[][] northTurn = sendNorth(finalGrid);
            final Character[][] westTurn = sendWest(northTurn);
            final Character[][] southTurn = sendSouth(westTurn);
            final Character[][] eastTurn = sendEast(southTurn);

            if (Arrays.deepEquals(eastTurn, finalGrid)) {
                break;
            }

            finalGrid = ArrayUtils.deepCopy(eastTurn);
            f = new Final(finalGrid);

            int index = seen.indexOf(f);
            if (index != -1) {
                cycleStart = index;
                break;
            }
            seen.add(f);
        }

        finalGrid = seen.get((cycleStart + ((cycles - cycleStart) % (seen.size() - cycleStart))) - 1).getGrid();
        return calculateLoad(finalGrid);
    }

    private static long calculateLoad(final Character[][] grid) {
        int multiplier = 1;

        long total = 0;
        for (int i = grid.length - 1; i >= 0; i--) {
            final Character[] row = grid[i];
            int rocks = 0;

            for (final char c : row) {
                if (c == 'O') {
                    rocks++;
                }
            }
            total += (long) rocks * multiplier;
            multiplier++;
        }

        return total;
    }

    final Map<Character[][], Character[][]> north = new HashMap<>();
    final Map<Character[][], Character[][]> south = new HashMap<>();
    final Map<Character[][], Character[][]> east = new HashMap<>();
    final Map<Character[][], Character[][]> west = new HashMap<>();

    private Character[][] sendNorth(final Character[][] grid) {
        if (north.containsKey(grid)) {
            norths++;
            return north.get(grid);
        }

        final Character[][] newGrid = new Character[grid.length][grid[0].length];
        System.arraycopy(grid[0], 0, newGrid[0], 0, grid[0].length);

        for (int row = 1; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                final char current = grid[row][column];

                if (current == 'O') {
                    final int newRow = findFirstEmptyRowGoingNorth(newGrid, row, column);
                    newGrid[row][column] = '.';
                    newGrid[newRow][column] = 'O';
                } else {
                    newGrid[row][column] = current;
                }
            }
        }

        final Character[][] updatedGrid = ArrayUtils.deepCopy(newGrid);
        north.put(grid, updatedGrid);
        return updatedGrid;
    }


    private Character[][] sendSouth(final Character[][] grid) {
        if (south.containsKey(grid)) {
            souths++;
            return south.get(grid);
        }

        final Character[][] newGrid = new Character[grid.length][grid[0].length];
        System.arraycopy(grid[grid.length - 1], 0, newGrid[newGrid.length - 1], 0, grid[0].length);

        for (int row = grid.length - 2; row >= 0; row--) {
            for (int column = 0; column < grid[0].length; column++) {
                final char current = grid[row][column];

                if (current == 'O') {
                    final int newRow = findFirstEmptyRowGoingSouth(newGrid, row, column);
                    newGrid[row][column] = '.';
                    newGrid[newRow][column] = 'O';
                } else {
                    newGrid[row][column] = current;
                }
            }
        }

        final Character[][] updatedGrid = ArrayUtils.deepCopy(newGrid);
        south.put(grid, updatedGrid);
        return updatedGrid;
    }

    private Character[][] sendWest(final Character[][] grid) {
        if (west.containsKey(grid)) {
            wests++;
            return west.get(grid);
        }
        final Character[][] newGrid = new Character[grid.length][grid[0].length];

        for (int column = 0; column < 1; column++) {
            for (int row = 0; row < grid.length; row++) {
                newGrid[row][column] = grid[row][column];
            }
        }

        for (int column = 1; column < grid[0].length; column++) {
            for (int row = 0; row < grid.length; row++) {
                final char current = grid[row][column];

                if (current == 'O') {
                    final int newColumn = findFirstEmptyColumnGoingWest(newGrid, row, column);
                    newGrid[row][column] = '.';
                    newGrid[row][newColumn] = 'O';
                } else {
                    newGrid[row][column] = current;
                }
            }
        }

        final Character[][] updatedGrid = ArrayUtils.deepCopy(newGrid);
        west.put(grid, updatedGrid);
        return updatedGrid;
    }

    private Character[][] sendEast(final Character[][] grid) {
        if (east.containsKey(grid)) {
            easts++;
            return east.get(grid);
        }
        final Character[][] newGrid = new Character[grid.length][grid[0].length];

        for (int column = grid.length - 1; column < grid.length; column++) {
            for (int row = 0; row < grid.length; row++) {
                newGrid[row][column] = grid[row][column];
            }
        }

        for (int column = grid[0].length - 2; column >= 0; column--) {
            for (int row = 0; row < grid.length; row++) {
                final char current = grid[row][column];

                if (current == 'O') {
                    final int newColumn = findFirstEmptyColumnGoingEast(newGrid, row, column);
                    newGrid[row][column] = '.';
                    newGrid[row][newColumn] = 'O';
                } else {
                    newGrid[row][column] = current;
                }
            }
        }

        final Character[][] updatedGrid = ArrayUtils.deepCopy(newGrid);
        east.put(grid, updatedGrid);
        return updatedGrid;
    }

    private static int findFirstEmptyColumnGoingWest(Character[][] newGrid, int row, int startColumn) {
        int newColumn = startColumn;
        for (int i = startColumn - 1; i >= 0; i--) {
            final char previousColumnCharacter = newGrid[row][i];
            if (previousColumnCharacter != '#' && previousColumnCharacter != 'O') {
                newColumn--;
            } else {
                break;
            }
        }

        return newColumn;
    }

    private static int findFirstEmptyColumnGoingEast(Character[][] newGrid, int row, int startColumn) {
        int newColumn = startColumn;
        for (int i = startColumn + 1; i < newGrid[newGrid.length - 1].length; i++) {
            final char nextColumnCharacter = newGrid[row][i];
            if (nextColumnCharacter != '#' && nextColumnCharacter != 'O') {
                newColumn++;
            } else {
                break;
            }
        }

        return newColumn;
    }

    private static int findFirstEmptyRowGoingNorth(final Character[][] newGrid, final int startRow, final int column) {
        int newRow = startRow;
        for (int i = startRow - 1; i >= 0; i--) {
            final char previousRowCharacter = newGrid[i][column];
            if (previousRowCharacter != '#' && previousRowCharacter != 'O') {
                newRow--;
            } else {
                break;
            }
        }

        return newRow;
    }

    private static int findFirstEmptyRowGoingSouth(final Character[][] newGrid, final int startRow, final int column) {
        int newRow = startRow;
        for (int i = startRow + 1; i < newGrid.length; i++) {
            final char nextRowCharacter = newGrid[i][column];
            if (nextRowCharacter != '#' && nextRowCharacter != 'O') {
                newRow++;
            } else {
                break;
            }
        }

        return newRow;
    }
}
