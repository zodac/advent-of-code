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

package me.zodac.advent.pojo;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import me.zodac.advent.pojo.grid.Grid;

/**
 * Utility class used to define a loop of {@link Point}s within a {@link Grid}.
 *
 * <p>
 * Currently only supports {@link Pipe}, but hopefully this can be made generic eventually.
 */
public class Loop {

    private final Grid<Pipe> grid;
    private final Set<Point> pointsInLoop;
    private boolean isInsideLoop;

    /**
     * Package-private constructor, only called from {@link LoopFinder}.
     *
     * @param grid         the {@link Grid}
     * @param pointsInLoop the known {@link Point}s in the {@link Loop}
     */
    Loop(final Grid<Pipe> grid, final Set<Point> pointsInLoop) {
        this.grid = grid;
        this.pointsInLoop = new LinkedHashSet<>(pointsInLoop);
        isInsideLoop = false;
    }

    /**
     * Creates the {@link Loop} for a looping of points within a {@link Grid}. Using the provided {@link Predicate}, the start {@link Point} of the
     * loop is determined, and the {@link LoopFinder} works to find a looping of {@link Point}s back to the start {@link Point}.
     *
     * @param grid                the {@link Grid} of elements which contains the {@link Loop}
     * @param startPointPredicate the {@link Predicate} defining how to find the start {@link Point}
     * @return the {@link Loop}
     * @throws IllegalArgumentException if no valid {@link Loop} is found for the provided {@link Grid}
     * @see LoopFinder#findLoop()
     */
    public static Loop create(final Grid<Pipe> grid, final Predicate<? super Pipe> startPointPredicate) {
        try {
            final LoopFinder loopFinder = new LoopFinder(grid, startPointPredicate);
            return loopFinder.findLoop();
        } catch (final IllegalStateException e) {
            throw new IllegalArgumentException(String.format("Unable to find any loop in provided %s", Grid.class.getSimpleName()), e);
        }
    }

    /**
     * The number of elements in the {@link Loop}.
     *
     * @return the size of the {@link Loop}
     */
    public int size() {
        return pointsInLoop.size();
    }

    /**
     * Counts the number of {@link Point}s 'trapped' inside the {@link Loop}. This does not include any of the {@link Point}s on the {@link Loop}
     * itself, but any {@link Point}s that are surrounded entirely by {@link Loop} {@link Point}s.
     *
     * @return the number of {@link Point}s inside the {@link Loop}
     */
    public int countPointsInsideLoop() {
        int numberOfPointsInsidePipeLoop = 0;
        for (int rowIndex = 0; rowIndex < grid.numberOfRows(); rowIndex++) {
            final Pipe[] row = grid.rowAt(rowIndex);
            numberOfPointsInsidePipeLoop += countPointsInsidePipeLoopForRow(row, rowIndex);
        }
        return numberOfPointsInsidePipeLoop;
    }

    private int countPointsInsidePipeLoopForRow(final Pipe[] row, final int rowIndex) {
        int countForRow = 0;
        Pipe currentCorner = Pipe.EMPTY; // Used to keep track of a corner to identify: F--J, F--7, L--J, L--7

        for (int j = 0; j < row.length; j++) {
            final Pipe value = row[j];
            final Point p = Point.of(rowIndex, j);

            if (!pointsInLoop.contains(p)) {
                if (isInsideLoop) {
                    countForRow++;
                }
                continue;
            }

            // Rules:
            // -> | (flip)
            // -> F-J (flip)
            // -> L-7 (flip)
            // -> F-7 (no flip)
            // -> L-J (no flip)

            if (value == Pipe.VERTICAL) {
                isInsideLoop = !isInsideLoop;
                currentCorner = Pipe.EMPTY;
            }

            if (value == Pipe.TOP_LEFT_CORNER || value == Pipe.BOTTOM_LEFT_CORNER) {
                currentCorner = value;
            }

            if (value == Pipe.BOTTOM_RIGHT_CORNER) {
                if (currentCorner == Pipe.TOP_LEFT_CORNER) {
                    isInsideLoop = !isInsideLoop;
                }
                currentCorner = Pipe.EMPTY;
            }

            if (value == Pipe.TOP_RIGHT_CORNER) {
                if (currentCorner == Pipe.BOTTOM_LEFT_CORNER) {
                    isInsideLoop = !isInsideLoop;
                }
                currentCorner = Pipe.EMPTY;
            }
        }

        return countForRow;
    }
}
