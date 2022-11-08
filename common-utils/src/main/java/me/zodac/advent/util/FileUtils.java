/*
 * Zero-Clause BSD License
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

import static me.zodac.advent.util.CollectionUtils.getFirst;

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

    private static final int SINGLE_ENTRY = 1;

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
     * Reads the only line from a file in {@code src/main/resources}.
     *
     * @param filePathInResources file path to be read
     * @return the {@link String} line from the file
     * @throws IllegalArgumentException thrown if there is not exactly one line in the file
     */
    public static String readSingleLine(final String filePathInResources) {
        final List<String> lines = readLines(filePathInResources);
        if (lines.size() != SINGLE_ENTRY) {
            throw new IllegalArgumentException("Expected a single line, found: " + lines.size());
        }

        return getFirst(lines);
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
