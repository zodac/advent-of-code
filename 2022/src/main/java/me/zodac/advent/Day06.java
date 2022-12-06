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
import java.util.List;
import me.zodac.advent.util.CollectionUtils;

/**
 * Solution for 2022, Day 6.
 *
 * @see <a href="https://adventofcode.com/2022/day/6">AoC 2022, Day 6</a>
 */
public final class Day06 {

    private Day06() {

    }

    public static long part1(final String value) {
        for (int i = 0; i < value.length() - 4; i++) {

            char c1 = value.charAt(i);
            char c2 = value.charAt(i + 1);
            char c3 = value.charAt(i + 2);
            char c4 = value.charAt(i + 3);

            if(c1 != c2 && c1 != c3 && c1 != c4 && c2 != c3 && c2 != c4 && c3 != c4){
                return i+4;
            }

        }

        return 0L;
    }

    public static long part2(final String value) {
        for (int i = 0; i < value.length() - 14; i++) {


            final List<Character> toCheck = new ArrayList<>();

            for(int j =0; j < 14; j++){
                toCheck.add(value.charAt(i + j));
            }

            if(!CollectionUtils.containsDuplicates(toCheck)){
                return i+14;
            }

        }

        return 0L;
    }
}
