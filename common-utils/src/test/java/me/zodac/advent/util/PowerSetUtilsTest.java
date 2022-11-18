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

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PowerSetUtils}.
 */
class PowerSetUtilsTest {

    @Test
    void whenGetPowerSet_givenThreeElements_thenPowerSetIsReturned() {
        final Set<Integer> input = Set.of(1, 2, 3);
        final Set<Set<Integer>> output = PowerSetUtils.getPowerSet(input);
        assertThat(output)
            .hasSize(8)
            .containsExactlyInAnyOrder(
                Set.of(),
                Set.of(1),
                Set.of(2),
                Set.of(3),
                Set.of(1, 2),
                Set.of(1, 3),
                Set.of(2, 3),
                Set.of(1, 2, 3)
            );
    }

    @Test
    void whenGetPowerSet_givenEmptyInput_thenEmptySetIsReturned() {
        final Set<Integer> input = Set.of();
        final Set<Set<Integer>> output = PowerSetUtils.getPowerSet(input);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder(
                Set.of()
            );
    }

    @Test
    void whenGetFilteredPowerSet_givenTwoElements_thenPowerSetIsReturned() {
        final Set<BaseFilterable> input = Set.of(
            FirstFilterable.INSTANCE,
            SecondFilterable.INSTANCE
        );
        final List<PowerSetFilter<? extends BaseFilterable>> filters = List.of(
            new PowerSetFilter<>(FirstFilterable.class, 1, 2, false),
            new PowerSetFilter<>(SecondFilterable.class, 1, 2, true)
        );

        final Set<Set<BaseFilterable>> output = PowerSetUtils.getFilteredPowerSet(input, filters);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder(
                Set.of(FirstFilterable.INSTANCE, SecondFilterable.INSTANCE)
            );
    }

    @Test
    void whenGetFilteredPowerSet_givenEmptyInput_andFiltersAllowEmpty_thenEmptyListIsReturned() {
        final Set<BaseFilterable> input = Set.of();
        final List<PowerSetFilter<? extends BaseFilterable>> filters = List.of(
            new PowerSetFilter<>(FirstFilterable.class, 0, 2, false),
            new PowerSetFilter<>(SecondFilterable.class, 0, 2, true)
        );

        final Set<Set<BaseFilterable>> output = PowerSetUtils.getFilteredPowerSet(input, filters);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder(
                Set.of()
            );
    }

    @Test
    void whenGetPowerList_givenThreeElements_thenPowerSetIsReturned() {
        final List<Integer> input = List.of(1, 2, 3);
        final List<List<Integer>> output = PowerSetUtils.getPowerList(input);
        assertThat(output)
            .hasSize(8)
            .containsExactlyInAnyOrder(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(1, 2),
                List.of(1, 3),
                List.of(2, 3),
                List.of(1, 2, 3)
            );
    }

    @Test
    void whenGetPowerList_givenThreeElementsWithDuplicate_thenPowerSetIsReturned() {
        final List<Integer> input = List.of(1, 1, 3);
        final List<List<Integer>> output = PowerSetUtils.getPowerList(input);
        assertThat(output)
            .hasSize(8)
            .containsExactlyInAnyOrder(
                List.of(),
                List.of(1),
                List.of(1),
                List.of(3),
                List.of(1, 1),
                List.of(1, 3),
                List.of(1, 3),
                List.of(1, 1, 3)
            );
    }

    @Test
    void whenGetPowerList_givenEmptyInput_thenEmptyListIsReturned() {
        final List<Integer> input = List.of();
        final List<List<Integer>> output = PowerSetUtils.getPowerList(input);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder(
                List.of()
            );
    }

    @Test
    void whenGetFilteredPowerList_givenThreeElements_andSomeCombinationFailsFilter_thenPowerListIsReturnedWithoutFailingCombinations() {
        final List<BaseFilterable> input = List.of(
            FirstFilterable.INSTANCE,
            FirstFilterable.INSTANCE,
            SecondFilterable.INSTANCE
        );
        final List<PowerSetFilter<? extends BaseFilterable>> filters = List.of(
            new PowerSetFilter<>(FirstFilterable.class, 1, 2, true),
            new PowerSetFilter<>(SecondFilterable.class, 1, 2, true)
        );

        final List<List<BaseFilterable>> output = PowerSetUtils.getFilteredPowerList(input, filters);
        assertThat(output)
            .hasSize(3)
            .containsExactlyInAnyOrder(
                List.of(FirstFilterable.INSTANCE, SecondFilterable.INSTANCE),
                List.of(FirstFilterable.INSTANCE, SecondFilterable.INSTANCE),
                List.of(FirstFilterable.INSTANCE, FirstFilterable.INSTANCE, SecondFilterable.INSTANCE)
            );
    }

    @Test
    void whenGetFilteredPowerList_givenThreeElementsAndDisallowDuplicates_thenPowerListIsReturnedWithoutDuplicatesCombinations() {
        final List<BaseFilterable> input = List.of(
            FirstFilterable.INSTANCE,
            FirstFilterable.INSTANCE,
            SecondFilterable.INSTANCE
        );
        final List<PowerSetFilter<? extends BaseFilterable>> filters = List.of(
            new PowerSetFilter<>(FirstFilterable.class, 1, 2, false),
            new PowerSetFilter<>(SecondFilterable.class, 1, 2, true)
        );

        final List<List<BaseFilterable>> output = PowerSetUtils.getFilteredPowerList(input, filters);
        assertThat(output)
            .hasSize(2)
            .containsExactlyInAnyOrder(
                List.of(FirstFilterable.INSTANCE, SecondFilterable.INSTANCE),
                List.of(FirstFilterable.INSTANCE, SecondFilterable.INSTANCE)
            );
    }

    @Test
    void whenGetFilteredPowerList_givenEmptyInput_andFiltersAllowEmpty_thenEmptyListIsReturned() {
        final List<BaseFilterable> input = List.of();
        final List<PowerSetFilter<? extends BaseFilterable>> filters = List.of(
            new PowerSetFilter<>(FirstFilterable.class, 0, 3, false),
            new PowerSetFilter<>(SecondFilterable.class, 0, 3, true)
        );

        final List<List<BaseFilterable>> output = PowerSetUtils.getFilteredPowerList(input, filters);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder(
                List.of()
            );
    }

    private static class BaseFilterable {

    }

    private static final class FirstFilterable extends BaseFilterable {

        private static final FirstFilterable INSTANCE = new FirstFilterable();
    }

    private static final class SecondFilterable extends BaseFilterable {

        private static final SecondFilterable INSTANCE = new SecondFilterable();
    }
}
