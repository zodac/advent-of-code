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

import java.util.Collection;
import java.util.List;
import me.zodac.advent.pojo.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FileUtils}.
 */
class FileUtilsTest {

    @Test
    void whenReadLines_givenValidFileOfStrings_thenListOfStringsIsReturned() {
        final String input = "validFileOfStrings.txt";
        final List<String> output = FileUtils.readLines(input);
        assertThat(output)
            .hasSize(3)
            .containsExactly("line1", "line2", "line3");
    }

    @Test
    void whenReadLines_givenEmptyFile_thenEmptyListIsReturned() {
        final String input = "emptyFile.txt";
        final List<String> output = FileUtils.readLines(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithDelimiter_thenPairOfListOfStringsIsReturned() {
        final String input = "validFileOfStringsWithDelimiter.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, String::isEmpty);
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of("line1", "line2"),
                List.of("line3")
            ));
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithDelimiterOnFirstLine_thenPairOfListOfStringsIsReturned() {
        final String input = "validFileOfStringsWithDelimiterOnFirstLine.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, String::isEmpty);
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of(),
                List.of("line1", "line2", "line3")
            ));
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithCustomDelimiter_thenOnlyFirstDelimiterIsConsideredAndPairIsReturned() {
        final String input = "validFileOfStringsWithCustomDelimiter.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, s -> s.startsWith("Can"));
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of("line1", "line2"),
                List.of("line3")
            ));
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithMultipleDelimiters_thenOnlyFirstDelimiterIsConsideredAndPairIsReturned() {
        final String input = "validFileOfStringsWithMultipleDelimiters.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, String::isEmpty);
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of("line1"),
                List.of("line2", "", "line3")
            ));
    }

    @Test
    void whenReadLinesAndSplit_givenValidFileOfStringsWithoutDelimiter_thenPairOfListOfStringsIsReturned() {
        final String input = "validFileOfStringsWithoutDelimiter.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, String::isEmpty);
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of("line1", "line2", "line3"),
                List.of()
            ));
    }

    @Test
    void whenReadLinesAndSplit_givenEmptyFile_thenEmptyListIsReturned() {
        final String input = "emptyFile.txt";
        final Pair<List<String>, List<String>> output = FileUtils.readLinesAndSplit(input, String::isEmpty);
        assertThat(output)
            .isEqualTo(Pair.of(
                List.of(),
                List.of()
            ));
    }

    @Test
    void whenReadLinesAsIntegers_givenValidFileOfIntegers_thenListOfStringsIsReturned() {
        final String input = "validFileOfIntegers.txt";
        final List<Integer> output = FileUtils.readLinesAsIntegers(input);
        assertThat(output)
            .hasSize(3)
            .containsExactly(1, 2, 3);
    }

    @Test
    void whenReadLinesAsIntegers_givenInvalidFileOfIntegers_thenExceptionIsThrown() {
        final String input = "validFileOfStrings.txt";
        assertThatThrownBy(() -> FileUtils.readLinesAsIntegers(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("For input string: \"line1\"");
    }

    @Test
    void whenReadLinesAsIntegers_givenEmptyFile_thenEmptyListIsReturned() {
        final String input = "emptyFile.txt";
        final List<Integer> output = FileUtils.readLinesAsIntegers(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadLinesAsSingleString_givenValidFileOfStrings_thenListOfStringsIsReturned() {
        final String input = "validFileOfStrings.txt";
        final String output = FileUtils.readLinesAsSingleString(input);
        assertThat(output)
            .isEqualTo("""
                line1
                line2
                line3""");
    }

    @Test
    void whenReadLinesAsSingleString_givenEmptyFile_thenEmptyListIsReturned() {
        final String input = "emptyFile.txt";
        final String output = FileUtils.readLinesAsSingleString(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReadSingleLine_givenFileWithSingleString_thenStringIsReturned() {
        final String input = "validFileOfSingleString.txt";
        final String output = FileUtils.readSingleLine(input);
        assertThat(output)
            .isEqualTo("line1");
    }

    @Test
    void whenReadSingleLine_givenFileWithMultipleStrings_thenExceptionIsThrown() {
        final String input = "validFileOfStrings.txt";
        assertThatThrownBy(() -> FileUtils.readSingleLine(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected a single line, found: 3");
    }

    @Test
    void whenReadSingleLine_givenEmptyFile_thenExceptionIsThrown() {
        final String input = "emptyFile.txt";
        assertThatThrownBy(() -> FileUtils.readSingleLine(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected a single line, found: 0");
    }

    @Test
    void whenReadSingleLineOfCommaSeparatedIntegers_givenMultipleLinesOfIntegers_thenListOfListOfIntegersIsReturned() {
        final String input = "validCsvOfIntegers.txt";
        final List<List<Integer>> output = FileUtils.readSingleLineOfCommaSeparatedIntegers(input);

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
        final String input = "invalidCsvOfIntegers.txt";
        assertThatThrownBy(() -> FileUtils.readSingleLineOfCommaSeparatedIntegers(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("For input string: \"five\"");
    }

    @Test
    void whenReadSingleLineOfCommaSeparatedIntegers_givenEmptyFile_thenEmptyListIsReturned() {
        final String input = "emptyFile.txt";
        final List<List<Integer>> output = FileUtils.readSingleLineOfCommaSeparatedIntegers(input);

        assertThat(output)
            .isEmpty();
    }
}
