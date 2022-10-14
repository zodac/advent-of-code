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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CryptoUtils}.
 */
class CryptoUtilsTest {

    @Test
    void whenHashAsHexString_givenValidString_thenHexStringIsReturned() throws NoSuchAlgorithmException {
        final String input = "abcdef609043";
        final String output = CryptoUtils.hashAsHexString(input, HashingAlgorithm.MD5);
        assertThat(output)
            .isEqualTo("000001DBBFA3A5C83A2D506429C7B00E");
    }

    @Test
    void whenHashAsHexString_givenEmptyString_thenIllegalArgumentExceptionIsThrown() {
        final String input = "";
        assertThatThrownBy(() -> CryptoUtils.hashAsHexString(input, HashingAlgorithm.MD5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenHashAsHexString_givenBlankString_thenIllegalArgumentExceptionIsThrown() {
        final String input = " ";
        assertThatThrownBy(() -> CryptoUtils.hashAsHexString(input, HashingAlgorithm.MD5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenHashAsHexString_givenNullString_thenIllegalArgumentExceptionIsThrown() {
        final String input = null;
        assertThatThrownBy(() -> CryptoUtils.hashAsHexString(input, HashingAlgorithm.MD5))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
