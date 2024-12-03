/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link PermutationUtils}.
 */
public class PermutationUtilsTest {

    @ParameterizedTest
    @MethodSource("provideForGenerateAll")
    <E> void testGenerateAll(final List<? extends E> input, final List<? extends List<E>> expected) {
        final List<List<E>> output = PermutationUtils.generateAll(input);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForGenerateAll() {
        return Stream.of(
            Arguments.of(List.of("a"), List.of(List.of("a"))),   // Single String entry
            Arguments.of(List.of(1), List.of(List.of(1))),       // Single Integer entry
            Arguments.of(List.of(), List.of(List.of())),                // Empty
            Arguments.of(List.of("a", "b", "c"),                        // Multiple String entries
                List.of(
                    List.of("a", "b", "c"),
                    List.of("a", "c", "b"),
                    List.of("b", "a", "c"),
                    List.of("b", "c", "a"),
                    List.of("c", "a", "b"),
                    List.of("c", "b", "a")
                )
            ),
            Arguments.of(List.of(1, 2, 3),                              // Multiple Integer entries
                List.of(
                    List.of(1, 2, 3),
                    List.of(1, 3, 2),
                    List.of(2, 1, 3),
                    List.of(2, 3, 1),
                    List.of(3, 1, 2),
                    List.of(3, 2, 1)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGenerateAllWithOneRemovedEntry")
    <E> void testGenerateAllWithOneRemovedEntry(final List<? extends E> input, final List<? extends List<E>> expected) {
        final List<List<E>> output = PermutationUtils.generateAllWithOneRemovedEntry(input);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForGenerateAllWithOneRemovedEntry() {
        return Stream.of(
            Arguments.of(List.of("a"), List.of(List.of())),  // Single entry
            Arguments.of(List.of(), List.of()),                 // Empty
            // Multiple String entries
            Arguments.of(List.of("a", "b", "c"),
                List.of(
                    List.of("a", "b"),
                    List.of("b", "c"),
                    List.of("a", "c")
                )
            ),
            // Multiple Integer entries
            Arguments.of(List.of(1, 2, 3),
                List.of(
                    List.of(1, 2),
                    List.of(2, 3),
                    List.of(1, 3)
                )
            )
        );
    }
}
