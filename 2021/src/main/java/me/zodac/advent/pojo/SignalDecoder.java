/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import me.zodac.advent.util.CollectionUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Utility class used to decode the output value of a {@link Signal}.
 */
public final class SignalDecoder {

    private static final Set<Integer> UNIQUE_OUTPUT_VALUES = Set.of(2, 3, 4, 7);
    private static final int INPUT_SIZE_THAT_MUST_BE_DECODED_LAST = 5;

    private SignalDecoder() {

    }

    /**
     * Checks if the provided {@link Signal} output value is a unique value. In a standard 7-segment display, we can identify four values
     * based on the number of segments that are lit:
     * <ol>
     *     <li>2-segments: '1'</li>
     *     <li>3-segments: '7'</li>
     *     <li>4-segments: '4'</li>
     *     <li>7-segments: '8'</li>
     * </ol>
     *
     * @param signalOutputValue {@link String} to check
     * @return {@code true} if the output value is '1', '4', '7' or '8'
     */
    public static boolean isUniqueOutputValue(final String signalOutputValue) {
        return UNIQUE_OUTPUT_VALUES.contains(signalOutputValue.length());
    }

    /**
     * Generates a decoder for the provided {@link Signal} based on its input values, then decodes the outputs.
     *
     * <p>
     * The decoder is generated based on the input values. Based on the input value's size and how 7-segment displays are lit, we can determine a few
     * things:
     * <ol>
     *     <li>If the size of the input is 2, the value is '1'</li>
     *     <li>If the size of the input is 3, the value is '7'</li>
     *     <li>If the size of the input is 4, the value is '4'</li>
     *     <li>If the size of the input is 7, the value is '8'</li>
     *     <li>If the size of the input is 6:
     *         <ol>
     *             <li>If the input is a superset of the characters in the values for '1', '7' and '4', the value is '9'</li>
     *             <li>Else if the input is a superset of the characters in the values for '1' and '7', the value is '0'</li>
     *             <li>Else the value is '6'</li>
     *         </ol>
     *     </li>
     *     <li>If the size of the input is 5:
     *         <ol>
     *             <li>If the input is a superset of the characters in the values for '1' and '7', the value is '3'</li>
     *             <li>Else if the input is a subset of the characters in the value for '6', the value is '5'</li>
     *             <li>Else the value is '2'</li>
     *         </ol>
     *     </li>
     * </ol>
     *
     * @param signal the {@link Signal} whose output is to be decoded
     * @return the decoded {@link Signal}
     */
    public static long decode(final Signal signal) {
        final Map<String, Integer> decoder = generateDecoderForSignal(signal);

        final String outputsDecodedAndConcatenated = signal.outputs()
            .stream()
            .map(decoder::get)
            .map(String::valueOf)
            .collect(Collectors.joining());
        return Long.parseLong(outputsDecodedAndConcatenated);
    }

    private static Map<String, Integer> generateDecoderForSignal(final Signal signal) {
        final List<String> inputs = sortInputsForDecoding(signal.inputs());
        final Map<String, Integer> decoder = HashMap.newHashMap(inputs.size());

        for (final String input : inputs) {
            final int inputLength = input.length();

            switch (inputLength) {
                case 2 -> decoder.put(input, 1);
                case 3 -> decoder.put(input, 7);
                case 4 -> decoder.put(input, 4);
                case 5 -> decoder.put(input, lengthFive(input, decoder));
                case 6 -> decoder.put(input, lengthSix(input, decoder));
                case 7 -> decoder.put(input, 8);
                default -> throw new IllegalStateException("Cannot decode input with length: " + inputLength);
            }
        }

        return decoder;
    }

    private static int lengthFive(final String input, final Map<String, ? super Integer> decoder) {
        final String valueForOne = CollectionUtils.getKeyByValue(decoder, 1).orElseThrow();
        final String valueForSeven = CollectionUtils.getKeyByValue(decoder, 7).orElseThrow();

        // If the input is a superset containing '1' and '7', value is '3'
        if (StringUtils.containsAllCharacters(input, valueForOne, valueForSeven)) {
            return 3;
        }

        final String valueForSix = CollectionUtils.getKeyByValue(decoder, 6).orElseThrow();

        // If the input is a subset of the value for '6', value is '5'
        if (StringUtils.containsAllCharacters(valueForSix, input)) {
            return 5;
        }

        // If the other values did not match, we can assume the value is '2'
        return 2;
    }

    private static int lengthSix(final String input, final Map<String, ? super Integer> decoder) {
        final String valueForOne = CollectionUtils.getKeyByValue(decoder, 1).orElseThrow();
        final String valueForFour = CollectionUtils.getKeyByValue(decoder, 4).orElseThrow();
        final String valueForSeven = CollectionUtils.getKeyByValue(decoder, 7).orElseThrow();

        // If the input is a superset containing '1', '7' and '4', value is '9'
        if (StringUtils.containsAllCharacters(input, valueForOne, valueForFour, valueForSeven)) {
            return 9;
        }

        // If the input is a superset containing '1', and '7', value is '0'
        if (StringUtils.containsAllCharacters(input, valueForOne, valueForSeven)) {
            return 0;
        }

        // If the other values did not match, we can assume the value is '6'
        return 6;
    }

    // For the decoding, inputs of size 5 must be determined last, as the only way to differentiate a 2 and a 5 is comparing to 6
    // All other inputs are ordered by the size of the String
    private static List<String> sortInputsForDecoding(final Collection<String> inputs) {
        final List<String> orderedInputs = new ArrayList<>(inputs.size());

        for (final String input : inputs) {
            if (input.length() != INPUT_SIZE_THAT_MUST_BE_DECODED_LAST) {
                orderedInputs.add(input);
            }
        }
        orderedInputs.sort(Comparator.comparingInt(String::length));

        for (final String input : inputs) {
            if (input.length() == INPUT_SIZE_THAT_MUST_BE_DECODED_LAST) {
                orderedInputs.add(input);
            }
        }

        return orderedInputs;
    }
}
