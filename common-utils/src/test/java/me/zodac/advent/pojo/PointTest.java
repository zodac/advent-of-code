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

package me.zodac.advent.pojo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import me.zodac.advent.pojo.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link Point}.
 */
class PointTest {

    @Test
    void testConstructors() {
        final Point point = Point.of(-1, 2);
        assertThat(point.x())
            .isEqualTo(-1);
        assertThat(point.y())
            .isEqualTo(2);

        final Point originPoint = Point.atOrigin();
        assertThat(originPoint.x())
            .isZero();
        assertThat(originPoint.y())
            .isZero();
    }

    @ParameterizedTest
    @MethodSource("provideForParse")
    void testParse(final String input, final int expectedX, final int expectedY) {
        final Point point = Point.parse(input);
        assertThat(point.x())
            .isEqualTo(expectedX);
        assertThat(point.y())
            .isEqualTo(expectedY);
    }

    private static Stream<Arguments> provideForParse() {
        return Stream.of(
            Arguments.of("1,2", 1, 2),                                                      // Basic point, no spaces
            Arguments.of("1, 3", 1, 3),                                                     // Basic point, space between values
            Arguments.of("1|5", 1, 5),                                                      // Basic point, non-comma delimiter
            Arguments.of("-1,-9", -1, -9),                                                  // Negative values
            Arguments.of("10,-100", 10, -100),                                              // Multiple digits
            Arguments.of("2147483647,2147483647", Integer.MAX_VALUE, Integer.MAX_VALUE),    // Integer max
            Arguments.of("-2147483648,-2147483648", Integer.MIN_VALUE, Integer.MIN_VALUE)   // Integer min
        );
    }

    @ParameterizedTest
    @MethodSource("provideForParse_invalid")
    void testParse_invalid(final String input, final String errorMessage) {
        assertThatThrownBy(() -> Point.parse(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForParse_invalid() {
        return Stream.of(
            Arguments.of("hello", "Cannot find two values in input: 'hello'"),                              // No integers
            Arguments.of("1", "Cannot find two values in input: '1'"),                                      // Single integers
            Arguments.of("1,2,3", "Cannot find two values in input: '1,2,3'"),                              // More than 2 integers
            Arguments.of("2147483648,1", "Input has values larger than an integer: '2147483648,1'"),        // X is too big
            Arguments.of("1,2147483648", "Input has values larger than an integer: '1,2147483648'"),        // Y is too big
            Arguments.of("-2147483649,1", "Input has values smaller than an integer: '-2147483649,1'"),     // X is too small
            Arguments.of("1,-2147483649", "Input has values smaller than an integer: '1,-2147483649'"),     // Y is too small
            Arguments.of("", "Cannot find two values in input: ''")                                         // Empty
        );
    }

    @ParameterizedTest
    @MethodSource("provideForOfMany")
    void testOfMany(final String input, final List<Point> expected) {
        final List<Point> points = Point.ofMany(input);
        assertThat(points)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForOfMany() {
        return Stream.of(
            // Basic point, no spaces
            Arguments.of("1,2 -> 2,3", List.of(Point.of(1, 2), Point.of(2, 3))),
            // Basic point, space between values
            Arguments.of("1| 3", List.of(Point.of(1, 3))),
            // Integer max
            Arguments.of("2147483647,2147483647", List.of(Point.of(Integer.MAX_VALUE, Integer.MAX_VALUE))),
            // Integer min
            Arguments.of("-2147483648,-2147483648", List.of(Point.of(Integer.MIN_VALUE, Integer.MIN_VALUE))),
            // Three points, with negatives, spaces and difference separators
            Arguments.of("1|5 -> -4 , 5 -> 1,-2", List.of(Point.of(1, 5), Point.of(-4, 5), Point.of(1, -2)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForOfMany_invalid")
    void testOfMany_invalid(final String input, final String errorMessage) {
        assertThatThrownBy(() -> Point.parse(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(errorMessage);
    }

    private static Stream<Arguments> provideForOfMany_invalid() {
        return Stream.of(
            Arguments.of("hello", "Cannot find two values in input: 'hello'"),                              // No integers
            Arguments.of("1", "Cannot find two values in input: '1'"),                                      // Single integers
            Arguments.of("1,2,3", "Cannot find two values in input: '1,2,3'"),                              // More than 2 integers
            Arguments.of("2147483648,1", "Input has values larger than an integer: '2147483648,1'"),        // X is too big
            Arguments.of("1,2147483648", "Input has values larger than an integer: '1,2147483648'"),        // Y is too big
            Arguments.of("-2147483649,1", "Input has values smaller than an integer: '-2147483649,1'"),     // X is too small
            Arguments.of("1,-2147483649", "Input has values smaller than an integer: '1,-2147483649'"),     // Y is too small
            Arguments.of("", "Cannot find two values in input: ''")                                         // Empty
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDeltaTo")
    void testDeltaTo(final Point first, final Point second, final int deltaX, final int deltaY) {
        final Pair<Integer, Integer> delta = first.deltaTo(second);
        assertThat(delta.first())
            .isEqualTo(deltaX);
        assertThat(delta.second())
            .isEqualTo(deltaY);
    }

    private static Stream<Arguments> provideForDeltaTo() {
        return Stream.of(
            Arguments.of(Point.of(0, 0), Point.of(1, 1), 1, 1), // Second greater than first
            Arguments.of(Point.of(0, 0), Point.of(-1, -1), -1, -1), // Second less than first
            Arguments.of(Point.of(0, 0), Point.of(-1, 1), -1, 1), // Second mixed greater/less than first
            Arguments.of(Point.of(5, 5), Point.of(5, 5), 0, 0) // Second equal to first
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDistanceTo")
    void testDistanceTo(final Point first, final Point second, final long expected) {
        final long distance = first.distanceTo(second);
        assertThat(distance)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideForDistanceTo() {
        return Stream.of(
            Arguments.of(Point.of(0, 0), Point.of(1, 1), 2),    // Positive
            Arguments.of(Point.of(0, 0), Point.of(-1, -1), 2),  // Negative
            Arguments.of(Point.of(0, 0), Point.of(-1, 1), 2),   // Mixed
            Arguments.of(Point.of(5, 5), Point.of(5, 5), 0, 0)  // No distance
        );
    }
}
