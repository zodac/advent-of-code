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