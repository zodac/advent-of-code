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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.atIndex;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ArrayUtils}.
 */
class ArrayUtilsTest {

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

    @Test
    void whenConvertToArrayOfBooleanArrays_givenValidListOfStrings_thenArrayOfBooleanArraysIsReturned() {
        final List<String> input = List.of("abb", "bab", "aaa");
        final Boolean[][] output = ArrayUtils.convertToArrayOfBooleanArrays(input, 'a');
        assertThat(output)
            .contains(new Boolean[] {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE}, atIndex(0))
            .contains(new Boolean[] {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE}, atIndex(1))
            .contains(new Boolean[] {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE}, atIndex(2));
    }

    @Test
    void whenConvertToArrayOfBooleanArrays_givenValidListOfStrings_andLongestStringIsNotFirstString_thenArrayOfBooleanArraysIsReturned() {
        final List<String> input = List.of("abbb", "bab", "aa", "bbb");
        final Boolean[][] output = ArrayUtils.convertToArrayOfBooleanArrays(input, 'a');
        assertThat(output)
            .contains(new Boolean[] {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, atIndex(0))
            .contains(new Boolean[] {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE}, atIndex(1))
            .contains(new Boolean[] {Boolean.TRUE, Boolean.TRUE}, atIndex(2))
            .contains(new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, atIndex(3));
    }

    @Test
    void whenConvertToArrayOfBooleanArrays_givenEmptyList_thenArrayOfBooleanArraysIsReturned() {
        final List<String> input = List.of();
        final Boolean[][] output = ArrayUtils.convertToArrayOfBooleanArrays(input, 'a');
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenConvertToArrayOfBooleanArrays_givenListOfEmptyString_thenArrayOfBooleanArraysIsReturned() {
        final List<String> input = List.of("");
        final Boolean[][] output = ArrayUtils.convertToArrayOfBooleanArrays(input, 'a');
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenConvertToArrayOfCharArrays_givenValidListOfStrings_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("abc", "def", "ghi");
        final char[][] output = ArrayUtils.convertToArrayOfCharArrays(input);
        assertThat(output)
            .contains(new char[] {'a', 'b', 'c'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f'}, atIndex(1))
            .contains(new char[] {'g', 'h', 'i'}, atIndex(2));
    }

    @Test
    void whenConvertToArrayOfCharArrays_givenValidListOfStrings_andLongestStringIsNotFirstString_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("abc", "defg", "hij");
        final char[][] output = ArrayUtils.convertToArrayOfCharArrays(input);
        assertThat(output)
            .hasNumberOfRows(3)
            .contains(new char[] {'a', 'b', 'c'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f', 'g'}, atIndex(1))
            .contains(new char[] {'h', 'i', 'j'}, atIndex(2));
    }

    @Test
    void whenConvertToArrayOfCharArrays_givenEmptyList_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of();
        final char[][] output = ArrayUtils.convertToArrayOfCharArrays(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenConvertToArrayOfCharArrays_givenListOfEmptyString_thenArrayOfCharArraysIsReturned() {
        final List<String> input = List.of("");
        final char[][] output = ArrayUtils.convertToArrayOfCharArrays(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenConvertToArrayOfIntegerArrays_givenValidListOfStrings_thenArrayOfIntegerArraysIsReturned() {
        final List<String> input = List.of("123", "456", "789");
        final Integer[][] output = ArrayUtils.convertToArrayOfIntegerArrays(input);
        assertThat(output)
            .contains(new Integer[] {1, 2, 3}, atIndex(0))
            .contains(new Integer[] {4, 5, 6}, atIndex(1))
            .contains(new Integer[] {7, 8, 9}, atIndex(2));
    }

    @Test
    void whenConvertToArrayOfIntegerArrays_givenValidListOfStrings_andLongestStringIsNotFirstString_thenArrayOfIntegerArraysIsReturned() {
        final List<String> input = List.of("1234", "567", "89", "012");
        final Integer[][] output = ArrayUtils.convertToArrayOfIntegerArrays(input);
        assertThat(output)
            .contains(new Integer[] {1, 2, 3, 4}, atIndex(0))
            .contains(new Integer[] {5, 6, 7}, atIndex(1))
            .contains(new Integer[] {8, 9}, atIndex(2))
            .contains(new Integer[] {0, 1, 2}, atIndex(3));
    }

    @Test
    void whenConvertToArrayOfIntegerArrays_givenEmptyList_thenArrayOfIntegerArraysIsReturned() {
        final List<String> input = List.of();
        final Integer[][] output = ArrayUtils.convertToArrayOfIntegerArrays(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenConvertToArrayOfIntegerArrays_givenListOfEmptyString_thenArrayOfIntegerArraysIsReturned() {
        final List<String> input = List.of("");
        final Integer[][] output = ArrayUtils.convertToArrayOfIntegerArrays(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCountPerimeterElements_givenValidArray_thenCorrectCountIsReturned() {
        final int output1 = ArrayUtils.countPerimeterElements(populateArrayOfArraysWithSize(3));
        assertThat(output1)
            .isEqualTo(8);

        final int output2 = ArrayUtils.countPerimeterElements(populateArrayOfArraysWithSize(10));
        assertThat(output2)
            .isEqualTo(36);

        final int output3 = ArrayUtils.countPerimeterElements(populateArrayOfArraysWithSize(99));
        assertThat(output3)
            .isEqualTo(392);
    }

    private static Integer[][] populateArrayOfArraysWithSize(final int size) {
        final Integer[][] array = new Integer[size][size];
        for (final Integer[] row : array) {
            Arrays.fill(row, 1);
        }
        return array;
    }

    @Test
    void whenCountPerimeterElements_givenNonSquareArray_thenExceptionIsThrown() {
        final Integer[][] input = {{1, 2, 3, 4}, {5, 6}, {7, 8, 9}};
        assertThatThrownBy(() -> ArrayUtils.countPerimeterElements(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Outer size must match inner size, found outer: 3, inner: 4");
    }

    @Test
    void whenCountPerimeterElements_givenEmptyArray_thenExceptionIsThrown() {
        final Integer[][] input = {};
        assertThatThrownBy(() -> ArrayUtils.countPerimeterElements(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null or empty");
    }

    @Test
    void whenCountPerimeterElements_givenNullArray_thenExceptionIsThrown() {
        final Integer[][] input = null;
        assertThatThrownBy(() -> ArrayUtils.countPerimeterElements(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null or empty");
    }

    @Test
    void whenDeepFill_givenValidInput_thenNewFilledArrayIsReturned_andOriginalArrayIsUnchanged() {
        final boolean[][] input = new boolean[3][3];
        final boolean[][] output = ArrayUtils.deepFill(input, true);
        assertThat(output)
            .contains(new boolean[] {true, true, true}, atIndex(0))
            .contains(new boolean[] {true, true, true}, atIndex(1))
            .contains(new boolean[] {true, true, true}, atIndex(2));

        final boolean[][] secondOutput = ArrayUtils.deepFill(input, false);
        assertThat(secondOutput)
            .contains(new boolean[] {false, false, false}, atIndex(0))
            .contains(new boolean[] {false, false, false}, atIndex(1))
            .contains(new boolean[] {false, false, false}, atIndex(2));

        // Confirm new 2D array is not modified if original array is modified
        output[0][0] = true;
        assertThat(secondOutput)
            .contains(new boolean[] {false, false, false}, atIndex(0))
            .contains(new boolean[] {false, false, false}, atIndex(1))
            .contains(new boolean[] {false, false, false}, atIndex(2));
    }

    @Test
    void whenDeepFill_givenValidInputWithColumnsOfDifferentLengths_thenNewFilledArrayIsReturned() {
        final boolean[][] input = new boolean[3][2];
        final boolean[][] output = ArrayUtils.deepFill(input, true);
        assertThat(output)
            .contains(new boolean[] {true, true}, atIndex(0))
            .contains(new boolean[] {true, true}, atIndex(1))
            .contains(new boolean[] {true, true}, atIndex(2));
    }

    @Test
    void whenDeepFill_givenEmptyArray_thenEmptyArrayIsReturned() {
        final boolean[][] input = {};
        final boolean[][] output = ArrayUtils.deepFill(input, true);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenDeepFill_givenArrayOfEmptyArrays_thenEmptyArrayIsReturned() {
        final boolean[][] input = {{}, {}, {}};
        final boolean[][] output = ArrayUtils.deepFill(input, true);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenDeepFill_givenNullArray_thenEmptyArrayIsReturned() {
        final boolean[][] input = null;
        final boolean[][] output = ArrayUtils.deepFill(input, true);
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
    void whenFindSmallestIndexGreaterThanThreshold_givenNullInput_thenExceptionIsThrown() {
        final int[] input = null;
        assertThatThrownBy(() -> ArrayUtils.findSmallestIndexGreaterThanThreshold(input, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Input cannot be null or empty");
    }

    @Test
    void whenFlatten_givenValidArray_thenFlattenedArrayIsReturned() {
        final boolean[][] input = {{true, true, true}, {false, false, false}, {true, false, true}};
        final boolean[] output = ArrayUtils.flatten(input);
        assertThat(output)
            .hasSize(9)
            .containsExactly(true, true, true, false, false, false, true, false, true);
    }

    @Test
    void whenFlatten_givenValidArrayWithColumnsOfDifferingLengths_thenFlattenedArrayIsReturned() {
        final boolean[][] input = {{true, true, true}, {false, false}, {true}};
        final boolean[] output = ArrayUtils.flatten(input);
        assertThat(output)
            .hasSize(6)
            .containsExactly(true, true, true, false, false, true);
    }

    @Test
    void whenFlatten_givenEmptyArray_thenEmptyArrayIsReturned() {
        final boolean[][] input = {};
        final boolean[] output = ArrayUtils.flatten(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFlatten_givenArrayOfEmptyArrays_thenEmptyArrayIsReturned() {
        final boolean[][] input = {{}, {}, {}};
        final boolean[] output = ArrayUtils.flatten(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFlatten_givenNullArray_thenEmptyArrayIsReturned() {
        final boolean[][] input = null;
        final boolean[] output = ArrayUtils.flatten(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenMaxInnerLength_givenArrayWithEqualLengths_thenMaxInnerLengthIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        final int output = ArrayUtils.maxInnerLength(input);
        assertThat(output)
            .isEqualTo(3);
    }

    @Test
    void whenMaxInnerLength_givenSingleInnerArray_thenMaxInnerLengthIsReturned() {
        final char[][] input = {{'a', 'b'}};
        final int output = ArrayUtils.maxInnerLength(input);
        assertThat(output)
            .isEqualTo(2);
    }

    @Test
    void whenMaxInnerLength_givenArrayWithDifferingLengths_thenMaxInnerLengthIsReturned() {
        final char[][] input = {{'a', 'b'}, {'c', 'd', 'e', 'f'}, {'g', 'h', 'i'}};
        final int output = ArrayUtils.maxInnerLength(input);
        assertThat(output)
            .isEqualTo(4);
    }

    @Test
    void whenMaxInnerLength_givenEmptyArray_thenEmptyArrayIsReturned() {
        final char[][] input = {};
        assertThatThrownBy(() -> ArrayUtils.maxInnerLength(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Cannot find max length of input: []");
    }

    @Test
    void whenMaxInnerLength_givenArrayOfEmptyArrays_thenEmptyArrayIsReturned() {
        final char[][] input = {{}, {}, {}};
        final int output = ArrayUtils.maxInnerLength(input);
        assertThat(output)
            .isEqualTo(0);
    }

    @Test
    void whenMaxInnerLength_givenNullArray_thenEmptyArrayIsReturned() {
        final char[][] input = null;
        assertThatThrownBy(() -> ArrayUtils.maxInnerLength(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null");
    }

    @Test
    void whenReverseRows_givenValidInput_thenReversedArrayIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .contains(new char[] {'g', 'h', 'i'}, atIndex(0))
            .contains(new char[] {'d', 'e', 'f'}, atIndex(1))
            .contains(new char[] {'a', 'b', 'c'}, atIndex(2));
    }

    @Test
    void whenReverseRows_givenInputWithColumnsOfDifferentLengths_thenReversedArrayIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e'}, {'f', 'g', 'h', 'i'}};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .contains(new char[] {'f', 'g', 'h', 'i'}, atIndex(0))
            .contains(new char[] {'d', 'e'}, atIndex(1))
            .contains(new char[] {'a', 'b', 'c'}, atIndex(2));
    }

    @Test
    void whenReverseRows_givenEmptyArray_thenInputIsReturned() {
        final char[][] input = {};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isEqualTo(input);
    }

    @Test
    void whenReverseRows_givenArrayOfEmptyArray_thenInputIsReturned() {
        final char[][] input = {{}, {}};
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReverseRows_givenNullArray_thenInputIsReturned() {
        final char[][] input = null;
        final char[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenSize_givenValidArray_thenFlattenedArrayIsReturned() {
        final boolean[][] input = {{true, true, true}, {false, false, false}, {true, false, true}};
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isEqualTo(9);
    }

    @Test
    void whenSize_givenValidArrayWithColumnsOfDifferingLengths_thenFlattenedArrayIsReturned() {
        final boolean[][] input = {{true, true, true}, {false, false}, {true}};
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isEqualTo(6);
    }

    @Test
    void whenSize_givenValidArrayWithColumnsOfDifferingLengths_andFirstRowIsEmpty_thenFlattenedArrayIsReturned() {
        final boolean[][] input = {{}, {false, false}, {true}};
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isEqualTo(3);
    }

    @Test
    void whenSize_givenEmptyArray_thenZeroArrayIsReturned() {
        final boolean[][] input = {};
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenSize_givenArrayOfEmptyArrays_thenZeroIsReturned() {
        final boolean[][] input = {{}, {}, {}};
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenSize_givenNullArray_thenEmptyZeroIsReturned() {
        final boolean[][] input = null;
        final int output = ArrayUtils.size(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenTranspose_givenValidInput_thenTransposedArrayIsReturned() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}};
        final char[][] output = ArrayUtils.transpose(input);
        assertThat(output)
            .contains(new char[] {'a', 'd', 'g', 'j'}, atIndex(0))
            .contains(new char[] {'b', 'e', 'h', 'k'}, atIndex(1))
            .contains(new char[] {'c', 'f', 'i', 'l'}, atIndex(2));
    }

    @Test
    void whenTranspose_givenInputWithColumnsOfDifferentLengths_thenTransposedArrayIsReturned_andGapsFilledWithEmptyCharacter() {
        final char[][] input = {{'a', 'b', 'c'}, {'d', 'e', 'f', 'g'}, {'h', 'i'}};
        final char[][] output = ArrayUtils.transpose(input);
        assertThat(output)
            .contains(new char[] {'a', 'd', 'h'}, atIndex(0))
            .contains(new char[] {'b', 'e', 'i'}, atIndex(1))
            .contains(new char[] {'c', 'f', ' '}, atIndex(2))
            .contains(new char[] {' ', 'g', ' '}, atIndex(3));
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
}
