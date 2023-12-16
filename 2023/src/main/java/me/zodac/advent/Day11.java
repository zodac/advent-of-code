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
import java.util.Collection;
import java.util.List;
import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.CharacterGrid;
import me.zodac.advent.util.CollectionUtils;

/**
 * Solution for 2023, Day 11.
 *
 * @see <a href="https://adventofcode.com/2023/day/11">[2023: 11] Cosmic Expansion</a>
 */
public final class Day11 {

    private static final char GALAXY_SYMBOL = '#';

    private Day11() {

    }

    /**
     * Given a {@link List} of {@link String}s representing space, each {@link #GALAXY_SYMBOL} represents a galaxy. In order to account for the
     * expansion of space-time, any row or column that does not contain a galaxy needs to be replaced with {@code expansionSize} number of empty rows
     * or columns.
     *
     * <p>
     * Once the expansion is complete, we count the distance from each galaxy to every other galaxy (in either direction, so only count the distance
     * from galaxy A -> galaxy B once time), then sum up all these distances.
     *
     * @param values        the input space map
     * @param expansionSize the number of additional rows/columns to add to any empty rows/columns
     * @return the total distance between all galaxies
     */
    public static long sumOfDistancesBetweenGalaxies(final List<String> values, final int expansionSize) {
        final CharacterGrid characterGrid = CharacterGrid.parse(values);
        final List<Integer> emptyRows = findRowsWithoutGalaxies(characterGrid);
        final List<Integer> emptyColumns = findColumnsWithoutGalaxies(characterGrid);
        final int actualExpansionSize = expansionSize - 1; // We are replacing rows/columns, not adding, so we don't count the existing ones

        final List<Point> galaxies = findGalaxies(characterGrid, emptyColumns, emptyRows, actualExpansionSize);

        long total = 0L;

        for (int i = 0; i < galaxies.size(); i++) {
            final Point currentGalaxy = galaxies.get(i);

            total += galaxies.subList(i, galaxies.size())
                .stream()
                .mapToLong(currentGalaxy::distanceTo)
                .sum();
        }

        return total;
    }

    private static List<Point> findGalaxies(final CharacterGrid characterGrid,
                                            final Collection<Integer> emptyColumns,
                                            final Collection<Integer> emptyRows,
                                            final int expansionSize) {
        final List<Point> galaxies = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < characterGrid.numberOfRows(); rowIndex++) {
            final Character[] row = characterGrid.rowAt(rowIndex);

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                final char val = row[columnIndex];
                if (val == GALAXY_SYMBOL) {
                    final Point galaxy = createGalaxyPoint(emptyRows, emptyColumns, rowIndex, columnIndex, expansionSize);
                    galaxies.add(galaxy);
                }
            }
        }
        return galaxies;
    }

    private static Point createGalaxyPoint(final Collection<Integer> emptyRows,
                                           final Collection<Integer> emptyColumns,
                                           final int rowIndex,
                                           final int columnIndex,
                                           final int expansionSize) {
        final int emptyColumnsToAdd = CollectionUtils.findValuesLessThan(emptyColumns, columnIndex, Integer::compareTo).size();
        final int emptyRowsToAdd = CollectionUtils.findValuesLessThan(emptyRows, rowIndex, Integer::compareTo).size();

        final int extraColumns = emptyColumnsToAdd * expansionSize;
        final int extraRows = emptyRowsToAdd * expansionSize;
        return Point.of(rowIndex + extraRows, columnIndex + extraColumns);
    }

    private static List<Integer> findColumnsWithoutGalaxies(final CharacterGrid characterGrid) {
        return characterGrid
            .findColumnsWith(character -> character != GALAXY_SYMBOL)
            .toList();
    }

    private static List<Integer> findRowsWithoutGalaxies(final CharacterGrid characterGrid) {
        return characterGrid
            .findRowsWith(character -> character != GALAXY_SYMBOL)
            .toList();
    }
}
