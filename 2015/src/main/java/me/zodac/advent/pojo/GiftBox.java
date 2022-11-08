/*
 * Zero-Clause BSD License
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

/**
 * A perfect rectangular prism, describing a gift box.
 *
 * @param length the length of the {@link GiftBox}
 * @param width  the width of the {@link GiftBox}
 * @param height the height of the {@link GiftBox}
 */
public record GiftBox(int length, int width, int height) {

    private static final String DELIMITER = "x";

    /**
     * Parses a {@link String} into a {@link GiftBox}, if the {@link String} follows the format:
     * <pre>
     *     LengthxWidthxHeight
     * </pre>
     *
     * @param input the input {@link String} to be converted to a {@link GiftBox}
     * @return the {@link GiftBox} with the dimensions from the {@link String}
     * @throws IllegalArgumentException thrown if the input does not contain the delimiter {@link #DELIMITER}
     */
    public static GiftBox createFromString(final String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(String.format("Input string does not contain expected delimiter '%s': '%s'", DELIMITER, input));
        }

        final String[] tokens = input.split(DELIMITER);
        return new GiftBox(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }

    /**
     * Calculates the surface area of a rectangular prism:
     * <pre>
     *     (2xLengthxWidth) + (2xWidthxHeight) + (2xHeight*Length)
     * </pre>
     *
     * @return the surface area of the {@link GiftBox}
     */
    public long surfaceArea() {
        return (2L * length * width) + (2L * width * height) + (2L * height * length);
    }

    /**
     * Calculates the area of only the smallest side of the {@link GiftBox}.
     *
     * @return the area of the smallest side
     */
    public long areaOfSmallestSide() {
        final int sideLengthWidth = length * width;
        final int sideWidthHeight = width * height;
        final int sideHeightLength = height * length;

        return (sideLengthWidth < sideWidthHeight)
            ? Math.min(sideHeightLength, sideLengthWidth)
            : Math.min(sideWidthHeight, sideHeightLength);
    }

    /**
     * Calculates the smallest perimeter around the {@link GiftBox}, across the smallest two sides.
     *
     * @return the smallest perimeter
     */
    public long smallestPerimeter() {
        if (length < width) {
            return (height < width) ? 2L * (length + height) : 2L * (length + width);
        } else {
            return (height < length) ? 2L * (width + height) : 2L * (length + width);
        }
    }

    /**
     * The product of the length, width, height.
     *
     * @return the product of the {@link GiftBox} dimensions
     */
    public long productOfDimensions() {
        return (long) length * width * height;
    }
}
