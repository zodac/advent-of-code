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

import me.zodac.advent.pojo.rpg.magic.BattleRound;
import me.zodac.advent.pojo.rpg.magic.MageBoss;
import me.zodac.advent.pojo.rpg.magic.MagePlayer;
import me.zodac.advent.pojo.rpg.magic.Spell;
import me.zodac.advent.search.Dijkstra;
import me.zodac.advent.search.SearchNode;

/**
 * Solution for 2015, Day 22.
 *
 * @see <a href="https://adventofcode.com/2015/day/22">AoC 2015, Day 22</a>
 */
public final class Day22 {

    private static final MagePlayer STARTING_PLAYER = MagePlayer.create(50, 500);

    private Day22() {

    }

    /**
     * Given a {@link MageBoss}, we generate a {@link MagePlayer} and simulate a battle. Each round we will determine the possible next round,
     * based on available {@link Spell}s and the {@link MagePlayer} mana. Using {@link Dijkstra}'s algorithm, we will attempt to find the shortest
     * path in terms of mana usage that still lead to a victory.
     *
     * @param boss                     the {@link MageBoss} to defeat
     * @param healthLossEachPlayerTurn the health the player loses at the start of each turn
     * @return the loest amount of mana that wins the battle
     * @see BattleRound
     * @see Dijkstra
     */
    public static long findCheapestManaCostToWinBattle(final MageBoss boss, final int healthLossEachPlayerTurn) {
        final SearchNode searchNode = BattleRound.createFirstRound(STARTING_PLAYER, boss, healthLossEachPlayerTurn);
        return Dijkstra.findShortestDistance(searchNode);
    }
}
