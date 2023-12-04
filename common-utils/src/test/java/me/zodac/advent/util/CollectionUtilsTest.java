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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link CollectionUtils}.
 */
class CollectionUtilsTest {

    @ParameterizedTest
    @MethodSource("provideForContainsDuplicates")
    void testContainsDuplicates(final List<Integer> input, final boolean expected) {
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideForContainsDuplicates() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 2), true),       // Input with single duplicate
            Arguments.of(List.of(1, 1, 2, 2), true),    // Input with multiple duplicates
            Arguments.of(List.of(1, 2, 3), false),      // Input with no duplicates
            Arguments.of(List.of(), false),             // Empty
            Arguments.of(null, false)                   // Null
        );
    }

    @ParameterizedTest
    @MethodSource("provideForExtractValuesAsList")
    void testExtractValuesAsList(final List<Long> input, final List<Integer> expected) {
        final List<Integer> output = CollectionUtils.extractValuesAsList(input, Long::intValue);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForExtractValuesAsList() {
        return Stream.of(
            Arguments.of(List.of(1L, 2L, 3L), List.of(1, 2, 3)),    // Collection of Long, extracting as Integers
            Arguments.of(List.of(), List.of()),                     // Empty
            Arguments.of(null, List.of())                 // Null
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGeneratePermutations")
    void testGeneratePermutations(final List<String> input, final List<? extends List<String>> expected) {
        final List<List<String>> output = CollectionUtils.generatePermutations(input);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForGeneratePermutations() {
        return Stream.of(
            Arguments.of(List.of("a"), List.of(List.of("a"))),  // Single entry
            Arguments.of(List.of(), List.of(List.of())),               // Empty
            // Multiple entries
            Arguments.of(List.of("a", "b", "c"),
                List.of(
                    List.of("a", "b", "c"),
                    List.of("a", "c", "b"),
                    List.of("b", "a", "c"),
                    List.of("b", "c", "a"),
                    List.of("c", "a", "b"),
                    List.of("c", "b", "a")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetKeyByValue")
    void testGetKeyByValue(final Map<String, ? super String> input, final String value, final Optional<String> expected) {
        final Optional<String> output = CollectionUtils.getKeyByValue(input, value);
        assertThat(output)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideForGetKeyByValue() {
        final Map<String, String> inputMap = Map.of("key1", "value1", "key2", "value2");
        return Stream.of(
            Arguments.of(inputMap, "value2", Optional.of("key2")),  // Key exists
            Arguments.of(inputMap, "value3", Optional.empty()),                  // Key doesn't exist
            Arguments.of(Map.of(), "value1", Optional.empty())                   // Empty
        );
    }

    @ParameterizedTest
    @MethodSource("provideForMiddleValue")
    void testMiddleValue(final List<String> input, final String expected) {
        final String output = CollectionUtils.getMiddleValue(input);
        assertThat(output)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideForMiddleValue() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c"), "b"),  // Sorted list of odd size
            Arguments.of(List.of("c", "a", "b"), "b"),  // Unsorted list of odd size
            Arguments.of(List.of("a"), "a")         // List with single size
        );
    }

    @ParameterizedTest
    @MethodSource("provideForMiddleValue_invalid")
    void testMiddleValue_givenInvalidInputs(final List<String> input, final String errorMessage) {
        assertThatThrownBy(() -> CollectionUtils.getMiddleValue(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForMiddleValue_invalid() {
        return Stream.of(
            Arguments.of(List.of("a", "b"), "Expected list with positive odd size, found size: 2"),     // List of even size
            Arguments.of(List.of(), "Expected list with positive odd size, found size: 0")              // Empty
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGroupBySize")
    void testGroupBySize(final List<String> input, final int amountPerGroup, final List<? extends List<String>> expected) {
        final List<List<String>> output = CollectionUtils.groupBySize(input, amountPerGroup);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForGroupBySize() {
        return Stream.of(
            Arguments.of(List.of("a", "b", "c", "d"), 2, List.of(List.of("a", "b"), List.of("c", "d"))),   // Divisible by amountPerGroup
            Arguments.of(List.of(), 2, List.of(List.of()))                                                 // Empty
        );
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "2|Expected number of entries to be divisible by 2, found: 1",  // Invalid number of entries
        "0|amountPerGroup must be at least 1, found: 0",                // Zero amount per group
        "-2|amountPerGroup must be at least 1, found: -2",              // Negative amount per group
    })
    void testGroupBySize_givenInvalidInputs(final int amountPerGroup, final String errorMessage) {
        final List<String> input = List.of("a");
        assertThatThrownBy(() -> CollectionUtils.groupBySize(input, amountPerGroup))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("provideForIntersection")
    void testIntersection(final Set<String> first, final Set<String> second, final Set<String> expected) {
        final Set<String> output = CollectionUtils.intersection(first, second);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForIntersection() {
        return Stream.of(
            Arguments.of(Set.of("a", "b", "c"), Set.of("a", "b", "c"), Set.of("a", "b", "c")),                  // Both inputs match
            Arguments.of(Set.of("a", "b", "c"), Set.of("b", "c", "a"), Set.of("a", "b", "c")),                  // Order insensitive
            Arguments.of(Set.of("a", "b", "c"), Set.of("c", "d", "e", "f"), Set.of("c")),                       // Single match
            Arguments.of(Set.of("a", "b", "c"), Set.of("a", "b", "c", "d", "e", "f"), Set.of("a", "b", "c")),   // Multiple matches
            Arguments.of(Set.of("a", "b", "c"), Set.of("a", "b"), Set.of("a", "b")),                            // Second is a subset of first
            Arguments.of(Set.of("a", "b", "c"), Set.of("d", "e", "f"), Set.of())                                // No match
        );
    }
}
