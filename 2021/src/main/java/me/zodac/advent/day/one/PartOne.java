package me.zodac.advent.day.one;

import java.util.List;

/**
 * @see <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1, Part 1</a>
 */
public record PartOne() {

    public int countValuesHigherThanPreviousValue(final List<String> values) {
        if (values.isEmpty()) {
            return 0;
        }

        int count = 0;

        // Initialise with first value, rather than assuming the value cannot be negative
        int currentValue = Integer.parseInt(values.get(0));

        for (final String value : values) {
            final int nextValue = Integer.parseInt(value);

            if (nextValue > currentValue) {
                count++;
            }

            currentValue = nextValue;
        }

        return count;
    }
}
