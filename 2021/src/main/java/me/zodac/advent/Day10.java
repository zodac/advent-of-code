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
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.Pair;
import me.zodac.advent.pojo.SyntaxLine;
import me.zodac.advent.util.CollectionUtils;

/**
 * Solution for 2021, Day 10.
 *
 * @see <a href="https://adventofcode.com/2021/day/10">AoC 2021, Day 10</a>
 */
public final class Day10 {

    private static final Map<Character, Integer> SYNTAX_ERROR_SCORE_FOR_SYMBOL = Map.of(
        ')', 3,
        ']', 57,
        '}', 1_197,
        '>', 25_137
    );

    private static final Map<Character, Integer> COMPLETION_SCORE_FOR_SYMBOL = Map.of(
        ')', 1,
        ']', 2,
        '}', 3,
        '>', 4
    );

    private Day10() {

    }

    /**
     * For the given {@link List} of {@link SyntaxLine}s, evaluate them using {@link SyntaxLine#evaluateSyntaxErrorScoreForLine()}. This will return
     * the first error symbol on the line, which can then be mapped to {@link #SYNTAX_ERROR_SCORE_FOR_SYMBOL}. Sum these up for each
     * {@link SyntaxLine} to calculate the total syntax error score.
     *
     * <p>
     * Note that any valid {@link SyntaxLine} will not return a valid key to use for {@link #SYNTAX_ERROR_SCORE_FOR_SYMBOL}, so we default to
     * <b>0</b>, which allows us to sum all values safely.
     *
     * @param syntaxLines the {@link SyntaxLine}s whose syntax error scores are to be calculated
     * @return the syntax error score for all {@link SyntaxLine}s
     * @see SyntaxLine#evaluateSyntaxErrorScoreForLine()
     */
    public static long calculateSyntaxErrorScoreForLines(final Collection<SyntaxLine> syntaxLines) {
        return syntaxLines
            .stream()
            .map(SyntaxLine::evaluateSyntaxErrorScoreForLine)
            .map(Pair::first)
            .mapToInt(errorSymbol -> SYNTAX_ERROR_SCORE_FOR_SYMBOL.getOrDefault(errorSymbol, 0))
            .sum();

    }

    /**
     * For the given {@link List} of {@link SyntaxLine}s, evaluate them using {@link SyntaxLine#evaluateSyntaxErrorScoreForLine()}. We can then filter
     * this to only include evaluated {@link SyntaxLine}s that have some remaining open symbols, as an invalid {@link SyntaxLine} would return an
     * empty {@link Deque}, as would a valid but completed {@link SyntaxLine}.
     *
     * <p>
     * We iterate over the remaining symbols (in reverse, so we get the most recent open symbol first), find their matching close symbol, and
     * calculate the completion score for the {@link SyntaxLine}.
     *
     * <p>
     * Once we have all completion scores, we sort the {@link List} and retrieve the middle index for the final score.
     *
     * @param syntaxLines the {@link SyntaxLine}s whose middle completion score is to be found
     * @return the value of the middle index for the completion scores for the valid {@link SyntaxLine}s
     * @see SyntaxLine#evaluateSyntaxErrorScoreForLine()
     * @see CollectionUtils#getMiddleValueOfList(List)
     */
    public static long calculateMiddleScoreForIncompleteLines(final Collection<SyntaxLine> syntaxLines) {
        final List<Deque<Character>> openSymbolsForIncompleteLines = syntaxLines
            .stream()
            .map(SyntaxLine::evaluateSyntaxErrorScoreForLine)
            .filter(evaluatedSyntaxLine -> !evaluatedSyntaxLine.second().isEmpty())
            .map(Pair::second)
            .toList();

        final List<Long> scores = new ArrayList<>(openSymbolsForIncompleteLines.size());
        for (final Deque<Character> remainingOpenSymbolsForLine : openSymbolsForIncompleteLines) {
            long score = 0L;

            // Iterate over the remaining open symbols to find their required close symbol
            for (final char remainingOpenSymbol : remainingOpenSymbolsForLine) {
                final char requiredCloseSymbol = SyntaxLine.getMatchingCloseSymbol(remainingOpenSymbol);
                score = calculateNewCompletionScore(score, requiredCloseSymbol);
            }
            scores.add(score);
        }

        return CollectionUtils.getMiddleValueOfList(scores);
    }

    private static long calculateNewCompletionScore(final long currentScore, final char closeSymbol) {
        final int scoreForCloseSymbol = COMPLETION_SCORE_FOR_SYMBOL.get(closeSymbol);
        return (currentScore * 5) + scoreForCloseSymbol;
    }
}
