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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.zodac.advent.pojo.BingoBoard;
import me.zodac.advent.pojo.Pair;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2021, Day 4.
 *
 * @see <a href="https://adventofcode.com/2021/day/4">AoC 2021, Day 4</a>
 */
public final class Day04 {

    // To be used when no winning bingo board can be found
    private static final int INVALID_WINNING_NUMBER = -1;

    private Day04() {

    }

    /**
     * For the provided {@link BingoBoard} values, we convert the raw values to {@link BingoBoard}s, then iterate through drawing the bingo numbers.
     * We iterate until we find the first {@link BingoBoard} to be a winner.
     *
     * <p>
     * We then calculate the sum of its remaining, un-drawn values. This is multiplied by the winning number, to calculate the final score of the
     * {@link BingoBoard}.
     *
     * @param bingoNumbersToDraw an ordered {@link List} of the numbers to be drawn
     * @param bingoBoardValues   the raw {@link BingoBoard} values
     * @return the final score of the first winning {@link BingoBoard}
     * @see BingoBoard#isWinner()
     */
    public static long finalScoreOfFirstWinningBingoBoard(final Iterable<Integer> bingoNumbersToDraw, final List<String> bingoBoardValues) {
        final List<BingoBoard> bingoBoards = convertBingoBoards(bingoBoardValues);
        final Pair<Integer, BingoBoard> firstWinningNumberAndBingoBoard = drawNumbersAndReturnFirstWinner(bingoNumbersToDraw, bingoBoards);

        if (firstWinningNumberAndBingoBoard.first() == INVALID_WINNING_NUMBER) {
            return 0L;
        }

        return firstWinningNumberAndBingoBoard.second().sum() * firstWinningNumberAndBingoBoard.first();
    }

    /**
     * For the provided {@link BingoBoard} values, we convert the raw values to {@link BingoBoard}s, then iterate through drawing the bingo numbers.
     * We iterate through them all, and keep not of the last {@link BingoBoard} to be a winner.
     *
     * <p>
     * We then calculate the sum of its remaining, un-drawn values. This is multiplied by the winning number, to calculate the final score of the
     * {@link BingoBoard}.
     *
     * @param bingoNumbersToDraw an ordered {@link List} of the numbers to be drawn
     * @param bingoBoardValues   the raw {@link BingoBoard} values
     * @return the final score of the last winning {@link BingoBoard}
     * @see BingoBoard#isWinner()
     */
    public static long finalScoreOfLastWinningBingoBoard(final Iterable<Integer> bingoNumbersToDraw, final List<String> bingoBoardValues) {
        final List<BingoBoard> bingoBoards = convertBingoBoards(bingoBoardValues);
        final Pair<Integer, BingoBoard> lastWinningNumberAndBingoBoard = drawNumbersAndReturnLastWinner(bingoNumbersToDraw, bingoBoards);

        if (lastWinningNumberAndBingoBoard.first() == INVALID_WINNING_NUMBER) {
            return 0L;
        }

        return lastWinningNumberAndBingoBoard.second().sum() * lastWinningNumberAndBingoBoard.first();
    }

    private static Pair<Integer, BingoBoard> drawNumbersAndReturnFirstWinner(final Iterable<Integer> pickedNumbers,
                                                                             final Iterable<BingoBoard> bingoBoards) {
        for (final int pickedNumber : pickedNumbers) {
            for (final BingoBoard bingoBoard : bingoBoards) {
                bingoBoard.mark(pickedNumber);

                if (bingoBoard.isWinner()) {
                    return Pair.of(pickedNumber, bingoBoard);
                }
            }
        }

        return Pair.withNull(INVALID_WINNING_NUMBER);
    }

    private static Pair<Integer, BingoBoard> drawNumbersAndReturnLastWinner(final Iterable<Integer> bingoNumbers,
                                                                            final List<BingoBoard> bingoBoards) {
        Pair<Integer, BingoBoard> lastWinner = Pair.withNull(INVALID_WINNING_NUMBER);

        // If the board is not a winner, it is added to the boards to be checked for the next number
        // If it is a winner, we update 'lastWinner' and stop checking it for future numbers
        List<BingoBoard> bingoBoardsToCheck = new ArrayList<>(bingoBoards);
        for (final int number : bingoNumbers) {
            final List<BingoBoard> nextBingoBoardsToCheck = new ArrayList<>();

            for (final BingoBoard bingoBoard : bingoBoardsToCheck) {
                bingoBoard.mark(number);

                if (bingoBoard.isWinner()) {
                    lastWinner = Pair.of(number, bingoBoard);
                } else {
                    nextBingoBoardsToCheck.add(bingoBoard);
                }
            }

            bingoBoardsToCheck = nextBingoBoardsToCheck;
        }

        return lastWinner;
    }

    private static List<BingoBoard> convertBingoBoards(final List<String> bingoBoardValues) {
        if (bingoBoardValues.isEmpty()) {
            return Collections.emptyList();
        }

        final int boardSize = StringUtils.splitOnWhitespace(bingoBoardValues.get(0)).length;
        final List<BingoBoard> bingoBoards = new ArrayList<>();
        final int numberOfBoardValues = bingoBoardValues.size();

        for (int i = 0; i < numberOfBoardValues; i += boardSize) {
            final StringBuilder boardNumbersRaw = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                boardNumbersRaw.append(bingoBoardValues.get(i + j)).append(' ');
            }

            final List<Integer> boardNumbers =
                Arrays.stream(StringUtils.splitOnWhitespace(boardNumbersRaw.toString()))
                    .filter(input -> !input.isBlank())
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();

            bingoBoards.add(BingoBoard.create(boardNumbers, boardSize));
        }

        return bingoBoards;
    }
}
