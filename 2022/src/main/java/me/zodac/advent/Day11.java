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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zodac.advent.pojo.tuple.Pair;
import me.zodac.advent.util.StringUtils;

/**
 * Solution for 2022, Day 11.
 *
 * @see <a href="https://adventofcode.com/2022/day/11">AoC 2022, Day 11</a>
 */
public final class Day11 {

    private Day11() {

    }

    /**
     * Part 1.
     *
     * @param values the input {@link String}s
     * @return the result
     */
    public static BigDecimal solve(final Collection<String> values, int rounds, boolean worry) {
        final Map<Integer, Monkey> monkeyById = new HashMap<>();

        final List<String> currentMonkey = new ArrayList<>();
        for (final String value : values) {
            if (value.isBlank()) {
                final Monkey monkey = new Monkey(currentMonkey);
                monkeyById.put(monkey.id, monkey);
                currentMonkey.clear();
            } else {
                currentMonkey.add(value);
            }
        }

        final Monkey monkeyTemp = new Monkey(currentMonkey);
        monkeyById.put(monkeyTemp.id, monkeyTemp);

        long commonDenominator = monkeyById
            .values()
            .stream()
            .mapToLong(monkey -> monkey.divisorTest)
            .reduce(1L, (first, second) -> first * second);

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeyById.values()) {
                var pairs = monkey.test(worry, commonDenominator);

                for (final Pair<Integer, BigDecimal> pair : pairs) {
                    monkeyById.get(pair.first()).add(pair.second());
                }
            }
        }

        return monkeyById
            .values()
            .stream()
            .map(Monkey::getInspectionCount)
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .map(BigDecimal::valueOf)
            .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    private static class Monkey {

        int id;
        List<BigDecimal> nums;
        char op;
        int opValue;
        int divisorTest;
        int trueMonkey;
        int falseMonkey;

        int inspectionCount = 0;

        public int getInspectionCount() {
            return inspectionCount;
        }

        public Monkey(List<String> input) {

            id = Integer.parseInt(StringUtils.removeLastCharacter(input.get(0).split("\\s+", 2)[1]));
            nums = new ArrayList<>(StringUtils.collectIntegersInOrder(input.get(1)).stream().map(BigDecimal::valueOf).toList());

            if (input.get(2).equals("  Operation: new = old * old")) {
                op = '^';
                opValue = 2;
            } else {
                op = input.get(2).charAt(23);
                opValue =
                    Integer.parseInt(("" + input.get(2).charAt(input.get(2).length() - 2) + input.get(2).charAt(input.get(2).length() - 1)).strip());
            }

            divisorTest =
                Integer.parseInt(("" + input.get(3).charAt(input.get(3).length() - 2) + input.get(3).charAt(input.get(3).length() - 1)).strip());
            trueMonkey = Integer.parseInt(String.valueOf(input.get(4).charAt(input.get(4).length() - 1)));
            falseMonkey = Integer.parseInt(String.valueOf(input.get(5).charAt(input.get(5).length() - 1)));
        }

        public void add(BigDecimal i) {
            nums.add(i);
        }

        public List<Pair<Integer, BigDecimal>> test(boolean worry, long gcd) {
            var pairs = new ArrayList<Pair<Integer, BigDecimal>>();

            for (BigDecimal i : nums) {
                BigDecimal newi = i;
                if (op == '+') {
                    newi = newi.add(BigDecimal.valueOf(opValue));
                } else if (op == '*') {
                    newi = newi.multiply(BigDecimal.valueOf(opValue));
                } else if (op == '^') {
                    newi = newi.multiply(newi);
                }

                if (worry) {
                    newi = BigDecimal.valueOf(newi.intValue() / 3);
                } else {
                    newi = newi.remainder(BigDecimal.valueOf(gcd));
                }

                if (newi.remainder(BigDecimal.valueOf(divisorTest)).intValue() == 0) {
                    pairs.add(Pair.of(trueMonkey, newi));
                } else {
                    pairs.add(Pair.of(falseMonkey, newi));
                }
                inspectionCount++;
            }

            nums.clear();
            return pairs;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                "id=" + id +
                ", nums=" + nums +
                ", op=" + op +
                ", opValue=" + opValue +
                ", divisorTest=" + divisorTest +
                ", trueMonkey=" + trueMonkey +
                ", falseMonkey=" + falseMonkey +
                '}';
        }
    }
}
