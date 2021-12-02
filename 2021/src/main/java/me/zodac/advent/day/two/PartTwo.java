/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
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

package me.zodac.advent.day.two;

import java.util.List;
import me.zodac.advent.util.Direction;
import me.zodac.advent.util.Movement;

/**
 * @see <a href="https://adventofcode.com/2021/day/2#part2">AoC 2021, Day 2, Part 2</a>
 */
public record PartTwo() {

    /**
     * Iterates over and calculates the magnitude of the supplied {@link Movement}s.
     *
     * <p>
     * A {@link Movement} with {@link Direction} of {@link Direction#DOWN} or {@link Direction#UP} does not change the vertical value, but instead
     * sets and {@code aim} value. When a {@link Direction#FORWARD} {@link Movement} is found, it will calculate the change in the vertical position
     * as this {@code aim} and the number of spaces. It will also increment the horizontal position at this time.
     *
     * <p>
     * The magnitude is the resultant vertical multiplied by the resultant horizontal.
     *
     * @param movements the {@link Movement}s to iterate over
     * @return the magnitude of all vertical and horizontal {@link Movement}s
     */
    public long magnitudeOfAllMovementsWithAim(final List<Movement> movements) {
        long horizontal = 0L;
        long vertical = 0L;
        long aim = 0L;

        for (final Movement movement : movements) {
            final Direction direction = movement.direction();
            final int spaces = movement.spaces();

            switch (direction) {
                case UP -> aim -= spaces;
                case DOWN -> aim += spaces;
                case FORWARD -> {
                    horizontal += spaces;
                    vertical += (aim * spaces);
                }
                default -> throw new IllegalStateException(String.format("Cannot handle direction: '%s'", direction));
            }
        }

        return horizontal * vertical;
    }
}
