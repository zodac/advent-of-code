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

    @Test
    void whenParseList_givenValidListOfStrings_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("abc", "def", "ghi");
        final char[][] output = ArrayUtils.parseList(input);
        assertThat(output)
            .contains(new char[] {'a', 'b', 'c'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f'}, atIndex(1))
            .contains(new char[] {'g', 'h', 'i'}, atIndex(2));
    }

    @Test
    void whenParseList_givenValidListOfStrings_andLongestStringIsNotFirstString_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("abc", "defg", "hij");
        final char[][] output = ArrayUtils.parseList(input);
        assertThat(output)
            .hasNumberOfRows(3)
            .contains(new char[] {'a', 'b', 'c'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f', 'g'}, atIndex(1))
            .contains(new char[] {'h', 'i', 'j'}, atIndex(2));
    }

    @Test
    void whenParseList_givenEmptyList_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of();
        final char[][] output = ArrayUtils.parseList(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenParseList_givenListOfEmptyString_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("");
        final char[][] output = ArrayUtils.parseList(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReverseColumns_givenValidInput_thenReversedArrayIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .contains(new char[] {'g', 'h', 'i'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f'}, atIndex(1))
            .contains(new char[] {'a', 'b', 'c'}, atIndex(2));
    }

    @Test
    void whenReverseColumns_givenInputWithColumnsOfDifferentLengths_thenExceptionIsThrown() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e'}, {'f', 'g', 'h', 'i'}};
        assertThatThrownBy(() -> ArrayUtils.reverseRows(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Column lengths must be the same in all rows, found: ");
    }

    @Test
    void whenReverseColumns_givenEmptyArray_thenInputIsReturned() {
        final char[][] input = {};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isEqualTo(input);
    }

    @Test
    void whenReverseColumns_givenArrayOfEmptyArray_thenInputIsReturned() {
        final char[][] input = {{}, {}};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenTranspose_givenValidInput_thenReversedArrayIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}};
        final char[][] output = ArrayUtils.transpose(input);
        assertThat(output)
            .contains(new char[] {'a', 'd', 'g', 'j'}, atIndex(0))
            .contains(new char[] {'b', 'e', 'h', 'k'}, atIndex(1))
            .contains(new char[] {'c', 'f', 'i', 'l'}, atIndex(2));
    }

    @Test
    void whenTranspose_givenInputWithColumnsOfDifferentLengths_thenExceptionIsThrown() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i', 'j'}};
        assertThatThrownBy(() -> ArrayUtils.transpose(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Column lengths must be the same in all rows, found: ");
    }

    @Test
    void whenTranspose_givenEmptyArray_thenInputIsReturned() {
        final char[][] input = {};
        final char[][] output = ArrayUtils.transpose(input);
        assertThat(output)
            .isEqualTo(input);
    }

    @Test
    void whenTranspose_givenArrayOfEmptyArray_thenInputIsReturned() {
        final char[][] input = {{}, {}};
        final char[][] output = ArrayUtils.transpose(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenAreColumnLengthsDifferent_givenArrayWithDifferentColumnLengths_thenTrueIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e'}};
        final boolean output = ArrayUtils.areColumnLengthsDifferent(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenAreColumnLengthsDifferent_givenArrayWithConstantColumnLength_thenFalseIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        final boolean output = ArrayUtils.areColumnLengthsDifferent(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenAreColumnLengthsDifferent_givenEmptyArray_thenFalseIsReturned() {
        final char[][] input = {};
        final boolean output = ArrayUtils.areColumnLengthsDifferent(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenAreColumnLengthsDifferent_givenArrayOfEmptyArrays_thenFalseIsReturned() {
        final char[][] input = {{}, {}};
        final boolean output = ArrayUtils.areColumnLengthsDifferent(input);
        assertThat(output)
            .isFalse();
    }
}
