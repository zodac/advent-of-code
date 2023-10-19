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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class with functions for reading {@link String}s from input files.
 */
final class FileUtils {

    private static final int SINGLE_ENTRY = 1;

    private FileUtils() {

    }

    /**
     * Reads all lines from an input file {@link Path}.
     *
     * @param filePath file {@link Path}
     * @return a {@link List} of the {@link String} lines from the file, or {@link Collections#emptyList()} if an error occurs
     */
    static List<String> readLines(final Path filePath) {
        try {
            return Files.readAllLines(filePath);
        } catch (final IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Given a {@link Collection} of {@link String} lines from a file, groups the lines into {@link List}s, where each group occurs when the provided
     * {@link Predicate} is met.
     *
     * <ul>
     *     <li>The line which matches the {@link Predicate} is discarded and not included in the output</li>
     *     <li>If the {@link Predicate} is not met, we return an empty {@link List}</li>
     *     <li>Empty groups are not included in the output</li>
     * </ul>
     *
     * @param lines     the input {@link String} lines
     * @param predicate the {@link Predicate} defining where the {@link List} is to be split
     * @return the group of {@link List}s of {@link String} lines
     */
    static List<List<String>> readLinesAsGroups(final Collection<String> lines, final Predicate<? super String> predicate) {
        if (lines.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<String>> groups = new ArrayList<>();

        List<String> currentGroup = new ArrayList<>();
        for (final String line : lines) {
            if (predicate.test(line)) {
                if (!currentGroup.isEmpty()) {
                    groups.add(currentGroup);
                }
                currentGroup = new ArrayList<>();
            } else {
                currentGroup.add(line);
            }
        }

        // Add last group if not empty
        if (!currentGroup.isEmpty()) {
            groups.add(currentGroup);
        }
        return groups;
    }

    /**
     * Given a {@link Collection} of {@link String} lines from a file, then mapping each {@link String} to an {@link Integer}.
     *
     * @param lines the input {@link String} lines
     * @return a {@link List} of the {@link Integer}s from the file, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value in the file is not a valid {@link Integer}
     */
    static List<Integer> readLinesAsIntegers(final Collection<String> lines) {
        try {
            return lines
                .stream()
                .map(Integer::parseInt)
                .toList();
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Given a {@link List} of {@link String} lines from a file, expects only a single line to exist, and returns that {@link String}.
     *
     * @param lines the input {@link String} lines
     * @return the {@link String} line from the file
     * @throws IllegalArgumentException thrown if there is not exactly one line in the file
     */
    static String readSingleLine(final List<String> lines) {
        if (lines.size() != SINGLE_ENTRY) {
            throw new IllegalArgumentException("Expected a single line, found: " + lines.size());
        }

        return lines.getFirst();
    }

    /**
     * Given a {@link Collection} of {@link String} lines from a file, where each line is a row of comma-separated {@link Integer}s, reads each line
     * as a {@link List} of {@link Integer}, returning all lines as a {@link List} of {@link List}s.
     *
     * @param lines the input {@link String} lines
     * @return a {@link List} of each line from the file as a {@link List} of {@link Integer}s, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value is not a valid {@link Integer} separated by commas
     */
    static List<List<Integer>> readSingleLineOfCommaSeparatedIntegers(final Collection<String> lines) {
        try {
            return lines
                .stream()
                .map(input -> Arrays.asList(input.split(",")))
                .map(listOfStrings -> listOfStrings.stream().map(Integer::parseInt).toList())
                .toList();
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
