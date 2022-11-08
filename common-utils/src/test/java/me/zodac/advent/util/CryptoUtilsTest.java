/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2022 zodac.me
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
