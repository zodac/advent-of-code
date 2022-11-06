/*
 * MIT License
 *
 * Copyright (c) 2021-2022 zodac.me
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

package me.zodac.advent.pojo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * POJO defining an {@link Ingredient} for a recipe.
 *
 * @param name       the name of the {@link Ingredient}
 * @param capacity   the capacity
 * @param durability the durability
 * @param flavour    the flavour
 * @param texture    the texture
 * @param calories   the calories
 */
public record Ingredient(String name, int capacity, int durability, int flavour, int texture, int calories) {

    private static final Pattern INGREDIENT_PATTERN = Pattern.compile(
        "([a-zA-z]+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");

    /**
     * Creates a {@link Ingredient} from a {@link CharSequence} in the format:
     * <pre>
     *     [name]: capacity [capacity], durability [durability], flavor [flavour], texture [texture], calories [calories]
     * </pre>
     *
     * @param input the {@link CharSequence} to parse
     * @return the {@link Ingredient}
     */
    public static Ingredient parse(final CharSequence input) {
        final Matcher matcher = INGREDIENT_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input string: " + input);
        }

        final String name = matcher.group(1);
        final int capacity = Integer.parseInt(matcher.group(2));
        final int durability = Integer.parseInt(matcher.group(3));
        final int flavour = Integer.parseInt(matcher.group(4));
        final int texture = Integer.parseInt(matcher.group(5));
        final int calories = Integer.parseInt(matcher.group(6));

        return new Ingredient(name, capacity, durability, flavour, texture, calories);
    }
}
