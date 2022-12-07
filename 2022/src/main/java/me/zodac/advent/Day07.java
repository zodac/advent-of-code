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

package me.zodac.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2022, Day 7.
 *
 * @see <a href="https://adventofcode.com/2022/day/7">AoC 2022, Day 7</a>
 */
public final class Day07 {

    private static final String ROOT_DIRECTORY = "/";

    private Day07() {

    }

    private static final class Directory {
        final Directory parent;
        final String path;
        final String name;
        final List<Directory> dirs;
        final List<MyFile> files;

        private Directory(final Directory parent, final String name, final List<Directory> dirs, final List<MyFile> files) {
            this.parent = parent;
            this.name = name;
            this.dirs = dirs;
            this.files = files;
            path = parent == null ? name : parent.path + File.pathSeparator + name;
        }

        private Directory(final Directory parent, final String name) {
            this(parent, name, new ArrayList<>(), new ArrayList<>());
        }
    }

    private record MyFile(String name, long size) {
    }

    /**
     * Part 1.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static long part1(final Iterable<String> values) {
        final Map<String, Directory> dirsByPath = new HashMap<>();
        Directory currentDir = new Directory(null, ROOT_DIRECTORY);
        dirsByPath.put(currentDir.path, currentDir);

        final Map<String, Long> sizeByDirPath = new HashMap<>();
        sizeByDirPath.put(currentDir.path, 0L);

        for (final String command : values) {
            if ("$ ls".equals(command) || "$ cd /".equals(command)) {
                continue;
            }

            if (command.startsWith("$ cd")) {
                final String newDirName = StringUtils.splitOnWhitespace(command)[2];

                if ("..".equals(newDirName)) {
                    currentDir = currentDir.parent;
                } else {
                    currentDir = dirsByPath.get(currentDir.path + File.pathSeparator + newDirName);
                }
            } else if (command.startsWith("dir")) {
                final String dirName = StringUtils.splitOnWhitespace(command)[1];
                final Directory newDir = new Directory(currentDir, dirName);
                currentDir.dirs.add(newDir);
                dirsByPath.put(newDir.path, newDir);

                if (!sizeByDirPath.containsKey(newDir.path)) {
                    sizeByDirPath.put(newDir.path, 0L);
                }
            } else {
                // File
                final String[] tokens = StringUtils.splitOnWhitespace(command);
                final long size = Long.parseLong(tokens[0]);
                final String fileName = tokens[1];
                final MyFile file = new MyFile(fileName, size);

                currentDir.files.add(file);

                Directory curr = currentDir;
                while (curr != null) {
                    final long currVal = sizeByDirPath.getOrDefault(curr.path, 0L);
                    final long newVal = currVal + file.size;
                    sizeByDirPath.put(curr.path, newVal);

                    curr = curr.parent;
                }

            }
        }

        return sizeByDirPath.values().stream().filter(aLong -> aLong < 100_000L).mapToLong(Long::longValue).sum();
    }

    /**
     * Part 2.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static long part2(final Iterable<String> values) {
        final Map<String, Directory> dirsByPath = new HashMap<>();
        Directory currentDir = new Directory(null, ROOT_DIRECTORY);
        dirsByPath.put(currentDir.path, currentDir);

        final Map<String, Long> sizeByDirPath = new HashMap<>();
        sizeByDirPath.put(currentDir.path, 0L);

        for (final String command : values) {
            if ("$ ls".equals(command) || "$ cd /".equals(command)) {
                continue;
            }

            if (command.startsWith("$ cd")) {
                final String newDirName = StringUtils.splitOnWhitespace(command)[2];

                if ("..".equals(newDirName)) {
                    currentDir = currentDir.parent;
                } else {
                    currentDir = dirsByPath.get(currentDir.path + File.pathSeparator + newDirName);
                }
            } else if (command.startsWith("dir")) {
                final String dirName = StringUtils.splitOnWhitespace(command)[1];
                final Directory newDir = new Directory(currentDir, dirName);
                currentDir.dirs.add(newDir);
                dirsByPath.put(newDir.path, newDir);

                if (!sizeByDirPath.containsKey(newDir.path)) {
                    sizeByDirPath.put(newDir.path, 0L);
                }
            } else {
                // File
                final String[] tokens = StringUtils.splitOnWhitespace(command);
                final long size = Long.parseLong(tokens[0]);
                final String fileName = tokens[1];
                final MyFile file = new MyFile(fileName, size);

                currentDir.files.add(file);

                Directory curr = currentDir;
                while (curr != null) {
                    final long currVal = sizeByDirPath.getOrDefault(curr.path, 0L);
                    final long newVal = currVal + file.size;
                    sizeByDirPath.put(curr.path, newVal);

                    curr = curr.parent;
                }

            }
        }

        final long unusedSpace = 70000000 - sizeByDirPath.get(ROOT_DIRECTORY);
        final long spaceToFind = 30000000 - unusedSpace;

        return sizeByDirPath
            .values()
            .stream()
            .filter(aLong -> aLong > spaceToFind)
            .mapToLong(l -> l)
            .min()
            .orElse(0L);
    }
}
