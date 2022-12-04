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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.zodac.advent.pojo.Pair;
import me.zodac.advent.util.CollectionUtils;

/**
 * Solution for 2022, Day 3.
 *
 * @see <a href="https://adventofcode.com/2022/day/3">AoC 2022, Day 3</a>
 */
public final class Day03 {

    private static final String VALUES = "*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Day03() {

    }

    public static long part1(final Iterable<String> values) {
        long total = 0L;

        for (final String value : values) {
            final Pair<String, String> bisectedString = bisect(value);
            final Set<Character> commonCharacters = commonChars(bisectedString);
            final Character commonCharacter = CollectionUtils.getFirst(commonCharacters); // Assuming only one common char
            total += VALUES.indexOf(commonCharacter);
        }

        return total;
    }

    public static long part2(final Iterable<String> values) {
        long total = 0L;

        final Collection<Collection<String>> groupedValues = groupEntries(values, 3);

        for (final Collection<String> groupedValue : groupedValues) {
            final Set<Character> commonCharacters = commonChars(groupedValue);
            final Character commonCharacter = CollectionUtils.getFirst(commonCharacters); // Assuming only one common char
            total += VALUES.indexOf(commonCharacter);
        }

        return total;
    }

    public static Collection<Collection<String>> groupEntries(final Iterable<String> values, final int amountInEachGroup) {
        final Collection<Collection<String>> groups = new ArrayList<>();

        Collection<String> group = new ArrayList<>();
        int counter = 0;
        for (final String value : values) {
            if (counter == amountInEachGroup) {
                groups.add(group);
                group = new ArrayList<>();
                counter = 0;
            }

            group.add(value);
            counter++;
        }

        if (!group.isEmpty()) {
            groups.add(group);
        }

        return groups;
    }

    public static Pair<String, String> bisect(final String input) {
        final int middleIndex = input.length() / 2;
        return Pair.of(input.substring(0, middleIndex), input.substring(middleIndex));
    }

    public static Set<Character> commonChars(final Pair<String, String> pair) {
        return commonChars(pair.first(), pair.second());
    }

    public static Set<Character> commonChars(final Collection<String> strings) {
        final List<String> stringsAsList = new ArrayList<>(strings);
        return commonChars(stringsAsList.get(0), stringsAsList.get(1), stringsAsList.get(2));
    }

    public static Set<Character> commonChars(final String first, final String... others) {
        final Set<Character> firstChars = new HashSet<>();
        for (final char firstChar : first.toCharArray()) {
            firstChars.add(firstChar);
        }

        final Collection<Set<Character>> allOtherChars = new HashSet<>();

        for (final String other : others) {
            final Set<Character> otherChars = new HashSet<>();
            for (final char otherChar : other.toCharArray()) {
                otherChars.add(otherChar);
            }
            allOtherChars.add(otherChars);
        }


        for(final Set<Character> otherChars : allOtherChars){
            firstChars.retainAll(otherChars);
        }

        return firstChars;
    }
}
