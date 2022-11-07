/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
