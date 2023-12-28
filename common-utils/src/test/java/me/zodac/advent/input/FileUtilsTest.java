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

package me.zodac.advent.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link FileUtils}.
 */
class FileUtilsTest {

    @ParameterizedTest
    @MethodSource("provideForReadLines")
    void testReadLines(final String fileName, final List<String> expected) {
        final Path input = get(fileName);
        final List<String> output = FileUtils.readLines(input);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForReadLines() {
        return Stream.of(
            Arguments.of("validFileOfStrings.txt", List.of("line1", "line2", "line3")),
            Arguments.of("emptyFile.txt", List.of())
        );
    }

    @Test
    void testReadLines_givenNonExistingFile() {
        final Path input = get("nonExistingFile.txt");
        assertThatThrownBy(() -> FileUtils.readLines(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Unable to read input file 'nonExistingFile_nonExistingFile.txt'");
    }

    @ParameterizedTest
    @MethodSource("provideForReadLinesAsGroups")
    void testReadLinesAsGroups(final String fileName, final Predicate<? super String> predicate, final List<? extends List<String>> expected) {
        final Path input = get(fileName);
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, predicate);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForReadLinesAsGroups() {
        final Predicate<? super String> predicate = String::isEmpty;
        final Predicate<? super String> customPredicate = s -> s.startsWith("Can");
        return Stream.of(
            Arguments.of("validFileOfStringsWithDelimiter.txt", predicate, List.of(List.of("line1", "line2"), List.of("line3"))),
            Arguments.of("validFileOfStringsWithDelimiterOnFirstLine.txt", predicate, List.of(List.of("line1", "line2", "line3"))),
            Arguments.of("validFileOfStringsWithCustomDelimiter.txt", customPredicate, List.of(List.of("line1", "line2"), List.of("line3"))),
            Arguments.of("validFileOfStringsWithMultipleDelimiters.txt", predicate, List.of(List.of("line1"), List.of("line2"), List.of("line3"))),
            Arguments.of("validFileOfStringsWithoutDelimiter.txt", predicate, List.of(List.of("line1", "line2", "line3"))),
            Arguments.of("emptyFile.txt", predicate, List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideForReadLinesAsIntegers")
    void testReadLinesAsIntegers(final String fileName, final List<Integer> expected) {
        final Path input = get(fileName);
        final List<String> lines = FileUtils.readLines(input);

        final List<Integer> output = FileUtils.readLinesAsIntegers(lines);
        assertThat(output)
            .hasSameElementsAs(expected);
    }

    private static Stream<Arguments> provideForReadLinesAsIntegers() {
        return Stream.of(
            Arguments.of("validFileOfIntegers.txt", List.of(1, 2, 3)),
            Arguments.of("emptyFile.txt", List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideForReadLinesAsIntegers_invalid")
    void testReadLinesAsIntegers_givenInvalidInputs(final String fileName, final String errorMessage) {
        final Path input = get(fileName);
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readLinesAsIntegers(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(errorMessage);
    }

    private static Stream<Arguments> provideForReadLinesAsIntegers_invalid() {
        return Stream.of(
            Arguments.of("validFileOfLongs.txt", "For input string: \"2147483648\""),
            Arguments.of("validFileOfStrings.txt", "For input string: \"line1\"")
        );
    }

    @Test
    void testReadSingleLine() {
        final Path input = get("validFileOfSingleString.txt");
        final List<String> lines = FileUtils.readLines(input);

        final String output = FileUtils.readSingleLine(lines);
        assertThat(output)
            .isEqualTo("line1");
    }

    @ParameterizedTest
    @MethodSource("provideForReadSingleLine_invalid")
    void testReadSingleLine_givenInvalidInputs(final String fileName, final String errorMessage) {
        final Path input = get(fileName);
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readSingleLine(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(errorMessage);
    }

    private static Stream<Arguments> provideForReadSingleLine_invalid() {
        return Stream.of(
            Arguments.of("validFileOfStrings.txt", "Expected a single line, found: 3"),
            Arguments.of("emptyFile.txt", "Expected a single line, found: 0")
        );
    }

    @Test
    void whenReadLinesOfCommaSeparatedIntegers_givenSingleLineOfIntegers_thenListOfListOfIntegersIsReturned() {
        final Path input = get("validCsvOfIntegersSingleLine.csv");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<Integer>> output = FileUtils.readLinesOfCommaSeparatedIntegers(lines);

        assertThat(output)
            .hasSameElementsAs(List.of(List.of(1, 2, 3)));
    }

    @Test
    void whenReadLinesOfCommaSeparatedIntegers_givenMultipleLinesOfIntegers_thenListOfListOfIntegersIsReturned() {
        final Path input = get("validCsvOfIntegers.csv");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<Integer>> output = FileUtils.readLinesOfCommaSeparatedIntegers(lines);

        assertThat(output)
            .hasSameElementsAs(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)));
    }

    @Test
    void whenReadLinesOfCommaSeparatedIntegers_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<Integer>> output = FileUtils.readLinesOfCommaSeparatedIntegers(lines);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadLinesOfCommaSeparatedIntegers_givenInvalidInteger_thenExceptionIsThrown() {
        final Path input = get("invalidCsvOfIntegers.csv");
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readLinesOfCommaSeparatedIntegers(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("For input string: \"five\"");
    }

    @Test
    void whenReadLinesOfCommaSeparatedStrings_givenSingleLineOfStrings_thenListOfListOfStringsIsReturned() {
        final Path input = get("validCsvOfStringsSingleLine.csv");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesOfCommaSeparatedStrings(lines);

        assertThat(output)
            .hasSameElementsAs(List.of(List.of("one", "two", "three")));
    }

    @Test
    void whenReadLinesOfCommaSeparatedStrings_givenMultipleLinesOfStrings_thenListOfListOfIntegersIsReturned() {
        final Path input = get("validCsvOfStrings.csv");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesOfCommaSeparatedStrings(lines);

        assertThat(output)
            .hasSameElementsAs(List.of(List.of("one", "two", "three"), List.of("four", "five", "six"), List.of("seven", "eight", "nine")));
    }

    @Test
    void whenReadLinesOfCommaSeparatedStrings_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesOfCommaSeparatedStrings(lines);
        assertThat(output)
            .isEmpty();
    }

    private static Path get(final String fileName) {
        try {
            final URL url = ClassLoader.getSystemResource(fileName);
            return url == null ? Paths.get("nonExistingFile_" + fileName) : Paths.get(url.toURI());
        } catch (final URISyntaxException e) {
            throw new AssertionError(e);
        }
    }
}
