/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.zodac.advent.pojo.Direction;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.AdjacentDirection;
import me.zodac.advent.pojo.grid.AdjacentPointsSelector;
import me.zodac.advent.pojo.grid.Grid;

/**
 * Solution for 2024, Day 4.
 *
 * @see <a href="https://adventofcode.com/2024/day/4">[2024: 04] Ceres Search</a>
 */
public final class Day04 {

    private static final char FIRST_LETTER_OF_XMAS = 'X';
    private static final String XMAS_SEARCH_WORD = "XMAS";

    private static final char CENTRE_OF_X_MAS = 'A';
    // Possible combinations of 'MAS' in an 'X' formation with natural ordering of Points
    private static final Set<String> POSSIBLE_X_MAS_WORDS = Set.of("SSMM", "MMSS", "MSMS", "SMSM");

    private Day04() {

    }

    /**
     * Given a {@link Grid} of {@link Character}s, find all occurrances of the {@link Character}s: 'X', 'M', 'A', 'S', in any order. The letters can
     * overlap and be used for multiple matches. For example, the {@link Grid} below as <b>2</b> occurances:
     *
     * <pre>
     *     - X - X -
     *     - - M M -
     *     - - - A -
     *     - - - S S
     * </pre>
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the number of "XMAS" matches in the {@link Grid}
     */
    public static long countOccurancesOfXmas(final Grid<Character> characterGrid) {
        final Collection<Point> starts = new ArrayList<>();
        final Character[][] grid = characterGrid.getInternalGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                final Point point = Point.of(i, j);
                final char value = characterGrid.at(point);

                if (value != FIRST_LETTER_OF_XMAS) {
                    continue;
                }

                // Find the next 3 points in each direction, and check if the values add to 'XMAS'
                for (final Direction direction : Direction.ALL_VALUES) {
                    if (isValidStartOfXmas(point, direction, characterGrid)) {
                        starts.add(point);
                    }
                }
            }
        }

        return starts.size();
    }

    private static boolean isValidStartOfXmas(final Point point, final Direction direction, final Grid<Character> characterGrid) {
        final List<Point> pointsInLine = point.findPointsInLine(direction, 3); // Find "-MAS"
        final String result = pointsInLine
            .stream()
            .filter(characterGrid::exists)
            .map(characterGrid::at)
            .map(String::valueOf)
            .collect(Collectors.joining());

        return XMAS_SEARCH_WORD.equals(result);
    }

    /**
     * Given a {@link Grid} of {@link Character}s, find all occurrances of the {@link Character} 'A' which has 'M' and 'S' in a line along both
     * diagonal, in any order. The letters can overlap and be used for multiple matches. For example, the {@link Grid} below as <b>2</b> occurances:
     *
     * <pre>
     *     M - S - M
     *     - A - A -
     *     M - S - M
     * </pre>
     *
     * @param characterGrid the input {@link Character} {@link Grid}
     * @return the number of "MAS" matches in X-formation in the {@link Grid}
     */
    public static long countOccurancesOfMasAsX(final Grid<Character> characterGrid) {
        final Collection<Point> starts = new ArrayList<>();
        final Character[][] grid = characterGrid.getInternalGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                final Point point = Point.of(i, j);
                final char value = characterGrid.at(point);

                if (value != CENTRE_OF_X_MAS) {
                    continue;
                }

                if (isValidCenterOfXmas(point, characterGrid)) {
                    starts.add(point);
                }
            }
        }

        return starts.size();
    }

    private static boolean isValidCenterOfXmas(final Point point, final Grid<Character> characterGrid) {
        final String combination = point
            .getAdjacentPoints(AdjacentPointsSelector.bounded(false, AdjacentDirection.DIAGONAL_ONLY, characterGrid.numberOfRows()))
            .filter(characterGrid::exists)
            .sorted() // Must sort so the Points are in a consistent order so we can compare to the expected Strings
            .map(characterGrid::at)
            .map(String::valueOf)
            .collect(Collectors.joining());

        return POSSIBLE_X_MAS_WORDS.contains(combination);
    }
}
