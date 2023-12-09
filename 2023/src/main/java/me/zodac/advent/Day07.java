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

package me.zodac.advent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import me.zodac.advent.pojo.CamelCard;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2023, Day 7.
 *
 * @see <a href="https://adventofcode.com/2023/day/7">[2023: 07] Camel Cards</a>
 */
public final class Day07 {

    private Day07() {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final Collection<String> values) {
        final List<CamelCard> sortedCards = values.stream()
            .map((String input) -> CamelCard.parse(input, false))
            .sorted()
            .toList();

        long totalWinnings = 0L;
        for (int rank = 1; rank <= sortedCards.size(); rank++) {
            final CamelCard camelCard = sortedCards.get(rank - 1);

            totalWinnings += camelCard.wager() * rank;
        }

        return totalWinnings;
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final Collection<String> values) {
        final List<CamelCard> sortedCards = values.stream()
            .map((String input) -> CamelCard.parse(input, true))
            .sorted()
            .toList();

        long totalWinnings = 0L;
        for (int rank = 1; rank <= sortedCards.size(); rank++) {
            final CamelCard camelCard = sortedCards.get(rank - 1);
            totalWinnings += camelCard.wager() * rank;
        }

        return totalWinnings;
    }
}
