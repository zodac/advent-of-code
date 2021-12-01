package me.zodac.advent.day;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.zodac.advent.day.one.PartOne;
import me.zodac.advent.day.one.PartTwo;
import me.zodac.advent.util.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests to verify answers for <a href="https://adventofcode.com/2021/day/1">AoC 2021, Day 1</a>.
 */
class DayOneTest {

    private static final String INPUT_FILE_PATH = "day1/input.txt";

    @Test
    void partOne() {
        final PartOne partOne = new PartOne();
        final List<String> values = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH);

        final int count = partOne.countValuesHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_766);
    }

    @Test
    void partTwo() {
        final PartTwo partTwo = new PartTwo();
        final List<String> values = FileUtils.readLinesFromFileInResources(INPUT_FILE_PATH);

        final int count = partTwo.countThreeValueWindowHigherThanPreviousValue(values);
        assertThat(count)
            .isEqualTo(1_797);
    }
}
