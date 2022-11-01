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
 * POJO defining a route between two locations, and the value of the distance between them.
 *
 * @param from  the start of the {@link Route}
 * @param to    the end of the {@link Route}
 * @param value the value of the distance between the start and end of the {@link Route}
 */
public record Route(String from, String to, int value) {

    /**
     * Creates a {@link Route} from a {@link String} in the format:
     * <pre>
     *     source to destination = 999
     * </pre>
     *
     * @param input the {@link String} to parse
     * @return the {@link Route}
     */
    public static Route parse(final String input) {
        final String[] tokens = StringUtils.splitOnWhitespace(input);
        final int routeValue = Integer.parseInt(tokens[4]);
        return new Route(tokens[0], tokens[2], routeValue);
    }
}
