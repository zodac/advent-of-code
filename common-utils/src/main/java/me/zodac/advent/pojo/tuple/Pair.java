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

package me.zodac.advent.pojo.tuple;

import java.util.Optional;

/**
 * Simple tuple for two objects.
 *
 * @param first  the first object
 * @param second the second object
 * @param <A>    type of first value
 * @param <B>    type of second value
 */
public record Pair<A, B>(A first, B second) {

    /**
     * Create a {@link Pair} with two values.
     *
     * @param first  the first value
     * @param second the second value
     * @param <A>    type of first value
     * @param <B>    type of second value
     * @return the created {@link Pair}
     */
    public static <A, B> Pair<A, B> of(final A first, final B second) {
        return new Pair<>(first, second);
    }

    /**
     * Create a {@link Pair} with one value, with the second value set to {@link Optional#empty()}.
     *
     * @param first the first value
     * @param <A>   type of first value
     * @param <B>   type of second value
     * @return the created {@link Pair}
     */
    public static <A, B> Pair<A, Optional<B>> ofSingle(final A first) {
        return new Pair<>(first, Optional.empty());
    }
}
