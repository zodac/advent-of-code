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

import java.util.Arrays;
import me.zodac.advent.pojo.CircularBoard;
import me.zodac.advent.pojo.DeterministicDie;

/**
 * Solution for 2021, Day 21.
 *
 * @see <a href="https://adventofcode.com/2021/day/21">AoC 2021, Day 21</a>
 */
public final class Day21 {

    private Day21() {

    }

    /**
     * Given a number of player positions on a {@link CircularBoard}, we roll a {@link DeterministicDie} {@code numberOfDiceRolls} times. After each
     * set of rolls, we check the player's score. If it is above the {@code winningScoreThreshold}, the game ends.
     *
     * <p>
     * We then get the losing score (the lowest score) and multiply by the number of dice rolls thrown during the game.
     *
     * @param startPositions        the starting position for each player
     * @param numberOfDiceRolls     the number of times the {@link DeterministicDie} should be rolled
     * @param maxDieValue           the maximum value of the {@link DeterministicDie}
     * @param boardSize             the size of the {@link CircularBoard}
     * @param winningScoreThreshold the winning score for any player to reach
     * @return the lowest player's score times the total number of dice rolls
     */
    public static long getLosingScoreTimesNumberOfRolls(final int[] startPositions,
                                                        final int numberOfDiceRolls,
                                                        final int maxDieValue,
                                                        final int boardSize,
                                                        final long winningScoreThreshold) {
        final DeterministicDie deterministicDie = DeterministicDie.createWithMaxValue(maxDieValue);
        final CircularBoard circularBoard = CircularBoard.createWithMaxValue(boardSize);
        final int numberOfPlayers = startPositions.length;

        final int[] boardPositions = Arrays.copyOf(startPositions, numberOfPlayers);
        final long[] scores = new long[numberOfPlayers];

        long numberOfTimesRolled = 0;
        boolean searchingForWinner = true;

        while (searchingForWinner) {
            for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
                final int currentPosition = boardPositions[playerNumber];
                final long currentScore = scores[playerNumber];

                final long spacesToMove = deterministicDie.rollDieMultipleTimes(numberOfDiceRolls);
                numberOfTimesRolled += numberOfDiceRolls;

                final int newPosition = circularBoard.moveOnBoardAndGetNewPosition(currentPosition, spacesToMove);
                final long newScore = currentScore + newPosition;

                if (newScore >= winningScoreThreshold) {
                    searchingForWinner = false;
                    break; // Break so we don't roll for another player
                }

                boardPositions[playerNumber] = newPosition;
                scores[playerNumber] = newScore;
            }
        }

        Arrays.sort(scores);
        return scores[0] * numberOfTimesRolled;
    }
}
