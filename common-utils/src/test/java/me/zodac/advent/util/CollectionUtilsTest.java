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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CollectionUtils}.
 */
class CollectionUtilsTest {

    @Test
    void whenContainsDuplicates_givenInputWithDuplicates_thenTrueIsReturned() {
        final List<Integer> input = List.of(1, 2, 2);
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsDuplicates_givenInputWithMultipleDuplicates_thenTrueIsReturned() {
        final List<Integer> input = List.of(1, 1, 2, 2);
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsDuplicates_givenInputWithNoDuplicates_thenFalseIsReturned() {
        final List<Integer> input = List.of(1, 2, 3);
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenEmptyInput_thenFalseIsReturned() {
        final List<Integer> input = List.of();
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenNullInput_thenFalseIsReturned() {
        final List<Integer> input = null;
        final boolean output = CollectionUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenExtractValuesAsList_givenCollection_thenElementsAreExtractedAndReturnedAsList() {
        final List<Long> input = List.of(1L, 2L, 3L);
        final List<Integer> output = CollectionUtils.extractValuesAsList(input, Long::intValue);
        assertThat(output)
            .containsExactly(1, 2, 3);
    }

    @Test
    void whenExtractValuesAsList_givenEmptyCollection_thenEmptyListIsReturned() {
        final List<Long> input = List.of();
        final List<Integer> output = CollectionUtils.extractValuesAsList(input, Long::intValue);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenGeneratePermutations_givenListWithSingleEntry_thenSingleEntryIsReturned() {
        final List<String> input = List.of("a");
        final List<List<String>> output = CollectionUtils.generatePermutations(input);
        assertThat(output)
            .containsExactly(List.of("a"));
    }

    @Test
    void whenGeneratePermutations_givenListWithMultipleEntries_thenAllPermutationsAreReturned() {
        final List<String> input = List.of("a", "b", "c");
        final List<List<String>> output = CollectionUtils.generatePermutations(input);
        assertThat(output)
            .containsExactlyInAnyOrder(
                List.of("a", "b", "c"),
                List.of("a", "c", "b"),
                List.of("b", "a", "c"),
                List.of("b", "c", "a"),
                List.of("c", "a", "b"),
                List.of("c", "b", "a")
            );
    }

    @Test
    void whenGeneratePermutations_givenEmptyList_thenEmptyListIsReturned() {
        final List<String> input = List.of();
        final List<List<String>> output = CollectionUtils.generatePermutations(input);
        assertThat(output)
            .containsExactly(List.of());
    }

    @Test
    void whenGetFirst_givenCollection_thenFirstElementIsReturned() {
        final List<String> input = List.of("a", "b", "c");
        final String output = CollectionUtils.getFirst(input);
        assertThat(output)
            .isEqualTo("a");
    }

    @Test
    void whenGetFirst_givenEmptyCollection_thenExceptionIsThrown() {
        final List<String> input = List.of();
        assertThatThrownBy(() -> CollectionUtils.getFirst(input))
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenGetKeyByValue_givenValueDoesExist_thenKeyIsReturned() {
        final Map<String, String> inputMap = Map.of(
            "key1", "value1",
            "key2", "value2"
        );
        final String inputValue = "value2";

        final Optional<String> output = CollectionUtils.getKeyByValue(inputMap, inputValue);
        assertThat(output)
            .isPresent()
            .hasValue("key2");
    }

    @Test
    void whenGetKeyByValue_givenValueDoesNotExist_thenEmptyOptionalIsReturned() {
        final Map<String, String> inputMap = Map.of(
            "key1", "value1",
            "key2", "value2"
        );
        final String inputValue = "value3";

        final Optional<String> output = CollectionUtils.getKeyByValue(inputMap, inputValue);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenGetKeyByValue_givenEmptyMap_thenEmptyOptionalIsReturned() {
        final Map<String, String> inputMap = Collections.emptyMap();
        final String inputValue = "value1";

        final Optional<String> output = CollectionUtils.getKeyByValue(inputMap, inputValue);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenGetLast_givenCollection_thenLastElementIsReturned() {
        final List<String> input = List.of("a", "b", "c");
        final String output = CollectionUtils.getLast(input);
        assertThat(output)
            .isEqualTo("c");
    }

    @Test
    void whenGetLast_givenEmptyCollection_thenExceptionIsThrown() {
        final List<String> input = List.of();
        assertThatThrownBy(() -> CollectionUtils.getLast(input))
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenGetMiddleValueOfList_givenListOfOddSize_thenMiddleValueIsReturned() {
        final List<String> input = List.of("a", "b", "c");
        final String output = CollectionUtils.getMiddleValueOfList(input);
        assertThat(output)
            .isEqualTo("b");
    }

    @Test
    void whenGetMiddleValueOfList_givenUnsortedListOfOddSize_thenMiddleValueOfSortedListIsReturned() {
        final List<String> input = List.of("c", "a", "b");
        final String output = CollectionUtils.getMiddleValueOfList(input);
        assertThat(output)
            .isEqualTo("b");
    }

    @Test
    void whenGetMiddleValueOfList_givenListWithSingleEntry_thenOnlyValueIsReturned() {
        final List<String> input = List.of("a");
        final String output = CollectionUtils.getMiddleValueOfList(input);
        assertThat(output)
            .isEqualTo("a");
    }

    @Test
    void whenGetMiddleValueOfList_givenListOfEvenSize_thenIllegalArgumentExceptionIsThrown() {
        final List<String> input = List.of("a", "b");
        assertThatThrownBy(() -> CollectionUtils.getMiddleValueOfList(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenGetMiddleValueOfList_givenEmptyList_thenIllegalArgumentExceptionIsThrown() {
        final List<String> input = Collections.emptyList();
        assertThatThrownBy(() -> CollectionUtils.getMiddleValueOfList(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenGroupBySize_givenValidCollection_thenGroupedCollectionsAreReturned() {
        final List<String> input = List.of("a", "b", "c", "d");
        final List<List<String>> output = CollectionUtils.groupBySize(input, 2);
        assertThat(output)
            .hasSize(2)
            .containsExactly(
                List.of("a", "b"),
                List.of("c", "d")
            );
    }

    @Test
    void whenGroupBySize_givenEmptyCollection_thenEmptyGroupIsReturned() {
        final List<String> input = List.of();
        final List<List<String>> output = CollectionUtils.groupBySize(input, 2);

        assertThat(output)
            .hasSize(1)
            .containsExactly(List.of());
    }

    @Test
    void whenGroupBySize_givenCollectionWithInvalidNumberOfEntries_thenExceptionIsThrown() {
        final List<String> input = List.of("a");
        assertThatThrownBy(() -> CollectionUtils.groupBySize(input, 2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Expected number of entries to be divisible by 2, found: 1");
    }

    @Test
    void whenGroupBySize_givenCollection_andZeroAmountPerGroup_thenExceptionIsThrown() {
        final List<String> input = List.of("a");
        assertThatThrownBy(() -> CollectionUtils.groupBySize(input, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("amountPerGroup must be at least 1, found: 0");
    }

    @Test
    void whenGroupBySize_givenCollection_andNegativeAmountPerGroup_thenExceptionIsThrown() {
        final List<String> input = List.of("a");
        assertThatThrownBy(() -> CollectionUtils.groupBySize(input, -2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("amountPerGroup must be at least 1, found: -2");
    }
}
