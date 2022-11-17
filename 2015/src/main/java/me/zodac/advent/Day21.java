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

import java.util.List;
import me.zodac.advent.pojo.rpg.Equipment;
import me.zodac.advent.pojo.rpg.Fighter;

/**
 * Solution for 2015, Day 21.
 *
 * @see <a href="https://adventofcode.com/2015/day/21">AoC 2015, Day 21</a>
 */
public final class Day21 {

    private static final int PLAYER_HITPOINTS = 100;

    private Day21() {

    }

    /**
     * Given all valid {@link Equipment} combinations, we calculate the cost of each combination, then attempt to defeat the boss {@link Fighter}. For
     * the {@link Equipment} combinations that can defeat the {@link Fighter}, we return the lowest cost.
     *
     * @param equipmentCombinations the {@link Equipment} combinations
     * @param bossToDefeat          the boss {@link Fighter} to defeat
     * @return the cost of the cheapest {@link Equipment} combination that successfully defeats the {@link Fighter}
     */
    public static long costOfCheapestEquipmentThatDefeatsBoss(final Iterable<? extends List<Equipment>> equipmentCombinations,
                                                              final Fighter bossToDefeat) {
        int lowestCost = Integer.MAX_VALUE;

        for (final List<Equipment> equipmentCombination : equipmentCombinations) {
            int totalCost = 0;
            int totalAttack = 0;
            int totalDefence = 0;

            for (final Equipment equipment : equipmentCombination) {
                totalCost += equipment.cost();
                totalAttack += equipment.attack();
                totalDefence += equipment.defence();
            }

            if (totalCost > lowestCost) {
                continue;
            }

            final Fighter me = Fighter.create(PLAYER_HITPOINTS, totalAttack, totalDefence);

            if (me.canWinFightAgainst(bossToDefeat)) {
                lowestCost = totalCost;
            }
        }

        return lowestCost;
    }

    /**
     * Given all valid {@link Equipment} combinations, we calculate the cost of each combination, then attempt to defeat the boss {@link Fighter}. For
     * the {@link Equipment} combinations that cannotdefeat the {@link Fighter}, we return the highest cost.
     *
     * @param equipmentCombinations the {@link Equipment} combinations
     * @param bossToDefeat          the boss {@link Fighter} to defeat
     * @return the cost of the most expensive {@link Equipment} combination that cannot successfully defeat the {@link Fighter}
     */
    public static long costOfMostExpensiveArmourThatLosesToBoss(
        final Iterable<? extends List<Equipment>> equipmentCombinations, final Fighter bossToDefeat) {
        int highestCost = Integer.MIN_VALUE;

        for (final List<Equipment> equipmentCombination : equipmentCombinations) {
            int totalCost = 0;
            int totalAttack = 0;
            int totalDefence = 0;

            for (final Equipment equipment : equipmentCombination) {
                totalCost += equipment.cost();
                totalAttack += equipment.attack();
                totalDefence += equipment.defence();
            }

            if (totalCost < highestCost) {
                continue;
            }

            final Fighter me = Fighter.create(PLAYER_HITPOINTS, totalAttack, totalDefence);

            if (!me.canWinFightAgainst(bossToDefeat)) {
                highestCost = totalCost;
            }
        }

        return highestCost;
    }
}
