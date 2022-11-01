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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link CollectionUtils}.
 */
class CollectionUtilsTest {

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
}
