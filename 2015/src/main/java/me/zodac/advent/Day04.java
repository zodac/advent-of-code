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

package me.zodac.advent;

import java.security.NoSuchAlgorithmException;
import me.zodac.advent.util.CryptoUtils;
import me.zodac.advent.util.HashingAlgorithm;

/**
 * Solution for 2015, Day 4.
 *
 * @see <a href="https://adventofcode.com/2015/day/4">AoC 2015, Day 4</a>
 */
public final class Day04 {

    private Day04() {

    }

    /**
     * Generates an {@link HashingAlgorithm#MD5} hash of the concatenation of the provided {@code secretKey} and an {@link Integer} counter.
     * This hash is then checked to see if it starts with the provided {@code prefixToFind}.
     *
     * <p>
     * The counter is incremented until either the wanted prefix is found, or {@link Integer#MAX_VALUE}.
     *
     * @param secretKey    the secret key prefix to the hash input
     * @param prefixToFind the wanted prefix to the hash output
     * @return the id of attempts needed to find the wanted prefix
     * @throws IllegalArgumentException if the {@code prefixToFind} is not found
     * @throws NoSuchAlgorithmException thrown if the {@link HashingAlgorithm#MD5} algorithm is not found
     */
    public static long iterateHashesToFindPrefix(final String secretKey, final String prefixToFind) throws NoSuchAlgorithmException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            final String stringToHash = secretKey + i;
            final String hexidecimalHash = CryptoUtils.hashAsHexString(stringToHash, HashingAlgorithm.MD5);

            if (hexidecimalHash.startsWith(prefixToFind)) {
                return i;
            }
        }

        throw new IllegalArgumentException(String.format("No valid %s hash found for '%s' starting with prefix '%s' after %s iterations",
            HashingAlgorithm.MD5, secretKey, prefixToFind, Integer.MAX_VALUE));
    }
}
