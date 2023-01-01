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
 */
public final class JsonInteger implements JsonElement {

    private final int value;

    private JsonInteger(final int value) {
        this.value = value;
    }

    /**
     * Parses the input {@link String} as an {@code int} and creates a {@link JsonInteger}.
     *
     * @param input the input {@link String}
     * @return the created {@link JsonInteger}
     * @throws IllegalArgumentException thrown if the input {@link String} cannot be parses as an {@code int}
     */
    public static JsonInteger create(final String input) {
        if (!StringUtils.isInteger(input)) {
            throw new IllegalArgumentException(String.format("Cannot parse input '%s' as %s", input, JsonInteger.class.getSimpleName()));
        }

        return new JsonInteger(Integer.parseInt(input));
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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof JsonInteger other)) {
            return false;
        }

        return value == other.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
