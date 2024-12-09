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

import me.zodac.advent.pojo.Pipe;
import me.zodac.advent.pojo.PipeLoop;
import me.zodac.advent.pojo.grid.Grid;

/**
 * Solution for 2023, Day 10.
 *
 * @see <a href="https://adventofcode.com/2023/day/10">[2023: 10] Pipe Maze</a>
 */
public final class Day10 {

    private Day10() {

    }

    /**
     * Given a {@link Grid} of {@link Pipe}s, we need to find the {@link PipeLoop} that exists. From that, we find the number of steps to the furthest
     * part of the {@link PipeLoop}. This can be calculated as {@code numberOfElementsInLoop} / {@code 2}.
     *
     * @param pipeGrid the {@link Grid} of {@link Pipe}s
     * @return the number of steps to the furthest {@link Pipe} of the {@link PipeLoop}
     * @see PipeLoop#size()
     */
    public static long numberOfStepsToFurthestPartOfLoop(final Grid<Pipe> pipeGrid) {
        final PipeLoop pipeLoop = PipeLoop.create(pipeGrid, pipe -> pipe == Pipe.START);
        return pipeLoop.size() / 2;
    }

    /**
     * Given a {@link Grid} of {@link Pipe}s, we need to find the {@link PipeLoop} that exists. Once it has been found, we need to count the number of
     * {@link Pipe} elements within the loop.
     *
     * @param pipeGrid the {@link Grid} of {@link Pipe}s
     * @return the number of steps to the furthest {@link Pipe} of the {@link PipeLoop}
     * @see PipeLoop#countPointsInsideLoop()
     */
    public static long numberOfPointsInsidePipeLoop(final Grid<Pipe> pipeGrid) {
        final PipeLoop pipeLoop = PipeLoop.create(pipeGrid, pipe -> pipe == Pipe.START);
        return pipeLoop.countPointsInsideLoop();
    }
}
