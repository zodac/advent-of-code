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

package me.zodac.advent.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FileUtils}.
 */
class FileUtilsTest {

    @Test
    void whenReadLines_givenValidFileOfStrings_thenListOfStringsIsReturned() {
        final Path input = get("validFileOfStrings.txt");
        final List<String> output = FileUtils.readLines(input);
        assertThat(output)
            .hasSize(3)
            .containsExactly("line1", "line2", "line3");
    }

    @Test
    void whenReadLines_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> output = FileUtils.readLines(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithDelimiter_thenGroupOfListOfStringsIsReturned() {
        final Path input = get("validFileOfStringsWithDelimiter.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, String::isEmpty);
        assertThat(output)
            .containsExactly(
                List.of("line1", "line2"),
                List.of("line3")
            );
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithDelimiterOnFirstLine_thenGroupOfStringsIsReturned() {
        final Path input = get("validFileOfStringsWithDelimiterOnFirstLine.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, String::isEmpty);
        assertThat(output)
            .containsExactly(
                List.of("line1", "line2", "line3")
            );
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithCustomDelimiter_thenGroupOfStringsIsReturned() {
        final Path input = get("validFileOfStringsWithCustomDelimiter.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, s -> s.startsWith("Can"));
        assertThat(output)
            .containsExactly(
                List.of("line1", "line2"),
                List.of("line3")
            );
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithMultipleDelimiters_thenMultipleGroupsAreReturned() {
        final Path input = get("validFileOfStringsWithMultipleDelimiters.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, String::isEmpty);
        assertThat(output)
            .containsExactly(
                List.of("line1"),
                List.of("line2"),
                List.of("line3")
            );
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithoutDelimiter_thenListWithSingleListOfStringsIsReturned() {
        final Path input = get("validFileOfStringsWithoutDelimiter.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, String::isEmpty);
        assertThat(output)
            .containsExactly(
                List.of("line1", "line2", "line3")
            );
    }

    @Test
    void whenReadLinesAndSplit_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<String>> output = FileUtils.readLinesAsGroups(lines, String::isEmpty);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadLinesAsIntegers_givenValidFileOfIntegers_thenListOfStringsIsReturned() {
        final Path input = get("validFileOfIntegers.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<Integer> output = FileUtils.readLinesAsIntegers(lines);
        assertThat(output)
            .hasSize(3)
            .containsExactly(1, 2, 3);
    }

    @Test
    void whenReadLinesAsIntegers_givenInvalidFileOfIntegers_thenExceptionIsThrown() {
        final Path input = get("validFileOfStrings.txt");
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readLinesAsIntegers(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("For input string: \"line1\"");
    }

    @Test
    void whenReadLinesAsIntegers_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<Integer> output = FileUtils.readLinesAsIntegers(lines);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadSingleLine_givenFileWithSingleString_thenStringIsReturned() {
        final Path input = get("validFileOfSingleString.txt");
        final List<String> lines = FileUtils.readLines(input);

        final String output = FileUtils.readSingleLine(lines);
        assertThat(output)
            .isEqualTo("line1");
    }

    @Test
    void whenReadSingleLine_givenFileWithMultipleStrings_thenExceptionIsThrown() {
        final Path input = get("validFileOfStrings.txt");
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readSingleLine(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected a single line, found: 3");
    }

    @Test
    void whenReadSingleLine_givenEmptyFile_thenExceptionIsThrown() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readSingleLine(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected a single line, found: 0");
    }

    @Test
    void whenReadSingleLineOfCommaSeparatedIntegers_givenMultipleLinesOfIntegers_thenListOfListOfIntegersIsReturned() {
        final Path input = get("validCsvOfIntegers.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<Integer>> output = FileUtils.readSingleLineOfCommaSeparatedIntegers(lines);

        assertThat(output)
            .hasSize(3)
            .containsExactly(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));

        final List<Integer> allValues = output
            .stream()
            .flatMap(Collection::stream)
            .toList();

        assertThat(allValues)
            .hasSize(9)
            .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    void whenReadSingleLineOfCommaSeparatedIntegers_givenInvalidInteger_thenExceptionIsThrown() {
        final Path input = get("invalidCsvOfIntegers.txt");
        final List<String> lines = FileUtils.readLines(input);

        assertThatThrownBy(() -> FileUtils.readSingleLineOfCommaSeparatedIntegers(lines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("For input string: \"five\"");
    }

    @Test
    void whenReadSingleLineOfCommaSeparatedIntegers_givenEmptyFile_thenEmptyListIsReturned() {
        final Path input = get("emptyFile.txt");
        final List<String> lines = FileUtils.readLines(input);

        final List<List<Integer>> output = FileUtils.readSingleLineOfCommaSeparatedIntegers(lines);
        assertThat(output)
            .isEmpty();
    }

    private static Path get(final String fileName) {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (final URISyntaxException e) {
            throw new AssertionError(e);
        }
    }
}
