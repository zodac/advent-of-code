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

package me.zodac.advent.pojo;

/**
 * Simple die that returns a deterministic roll. Each roll will increment the value by 1 until it reaches {@code maxValue}.
 */
public final class DeterministicDie {

    private final long maxValue;

    private long counter;

    private DeterministicDie(final long maxValue, final long counter) {
        this.maxValue = maxValue;
        this.counter = counter;
    }

    /**
     * Creates a {@link DeterministicDie} with a maximum value.
     *
     * @param maxValue the maximum value for a die roll
     * @return the created {@link DeterministicDie}
     */
    public static DeterministicDie createWithMaxValue(final long maxValue) {
        return new DeterministicDie(maxValue, 0L);
    }

    /**
     * Performs multiple rolls of the die, then returns the sum of all rolls.
     *
     * @param numberOfRolls the number of times to roll the die
     * @return the roll values
     * @throws IllegalArgumentException if the {@code maxValue} of a die roll is equal to {@link Long#MAX_VALUE} / {@code numberOfRolls} an overflow
     *                                  may occur
     */
    public long rollDieMultipleTimes(final int numberOfRolls) {
        if (maxValue > (Long.MAX_VALUE / numberOfRolls)) {
            throw new IllegalArgumentException(String.format("Possible overflow with %s rolls with a max value of %s", numberOfRolls, maxValue));
        }

        long totalRollScore = 0;

        for (int i = 0; i < numberOfRolls; i++) {
            totalRollScore += rollDie();
        }

        return totalRollScore;
    }

    /**
     * Performs a single roll of the die.
     *
     * @return the roll value
     */
    public long rollDie() {
        counter = ((counter + 1) % 100);
        return counter;
    }
}