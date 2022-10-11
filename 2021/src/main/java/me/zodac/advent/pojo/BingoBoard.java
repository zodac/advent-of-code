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

package me.zodac.advent.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class defining a bingo board.
 */
public final class BingoBoard {

    // As numbers are marked on the board, this value will replace the existing value
    private static final int MARKED_VALUE = 0;

    // Used to easily find the index for a given number on the board
    private final Map<Integer, Pair<Integer, Integer>> cellsAndIndex = new HashMap<>();

    private final int[][] board;

    private BingoBoard(final List<Integer> boardNumbers, final int boardSize) {
        board = new int[boardSize][boardSize];

        int index = 0;
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                final int value = boardNumbers.get(index++);

                board[row][column] = value;
                cellsAndIndex.put(value, Pair.of(row, column));
            }
        }
    }

    /**
     * Creates a {@link BingoBoard}.
     *
     * <p>
     * The board is populated row by row.
     *
     * @param boardNumbers a {@link List} of {@link Integer}s used to populate the board
     * @param boardSize    the size of the {@link BingoBoard} rows and columns
     * @return the created {@link BingoBoard}
     * @throws IllegalArgumentException thrown if {@code boardNumbers} is empty, or if the boardSize does not match the input {@code boardNumbers}
     */
    public static BingoBoard create(final List<Integer> boardNumbers, final int boardSize) {
        if (boardNumbers.isEmpty()) {
            throw new IllegalArgumentException("boardNumbers cannot be empty!");
        }

        if (boardNumbers.size() != (boardSize * boardSize)) {
            throw new IllegalArgumentException(String.format("Expected %1$dx%1$d numbers, found %2$d", boardSize, boardNumbers.size()));
        }

        return new BingoBoard(boardNumbers, boardSize);
    }

    /**
     * Marks off a number in the {@link BingoBoard}. If the number does not exist in the {@link BingoBoard}, nothing happens.
     *
     * @param number the number to mark off in the {@link BingoBoard}
     */
    public void mark(final int number) {
        final Pair<Integer, Integer> indexToMark = cellsAndIndex.get(number);

        if (indexToMark == null) {
            return;
        }

        board[indexToMark.first()][indexToMark.second()] = MARKED_VALUE;
    }

    /**
     * Checks if the {@link BingoBoard} is a winner.
     *
     * <p>
     * A winning {@link BingoBoard} has had an entire column or row marked off.
     *
     * <p>
     * We first check all rows if any is complete, then all column.
     *
     * @return <code>true</code> if the {@link BingoBoard} is a winner
     * @see #mark(int)
     */
    public boolean isWinner() {
        for (final int[] row : board) {
            boolean winner = true;
            for (final int columnInRow : row) {
                winner &= (columnInRow == MARKED_VALUE);
            }

            if (winner) {
                return true;
            }
        }

        for (int column = 0; column < board[0].length; column++) {
            boolean winner = true;
            for (final int[] row : board) {
                winner &= (row[column] == MARKED_VALUE);
            }

            if (winner) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds the sum of all non-marked numbers in the {@link BingoBoard}.
     *
     * <p>
     * If a number has been marked, its value will be set to {@link #MARKED_VALUE}, so it will not be included in the sum.
     *
     * @return the sum of all non-marked numbers in the {@link BingoBoard}
     */
    public long sum() {
        long sum = 0L;

        for (final int[] row : board) {
            for (int column = 0; column < board[0].length; column++) {
                sum += row[column];
            }
        }

        return sum;
    }
}
