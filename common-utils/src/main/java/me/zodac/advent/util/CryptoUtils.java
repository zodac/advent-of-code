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

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Utility functions for cryptography.
 */
public final class CryptoUtils {

    private static final String HEXES_KEY = "0123456789ABCDEF";
    private static final int BIT_MASK_FORWARD = 0xF0;
    private static final int BIT_MASK_REVERSE = 0x0F;

    private CryptoUtils() {

    }

    /**
     * Hashes the input {@link String} using the specified {@link HashingAlgorithm}, as a hexadecimal {@link String}.
     *
     * @param input            the {@link String} to be hashed
     * @param hashingAlgorithm the {@link HashingAlgorithm} to be used to hash the input
     * @return the hashed {@link String} as a hexadecimal {@link String}
     * @throws IllegalArgumentException thrown if the input is <b>null</b> or {@link String#isBlank()}
     * @throws NoSuchAlgorithmException thrown if the {@link HashingAlgorithm} is not found
     */
    public static String hashAsHexString(final String input, final HashingAlgorithm hashingAlgorithm) throws NoSuchAlgorithmException {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input must have at least one non-whitespace character, found: " + input);
        }

        final byte[] hash = hashingAlgorithm.hash(input);
        return asHexString(hash);
    }

    private static String asHexString(final byte[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input must have at least one element, found: " + Arrays.toString(input));
        }

        // Taken from: https://stackoverflow.com/a/9655224/2000246
        final StringBuilder hex = new StringBuilder();
        for (final byte b : input) {
            hex.append(HEXES_KEY.charAt((b & BIT_MASK_FORWARD) >> 4))
                .append(HEXES_KEY.charAt((b & BIT_MASK_REVERSE)));
        }
        return hex.toString();
    }
}
