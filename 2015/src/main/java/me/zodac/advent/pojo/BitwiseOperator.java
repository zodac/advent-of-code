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

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Enum defining the available {@link Integer} bitwise operations.
 */
public enum BitwiseOperator {

    /**
     * Performs a bitwise <b>AND</b> on two provided values:
     * <pre>
     *     x {@literal &} y
     * </pre>
     */
    AND {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != TWO_INPUTS) {
                throw new IllegalArgumentException("Expected 2 input values, found: " + inputs);
            }
            return inputs.get(0) & inputs.get(1);
        }
    },

    /**
     * Performs a bitwise <b>OR</b> on two provided values:
     * <pre>
     *     x {@literal |} y
     * </pre>
     */
    OR {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != TWO_INPUTS) {
                throw new IllegalArgumentException("Expected 2 input values, found: " + inputs);
            }
            return inputs.get(0) | inputs.get(1);
        }
    },

    /**
     * Performs a bitwise <b>LEFT SHIFT</b> on two provided values:
     * <pre>
     *     x {@literal <<} y
     * </pre>
     */
    LSHIFT {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != TWO_INPUTS) {
                throw new IllegalArgumentException("Expected 2 input values, found: " + inputs);
            }
            return inputs.get(0) << inputs.get(1);
        }
    },

    /**
     * Performs a bitwise <b>RIGHT SHIFT</b> on two provided values:
     * <pre>
     *     x {@literal >>} y
     * </pre>
     */
    RSHIFT {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != TWO_INPUTS) {
                throw new IllegalArgumentException("Expected 2 input values, found: " + inputs);
            }
            return inputs.get(0) >> inputs.get(1);
        }
    },

    /**
     * Performs a bitwise <b>NOT</b> on the single provided value:
     * <pre>
     *     {@literal ~}x
     * </pre>
     */
    NOT {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != SINGLE_INPUT) {
                throw new IllegalArgumentException("Expected 1 input values, found: " + inputs);
            }
            return ~inputs.get(0);
        }
    },

    /**
     * Default {@link BitwiseOperator}, simply returns the single value provided.
     */
    SET {
        @Override
        public int calculate(final List<Integer> inputs) {
            if (inputs.size() != SINGLE_INPUT) {
                throw new IllegalArgumentException("Expected 1 input values, found: " + inputs);
            }
            return inputs.get(0);
        }
    };

    private static final int SINGLE_INPUT = 1;
    private static final int TWO_INPUTS = 2;
    private static final Collection<BitwiseOperator> ALL_VALUES = Stream.of(values())
        .toList();

    /**
     * Calculates the value of the provided {@code inputs}.
     *
     * @param inputs the {@link Integer}s
     * @return the calculated value
     */
    public abstract int calculate(List<Integer> inputs);

    /**
     * Retrieve a {@link BitwiseOperator} based on the input {@link String}. The search is case-insensitive.
     *
     * @param input the {@link BitwiseOperator} as a {@link String}
     * @return the matching {@link BitwiseOperator}, or {@link BitwiseOperator#SET} if none is found
     */
    public static BitwiseOperator get(final String input) {
        return ALL_VALUES
            .stream()
            .filter(bitwiseOperator -> bitwiseOperator.toString().equalsIgnoreCase(input))
            .findAny()
            .orElse(SET);
    }
}