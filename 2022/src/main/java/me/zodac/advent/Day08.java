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

/**
 * Solution for 2022, Day 8.
 *
 * @see <a href="https://adventofcode.com/2022/day/8">AoC 2022, Day 8</a>
 */
public final class Day08 {

    private Day08() {

    }

    public static long part1(final List<String> values) {
        final int[][] trees = convertToArrayOfIntArrays(values);

        int innerCount = 0;
        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[0].length - 1; j++) {
                if (isTreeVisibleFromOutside(trees, i, j)) {
                    innerCount++;
                }
            }
        }

        final int outerEdgeCount = (2 * trees.length) + (2 * trees[0].length) - 4;
        return innerCount + outerEdgeCount;
    }

    private static boolean isTreeVisibleFromOutside(final int[][] trees, final int row, final int column) {
        final int tree = trees[row][column];

        return isVisibleUp(trees, tree, row, column)
            || isVisibleDown(trees, tree, row, column)
            || isVisibleLeft(trees, tree, row, column)
            || isVisibleRight(trees, tree, row, column);
    }

    private static boolean isVisibleUp(final int[][] trees, final int tree, final int row, final int column) {
        for (int i = (row - 1); i >= 0; i--) {
            if (tree <= trees[i][column]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleDown(final int[][] trees, final int tree, final int row, final int column) {
        for (int i = (row + 1); i < trees.length; i++) {
            if (tree <= trees[i][column]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleLeft(final int[][] trees, final int tree, final int row, final int column) {
        for (int j = (column - 1); j >= 0; j--) {
            if (tree <= trees[row][j]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isVisibleRight(final int[][] trees, final int tree, final int row, final int column) {
        for (int j = (column + 1); j < trees[0].length; j++) {
            if (tree <= trees[row][j]) {
                return false;
            }
        }

        return true;
    }

    public static int[][] convertToArrayOfIntArrays(final List<String> input) {
        final int outerLength = input.size();
        final int innerLength = input
            .stream()
            .mapToInt(String::length)
            .max()
            .orElse(outerLength);

        final int[][] arrayOfIntArrays = new int[outerLength][innerLength];

        for (int i = 0; i < input.size(); i++) {
            final String line = input.get(i);
            arrayOfIntArrays[i] = convertToArrayOfInts(line);
        }

        return arrayOfIntArrays;
    }

    private static int[] convertToArrayOfInts(final CharSequence input) {
        final int[] intArray = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            intArray[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return intArray;
    }

    public static long part2(final List<String> values) {
        final int[][] trees = convertToArrayOfIntArrays(values);
        long max = Long.MIN_VALUE;

        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[0].length - 1; j++) {
                final long scenicScore = getScenicScore(trees, i, j);
                max = Math.max(scenicScore, max);
            }
        }
        return max;
    }

    private static long getScenicScore(final int[][] trees, final int row, final int column) {
        final int tree = trees[row][column];

        return getScenicScoreUp(trees, row, column, tree)
            * getScenicScoreDown(trees, row, column, tree)
            * getScenicScoreLeft(trees, row, column, tree)
            * getScenicScoreRight(trees, row, column, tree);
    }

    private static long getScenicScoreUp(final int[][] trees, final int row, final int column, final int tree) {
        long upScore = 0;
        for (int i = (row - 1); i >= 0; i--) {
            if (tree <= trees[i][column]) {
                upScore++;
                break;
            }
            upScore++;
        }
        return upScore;
    }

    private static long getScenicScoreDown(final int[][] trees, final int row, final int column, final int tree) {
        long downScore = 0;
        for (int i = (row + 1); i < trees.length; i++) {
            if (tree <= trees[i][column]) {
                downScore++;
                break;
            }
            downScore++;
        }
        return downScore;
    }

    private static long getScenicScoreLeft(final int[][] trees, final int row, final int column, final int tree) {
        long leftScore = 0;
        for (int j = (column - 1); j >= 0; j--) {
            if (tree <= trees[row][j]) {
                leftScore++;
                break;
            }
            leftScore++;
        }
        return leftScore;
    }

    private static long getScenicScoreRight(final int[][] trees, final int row, final int column, final int tree) {
        int rightScore = 0;
        for (int j = (column + 1); j < trees[0].length; j++) {
            if (tree <= trees[row][j]) {
                rightScore++;
                break;
            }
            rightScore++;
        }
        return rightScore;
    }
}
