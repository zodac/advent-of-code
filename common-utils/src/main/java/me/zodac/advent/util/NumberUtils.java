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

package me.zodac.advent.util;

/**
 * Basic int and long utilities.
 */
public final class NumberUtils {

    private NumberUtils() {

    }

    /**
     * Checks if the input {@link String} is a valid {@link Integer}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if the input is an {@link Integer}
     */
    public static boolean isInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the input {@link String} is a valid {@link Long}.
     *
     * @param input the {@link String} to check
     * @return {@code true} if the input is an {@link Long}
     */
    public static boolean isLong(final String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static int toIntOrDefault(final Object object, final int defaultValue) {
        try {
            return Integer.parseInt(String.valueOf(object));
        } catch (final NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long toLongOrDefault(final Object object, final long defaultValue) {
        try {
            return Long.parseLong(String.valueOf(object));
        } catch (final NumberFormatException e) {
            return defaultValue;
        }
    }
}
