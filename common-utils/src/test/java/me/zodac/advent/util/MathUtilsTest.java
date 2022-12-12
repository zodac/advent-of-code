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

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MathUtils}.
 */
class MathUtilsTest {

    @Test
    void whenAreAnyLessThan_givenPositiveValue_andNoValuesLessThan_thenFalseIsReturned() {
        final long input = 1L;
        final long[] values = {1L, 2L, 3L};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenAreAnyLessThan_givenPositiveValue_andPositiveValuesLessThan_thenTrueIsReturned() {
        final long input = 1L;
        final long[] values = {0L, 3L, 4L};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenAreAnyLessThan_givenPositiveValue_andNegativeValuesLessThan_thenTrueIsReturned() {
        final long input = 1L;
        final long[] values = {-2L, 3L, 4L};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenAreAnyLessThan_givenNegativeValue_andNoValuesLessThan_thenFalseIsReturned() {
        final long input = -2L;
        final long[] values = {-1L, 3L, 4L};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenAreAnyLessThan_givenNegativeValue_andNegativeValuesLessThan_thenTrueIsReturned() {
        final long input = -1L;
        final long[] values = {-2L, 3L, 4L};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenAreAnyLessThan_givenValue_andNoInputValue_thenFalseIsReturned() {
        final long input = -2L;
        final long[] values = {};
        final boolean output = MathUtils.areAnyLessThan(input, values);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenDiagonalSum_givenValidInput_thenValueIsReturned() {
        final int row = 2;
        final int column = 4;

        final long output = MathUtils.diagonalSum(row, column);
        assertThat(output)
            .isEqualTo(13L);
    }

    @Test
    void whenDiagonalSum_givenValidOrigin_thenValueIsReturned() {
        final int row = 1;
        final int column = 1;

        final long output = MathUtils.diagonalSum(row, column);
        assertThat(output)
            .isEqualTo(0L);
    }

    @Test
    void whenDiagonalSum_givenNegativeRow_thenExceptionIsThrown() {
        final int row = -2;
        final int column = 4;

        assertThatThrownBy(() -> MathUtils.diagonalSum(row, column))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Both row -2 and column 4 must be at least 1");
    }

    @Test
    void whenDiagonalSum_givenNegativeColumn_thenExceptionIsThrown() {
        final int row = 2;
        final int column = -4;

        assertThatThrownBy(() -> MathUtils.diagonalSum(row, column))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Both row 2 and column -4 must be at least 1");
    }

    @Test
    void whenIsBetween_givenInputBetweenRange_thenTrueIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isBetween(1, 5, input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsBetween_givenInputEqualToStart_thenTrueIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isBetween(3, 5, input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsBetween_givenInputEqualToEnd_thenTrueIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isBetween(1, 3, input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsBetween_givenRangeAndInputEqual_thenTrueIsReturned() {
        final int input = 1;
        final boolean output = MathUtils.isBetween(1, 1, input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsBetween_givenNegativeValues_andInputBetweenRange_thenTrueIsReturned() {
        final int input = -3;
        final boolean output = MathUtils.isBetween(-5, -1, input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsBetween_givenInputNotBetweenRange_thenFalseIsReturned() {
        final int input = 9;
        final boolean output = MathUtils.isBetween(1, 5, input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsBetween_givenNegativeValues_andInputNotBetweenRange_thenFalseIsReturned() {
        final int input = -9;
        final boolean output = MathUtils.isBetween(-5, -1, input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsBetween_givenRangeEndIsLessThanRangeStart_thenExceptionIsThrown() {
        assertThatThrownBy(() -> MathUtils.isBetween(1, 0, 2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cannot have end 0 less than start 1");
    }

    @Test
    void whenIsEven_givenEvenNumber_thenTrueIsReturned() {
        final int input = 2;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsEven_givenOddNumber_thenFalseIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsEven_givenEvenNegativeNumber_thenTrueIsReturned() {
        final int input = -2;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsEven_givenOddNegativeNumber_thenFalseIsReturned() {
        final int input = -3;
        final boolean output = MathUtils.isEven(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsOdd_givenOddNumber_thenTrueIsReturned() {
        final int input = 3;
        final boolean output = MathUtils.isOdd(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsOdd_givenEvenNumber_thenFalseIsReturned() {
        final int input = 2;
        final boolean output = MathUtils.isOdd(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsOdd_givenOddNegativeNumber_thenTrueIsReturned() {
        final int input = -3;
        final boolean output = MathUtils.isOdd(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsOdd_givenEvenNegativeNumber_thenFalseIsReturned() {
        final int input = -2;
        final boolean output = MathUtils.isOdd(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenLowestCommonMultiple_givenThreeNumbers_thenLcmIsReturned() {
        final int first = 1;
        final int[] input = {2, 3};
        final long output = MathUtils.lowestCommonMultiple(first, input);
        assertThat(output)
            .isEqualTo(6);
    }

    @Test
    void whenLowestCommonMultiple_givenZeroAsAnInput_thenZeroIsReturned() {
        final int first = 1;
        final int[] input = {0, 3};
        final long output = MathUtils.lowestCommonMultiple(first, input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenLowestCommonMultiple_givenSingleNumberAsInput_thenInputIsReturned() {
        final int first = 1;
        final long output = MathUtils.lowestCommonMultiple(first);
        assertThat(output)
            .isEqualTo(first);
    }

    @Test
    void whenLowestCommonMultiple_givenPrimeNumbers_thenOutputIsSameAsProductOfInputs() {
        final int first = 7;
        final int[] input = {13, 29};
        final long output = MathUtils.lowestCommonMultiple(first, input);
        assertThat(output)
            .isEqualTo((7 * 13 * 29));
    }

    @Test
    void whenMax_givenPositiveNumbers_thenLargestPositiveNumberIsReturned() {
        final int[] input = {2, 3};
        final int output = MathUtils.max(1, input);
        assertThat(output)
            .isEqualTo(3);
    }

    @Test
    void whenMax_givenNegativeNumbers_thenLargestPositiveNumberIsReturned() {
        final int[] input = {-2, -3};
        final int output = MathUtils.max(-1, input);
        assertThat(output)
            .isEqualTo(-1);
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
}
