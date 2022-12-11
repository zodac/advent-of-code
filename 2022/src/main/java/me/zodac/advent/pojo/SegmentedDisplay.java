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

package me.zodac.advent.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import me.zodac.advent.util.ArrayUtils;

public final class SegmentedDisplay {

    private static final char ON = '█';
    private static final char OFF = ' ';
    private static final int COLUMNS_NEEDED_PER_CHARACTER = 5;
    private static final int DEFAULT_HEIGHT = 6;

    private final boolean[][] array;

    private SegmentedDisplay(final boolean[][] array) {
        this.array = array.clone();
    }

    public static SegmentedDisplay create(final int numberOfCharacters) {
        final boolean[][] array = new boolean[DEFAULT_HEIGHT][numberOfCharacters * COLUMNS_NEEDED_PER_CHARACTER];
        final boolean[][] filledArray = ArrayUtils.deepFill(array, false);

        return new SegmentedDisplay(filledArray);
    }

    public int length() {
        return array[0].length;
    }

    public void turnOn(final int row, final int column) {
        array[row][column] = true;
    }

    public void print() {
        for (final boolean[] rows : array) {
            for (final boolean column : rows) {
                System.out.print((column ? ON : OFF) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getLetters() {
        final boolean[][][] chars = chunk();
        final StringBuilder stringBuilder = new StringBuilder();

        for (final boolean[][] charValues : chars) {
            final String key = convert(charValues);
            if (ALPHABET_MAPPER.containsKey(key)) {
                stringBuilder.append(ALPHABET_MAPPER.get(key));
            }
        }

        return stringBuilder.toString();
    }

    public boolean[][][] chunk() {
        final int numChars = length() / COLUMNS_NEEDED_PER_CHARACTER;
        final boolean[][][] chars = new boolean[numChars][DEFAULT_HEIGHT][COLUMNS_NEEDED_PER_CHARACTER - 1];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                final int charNumber = j / 5;
                final int col = j % 5;

                // Skip the empty column
                if (col != (COLUMNS_NEEDED_PER_CHARACTER - 1)) {
                    chars[charNumber][i][col] = array[i][j];
                }
            }
        }

        return chars;
    }

    public static void print(final boolean[][] array) {
        for (final boolean[] rows : array) {
            for (final boolean column : rows) {
                System.out.print((column ? ON : OFF) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static final Map<String, String> ALPHABET_MAPPER = createMapper();


    private static Map<String, String> createMapper() {
        final Map<String, String> map = new HashMap<>();

        map.put("011010011001111110011001", "A");
        map.put("111010011110100110011110", "B");
        map.put("011010011000100010010110", "C");
        // map.put("", "D");
        map.put("111110001110100010001111", "E");
        map.put("111110001110100010001000", "F");
        map.put("011010011000101110010111", "G");
        map.put("100110011111100110011001", "H");
        // map.put("", "I");
        map.put("001100010001000110010110", "J");
        map.put("100110101100101010101001", "K");
        map.put("100010001000100010001111", "L");
        // map.put("", "M");
        // map.put("", "N");
        // map.put("", "O");
        map.put("111010011001111010001000", "P");
        // map.put("", "Q");
        map.put("111010011001111010101001", "R");
        // map.put("", "S");
        // map.put("", "T");
        map.put("100110011001100110010110", "U");
        // map.put("", "V");
        // map.put("", "W");
        // map.put("", "X");
        // map.put("", "Y");
        map.put("111100010010010010001111", "Z");
        return map;
    }

    private static String convert(final boolean[][] input) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                stringBuilder.append(input[i][j] ? "1" : "0");
            }
        }

        return stringBuilder.toString();
    }
}
