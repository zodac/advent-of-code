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

package me.zodac.advent;

import static me.zodac.advent.util.CollectionUtils.extractValuesAsList;

import java.util.Collection;
import java.util.List;
import me.zodac.advent.pojo.Ingredient;
import me.zodac.advent.util.MathUtils;

/**
 * Solution for 2015, Day 15.
 *
 * @see <a href="https://adventofcode.com/2015/day/15">AoC 2015, Day 15</a>
 */
public final class Day15 {

    private static final int NUMBER_OF_DIFFERENT_INGREDIENTS = 4;
    private static final int MAXIMUM_AMOUNT_OF_INGREDIENTS = 100;
    private static final int VALUE_FOR_NO_CALORIE_CHECK = 0;

    private Day15() {

    }

    /**
     * Given a {@link Collection} of <b>4</b> {@link Ingredient}s, we calculate the score for every combination of these ingredients that add up to
     * {@value #MAXIMUM_AMOUNT_OF_INGREDIENTS} units. For example, we might have 100 units of {@link Ingredient} #1, then 0 of the remainder. Or we
     * could have a mixture, such as 24 units of {@link Ingredient} #1, 45 units of {@link Ingredient} #2, etc. All {@link Ingredient}s must add up to
     * {@value #MAXIMUM_AMOUNT_OF_INGREDIENTS} units.
     *
     * <p>
     * The score is calculated by getting the total value of each property of an {@link Ingredient}:
     * <ul>
     *     <li>{@link Ingredient#capacity()}</li>
     *     <li>{@link Ingredient#durability()}</li>
     *     <li>{@link Ingredient#flavour()}</li>
     *     <li>{@link Ingredient#texture()}</li>
     * </ul>
     * The total value of any property is:
     * <pre>
     *     (valueOfIngredient1 * unitsOfIngredient1) + (valueOfIngredient1 * unitsOfIngredient1) ... + (valueOfIngredientN * unitsOfIngredientN)
     * </pre>
     * <b>NOTE:</b> If any property total is less than <b>0</b>, it will be instead set to <b>0</b>.
     *
     * <p>
     * Once all totals have been calculated, the total score for the combination is calculated by multiplying the total property values together. This
     * will be compared with all other combination scores, and the highest value will be returned.
     *
     * @param ingredients the {@link Ingredient}s
     * @return the score of the highest-scoring combinarion
     * @throws IllegalArgumentException thrown if input has more than {@value #NUMBER_OF_DIFFERENT_INGREDIENTS} elements
     * @see #scoreOfBestIngredients(Collection, int)
     */
    public static long scoreOfBestIngredients(final Collection<Ingredient> ingredients) {
        return scoreOfBestIngredients(ingredients, VALUE_FOR_NO_CALORIE_CHECK);
    }

    /**
     * Given a {@link Collection} of <b>4</b> {@link Ingredient}s, we calculate the score for every combination of these ingredients that add up to
     * {@value #MAXIMUM_AMOUNT_OF_INGREDIENTS} units. For example, we might have 100 units of {@link Ingredient} #1, then 0 of the remainder. Or we
     * could have a mixture, such as 24 units of {@link Ingredient} #1, 45 units of {@link Ingredient} #2, etc. All {@link Ingredient}s must add up to
     * {@value #MAXIMUM_AMOUNT_OF_INGREDIENTS} units.
     *
     * <p>
     * The score is calculated by getting the total value of each property of an {@link Ingredient}:
     * <ul>
     *     <li>{@link Ingredient#capacity()}</li>
     *     <li>{@link Ingredient#durability()}</li>
     *     <li>{@link Ingredient#flavour()}</li>
     *     <li>{@link Ingredient#texture()}</li>
     * </ul>
     * The total value of any property is:
     * <pre>
     *     (valueOfIngredient1 * unitsOfIngredient1) + (valueOfIngredient1 * unitsOfIngredient1) ... + (valueOfIngredientN * unitsOfIngredientN)
     * </pre>
     * <b>NOTE:</b> If any property total is less than <b>0</b>, it will be instead set to <b>0</b>.
     *
     * <p>
     * Once all totals have been calculated, the total score for the combination is calculated by multiplying the total property values together. This
     * will be compared with all other combination scores, and the highest value will be returned.
     *
     * <p>
     * If {@code wantedCalorieCount} is set to any value other than {@value #VALUE_FOR_NO_CALORIE_CHECK}, the total calorie count of the combination
     * will be calculated as above for {@link Ingredient#calories()}. If it is not exactly equal to {@code wantedCalorieCount}, it will not be
     * considered a valid combination.
     *
     * @param ingredients        the {@link Ingredient}s
     * @param wantedCalorieCount the wanted total calorie count for a valid combination
     * @return the score of the highest-scoring combinarion
     * @throws IllegalArgumentException thrown if input has more than {@value #NUMBER_OF_DIFFERENT_INGREDIENTS} elements
     */
    public static long scoreOfBestIngredients(final Collection<Ingredient> ingredients, final int wantedCalorieCount) {
        if (ingredients.size() != NUMBER_OF_DIFFERENT_INGREDIENTS) {
            throw new IllegalArgumentException(
                String.format("Expected %s %ss, found: %s", NUMBER_OF_DIFFERENT_INGREDIENTS, Ingredient.class.getSimpleName(), ingredients.size()));
        }

        long bestScore = Long.MIN_VALUE;

        for (int ingredient1Amount = 0; ingredient1Amount < MAXIMUM_AMOUNT_OF_INGREDIENTS; ingredient1Amount++) {
            for (int ingredient2Amount = 0; ingredient2Amount < MAXIMUM_AMOUNT_OF_INGREDIENTS; ingredient2Amount++) {
                for (int ingredient3Amount = 0; ingredient3Amount < MAXIMUM_AMOUNT_OF_INGREDIENTS; ingredient3Amount++) {
                    final int ingredient4Amount = MAXIMUM_AMOUNT_OF_INGREDIENTS - ingredient1Amount - ingredient2Amount - ingredient3Amount;

                    final List<Integer> amounts = List.of(ingredient1Amount, ingredient2Amount, ingredient3Amount, ingredient4Amount);

                    final long totalCapacity = MathUtils.multiplyElementsThenSum(amounts, extractValuesAsList(ingredients, Ingredient::capacity));
                    final long totalDurability = MathUtils.multiplyElementsThenSum(amounts, extractValuesAsList(ingredients, Ingredient::durability));
                    final long totalFlavour = MathUtils.multiplyElementsThenSum(amounts, extractValuesAsList(ingredients, Ingredient::flavour));
                    final long totalTexture = MathUtils.multiplyElementsThenSum(amounts, extractValuesAsList(ingredients, Ingredient::texture));

                    if (isCaloriesInvalid(ingredients, wantedCalorieCount, amounts)) {
                        continue;
                    }

                    if (MathUtils.isAnyLessThan(0L, totalCapacity, totalDurability, totalFlavour, totalTexture)) {
                        continue;
                    }

                    final long totalScore = totalCapacity * totalDurability * totalFlavour * totalTexture;

                    if (totalScore > bestScore) {
                        bestScore = totalScore;
                    }
                }
            }
        }

        return bestScore;
    }

    private static boolean isCaloriesInvalid(final Collection<Ingredient> ingredients, final int wantedCalorieCount, final List<Integer> amounts) {
        if (wantedCalorieCount == VALUE_FOR_NO_CALORIE_CHECK) {
            return false;
        }

        final long totalCalories = MathUtils.multiplyElementsThenSum(amounts, extractValuesAsList(ingredients, Ingredient::calories));
        return wantedCalorieCount != totalCalories;
    }
}
