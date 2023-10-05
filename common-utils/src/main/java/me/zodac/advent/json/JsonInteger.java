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

package me.zodac.advent.json;

import me.zodac.advent.util.StringUtils;

/**
 * Implementation of {@link JsonElement} defining a single {@code int} value.
 *
 * @param value the input {@link String}
 */
public record JsonInteger(int value) implements JsonElement {

    /**
     * Parses the input {@link String} as an {@code int} and creates a {@link JsonInteger}.
     *
     * @param value the input {@link String}
     * @return the created {@link JsonInteger}
     * @throws IllegalArgumentException thrown if the input {@link String} cannot be parses as an {@code int}
     */
    public static JsonInteger create(final String value) {
        if (!StringUtils.isInteger(value)) {
            throw new IllegalArgumentException(String.format("Cannot parse input '%s' as %s", value, JsonInteger.class.getSimpleName()));
        }

        return new JsonInteger(Integer.parseInt(value));
    }

    @Override
    public int compareTo(final JsonElement o) {
        if (o instanceof JsonInteger) {
            return Integer.compare(value, ((JsonInteger) o).value);
        }

        if (o instanceof JsonList) {
            final JsonList wrappedAsList = JsonList.create(this);
            return wrappedAsList.compareTo(o);
        }

        throw new IllegalArgumentException(String.format("Cannot compare %s to %s", JsonInteger.class.getSimpleName(), o.getClass().getSimpleName()));
    }
}
