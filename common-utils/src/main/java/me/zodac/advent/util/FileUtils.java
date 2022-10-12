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

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class with functions for accessing files.
 */
public final class FileUtils {

    private FileUtils() {

    }

    /**
     * Reads all lines from a file in {@code src/main/resources}.
     *
     * @param filePathInResources file path to be read
     * @return a {@link List} of the {@link String} lines from the file, or {@link Collections#emptyList()} if an error occurs
     */
    public static List<String> readLines(final String filePathInResources) {
        try {
            return Files.readAllLines(Paths.get(ClassLoader.getSystemResource(filePathInResources).toURI()));
        } catch (final IOException | URISyntaxException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Reads all lines from a file in {@code src/main/resources} where each line is a row of comma-separated {@link Integer}s.
     *
     * @param filePathInResources file path to be read
     * @return a {@link List} of each line from the file as a {@link List} of {@link Integer}s, or {@link Collections#emptyList()} if an error occurs
     */
    public static List<List<Integer>> readCommaSeparatedIntegers(final String filePathInResources) {
        return readLines(filePathInResources)
            .stream()
            .map(input -> Arrays.asList(input.split(",")))
            .map(listOfStrings -> listOfStrings.stream().mapToInt(Integer::parseInt).boxed().toList())
            .toList();
    }
}
