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
