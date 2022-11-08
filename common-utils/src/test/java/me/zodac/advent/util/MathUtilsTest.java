/*
 * Zero-Clause BSD License
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

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MathUtils}.
 */
class MathUtilsTest {

    @Test
    void whenMax_givenPositiveNumbers_thenLargestPositiveNumberIsReturned() {
        final int[] input = {2, 3};
        final int max = MathUtils.max(1, input);
        assertThat(max)
            .isEqualTo(3);
    }

    @Test
    void whenMax_givenNegativeNumbers_thenLargestPositiveNumberIsReturned() {
        final int[] input = {-2, -3};
        final int max = MathUtils.max(-1, input);
        assertThat(max)
            .isEqualTo(-1);
    }

    @Test
    void whenTriangularNumber_givenPositiveNumber_thenCorrectValueIsReturned() {
        final int input = 6;
        final long triangularNumber = MathUtils.triangularNumber(input);
        assertThat(triangularNumber)
            .isEqualTo(21L);
    }

    @Test
    void whenTriangularNumber_givenNegativeNumber_thenCorrectValueIsReturned() {
        final int input = -6;
        final long triangularNumber = MathUtils.triangularNumber(input);
        assertThat(triangularNumber)
            .isEqualTo(15L);
    }

    @Test
    void whenIsEven_givenEvenNnumber_thenTrueIsReturned() {
        final int input = 2;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsEven_givenOddNnumber_thenFalseIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsEven_givenEvenNegativeNnumber_thenTrueIsReturned() {
        final int input = -2;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsEven_givenOddNegativeNnumber_thenFalseIsReturned() {
        final int input = -3;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsAnyLessThan_givenPositiveValue_andNoValuesLessThan_thenFalseIsReturned() {
        final long input = 1L;
        final long[] values = {1L, 2L, 3L};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsAnyLessThan_givenPositiveValue_andPositiveValuesLessThan_thenTrueIsReturned() {
        final long input = 1L;
        final long[] values = {0L, 3L, 4L};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsAnyLessThan_givenPositiveValue_andNegativeValuesLessThan_thenTrueIsReturned() {
        final long input = 1L;
        final long[] values = {-2L, 3L, 4L};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsAnyLessThan_givenNegativeValue_andNoValuesLessThan_thenFalseIsReturned() {
        final long input = -2L;
        final long[] values = {-1L, 3L, 4L};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsAnyLessThan_givenNegativeValue_andNegativeValuesLessThan_thenTrueIsReturned() {
        final long input = -1L;
        final long[] values = {-2L, 3L, 4L};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsAnyLessThan_givenValue_andNoInputValue_thenFalseIsReturned() {
        final long input = -2L;
        final long[] values = {};
        final boolean output = MathUtils.isAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenMultiplyElementsThenSum_givenAllPositiveValues_thenValueIsReturned() {
        final List<Integer> first = List.of(1, 2, 3);
        final List<Integer> second = List.of(4, 5, 6);
        final long output = MathUtils.multiplyElementsThenSum(first, second);
        assertThat(output)
            .isEqualTo(32L);
    }

    @Test
    void whenMultiplyElementsThenSum_givenValuesIncludingNegativeValue_thenValueIsReturned() {
        final List<Integer> first = List.of(-1, 2, 3);
        final List<Integer> second = List.of(4, 5, 6);
        final long output = MathUtils.multiplyElementsThenSum(first, second);
        assertThat(output)
            .isEqualTo(24L);
    }

    @Test
    void whenMultiplyElementsThenSum_givenFirstValuesAllNegative_thenValueIsReturned() {
        final List<Integer> first = List.of(-1, -2, -3);
        final List<Integer> second = List.of(4, 5, 6);
        final long output = MathUtils.multiplyElementsThenSum(first, second);
        assertThat(output)
            .isEqualTo(-32L);
    }

    @Test
    void whenMultiplyElementsThenSum_givenValuesIncludingZero_thenValueIsReturned() {
        final List<Integer> first = List.of(0, 2, 3);
        final List<Integer> second = List.of(4, 5, 6);
        final long output = MathUtils.multiplyElementsThenSum(first, second);
        assertThat(output)
            .isEqualTo(28L);
    }

    @Test
    void whenMultiplyElementsThenSum_givenEmptyValues_thenZeroIsReturned() {
        final List<Integer> first = List.of();
        final List<Integer> second = List.of();
        final long output = MathUtils.multiplyElementsThenSum(first, second);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenMultiplyElementsThenSum_givenFirstValuesNotEqualSizeToSecondValues_thenExceptionIsThrown() {
        final List<Integer> first = List.of(1, 2, 3);
        final List<Integer> second = List.of(4);
        assertThatThrownBy(() -> MathUtils.multiplyElementsThenSum(first, second))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Inputs must be of same length, found: 3 and 1");
    }
}
