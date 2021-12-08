/*
 * MIT License
 *
 * Copyright (c) 2021 zodac.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zodac.advent.util;

import java.util.Arrays;

/**
 * Utility class with {@link String}-based functions.
 */
public final class StringUtils {

    private StringUtils() {

    }

    /**
     * Sorts the individual characters in the given {@link String} alphabetically.
     *
     * <p>
     * For example, <code>foobar</code> will be returned as <code>abfoor</code>
     *
     * @param input the {@link String} to sort
     * @return the sorted {@link String}
     */
    public static String sort(final String input) {
        final char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Splits the given {@link String} on any whitespaces. Will {@link String#trim()} before splitting.
     *
     * @param input the {@link String} to split
     * @return the array of split {@link String}s
     */
    public static String[] splitOnWhitespace(final String input) {
        return input.trim().split("\\s+");
    }

    /**
     * Checks if the characters in the provided {@code superString} is a superset for all provided {@code subStrings}.
     *
     * @param superString the {@link String} that should contain all characters in all {@code subStrings}
     * @param subStrings  the {@link String}s that should be subsets of {@code superString}
     * @return <code>false</code> if any character in any {@code subString} is not in the {@code superString}
     */
    public static boolean containsAll(final String superString, final String... subStrings) {
        for (final String subString : subStrings) {
            for (final char charToCheck : subString.toCharArray()) {
                if (!superString.contains(Character.toString(charToCheck))) {
                    return false;
                }
            }
        }
        return true;
    }
}
