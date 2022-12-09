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

import static me.zodac.advent.util.CollectionUtils.getFirst;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import me.zodac.advent.pojo.tuple.Pair;

/**
 * Utility class with functions for reading {@link String}s from files.
 */
public final class FileUtils {

    private static final int SINGLE_ENTRY = 1;
    private static final String NEW_LINE = "\n";

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
     * Reads all lines from a file in {@code src/main/resources}, then splits the {@link List} in two, with the split occurring when the provided
     * {@link Predicate} is met.
     *
     * <ul>
     *     <li>The line which matches the {@link Predicate} is discarded and not included in the output</li>
     *     <li>Even if the {@link Predicate} can be met multiple times, only the first will be considered</li>
     *     <li>If the {@link Predicate} is not met, we return a {@link Pair} of the full {@link List} and an empty {@link List}</li>
     * </ul>
     *
     * @param filePathInResources file path to be read
     * @param predicate           the {@link Predicate} defining where the {@link List} is to be split
     * @return a {@link Pair} of {@link List}s of {@link String} lines
     * @see #readLines(String)
     */
    public static Pair<List<String>, List<String>> readLinesAndSplit(final String filePathInResources, final Predicate<? super String> predicate) {
        final List<String> lines = readLines(filePathInResources);

        if (lines.isEmpty()) {
            return Pair.of(List.of(), List.of());
        }

        final List<String> first = new ArrayList<>();

        boolean lookingForPredicateMatch = true;
        int index = 0;
        while (lookingForPredicateMatch && index < lines.size()) {
            final String line = lines.get(index);
            if (predicate.test(line)) {
                lookingForPredicateMatch = false;
            } else {
                first.add(line);
            }

            index++;
        }

        if (lookingForPredicateMatch) {
            return Pair.of(first, List.of());
        }

        final List<String> second = lines.subList(index, lines.size());
        return Pair.of(first, second);
    }

    /**
     * Reads all lines from a file in {@code src/main/resources}, then mapping each {@link String} to an {@link Integer}.
     *
     * @param filePathInResources file path to be read
     * @return a {@link List} of the {@link Integer}s from the file, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value in the file is not a valid {@link Integer}
     */
    public static List<Integer> readLinesAsIntegers(final String filePathInResources) {
        try {
            return readLines(filePathInResources)
                .stream()
                .map(Integer::parseInt)
                .toList();
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Reads all lines from a file in {@code src/main/resources}, then concatenates them into a single {@link String}, with the delimiter as
     * {@value #NEW_LINE}.
     *
     * @param filePathInResources file path to be read
     * @return the {@link String} lines from the file
     * @see #readLines(String)
     */
    public static String readLinesAsSingleString(final String filePathInResources) {
        return String.join(NEW_LINE, readLines(filePathInResources));
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
     * @throws IllegalArgumentException thrown if any value is not a valid {@link Integer} separated by commas
     */
    public static List<List<Integer>> readSingleLineOfCommaSeparatedIntegers(final String filePathInResources) {
        try {
            return readLines(filePathInResources)
                .stream()
                .map(input -> Arrays.asList(input.split(",")))
                .map(listOfStrings -> listOfStrings.stream().mapToInt(Integer::parseInt).boxed().toList())
                .toList();
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
