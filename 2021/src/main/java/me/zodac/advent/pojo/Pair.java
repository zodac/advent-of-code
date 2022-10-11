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
 * Simple tuple for two objects.
 *
 * @param <E1> type of first value
 * @param <E2> type of second value
 */
public record Pair<E1, E2>(E1 first, E2 second) {

    /**
     * Create a {@link Pair} with two values.
     *
     * @param first  the first value
     * @param second the second value
     * @param <E1>   type of first value
     * @param <E2>   type of second value
     * @return the created {@link Pair}
     */
    public static <E1, E2> Pair<E1, E2> of(final E1 first, final E2 second) {
        return new Pair<>(first, second);
    }

    /**
     * Create a {@link Pair} with one values, with the second value set to {@code null}.
     *
     * @param first the first value
     * @param <E1>  type of first value
     * @param <E2>  type of second value
     * @return the created {@link Pair}
     */
    public static <E1, E2> Pair<E1, E2> withNull(final E1 first) {
        return of(first, null);
    }
}
