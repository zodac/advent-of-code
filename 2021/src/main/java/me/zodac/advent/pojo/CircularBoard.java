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

package me.zodac.advent.pojo;

/**
 * A game board that loops back on itself.
 */
public record CircularBoard(int maxValue) {

    /**
     * Creates a {@link CircularBoard} with a maximum value.
     *
     * @param maxValue the maximum value for the board
     * @return the created {@link CircularBoard}
     */
    public static CircularBoard createWithMaxValue(final int maxValue) {
        return new CircularBoard(maxValue);
    }

    /**
     * Moves {@code spacesToMove} along the {@link CircularBoard} from the {@code startPosition}, then returns the final position.
     *
     * <p>
     * As the board is circular, once the maximum position is passed, we loop back to the start
     *
     * @param startPosition the initial position
     * @param spacesToMove  the number of spaces to move
     * @return the final position on the {@link CircularBoard}
     */
    public int moveOnBoardAndGetNewPosition(final int startPosition, final long spacesToMove) {
        return (int) (startPosition + spacesToMove - 1) % maxValue + 1;
    }
}
