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

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class with functions for reading {@link String}s from example input files. The example inputs are retrieved from the
 * {@code src/main/resources} directory from the calling {@link Class}'s module itself.
 */
public final class ExampleInput {

    private static final String NEW_LINE = "\n";

    private ExampleInput() {

    }

    /**
     * Reads all lines from a file in {@code src/main/resources}.
     *
     * @param resourceFilePath file path in resources to be read
     * @return a {@link List} of the {@link String} lines from the file, or {@link Collections#emptyList()} if an error occurs
     * @see FileUtils#readLines(Path)
     */
    public static List<String> readLines(final String resourceFilePath) {
        try {
            final Path filePath = Paths.get(ClassLoader.getSystemResource(resourceFilePath).toURI());
            return FileUtils.readLines(filePath);
        } catch (final URISyntaxException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Reads all lines from a file in {@code src/main/resources}, then groups the lines into {@link List}s, where each group occurs when the
     * provided {@link Predicate} is met.
     *
     * <ul>
     *     <li>The line which matches the {@link Predicate} is discarded and not included in the output</li>
     *     <li>If the {@link Predicate} is not met, we return an empty {@link List}</li>
     *     <li>Empty groups are not included in the output</li>
     * </ul>
     *
     * @param resourceFilePath file path to be read
     * @param predicate        the {@link Predicate} defining where the {@link List} is to be split
     * @return the group of {@link List}s of {@link String} lines
     * @see #readLines(String)
     * @see FileUtils#readLinesAsGroups(Collection, Predicate)
     */
    public static List<List<String>> readLinesAsGroups(final String resourceFilePath, final Predicate<? super String> predicate) {
        final List<String> lines = readLines(resourceFilePath);
        return FileUtils.readLinesAsGroups(lines, predicate);
    }

    /**
     * Reads all lines from a file in {@code src/main/resources}, then mapping each {@link String} to an {@link Integer}.
     *
     * @param resourceFilePath file path to be read
     * @return a {@link List} of the {@link Integer}s from the file, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value in the file is not a valid {@link Integer}
     * @see #readLines(String)
     * @see FileUtils#readLinesAsIntegers(Collection)
     */
    public static List<Integer> readLinesAsIntegers(final String resourceFilePath) {
        final List<String> lines = readLines(resourceFilePath);
        return FileUtils.readLinesAsIntegers(lines);
    }

    /**
     * Reads all lines from a file in {@code src/main/resources}, then concatenates them into a single {@link String}, with the delimiter as
     * {@value #NEW_LINE}.
     *
     * @param resourceFilePath file path to be read
     * @return the {@link String} lines from the file
     * @see #readLines(String)
     */
    public static String readLinesAsSingleString(final String resourceFilePath) {
        return String.join(NEW_LINE, readLines(resourceFilePath));
    }

    /**
     * Reads the only line from a file in {@code src/main/resources}.
     *
     * @param resourceFilePath file path to be read
     * @return the {@link String} line from the file
     * @throws IllegalArgumentException thrown if there is not exactly one line in the file
     * @see #readLines(String)
     * @see FileUtils#readSingleLine(List)
     */
    public static String readSingleLine(final String resourceFilePath) {
        final List<String> lines = readLines(resourceFilePath);
        return FileUtils.readSingleLine(lines);
    }

    /**
     * Reads all lines from a file in {@code src/main/resources} where each line is a row of comma-separated {@link Integer}s.
     *
     * @param resourceFilePath file path to be read
     * @return a {@link List} of each line from the file as a {@link List} of {@link Integer}s, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value is not a valid {@link Integer} separated by commas
     * @see #readLines(String)
     * @see FileUtils#readSingleLineOfCommaSeparatedIntegers(Collection)
     */
    public static List<List<Integer>> readSingleLineOfCommaSeparatedIntegers(final String resourceFilePath) {
        final List<String> lines = readLines(resourceFilePath);
        return FileUtils.readSingleLineOfCommaSeparatedIntegers(lines);
    }
}
