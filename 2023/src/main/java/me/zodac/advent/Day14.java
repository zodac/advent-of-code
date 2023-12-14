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

import java.util.List;
import me.zodac.advent.pojo.RotationDirection;
import me.zodac.advent.pojo.grid.CharacterGrid;
import me.zodac.advent.pojo.grid.Grid;
import me.zodac.advent.search.CycleFinder;
import me.zodac.advent.search.CycleResult;

/**
 * Solution for 2023, Day 14.
 *
 * @see <a href="https://adventofcode.com/2023/day/14">[2023: 14] Parabolic Reflector Dish</a>
 */
public final class Day14 {

    private static final char EMPTY_SYMBOL = '.';
    private static final char ROCK_SYMBOL = 'O';
    private static final int NUMBER_OF_CYCLES = 1_000_000_000;

    private Day14() {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final Grid<Character> grid = CharacterGrid.parse(values);
        final Grid<Character> northTurn = sendNorth(grid);
        return calculateLoad(northTurn);
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final List<String> values) {
        final Grid<Character> grid = CharacterGrid.parse(values);

        final CycleResult<Grid<Character>> cycleResult = CycleFinder.findCycleResult(
            grid,
            Day14::performCycle,
            NUMBER_OF_CYCLES
        );

        if (!cycleResult.doesCycleExist()) {
            throw new IllegalStateException(String.format("Unable to find cycle in %s iterations", NUMBER_OF_CYCLES));
        }

        return calculateLoad(cycleResult.cycleValue());
    }

    private static Grid<Character> performCycle(final Grid<Character> startGrid) {
        final Grid<Character> northTurn = sendNorth(startGrid);
        final Grid<Character> westTurn = sendNorth(northTurn.rotate(RotationDirection.CLOCKWISE));
        final Grid<Character> southTurn = sendNorth(westTurn.rotate(RotationDirection.CLOCKWISE));
        final Grid<Character> eastTurn = sendNorth(southTurn.rotate(RotationDirection.CLOCKWISE));
        return eastTurn.rotate(RotationDirection.CLOCKWISE);
    }

    private static long calculateLoad(final Grid<Character> grid) {
        int multiplier = 1;

        long total = 0;
        for (int i = grid.numberOfRows() - 1; i >= 0; i--) {
            final Character[] row = grid.getRow(i);
            int rocks = 0;

            for (final char c : row) {
                if (c == ROCK_SYMBOL) {
                    rocks++;
                }
            }
            total += (long) rocks * multiplier;
            multiplier++;
        }

        return total;
    }

    private static Grid<Character> sendNorth(final Grid<Character> grid) {
        final Character[][] internalGrid = grid.getInternalGrid();
        final Character[][] newGrid = new Character[internalGrid.length][internalGrid[0].length];
        System.arraycopy(internalGrid[0], 0, newGrid[0], 0, internalGrid[0].length);

        for (int row = 1; row < internalGrid.length; row++) {
            for (int column = 0; column < internalGrid[0].length; column++) {
                final char current = internalGrid[row][column];

                if (current == ROCK_SYMBOL) {
                    final int newRow = findFirstEmptyRowGoingNorth(newGrid, row, column);
                    newGrid[row][column] = EMPTY_SYMBOL;
                    newGrid[newRow][column] = ROCK_SYMBOL;
                } else {
                    newGrid[row][column] = current;
                }
            }
        }

        return new Grid<>(newGrid);
    }

    private static int findFirstEmptyRowGoingNorth(final Character[][] newGrid, final int startRow, final int column) {
        int newRow = startRow;
        for (int i = startRow - 1; i >= 0; i--) {
            if (newGrid[i][column] != EMPTY_SYMBOL) {
                break;
            }
            newRow--;
        }

        return newRow;
    }
}
