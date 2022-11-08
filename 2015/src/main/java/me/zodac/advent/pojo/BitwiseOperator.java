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

import static me.zodac.advent.util.CollectionUtils.getFirst;
import static me.zodac.advent.util.CollectionUtils.getLast;

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
            return getFirst(inputs) & getLast(inputs);
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
            return getFirst(inputs) | getLast(inputs);
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
            return getFirst(inputs) << getLast(inputs);
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
            return getFirst(inputs) >> getLast(inputs);
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
            return ~getFirst(inputs);
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
            return getFirst(inputs);
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