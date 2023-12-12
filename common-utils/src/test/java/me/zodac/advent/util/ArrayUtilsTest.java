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
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link ArrayUtils}.
 */
class ArrayUtilsTest {

    @ParameterizedTest
    @MethodSource("provideForConvertToArrayOfArrays")
    <E> void testConvertToArrayOfArrays(final List<String> input, final Function<? super Character, ? extends E> converter, final E[][] expected) {
        final E[][] output = ArrayUtils.convertToArrayOfArrays(input, converter);
        assertThat(output)
            .isDeepEqualTo(expected);
    }

    private static Stream<Arguments> provideForConvertToArrayOfArrays() {
        return Stream.of(
            // Valid list of Booleans
            Arguments.of(List.of("abb", "bab", "aaa"), (Function<Character, Boolean>) character -> character == 'a',
                new Boolean[][] {
                    {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE},
                    {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE},
                    {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE}
                }
            ),
            // Valid list of Booleans, where first string is longer than others
            Arguments.of(List.of("abbb", "bab", "aaa"), (Function<Character, Boolean>) character -> character == 'a',
                new Boolean[][] {
                    {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE},
                    {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE},
                    {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE}
                }
            ),
            // Valid list of Booleans, where last string is longer than others
            Arguments.of(List.of("abb", "bab", "aaaa"), (Function<Character, Boolean>) character -> character == 'a',
                new Boolean[][] {
                    {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE},
                    {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE},
                    {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE}
                }
            ),
            // Valid list of Booleans, with no matches
            Arguments.of(List.of("abb", "bab", "aaa"), (Function<Character, Boolean>) character -> character == 'c',
                new Boolean[][] {
                    {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE},
                    {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE},
                    {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}
                }
            ),
            // Valid list of Characters
            Arguments.of(List.of("abc", "def", "ghi"), (Function<Character, Character>) character -> character,
                new Character[][] {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'}
                }
            ),
            // Valid list of Characters, where first string is longer than others
            Arguments.of(List.of("abcd", "efg", "hij"), (Function<Character, Character>) character -> character,
                new Character[][] {
                    {'a', 'b', 'c', 'd'},
                    {'e', 'f', 'g'},
                    {'h', 'i', 'j'}
                }
            ),
            // Valid list of Characters, where last string is longer than others
            Arguments.of(List.of("abc", "def", "ghij"), (Function<Character, Character>) character -> character,
                new Character[][] {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i', 'j'}
                }
            ),
            // Valid list of Integers
            Arguments.of(List.of("123", "456", "789"), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0),
                new Integer[][] {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
                }
            ),
            // Valid list of Integers, where first string is longer than others
            Arguments.of(List.of("1234", "567", "890"), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0),
                new Integer[][] {
                    {1, 2, 3, 4},
                    {5, 6, 7},
                    {8, 9, 0}
                }
            ),
            // Valid list of Integers, where last string is longer than others
            Arguments.of(List.of("123", "456", "7890"), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0),
                new Integer[][] {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9, 0}
                }
            ),
            // List with some integers, some non-integers
            Arguments.of(List.of("123", "4a6", "789"), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0),
                new Integer[][] {
                    {1, 2, 3},
                    {4, 0, 6},
                    {7, 8, 9}
                }
            ),
            // No integers
            Arguments.of(List.of("abc", "def", "ghi"), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0),
                new Integer[][] {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForConvertToArrayOfArrays_invalid")
    <E> void testConvertToArrayOfArrays_givenInvalidInputs(final List<String> input,
                                                           final Function<? super Character, ? extends E> converter,
                                                           final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.convertToArrayOfArrays(input, converter))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForConvertToArrayOfArrays_invalid() {
        return Stream.of(
            // List of empty string for Boolean conversion
            Arguments.of(List.of(""), (Function<Character, Boolean>) character -> character == 'a', "Input cannot be empty"),
            // Empty list for Boolean conversion
            Arguments.of(List.of(), (Function<Character, Boolean>) character -> character == 'a', "Input cannot be empty"),
            // List of empty string for Character conversion
            Arguments.of(List.of(""), (Function<Character, Character>) character -> character, "Input cannot be empty"),
            // Empty list for Character conversion
            Arguments.of(List.of(), (Function<Character, Character>) character -> character, "Input cannot be empty"),
            // List of empty string for Integer conversion
            Arguments.of(List.of(""), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0), "Input cannot be empty"),
            // Empty list for Integer conversion
            Arguments.of(List.of(), (Function<Character, Integer>) character -> NumberUtils.toIntOrDefault(character, 0), "Input cannot be empty")
        );
    }

    @ParameterizedTest
    @CsvSource({
        "3,8",
        "10,36",
        "99,392",
    })
    void testCountPerimeterElements(final int arraySize, final int expectedValue) {
        final Integer[][] input = new Integer[arraySize][arraySize];
        for (final Integer[] row : input) {
            Arrays.fill(row, 1);
        }

        final int output = ArrayUtils.countPerimeterElements(input);
        assertThat(output)
            .isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideForCountPerimeterElements_invalid")
    void testCountPerimeterElements_givenInvalidInputs(final Integer[][] input, final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.countPerimeterElements(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForCountPerimeterElements_invalid() {
        return Stream.of(
            Arguments.of(new Integer[][] {{1, 2, 3, 4}, {5, 6}, {7, 8, 9}}, "Outer size must match inner size, found outer: 3, inner: 4"),
            Arguments.of(new Integer[][] {}, "Input cannot be empty"),
            Arguments.of(new Integer[][] {{}}, "Outer size must match inner size, found outer: 1, inner: 0")
        );
    }

    @Test
    void whenDeepFill_givenValidInput_thenNewFilledArrayIsReturned_andOriginalArrayIsUnchanged() {
        final Boolean[][] input = new Boolean[3][3];
        final Boolean[][] output = ArrayUtils.deepFill(input, true);
        assertThat(output)
            .contains(new Boolean[] {true, true, true}, atIndex(0))
            .contains(new Boolean[] {true, true, true}, atIndex(1))
            .contains(new Boolean[] {true, true, true}, atIndex(2));

        final Boolean[][] secondOutput = ArrayUtils.deepFill(input, false);
        assertThat(secondOutput)
            .contains(new Boolean[] {false, false, false}, atIndex(0))
            .contains(new Boolean[] {false, false, false}, atIndex(1))
            .contains(new Boolean[] {false, false, false}, atIndex(2));

        // Confirm new 2D array is not modified if original array is modified
        output[0][0] = true;
        assertThat(secondOutput)
            .contains(new Boolean[] {false, false, false}, atIndex(0))
            .contains(new Boolean[] {false, false, false}, atIndex(1))
            .contains(new Boolean[] {false, false, false}, atIndex(2));
    }

    @ParameterizedTest
    @MethodSource("provideForDeepFill")
    <E> void testForDeepFill(final E[][] input, final E value, final E[][] expected) {
        final E[][] output = ArrayUtils.deepFill(input, value);
        assertThat(output)
            .isDeepEqualTo(expected);
    }

    private static Stream<Arguments> provideForDeepFill() {
        return Stream.of(
            Arguments.of(new Boolean[2][2], false, new Boolean[][] {{false, false}, {false, false}}),           // Same column lengths
            Arguments.of(new Boolean[3][2], true, new Boolean[][] {{true, true}, {true, true}, {true, true}})   // Different column lengths
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDeepFill_invalid")
    <E> void testForDeepFill_givenInvalidInputs(final E[][] input, final E value, final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.deepFill(input, value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForDeepFill_invalid() {
        return Stream.of(
            Arguments.of(new Boolean[][] {}, true, "Input cannot be empty"),        // Empty 1D array
            Arguments.of(new Boolean[][] {{}, {}}, true, "Input cannot be empty")   // Empty 2D array
        );
    }

    @ParameterizedTest
    @MethodSource("provideForFindSmallestIndexGreaterThanThreshold")
    void testFindSmallestIndexGreaterThanThreshold(final int[] input, final int threshold, final int expected) {
        final int output = ArrayUtils.findSmallestIndexGreaterThanThreshold(input, threshold);
        assertThat(output)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideForFindSmallestIndexGreaterThanThreshold() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3}, 2, 2),        // Single value above threshold (sorted)
            Arguments.of(new int[] {1, 2, 3}, 1, 1),        // Multiple values above threshold (sorted)
            Arguments.of(new int[] {3, 2, 1}, 2, 0),        // Single value above threshold (unsorted)
            Arguments.of(new int[] {2, 3, 1}, 1, 0),        // Multiple values above threshold (unsorted),
            Arguments.of(new int[] {-1, -2, -3}, -2, 0)     // Single value above threshold (negative)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindSmallestIndexGreaterThanThreshold_invalid")
    void testFindSmallestIndexGreaterThanThreshold_givenInvalidInputs(final int[] input, final int threshold, final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.findSmallestIndexGreaterThanThreshold(input, threshold))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideFindSmallestIndexGreaterThanThreshold_invalid() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3}, 3, "No value in input is greater than 3"),    // No value above threshold
            Arguments.of(new int[] {}, 1, "Input cannot be empty")                          // Empty array
        );
    }

    @ParameterizedTest
    @MethodSource("provideForReverseRows")
    <E> void testReverseRows(final E[][] input, final E[][] expected) {
        final E[][] output = ArrayUtils.reverseRows(input);
        assertThat(output)
            .isDeepEqualTo(expected);
    }

    private static Stream<Arguments> provideForReverseRows() {
        return Stream.of(
            // Valid input
            Arguments.of(
                new Character[][] {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'}
                },
                new Character[][] {
                    {'g', 'h', 'i'},
                    {'d', 'e', 'f'},
                    {'a', 'b', 'c'}
                }
            ),
            // Different column lengths
            Arguments.of(
                new Character[][] {
                    {'a', 'b', 'c'},
                    {'d', 'e'},
                    {'f', 'g', 'h', 'i'}
                },
                new Character[][] {
                    {'f', 'g', 'h', 'i'},
                    {'d', 'e'},
                    {'a', 'b', 'c'}
                }
            ),
            // Different type
            Arguments.of(
                new Integer[][] {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
                },
                new Integer[][] {
                    {7, 8, 9},
                    {4, 5, 6},
                    {1, 2, 3}
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForReverseRows_invalid")
    <E> void testReverseRows_givenInvalidInputs(final E[][] input, final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.reverseRows(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForReverseRows_invalid() {
        return Stream.of(
            Arguments.of(new Character[][] {}, "Input cannot be empty"),         // Empty 1D array
            Arguments.of(new Character[][] {{}, {}}, "Input cannot be empty")    // Empty 2D array
        );
    }

    @ParameterizedTest
    @MethodSource("provideForTranspose")
    <E> void testTranspose(final E[][] input, final E blankSpaceValue, final E[][] expected) {
        final E[][] output = ArrayUtils.transpose(input, blankSpaceValue);
        assertThat(output)
            .isDeepEqualTo(expected);
    }

    private static Stream<Arguments> provideForTranspose() {
        return Stream.of(
            // Valid
            Arguments.of(
                new Character[][] {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}},
                ' ',
                new Character[][] {{'a', 'd', 'g', 'j'}, {'b', 'e', 'h', 'k'}, {'c', 'f', 'i', 'l'}}
            ),
            // Valid with gaps
            Arguments.of(
                new Character[][] {{'a', 'b', 'c'}, {'d', 'e', 'f', 'g'}, {'h', 'i'}},
                ' ',
                new Character[][] {{'a', 'd', 'h'}, {'b', 'e', 'i'}, {'c', 'f', ' '}, {' ', 'g', ' '}}
            ),
            // Different type with gaps
            Arguments.of(
                new Integer[][] {{1, 2, 3}, {4, 5, 6, 7}, {8, 9}},
                0,
                new Integer[][] {{1, 4, 8}, {2, 5, 9}, {3, 6, 0}, {0, 7, 0}}
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForTranspose_invalid")
    <E> void testTranspose_givenInvalidInputs(final E[][] input, final E blankSpaceValue, final String errorMessage) {
        assertThatThrownBy(() -> ArrayUtils.transpose(input, blankSpaceValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForTranspose_invalid() {
        return Stream.of(
            Arguments.of(new Character[][] {}, ' ', "Input cannot be empty"),       // Empty 1D array
            Arguments.of(new Character[][] {{}, {}}, ' ', "Input cannot be empty")  // Empty 2D array
        );
    }
}
