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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.zodac.advent.pojo.rpg.Equipment;
import me.zodac.advent.pojo.rpg.EquipmentType;
import me.zodac.advent.pojo.rpg.Fighter;
import me.zodac.advent.util.CollectionUtils;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for {@link Day21}.
 */
class Day21Test {

    private static final String INPUT_FILENAME = "day21.txt";
    private static final int REQUIRED_AMOUNT_OF_WEAPONS = 1;
    private static final int REQUIRED_AMOUNT_OF_ARMOURS = 1;
    private static final int MAX_AMOUNT_OF_RINGS = 2;
    private static final List<List<Equipment>> EQUIPMENT_COMBINATIONS = getEquipmentCombinations();

    @Test
    void part1() {
        final String value = FileUtils.readLinesAsSingleString(INPUT_FILENAME);
        final Fighter boss = Fighter.parse(value);

        final long part1 = Day21.costOfCheapestEquipmentThatDefeatsBoss(EQUIPMENT_COMBINATIONS, boss);
        assertThat(part1)
            .isEqualTo(91L);
    }

    @Test
    void part2() {
        final String value = FileUtils.readLinesAsSingleString(INPUT_FILENAME);
        final Fighter boss = Fighter.parse(value);

        final long part1 = Day21.costOfMostExpensiveArmourThatLosesToBoss(EQUIPMENT_COMBINATIONS, boss);
        assertThat(part1)
            .isEqualTo(158L);
    }

    private static List<List<Equipment>> getEquipmentCombinations() {
        final List<Equipment> equipment = generateEquipment();
        return CollectionUtils.getPowerList(equipment)
            .stream()
            .filter(Day21Test::isValidCombination)
            .toList();
    }

    private static boolean isValidCombination(final Iterable<Equipment> equipmentCombination) {
        int numberOfWeapons = 0;
        int numberOfArmours = 0;
        final Collection<Equipment> rings = new ArrayList<>();

        for (final Equipment equipment : equipmentCombination) {
            switch (equipment.type()) {
                case ARMOUR -> numberOfArmours++;
                case RING -> rings.add(equipment);
                case WEAPON -> numberOfWeapons++;
                default -> throw new IllegalStateException(String.format("Cannot handle input: %s", equipment.type()));
            }
        }

        if (numberOfWeapons != REQUIRED_AMOUNT_OF_WEAPONS || numberOfArmours != REQUIRED_AMOUNT_OF_ARMOURS || rings.size() > MAX_AMOUNT_OF_RINGS) {
            return false;
        }

        if (MAX_AMOUNT_OF_RINGS == 2) {
            return !CollectionUtils.containsDuplicates(rings);
        }

        return true;
    }

    private static List<Equipment> generateEquipment() {
        return List.of(
            // Weapons
            Equipment.create(EquipmentType.WEAPON, "Dagger", 8, 4, 0),
            Equipment.create(EquipmentType.WEAPON, "Shortsword", 10, 5, 0),
            Equipment.create(EquipmentType.WEAPON, "Warhammer", 25, 6, 0),
            Equipment.create(EquipmentType.WEAPON, "Longsword", 40, 7, 0),
            Equipment.create(EquipmentType.WEAPON, "Greataxe", 74, 8, 0),

            // Armour, including blank entry to simulate no armour being chosen
            Equipment.empty(EquipmentType.ARMOUR),
            Equipment.create(EquipmentType.ARMOUR, "Leather", 13, 0, 1),
            Equipment.create(EquipmentType.ARMOUR, "Chainmail", 31, 0, 2),
            Equipment.create(EquipmentType.ARMOUR, "Splintmail", 53, 0, 3),
            Equipment.create(EquipmentType.ARMOUR, "Bandedmail", 75, 0, 4),
            Equipment.create(EquipmentType.ARMOUR, "Platemail", 102, 0, 5),

            // Rings, duplicated to simulate two rings being chosen
            Equipment.create(EquipmentType.RING, "Damage +1", 25, 1, 0),
            Equipment.create(EquipmentType.RING, "Damage +1", 25, 1, 0),
            Equipment.create(EquipmentType.RING, "Damage +2", 50, 2, 0),
            Equipment.create(EquipmentType.RING, "Damage +2", 50, 2, 0),
            Equipment.create(EquipmentType.RING, "Damage +3", 100, 3, 0),
            Equipment.create(EquipmentType.RING, "Damage +3", 100, 3, 0),
            Equipment.create(EquipmentType.RING, "Defence +1", 20, 0, 1),
            Equipment.create(EquipmentType.RING, "Defence +1", 20, 0, 1),
            Equipment.create(EquipmentType.RING, "Defence +2", 40, 0, 2),
            Equipment.create(EquipmentType.RING, "Defence +2", 40, 0, 2),
            Equipment.create(EquipmentType.RING, "Defence +3", 80, 0, 3),
            Equipment.create(EquipmentType.RING, "Defence +3", 80, 0, 3)
        );
    }
}
