/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.pojo.tuple.Triple;
import me.zodac.advent.util.CollectionUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2024, Day 5.
 *
 * @see <a href="https://adventofcode.com/2024/day/5">[2024: 05] Print Queue</a>
 */
public final class Day05 {

    private static final Pattern PATTERN = Pattern.compile("|");

    private Day05() {

    }

    /**
     * Part 1.
     *
     * @return the part 1 result
     */
    public static long sumMiddleValuesOfSortedPageNumbers(final List<String> inputRules, final List<String> page) {
        final List<Pair<String, String>> rules = inputRules.stream().map(s -> {
            final String[] tokens = PATTERN.split(s, 2);
            return Pair.of(tokens[0], tokens[1]);
        }).toList();

        final Map<Long, Set<Long>> beforeMap = new HashMap<>();
        final Map<Long, Set<Long>> afterMap = new HashMap<>();

        final Set<List<Long>> validUpdates = new HashSet<>();


        for (final String rule : inputRules) {
            final List<Long> numbers = StringUtils.collectNumbersInOrder(rule);
            final long first = numbers.getFirst();
            final long second = numbers.getLast();

            final Set<Long> firstAfter = afterMap.getOrDefault(first, new HashSet<>());
            final Set<Long> secondBefore = beforeMap.getOrDefault(second, new HashSet<>());

            firstAfter.add(second);
            secondBefore.add(first);

            afterMap.put(first, firstAfter);
            beforeMap.put(second, secondBefore);
        }

        final List<List<Long>> pages = page.stream().map(StringUtils::collectNumbersInOrder).toList();
        for (final List<Long> pageData : pages) {
            // 75, 47, 61, 53, 29
            boolean valid = true;
            for (int i = 0; i < pageData.size(); i++) {

                final long curr = pageData.get(i);
                final Set<Long> currBefore = beforeMap.getOrDefault(curr, new HashSet<>());
                final Set<Long> currAfter = afterMap.getOrDefault(curr, new HashSet<>());


//                System.out.println("Checking " + curr);
                if (i != 0) {
                    final List<Long> before = pageData.subList(0, i);
//                    System.out.println("Bfore: " + before);

                    for (final long befores : before) {
                        for (final long afters : currAfter) {
                            if (befores == afters) {
                                valid = false;
                            }
                        }
                    }
                }


                if (i != pageData.size() - 1) {
                    final List<Long> after = pageData.subList(i + 1, pageData.size());
//                    System.out.println("After: " + after);

                    for (final long afters : after) {
                        for (final long befores : currBefore) {
                            if (befores == afters) {
                                valid = false;
                            }
                        }
                    }
                }


            }
            if (valid) {
                validUpdates.add(pageData);
            }
//
//            System.out.println();
        }


        long total = 0L;

        for (final List<Long> valid : validUpdates) {
            final int middleIndex = valid.size() / 2;
            total += valid.get(middleIndex);
        }

        return total;
    }

    /**
     * Part 2.
     *
     * @return the part 2 result
     */
    public static long sumMiddleValuesOfReSortedPageNumbers(final List<String> inputRules, final List<String> page) {
        final List<Pair<String, String>> rules = inputRules.stream().map(s -> {
            final String[] tokens = PATTERN.split(s, 2);
            return Pair.of(tokens[0], tokens[1]);
        }).toList();

        final Map<Long, Set<Long>> beforeMap = new HashMap<>();
        final Map<Long, Set<Long>> afterMap = new HashMap<>();

        final Set<List<Long>> inValidUpdates = new HashSet<>();


        for (final String rule : inputRules) {
            final List<Long> numbers = StringUtils.collectNumbersInOrder(rule);
            final long first = numbers.getFirst();
            final long second = numbers.getLast();

            final Set<Long> firstAfter = afterMap.getOrDefault(first, new HashSet<>());
            final Set<Long> secondBefore = beforeMap.getOrDefault(second, new HashSet<>());

            firstAfter.add(second);
            secondBefore.add(first);

            afterMap.put(first, firstAfter);
            beforeMap.put(second, secondBefore);
        }

        final List<List<Long>> pages = page.stream().map(StringUtils::collectNumbersInOrder).toList();
        for (final List<Long> pageData : pages) {
            // 75, 47, 61, 53, 29
            boolean valid = true;
            for (int i = 0; i < pageData.size(); i++) {

                final long curr = pageData.get(i);
                final Set<Long> currBefore = beforeMap.getOrDefault(curr, new HashSet<>());
                final Set<Long> currAfter = afterMap.getOrDefault(curr, new HashSet<>());


//                System.out.println("Checking " + curr);
                if (i != 0) {
                    final List<Long> before = pageData.subList(0, i);
//                    System.out.println("Bfore: " + before);

                    for (final long befores : before) {
                        for (final long afters : currAfter) {
                            if (befores == afters) {
                                valid = false;
                            }
                        }
                    }
                }


                if (i != pageData.size() - 1) {
                    final List<Long> after = pageData.subList(i + 1, pageData.size());
//                    System.out.println("After: " + after);

                    for (final long afters : after) {
                        for (final long befores : currBefore) {
                            if (befores == afters) {
                                valid = false;
                            }
                        }
                    }
                }


            }
            if (!valid) {
                inValidUpdates.add(pageData);
            }
        }

        final Set<List<Long>> validUpdates = new HashSet<>();


        for (final List<Long> invalid : inValidUpdates) {
            validUpdates.add(invalid.stream().sorted(
                (o1, o2) -> beforeMap.getOrDefault(o1, new HashSet<>()).contains(o2) ? 1 : -1
            ).toList());
        }

        long total = 0L;
        for (final List<Long> valid : validUpdates) {
            final int middleIndex = valid.size() / 2;
            total += valid.get(middleIndex);
        }

        return total;
    }


}
