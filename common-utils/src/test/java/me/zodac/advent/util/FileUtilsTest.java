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

import java.util.Collection;
import java.util.List;
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
    void whenCommaSeparatedIntegers_givenMultipleLinesOfIntegers_thenEmptyListIsReturned() {
        final String input = "validCsvOfIntegers.txt";
        final List<List<Integer>> output = FileUtils.readCommaSeparatedIntegers(input);

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
}
