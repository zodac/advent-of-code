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

package me.zodac.advent.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.zodac.advent.util.CollectionUtils;
import me.zodac.advent.util.StringUtils;

/**
 * Utility class used to decode the output value of a {@link Signal}.
 */
public final class SignalDecoder {

    private static final int INPUT_SIZE_THAT_MUST_BE_DECODED_LAST = 5;

    private SignalDecoder() {

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
     *     <li>If the size of the input is 4:
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

        return Long.parseLong(
            signal.outputs()
                .stream()
                .map(decoder::get)
                .map(String::valueOf)
                .collect(Collectors.joining(""))
        );
    }

    private static Map<String, Integer> generateDecoderForSignal(final Signal signal) {
        final Map<String, Integer> decoder = new HashMap<>();
        final List<String> inputs = sortInputsForDecoding(signal.inputs());

        for (final String input : inputs) {
            final int inputLength = input.length();

            switch (inputLength) {
                default -> throw new IllegalStateException("Cannot decode input with length: " + inputLength);
                case 2 -> decoder.put(input, 1);
                case 3 -> decoder.put(input, 7);
                case 4 -> decoder.put(input, 4);
                case 5 -> decoder.put(input, lengthFive(input, decoder));
                case 6 -> decoder.put(input, lengthSix(input, decoder));
                case 7 -> decoder.put(input, 8);
            }
        }

        return decoder;
    }

    private static int lengthFive(final String input, final Map<String, Integer> decoder) {
        final String valueForOne = CollectionUtils.getKeyByValue(decoder, 1).orElseThrow();
        final String valueForSeven = CollectionUtils.getKeyByValue(decoder, 7).orElseThrow();

        // If the input is a superset containing '1' and '7', value is '3'
        if (StringUtils.containsAll(input, valueForOne, valueForSeven)) {
            return 3;
        }

        final String valueForSix = CollectionUtils.getKeyByValue(decoder, 6).orElseThrow();
        
        // If the input is a subset of the value for '6', value is '5'
        if (StringUtils.containsAll(valueForSix, input)) {
            return 5;
        }

        // If the other values did not match, we can assume the value is '2'
        return 2;
    }

    private static int lengthSix(final String input, final Map<String, Integer> decoder) {
        final String valueForOne = CollectionUtils.getKeyByValue(decoder, 1).orElseThrow();
        final String valueForFour = CollectionUtils.getKeyByValue(decoder, 4).orElseThrow();
        final String valueForSeven = CollectionUtils.getKeyByValue(decoder, 7).orElseThrow();

        // If the input is a superset containing '1', '7' and '4', value is '9'
        if (StringUtils.containsAll(input, valueForOne, valueForFour, valueForSeven)) {
            return 9;
        }

        // If the input is a superset containing '1', and '7', value is '0'
        if (StringUtils.containsAll(input, valueForOne, valueForSeven)) {
            return 0;
        }

        // If the other values did not match, we can assume the value is '6'
        return 6;
    }

    // For the decoding, inputs of size 5 must be determined last
    // This is because the only way to differentiate a 2 and a 5 is by checking if one is a subset of 6 (5 is, 2 is not)
    private static List<String> sortInputsForDecoding(final List<String> inputs) {
        final List<String> orderedInputs = new ArrayList<>(inputs.size());

        for (final String input : inputs) {
            if (input.length() != INPUT_SIZE_THAT_MUST_BE_DECODED_LAST) {
                orderedInputs.add(input);
            }
        }

        for (final String input : inputs) {
            if (input.length() == INPUT_SIZE_THAT_MUST_BE_DECODED_LAST) {
                orderedInputs.add(input);
            }
        }

        return orderedInputs;
    }
}
