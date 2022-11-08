/*
 * Zero-Clause BSD License
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

import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2015, Day 10.
 *
 * @see <a href="https://adventofcode.com/2015/day/10">AoC 2015, Day 10</a>
 */
public final class Day10 {

    private Day10() {

    }

    /**
     * Performs the 'lookAndSay' sequence on the input {@code numberOfExecutions} times, and returns the length of the result.
     *
     * @param input              the {@link String} to perform the sequence on
     * @param numberOfExecutions the number of times to perform the sequence
     * @return the length of the end result after the sequence has been applied {@code numberOfExecutions} times
     * @see StringUtils#lookAndSay(String)
     */
    public static long performLookAndSaySequenceAndReturnLength(final String input, final int numberOfExecutions) {
        String output = input;
        for (int i = 0; i < numberOfExecutions; i++) {
            output = StringUtils.lookAndSay(output);
        }
        return output.length();
    }
}
