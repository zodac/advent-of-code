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

import java.util.Collection;
import me.zodac.advent.pojo.Sue;

/**
 * Solution for 2015, Day 16.
 *
 * @see <a href="https://adventofcode.com/2015/day/16">AoC 2015, Day 16</a>
 */
public final class Day16 {

    private static final Sue WANTED_SUE = Sue.parseAllAttributes("""
        children: 3
        cats: 7
        samoyeds: 2
        pomeranians: 3
        akitas: 0
        vizslas: 0
        goldfish: 5
        trees: 3
        cars: 2
        perfumes: 1""");

    private Day16() {

    }

    /**
     * For the {@link Collection} of provided {@link Sue}s, we will compare each and see if it is a {@link Sue#isMatch(Sue)} with the
     * {@code WANTED_SUE}.
     *
     * @param partialSues the {@link Sue}s to check
     * @return the ID of the matching {@link Sue}, or of {@code WANTED_SUE} if no match is found
     */
    public static int findIdOfMatchingSue(final Collection<Sue> partialSues) {
        return partialSues
            .stream()
            .filter(WANTED_SUE::isMatch)
            .findFirst()
            .orElse(WANTED_SUE)
            .id();
    }

    /**
     * For the {@link Collection} of provided {@link Sue}s, we will compare each and see if it is a {@link Sue#isMatchWithRanges(Sue)} with the
     * {@code WANTED_SUE}.
     *
     * @param partialSues the {@link Sue}s to check
     * @return the ID of the matching {@link Sue}, or of {@code WANTED_SUE} if no match is found
     */
    public static int findIdOfMatchingSueWithRanges(final Collection<Sue> partialSues) {
        return partialSues
            .stream()
            .filter(WANTED_SUE::isMatchWithRanges)
            .findFirst()
            .orElse(WANTED_SUE)
            .id();
    }
}
