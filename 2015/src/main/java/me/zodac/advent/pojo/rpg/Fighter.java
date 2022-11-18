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

package me.zodac.advent.pojo.rpg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * POJO defining a {@link Fighter} with health, attack and defence, which can fight against other {@link Fighter}s.
 *
 * @param hitPoints     the {@link Fighter}'s hit points
 * @param equipmentCost the cost of all {@link Equipment} for the {@link Fighter}
 * @param attack        the {@link Fighter}'s attack stats
 * @param defence       the {@link Fighter}'s defence stats
 */
public record Fighter(long hitPoints, int equipmentCost, int attack, int defence) {

    private static final int MINIMUM_HITPOINTS_VALUE = 0;
    private static final int MINIMUM_ATTACK_VALUE = 0;
    private static final int MINIMUM_DEFENCE_VALUE = 0;

    private static final int MINIMUM_DAMAGE_PER_ROUND = 1;

    private static final Pattern FIGHTER_PATTERN = Pattern.compile("Hit Points: (\\d+)\nDamage: (\\d+)\nArmor: (\\d+)");

    /**
     * Checks whether this {@link Fighter} can defeat another {@link Fighter}. Each turn, first this {@link Fighter} attacks, then the other
     * {@link Fighter}. The damage is calculated as this {@link Fighter}'s attack stat, minus the other {@link Fighter}'s defence. Each attack will
     * have a minimum of {@value #MINIMUM_DAMAGE_PER_ROUND}, regardless of how high the other {@link Fighter}'s defence is.
     *
     * @param other the {@link Fighter} to fight
     * @return {@code true} if this {@link Fighter} defeats the other {@link Fighter}
     */
    public boolean canWinFightAgainst(final Fighter other) {
        final double turnsToKillOther = Math.ceil(other.hitPoints / (double) (attack - other.defence));
        return hitPoints - (long) (other.attack - defence) * (turnsToKillOther - MINIMUM_DAMAGE_PER_ROUND) >= 0;
    }

    /**
     * Creates a {@link Fighter}.
     *
     * @param hitPoints     the {@link Fighter}'s hit points
     * @param equipmentCost the cost of all {@link Equipment} for the {@link Fighter}
     * @param attack        the {@link Fighter}'s attack stats
     * @param defence       the {@link Fighter}'s defence stats
     * @return the created {@link Fighter}
     */
    public static Fighter create(final int hitPoints, final int equipmentCost, final int attack, final int defence) {
        if (hitPoints < MINIMUM_HITPOINTS_VALUE) {
            throw new IllegalArgumentException(String.format("'hitPoints' must be greater than %s, found: %s", MINIMUM_HITPOINTS_VALUE, hitPoints));
        }

        if (attack < MINIMUM_ATTACK_VALUE) {
            throw new IllegalArgumentException(String.format("'attack' must be greater than %s, found: %s", MINIMUM_ATTACK_VALUE, attack));
        }

        if (defence < MINIMUM_DEFENCE_VALUE) {
            throw new IllegalArgumentException(String.format("'defence' must be greater than %s, found: %s", MINIMUM_DEFENCE_VALUE, defence));
        }

        return new Fighter(hitPoints, equipmentCost, attack, defence);
    }

    /**
     * Creates a {@link Fighter} from a {@link CharSequence} in the format:
     * <pre>
     *     Hit Points: [hitPoints]
     *     Damage: [attack]
     *     Armor: [defence]
     * </pre>
     *
     * @param input the {@link CharSequence} to parse
     * @return the {@link Fighter}
     */
    public static Fighter parse(final CharSequence input) {
        final Matcher matcher = FIGHTER_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input: " + input);
        }

        final int hitPoints = Integer.parseInt(matcher.group(1));
        final int damage = Integer.parseInt(matcher.group(2));
        final int armour = Integer.parseInt(matcher.group(3));

        return create(hitPoints, 0, damage, armour);
    }
}
