/*
 * BSD Zero Clause License
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple representation of an element and its replacement.
 *
 * @param from the source element
 * @param to   the element it should be replaced with
 * @param <E>  the type of the element
 */
public record Replacement<E>(E from, E to) {

    private static final Pattern STRING_REPLACEMENT_PATTERN = Pattern.compile("(.*) => (.*)");

    /**
     * Creates a {@link String} {@link Replacement} from a {@link CharSequence} in the format:
     * <pre>
     *     [from] => [to]
     * </pre>
     *
     * @param input the {@link CharSequence} to parse
     * @return the {@link Replacement}
     */
    public static Replacement<String> parse(final CharSequence input) {
        final Matcher matcher = STRING_REPLACEMENT_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input: " + input);
        }

        final String from = matcher.group(1);
        final String to = matcher.group(2);

        return new Replacement<>(from, to);
    }
}
