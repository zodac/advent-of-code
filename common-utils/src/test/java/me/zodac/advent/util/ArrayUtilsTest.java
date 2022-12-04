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
import static org.assertj.core.api.Assertions.atIndex;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ArrayUtils}.
 */
class ArrayUtilsTest {

    @Test
    void whenConvertToArrayOfArrays_givenListOfListOfBooleans_thenArrayOfArraysOfBooleansReturned() {
        final List<List<Boolean>> input = List.of(
            List.of(false, false, true),
            List.of(false, true, false),
            List.of(true, false, false)
        );
        final Boolean[][] output = ArrayUtils.convertToArrayOfArrays(input);
        assertThat(output)
            .hasDimensions(3, 3)
            .contains(new Boolean[] {false, false, true}, atIndex(0))
            .contains(new Boolean[] {false, true, false}, atIndex(1))
            .contains(new Boolean[] {true, false, false}, atIndex(2));
    }

    @Test
    void whenConvertToArrayOfArrays_givenEmptyList_thenEmptyArrayOfBooleansReturned() {
        final List<List<Boolean>> input = List.of();
        final Boolean[][] output = ArrayUtils.convertToArrayOfArrays(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFindSmallestIndexGreaterThanThreshold_givenInputWithOneValueAboveThreshold_thenCorrectIndexIsReturned() {
        final int[] input = {1, 2, 3};
        final int output = ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 2);
        assertThat(output)
            .isEqualTo(2);
    }

    @Test
    void whenFindSmallestIndexGreaterThanThreshold_givenInputWithMultipleValuesAboveThreshold_thenSmallestIndexIsReturned() {
        final int[] input = {1, 2, 3};
        final int output = ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 1);
        assertThat(output)
            .isEqualTo(1);
    }

    @Test
    void whenFindSmallestIndexGreaterThanThreshold_givenInputWithMultipleUnorderedValuesAboveThreshold_thenSmallestIndexIsReturned() {
        final int[] input = {2, 3, 1};
        final int output = ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 1);
        assertThat(output)
            .isEqualTo(0);
    }

    @Test
    void whenFindSmallestIndexGreaterThanThreshold_givenInputWithNoValuesAboveThreshold_thenExceptionIsThrown() {
        final int[] input = {1, 2, 3};
        assertThatThrownBy(() -> ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 3))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("No value in input is greater than 3");
    }

    @Test
    void whenFindSmallestIndexGreaterThanThreshold_givenEmptyInput_thenExceptionIsThrown() {
        final int[] input = {};
        assertThatThrownBy(() -> ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Input cannot be null or empty");
    }
}
