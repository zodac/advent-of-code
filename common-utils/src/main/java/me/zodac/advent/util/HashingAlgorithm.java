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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Available hashing algorithms.
 */
public enum HashingAlgorithm {

    /**
     * The <b>MD5</b> hashing algorithm.
     */
    MD5 {
        @Override
        public byte[] hash(final String input) throws NoSuchAlgorithmException {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            return digest.digest();
        }
    };

    /**
     * Hashes the input {@link String} using the specified {@link HashingAlgorithm}.
     *
     * @param input the {@link String} to be hashed
     * @return the hashed output as a {@code byte[]}
     * @throws NoSuchAlgorithmException thrown if the {@link HashingAlgorithm} is not found
     */
    public abstract byte[] hash(String input) throws NoSuchAlgorithmException;
}
