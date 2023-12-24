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

/**
 * Utility functions for cryptography.
 */
public final class CryptoUtils {

    private static final char[] HEX_KEYS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int BIT_MASK_FORWARD = 0xF0;
    private static final int BIT_MASK_REVERSE = 0x0F;

    private CryptoUtils() {

    }

    /**
     * Hashes the input {@link String} using the specified {@link HashingAlgorithm}, as a hexadecimal {@link String}.
     *
     * @param input            the {@link String} to be hashed
     * @param hashingAlgorithm the {@link HashingAlgorithm} to be used to hash the input
     * @return the hashed {@link String} as a hexadecimal {@link String}, with all values in uppercase
     * @throws IllegalArgumentException thrown if the input is {@code null} or {@link String#isBlank()}
     * @throws NoSuchAlgorithmException thrown if the {@link HashingAlgorithm} is not found
     */
    public static String hashAsHexString(final String input, final HashingAlgorithm hashingAlgorithm) throws NoSuchAlgorithmException {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(String.format("Input must have at least one non-whitespace character, found: '%s'", input));
        }

        final byte[] hash = hashingAlgorithm.hash(input);
        return asHexString(hash);
    }

    private static String asHexString(final byte[] input) {
        // Using code from: https://stackoverflow.com/a/32976536/2000246
        final char[] result = new char[(input.length << 1)];
        for (int i = 0; i < input.length; ++i) {
            final byte b = input[i];
            result[2 * i] = HEX_KEYS[(b & BIT_MASK_FORWARD) >>> 4];
            result[2 * i + 1] = HEX_KEYS[b & BIT_MASK_REVERSE];
        }
        return new String(result);
    }
}
