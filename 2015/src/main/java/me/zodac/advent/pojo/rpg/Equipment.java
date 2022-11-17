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

/**
 * POJO defining a piece of {@link Equipment} to be used in an RPG.
 *
 * @param type    the {@link EquipmentType}
 * @param name    the name of the {@link Equipment}
 * @param cost    the cost of the {@link Equipment}
 * @param attack  the attack stats of the {@link Equipment}
 * @param defence the defence stats of the {@link Equipment}
 */
public record Equipment(EquipmentType type, String name, int cost, int attack, int defence) {

    private static final String DEFAULT_NAME = "None";
    private static final int MINIMUM_COST_VALUE = 0;
    private static final int MINIMUM_ATTACK_VALUE = 0;
    private static final int MINIMUM_DEFENCE_VALUE = 0;

    /**
     * Creates an {@link Equipment}.
     *
     * @param type    the {@link EquipmentType}
     * @param name    the name of the {@link Equipment}
     * @param cost    the cost of the {@link Equipment}
     * @param attack  the attack stats of the {@link Equipment}
     * @param defence the defence stats of the {@link Equipment}
     * @return the created {@link Equipment}
     * @throws IllegalArgumentException if the {@code name} is null or blank, or if the input stats are invalid
     */
    public static Equipment create(final EquipmentType type, final String name, final int cost, final int attack, final int defence) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("'name' cannot be null or blank");
        }

        if (cost < MINIMUM_COST_VALUE) {
            throw new IllegalArgumentException(String.format("'cost' must be greater than %s, found: %s", MINIMUM_COST_VALUE, cost));
        }

        if (attack < MINIMUM_ATTACK_VALUE) {
            throw new IllegalArgumentException(String.format("'attack' must be greater than %s, found: %s", MINIMUM_ATTACK_VALUE, attack));
        }

        if (defence < MINIMUM_DEFENCE_VALUE) {
            throw new IllegalArgumentException(String.format("'defence' must be greater than %s, found: %s", MINIMUM_DEFENCE_VALUE, defence));
        }

        return new Equipment(type, name, cost, attack, defence);
    }

    /**
     * Creates an 'empty' {@link Equipment} with no stats.
     *
     * @param type the {@link EquipmentType}
     * @return the created {@link Equipment} with no stats
     * @throws IllegalArgumentException if the {@code name} is null or blank, or if the input stats are invalid
     */
    public static Equipment empty(final EquipmentType type) {
        return create(type, DEFAULT_NAME, MINIMUM_COST_VALUE, MINIMUM_ATTACK_VALUE, MINIMUM_DEFENCE_VALUE);
    }
}
