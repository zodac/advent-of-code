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

package me.zodac.advent.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.util.StringUtils;

/**
 * Definition of an Aunt {@link Sue}. An Aunt {@link Sue} can have the following {@link Integer} attributes:
 * <ul>
 *     <li>children</li>
 *     <li>cats</li>
 *     <li>samoyeds</li>
 *     <li>pomeranians</li>
 *     <li>akitas</li>
 *     <li>vizslas</li>
 *     <li>goldfish</li>
 *     <li>trees</li>
 *     <li>cars</li>
 *     <li>perfumes</li>
 * </ul>
 *
 * <p>
 * There is no maximum or minimum number of attributes a {@link Sue} needs to have - they could have values for all, or none. Any missing attribute
 * simply means it is unknown, not that that value is <b>0</b>.
 *
 * @param id         the ID of the Aunt {@link Sue} (they do not have names)
 * @param attributes the attributes for the Aunt {@link Sue}
 */
public record Sue(int id, Map<String, Integer> attributes) {

    private static final int DEFAULT_SUE_NUMBER = 0;
    private static final int MAXIMUM_NUMBER_OF_ATTRIBUTES = 10;
    private static final Set<String> ATTRIBUTES_WITH_MINIMUM_VALUE = Set.of("cats", "trees");
    private static final Set<String> ATTRIBUTES_WITH_MAXIMUM_VALUE = Set.of("pomeranians", "goldfish");
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("([a-z]+): (\\d+)");
    private static final Pattern SUE_PATTERN = Pattern.compile("Sue (\\d+): ([a-z]+): (\\d+), ([a-z]+): (\\d+), ([a-z]+): (\\d+)");

    /**
     * Creates a {@link Sue} with three attributes. These can be any attribute, but there must be three.
     *
     * @param input the {@link CharSequence} to check
     * @return the created {@link Sue}
     * @throws IllegalArgumentException thrown if the input does not match the expected format
     */
    public static Sue parseThreeAttributes(final CharSequence input) {
        final Matcher matcher = SUE_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Unable to find match in input: " + input);
        }

        final int id = Integer.parseInt(matcher.group(1));
        final String attributeName1 = matcher.group(2);
        final int attributeValue1 = Integer.parseInt(matcher.group(3));
        final String attributeName2 = matcher.group(4);
        final int attributeValue2 = Integer.parseInt(matcher.group(5));
        final String attributeName3 = matcher.group(6);
        final int attributeValue3 = Integer.parseInt(matcher.group(7));

        final Map<String, Integer> attributes = Map.of(
            attributeName1, attributeValue1,
            attributeName2, attributeValue2,
            attributeName3, attributeValue3
        );

        return new Sue(id, attributes);
    }

    /**
     * Creates a {@link Sue} with all available attributes.
     *
     * @param input the {@link CharSequence} to check
     * @return the created {@link Sue}
     * @throws IllegalArgumentException thrown if the input does not contain {@value #MAXIMUM_NUMBER_OF_ATTRIBUTES} attributes
     */
    public static Sue parseAllAttributes(final CharSequence input) {
        final String[] lines = StringUtils.splitOnNewLines(input);
        final Map<String, Integer> attributes = HashMap.newHashMap(lines.length);

        for (final String line : lines) {
            final Matcher matcher = ATTRIBUTE_PATTERN.matcher(line);

            if (!matcher.find()) {
                throw new IllegalArgumentException("Unable to find match in input: " + input);
            }

            final String attributeName = matcher.group(1);
            final int attributeValue = Integer.parseInt(matcher.group(2));

            attributes.put(attributeName, attributeValue);
        }

        if (attributes.size() != MAXIMUM_NUMBER_OF_ATTRIBUTES) {
            throw new IllegalArgumentException(String.format("Expected %s attributes, found %s", MAXIMUM_NUMBER_OF_ATTRIBUTES, attributes.size()));
        }

        return new Sue(DEFAULT_SUE_NUMBER, attributes);
    }

    /**
     * Checks if the provided {@link Sue} is a match to this {@link Sue}. We assume this {@link Sue} has all attributes, so we compare whichever
     * attributes the provided {@link Sue} has, and compares them to this {@link Sue}.
     *
     * <p>
     * Some attributes will not be a direct match, but will instead be considered a minimum or a maximum.
     *
     * @param otherSue the other {@link Sue} to check
     * @return {@code true} if all attribute values for the other {@link Sue} match the values for this {@link Sue}
     * @see #ATTRIBUTES_WITH_MINIMUM_VALUE
     * @see #ATTRIBUTES_WITH_MAXIMUM_VALUE
     */
    public boolean isMatchWithRanges(final Sue otherSue) {
        return isMatch(otherSue, true);
    }

    /**
     * Checks if the provided {@link Sue} is a match to this {@link Sue}. We assume this {@link Sue} has all attributes, so we compare whichever
     * attributes the provided {@link Sue} has, and compares them to this {@link Sue}.
     *
     * @param otherSue the other {@link Sue} to check
     * @return {@code true} if all attribute values for the other {@link Sue} match the values for this {@link Sue}
     */
    public boolean isMatch(final Sue otherSue) {
        return isMatch(otherSue, false);
    }

    private boolean isMatch(final Sue otherSue, final boolean withRanges) {
        if (attributes.size() != MAXIMUM_NUMBER_OF_ATTRIBUTES) {
            throw new IllegalStateException(String.format("Expected this %s to have %s attributes, found %s",
                Sue.class.getSimpleName(), MAXIMUM_NUMBER_OF_ATTRIBUTES, attributes.size()));
        }

        for (final Map.Entry<String, Integer> otherSueAttribute : otherSue.attributes.entrySet()) {
            final String attributeName = otherSueAttribute.getKey();

            // Since source has all attributes, no need to check if key exists
            final int sourceValue = attributes.get(attributeName);
            final int otherValue = otherSueAttribute.getValue();

            // Do comparison with ranges on attribute name, if required
            if (withRanges && ATTRIBUTES_WITH_MINIMUM_VALUE.contains(attributeName)) {
                if (sourceValue > otherValue) {
                    return false;
                }
            } else if (withRanges && ATTRIBUTES_WITH_MAXIMUM_VALUE.contains(attributeName)) {
                if (sourceValue < otherValue) {
                    return false;
                }
            } else if (sourceValue != otherValue) {
                return false;
            }
        }

        // If we cannot find any unmatched attributes, we assume they are a match even if there are missing attributes
        return true;
    }
}
