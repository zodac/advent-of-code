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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility functions for {@link java.util.Collection}s.
 */
public final class CollectionUtils {

    private CollectionUtils() {

    }

    /**
     * For cases when the value of a {@link Map} might be known, but the key is not. We iterate over all {@link Map.Entry}s and check the value. If it
     * matches the input, then the key for that {@link Map.Entry} is returned.
     *
     * @param map   the {@link Map} to be searched
     * @param value the value whose key is to be found
     * @param <K>   the type of the key
     * @param <V>   the type of the value
     * @return an {@link Optional} of the found key
     */
    public static <K, V> Optional<K> getKeyByValue(final Map<K, V> map, final V value) {
        for (final Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return Optional.of(entry.getKey());
            }
        }

        return Optional.empty();
    }

    /**
     * Returns the value at the middle index of a {@link List}. The {@link List} is sorted then the middle index is returned.
     *
     * @param list the {@link List} whose middle index is to be found
     * @param <E>  the type of the {@link List}, must extend {@link Comparable}
     * @return the value at the middle of the sorted {@link List}
     * @throws IllegalArgumentException thrown if the {@link List} has an even size
     */
    public static <E extends Comparable<E>> E getMiddleValueOfList(final List<E> list) {
        if (list.size() % 2 == 0) {
            throw new IllegalArgumentException("Expected list with odd size, found size: " + list.size());
        }

        Collections.sort(list);
        return list.get((list.size() / 2));
    }
}
