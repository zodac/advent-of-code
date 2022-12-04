/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2022 zodac.me
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

/**
 * Simple tuple for three objects.
 *
 * @param first  the first object
 * @param second the second object
 * @param third  the third object
 * @param <E1>   type of first value
 * @param <E2>   type of second value
 * @param <E3>   type of second value
 */
public record Triple<E1, E2, E3>(E1 first, E2 second, E3 third) {

    /**
     * Create a {@link Triple} with three values.
     *
     * @param first  the first value
     * @param second the second value
     * @param third  the third value
     * @param <E1>   type of first value
     * @param <E2>   type of second value
     * @param <E3>   type of third value
     * @return the created {@link Triple}
     */
    public static <E1, E2, E3> Triple<E1, E2, E3> of(final E1 first, final E2 second, final E3 third) {
        return new Triple<>(first, second, third);
    }
}
