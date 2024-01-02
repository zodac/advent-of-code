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

import me.zodac.advent.pojo.Point;
import me.zodac.advent.pojo.grid.BooleanGrid;

/**
 * Solution for 2015, Day 18.
 *
 * @see <a href="https://adventofcode.com/2015/day/18">AoC 2015, Day 18</a>
 */
public final class Day18 {

    private Day18() {

    }

    /**
     * Given a {@link BooleanGrid}, plays the {@link BooleanGrid#playGameOfLife()} a number of times.
     *
     * @param initialState       the inital state of the {@link BooleanGrid}
     * @param numberOfIterations the number of times to execute {@link BooleanGrid#playGameOfLife()}
     * @return the number of {@link BooleanGrid} {@link Point}s that have been turned on
     */
    public static long playGameOfLife(final BooleanGrid initialState, final int numberOfIterations) {
        BooleanGrid booleanGrid = initialState;
        for (int i = 0; i < numberOfIterations; i++) {
            booleanGrid = booleanGrid.playGameOfLife();
        }

        return booleanGrid.sumValues(BooleanGrid.EVALUATOR);
    }

    /**
     * Given a {@link BooleanGrid}, plays the {@link BooleanGrid#playGameOfLife(boolean)} a number of times.
     *
     * <p>
     * For this execute, the corners of the {@link BooleanGrid} must be set to {@code true}.
     *
     * @param initialState       the inital state of the {@link BooleanGrid}
     * @param numberOfIterations the number of times to execute {@link BooleanGrid#playGameOfLife(boolean)}
     * @return the number of {@link BooleanGrid} {@link Point}s that have been turned on
     * @see BooleanGrid#updateCorners(Boolean)
     */
    public static long playGameOfLifeWithCornersAlwaysOn(final BooleanGrid initialState, final int numberOfIterations) {
        BooleanGrid booleanGrid = initialState.updateCorners(true);
        for (int i = 0; i < numberOfIterations; i++) {
            booleanGrid = booleanGrid.playGameOfLife(true);
        }

        return booleanGrid.sumValues(BooleanGrid.EVALUATOR);
    }
}
