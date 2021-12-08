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

import me.zodac.advent.util.MathUtils;

/**
 * Defines the cost of moving between two positions.
 */
public enum MoveCostType {

    /**
     * The cost for any movement is constant (<b>1</b>).
     */
    CONSTANT {
        @Override
        public long costForMove(final int startPosition, final int endPosition) {
            // We just get the distance from the current location to the possible location
            return Math.abs(startPosition - endPosition);
        }
    },

    /**
     * The cost for movement increases per move (<b>1</b>, then <b>2</b>, then <b>3</b>, etc.).
     */
    VARIABLE {
        @Override
        public long costForMove(final int startPosition, final int endPosition) {
            // This is 1 + 2 + 3 ... + n, described as n*(n+1)/2
            final int diff = Math.abs(startPosition - endPosition);
            return MathUtils.triangularNumber(diff);
        }
    };

    /**
     * Calculates the cost for the move from {@code startPosition} to {@code endPosition}.
     *
     * @param startPosition the start position
     * @param endPosition   the end position
     * @return the cost for the move
     */
    public abstract long costForMove(final int startPosition, final int endPosition);
}
