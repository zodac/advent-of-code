package me.zodac.advent.day.one;

import java.util.List;

/**
 * @see <a href="https://adventofcode.com/2021/day/1#part2">AoC 2021, Day 1, Part 2</a>
 */
public record PartTwo() {

    public int countThreeValueWindowHigherThanPreviousValue(final List<String> values) {
        if (values.size() < 3) {
            return 0;
        }

        int count = 0;

        // Initialise with first value, rather than assuming the value cannot be negative
        int currentValue = Integer.parseInt(values.get(0)) + Integer.parseInt(values.get(1)) + Integer.parseInt(values.get(2));

        for (int i = 0; i < values.size() - 2; i++) {
            final int firstValue = Integer.parseInt(values.get(i));
            final int secondValue = Integer.parseInt(values.get(i + 1));
            final int thirdValue = Integer.parseInt(values.get(i + 2));

            final int nextValue = firstValue + secondValue + thirdValue;

            if (nextValue > currentValue) {
                count++;
            }

            currentValue = nextValue;
        }

        return count;
    }
}
