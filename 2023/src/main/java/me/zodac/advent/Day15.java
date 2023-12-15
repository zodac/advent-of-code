/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2023 zodac.me
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solution for 2023, Day 15.
 *
 * @see <a href="https://adventofcode.com/2023/day/15">[2023: 15] Lens Library</a>
 */
public final class Day15 {

    private static final Pattern LENS_PATTERN = Pattern.compile("([a-z]*)([=-])(\\d?)");
    private static final int HASH_ALGORITHM_MULTIPLIER = 17;
    private static final int HASH_ALGORITHM_MODULO = 256;
    private static final int NUMBER_OF_BOXES = 256;

    private Day15() {

    }

    /**
     * For each {@link String}, we calculate the HASH algorithm, by performing the following.
     * <ul>
     *     <li>Start the {@code counter} at <b>0</b></li>
     *      <li>For each {@link Character}:
     *      <ol>
     *          <li>Get the {@link #getAsciiValue(char)}, add to the {@code counter}</li>
     *          <li>Multiple the {@code counter} by {@link #HASH_ALGORITHM_MULTIPLIER}</li>
     *          <li>Get the remainder of the {@code counter} after dividing by {@link #HASH_ALGORITHM_MODULO}</li>
     *      </ol>
     *     </li>
     * </ul>
     *
     * <p>
     * Sum these values up.
     *
     * @param values the input values to HASH
     * @return the sum of all HASH values
     */
    public static long calculateSumOfHashAlgorithms(final Collection<String> values) {
        return values
            .stream()
            .mapToInt(Day15::calculateHash)
            .sum();
    }

    private static int calculateHash(final String input) {
        int current = 0;
        for (final char c : input.toCharArray()) {
            current += getAsciiValue(c);
            current *= HASH_ALGORITHM_MULTIPLIER;
            current = current % HASH_ALGORITHM_MODULO;
        }
        return current;
    }

    private static int getAsciiValue(final char c) {
        return c;
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long calculateTotalFocusPower(final Iterable<String> values) {
        final Map<Integer, List<String>> boxes = new LinkedHashMap<>();
        for (int i = 0; i < NUMBER_OF_BOXES; i++) {
            boxes.put(i, new ArrayList<>());
        }

        for (final String value : values) {
            final Matcher matcher = LENS_PATTERN.matcher(value);

            if (!matcher.find()) {
                throw new IllegalArgumentException("Unable to find match in input: " + value);
            }

            final String label = matcher.group(1);
            final int box = calculateHash(label);
            final List<String> currentBox = boxes.getOrDefault(box, new ArrayList<>());
            final int index = getIndex(currentBox, label);

            if (index != -1) {
                currentBox.remove(index);
            }

            if (value.contains("=")) {
                if (index == -1) {
                    currentBox.add(value);
                } else {
                    currentBox.add(index, value);
                }
                boxes.put(box, currentBox);
            } else if (value.contains("-")) {
                if (index != -1L) {
                    boxes.put(box, currentBox);
                }
            }
        }

        return calculateFocusPower(boxes);
    }

    private static int getIndex(final List<String> currentBox, final String label) {
        for (int i = 0; i < currentBox.size(); i++) {
            final String value = currentBox.get(i);
            final String tokens = value.split("=")[0];
            if (label.equalsIgnoreCase(tokens)) {
                return i;
            }
        }

        return -1;
    }

    private static long calculateFocusPower(final Map<Integer, ? extends List<String>> boxes) {
        return boxes
            .entrySet()
            .stream()
            .mapToLong(Day15::calculateFocusPowerForBox)
            .sum();
    }

    private static Long calculateFocusPowerForBox(final Map.Entry<Integer, ? extends List<String>> entry) {
        long boxTotal = 0L;

        final List<String> value = entry.getValue();
        if (value.isEmpty()) {
            return boxTotal;
        }

        final int boxMultiplier = entry.getKey() + 1;
        for (int slot = 0; slot < value.size(); slot++) {
            final String s = value.get(slot);
            final int focalLength = Integer.parseInt(s.substring(s.length() - 1));

            boxTotal += ((long) boxMultiplier * (slot + 1) * focalLength);
        }
        return boxTotal;
    }
}
