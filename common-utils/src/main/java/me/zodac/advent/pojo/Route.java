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

import me.zodac.advent.util.StringUtils;

/**
 * POJO defining a route between a source and a target, and the value of the distance between them.
 *
 * @param from  the start of the {@link Route}
 * @param to    the end of the {@link Route}
 * @param value the value of the distance between the start and end of the {@link Route}
 */
public record Route(String from, String to, int value) {

    /**
     * Creates a {@link Route}.
     *
     * @param from  the start of the {@link Route}
     * @param to    the end of the {@link Route}
     * @param value the value of the distance between the start and end of the {@link Route}
     * @return the created {@link Route}
     */
    public static Route create(final String from, final String to, final int value) {
        return new Route(from, to, value);
    }

    /**
     * Creates a {@link Route} from a {@link String} in the format:
     * <pre>
     *     [source] to [destination] = [value]
     * </pre>
     *
     * @param input the {@link String} to parse
     * @return the {@link Route}
     */
    public static Route parseSourceDestination(final String input) {
        final String[] tokens = StringUtils.splitOnWhitespace(input);

        final String source = tokens[0];
        final String destination = tokens[2];
        final int value = Integer.parseInt(tokens[4]);

        return create(source, destination, value);
    }

    /**
     * Creates a {@link Route} from a {@link String} in the format:
     * <pre>
     *     [source] would gain/lose [value] happiness units by sitting next to [destination].
     * </pre>
     *
     * @param input the {@link String} to parse
     * @return the {@link Route}
     */
    public static Route parseSittingNextTo(final String input) {
        final String[] tokens = StringUtils.splitOnWhitespace(input);

        final String source = tokens[0];
        final String destination = StringUtils.removeLastCharacter(tokens[10]);
        final int routeValue = Integer.parseInt(tokens[3]);
        final int sign = "gain".equals(tokens[2]) ? 1 : -1;
        final int value = sign * routeValue;

        return create(source, destination, value);
    }
}
