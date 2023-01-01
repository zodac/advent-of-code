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

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class with functions for reading {@link String}s from {@code Advent of Code} input files. The puzzle inputs are retrieved from the
 * {@value #INPUTS_DIRECTORY_NAME} directory, <b>not</b> from the calling {@link Class}'s module itself.
 */
public final class PuzzleInput {

    private static final String INPUTS_DIRECTORY_NAME = "advent-of-code-inputs";
    private static final String NEW_LINE = "\n";

    private PuzzleInput() {

    }

    /**
     * Reads all lines from an input file in {@value #INPUTS_DIRECTORY_NAME}.
     *
     * <p>
     * We calculate the relative filePath by taking the current module directory, moving to the adjacent {@value #INPUTS_DIRECTORY_NAME} directory,
     * then expecting a child directory with a name matching the current module directory name. For example, given the current module <b>2015</b>, we
     * can find the inputs in the matching directory <b>2015</b> as below:
     *
     * <pre>
     *     |- /advent-of-code
     *        |- /2015
     *        |- /advent-of-code-inputs
     *           |- /2015
     * </pre>
     *
     * @param inputFilePath file path of the input file to be read
     * @return a {@link List} of the {@link String} lines from the file, or {@link Collections#emptyList()} if an error occurs
     * @see FileUtils#readLines(Path)
     */
    public static List<String> readLines(final String inputFilePath) {
        final Path currentModuleDirectory = Paths.get(System.getProperty("user.dir"));
        final String currentModuleName = currentModuleDirectory.getFileName().toString();
        final Path projectRootDirectory = currentModuleDirectory.getParent();
        final String inputsDirectory = projectRootDirectory + File.separator + INPUTS_DIRECTORY_NAME;
        final Path filePath = Paths.get(inputsDirectory + File.separator + currentModuleName + File.separator + inputFilePath);

        return FileUtils.readLines(filePath);
    }

    /**
     * Reads all lines from a file in {@value #INPUTS_DIRECTORY_NAME}, then groups the lines into {@link List}s, where each group occurs when the
     * provided {@link Predicate} is met.
     *
     * <ul>
     *     <li>The line which matches the {@link Predicate} is discarded and not included in the output</li>
     *     <li>If the {@link Predicate} is not met, we return an empty {@link List}</li>
     *     <li>Empty groups are not included in the output</li>
     * </ul>
     *
     * @param inputFilePath file path to be read
     * @param predicate     the {@link Predicate} defining where the {@link List} is to be split
     * @return the group of {@link List}s of {@link String} lines
     * @see #readLines(String)
     * @see FileUtils#readLinesAsGroups(Collection, Predicate)
     */
    public static List<List<String>> readLinesAsGroups(final String inputFilePath, final Predicate<? super String> predicate) {
        final List<String> lines = readLines(inputFilePath);
        return FileUtils.readLinesAsGroups(lines, predicate);
    }

    /**
     * Reads all lines from a file in {@value #INPUTS_DIRECTORY_NAME}, then mapping each {@link String} to an {@link Integer}.
     *
     * @param inputFilePath file path to be read
     * @return a {@link List} of the {@link Integer}s from the file, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value in the file is not a valid {@link Integer}
     * @see #readLines(String)
     * @see FileUtils#readLinesAsIntegers(Collection)
     */
    public static List<Integer> readLinesAsIntegers(final String inputFilePath) {
        final List<String> lines = readLines(inputFilePath);
        return FileUtils.readLinesAsIntegers(lines);
    }

    /**
     * Reads all lines from a file in {@value #INPUTS_DIRECTORY_NAME}, then concatenates them into a single {@link String}, with the delimiter as
     * {@value #NEW_LINE}.
     *
     * @param inputFilePath file path to be read
     * @return the {@link String} lines from the file
     * @see #readLines(String)
     */
    public static String readLinesAsSingleString(final String inputFilePath) {
        return String.join(NEW_LINE, readLines(inputFilePath));
    }

    /**
     * Reads the only line from a file in {@value #INPUTS_DIRECTORY_NAME}.
     *
     * @param inputFilePath file path to be read
     * @return the {@link String} line from the file
     * @throws IllegalArgumentException thrown if there is not exactly one line in the file
     * @see #readLines(String)
     * @see FileUtils#readSingleLine(List)
     */
    public static String readSingleLine(final String inputFilePath) {
        final List<String> lines = readLines(inputFilePath);
        return FileUtils.readSingleLine(lines);
    }

    /**
     * Reads all lines from a file in {@value #INPUTS_DIRECTORY_NAME} where each line is a row of comma-separated {@link Integer}s.
     *
     * @param inputFilePath file path to be read
     * @return a {@link List} of each line from the file as a {@link List} of {@link Integer}s, or {@link Collections#emptyList()} if an error occurs
     * @throws IllegalArgumentException thrown if any value is not a valid {@link Integer} separated by commas
     * @see #readLines(String)
     * @see FileUtils#readSingleLineOfCommaSeparatedIntegers(Collection)
     */
    public static List<List<Integer>> readSingleLineOfCommaSeparatedIntegers(final String inputFilePath) {
        final List<String> lines = readLines(inputFilePath);
        return FileUtils.readSingleLineOfCommaSeparatedIntegers(lines);
    }
}
