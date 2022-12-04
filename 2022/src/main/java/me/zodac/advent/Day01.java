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
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.zodac.advent.pojo.Elf;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2022, Day 1.
 *
 * @see <a href="https://adventofcode.com/2022/day/1">AoC 2022, Day 1</a>
 */
public final class Day01 {

    private Day01() {

    }

    public static long part1(final Iterable<String> values) {
        final Collection<Elf> elves = getElves(values);
        return elves.stream().mapToLong(Elf::getTotalCalories).max().orElse(0L);
    }

    public static long part2(final Iterable<String> values) {
        final Collection<Elf> elves = getElves(values);
        return elves.stream().map(Elf::getTotalCalories).sorted(Collections.reverseOrder()).mapToLong(l -> l).limit(3).sum();
    }

    private static Collection<Elf> getElves(final Iterable<String> values) {
        final Collection<Elf> elves = new ArrayList<>();

        Elf currentElf = new Elf();
        for (final String value : values) {
            if (value.isBlank()) {
                elves.add(currentElf);
                currentElf = new Elf();
            } else {
                currentElf.addCalorie(Integer.parseInt(value));
            }
        }

        return elves;
    }
}
