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

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * POJO defining a line of syntax characters.
 */
public record SyntaxLine(String line) {

    private static final Pattern VALID_SYNTAX_LINE = Pattern.compile("[()\\[\\]{}<>]+");
    private static final Set<Character> VALID_OPEN_SYMBOLS = Set.of('(', '[', '{', '<');
    private static final Set<Character> VALID_CLOSE_SYMBOLS = Set.of(')', ']', '}', '>');
    private static final Map<Character, Character> CLOSE_SYMBOL_FOR_OPEN_SYMBOL = Map.of(
        '(', ')',
        '[', ']',
        '{', '}',
        '<', '>'
    );

    /**
     * Takes the input {@link String} and creates a {@link SyntaxLine}. The only characters permitted are:
     * <ul>
     *     <li>(</li>
     *     <li>}</li>
     *     <li>[</li>
     *     <li>]</li>
     *     <li>{</li>
     *     <li>}</li>
     *     <li>{@literal <}</li>
     *     <li>{@literal >}</li>
     * </ul>
     *
     * @param inputLine the input {@link String} to create into a {@link SyntaxLine}
     * @return the created {@link SyntaxLine}
     * @throws IllegalArgumentException thrown if the input {@link String} contains an invalid character
     */
    public static SyntaxLine create(final String inputLine) {
        if (!VALID_SYNTAX_LINE.matcher(inputLine).matches()) {
            throw new IllegalArgumentException(String.format("Invalid input: '%s'", inputLine));
        }

        return new SyntaxLine(inputLine);
    }

    /**
     * Evaluates the {@link SyntaxLine} for validity. An invalid {@link SyntaxLine} will have a close symbol appear in order before another expected
     * close symbol. For example, given the input:
     * <pre>
     *     [[(({{)
     * </pre>
     *
     * <p>
     * We would expect the first close symbol to appear to be <b>}</b>, as <b>{</b> was the last open symbol listed. Instead we find <b>)</b>. Note
     * that a {@link SyntaxLine} that has no invalid close symbols may be incomplete, but is considered valid.
     *
     * <p>
     * Based on whether there is an invalid close symbol, we can return one of two {@link Pair}s:
     * <ul>
     *     <li>For an invalid {@link SyntaxLine}, a {@link Pair} of the first invalid close symbol and an empty {@link Stack}</li>
     *     <li>For a valid {@link SyntaxLine}, a {@link Pair} of the <b>'0'</b> character and a {@link Stack} of the remaining open characters</li>
     * </ul>
     *
     * @return the {@link Pair} for either invalid or valid {@link SyntaxLine}
     */
    public Pair<Character, Stack<Character>> evaluateSyntaxErrorScoreForLine() {
        final Stack<Character> openSymbols = new Stack<>();

        for (final char syntaxSymbol : symbols()) {
            if (SyntaxLine.isValidOpenSymbol(syntaxSymbol)) {
                openSymbols.push(syntaxSymbol);
            } else if (SyntaxLine.isValidCloseSymbol(syntaxSymbol)) {
                final char nextOpenSymbol = openSymbols.pop();
                final char expectedCloseSymbol = SyntaxLine.getMatchingCloseSymbol(nextOpenSymbol);

                if (expectedCloseSymbol != syntaxSymbol) {
                    return Pair.of(syntaxSymbol, new Stack<>());
                }
            }
        }

        return Pair.of('0', openSymbols);
    }

    /**
     * Returns the symbols in the {@link SyntaxLine}.
     *
     * @return the {@link SyntaxLine} symbols as a {@code char[]}
     */
    public char[] symbols() {
        return line.toCharArray();
    }

    /**
     * Checks if the provided input symbol is a valid opening symbol.
     *
     * @param inputSymbol the symbol to check
     * @return <code>true</code> if the input is a valid opening symbol
     */
    public static boolean isValidOpenSymbol(final char inputSymbol) {
        return VALID_OPEN_SYMBOLS.contains(inputSymbol);
    }

    /**
     * Checks if the provided input symbol is a valid close symbol.
     *
     * @param inputSymbol the symbol to check
     * @return <code>true</code> if the input is a valid close symbol
     */
    public static boolean isValidCloseSymbol(final char inputSymbol) {
        return VALID_CLOSE_SYMBOLS.contains(inputSymbol);
    }

    /**
     * Gets the matching close symbol for the provided open symbol.
     *
     * @param openSymbol the symbol for which the close symbol should be found
     * @return the matching close symbol
     * @throws IllegalArgumentException thrown if the input open symbol has no matching close symbol
     */
    public static char getMatchingCloseSymbol(final char openSymbol) {
        if (!CLOSE_SYMBOL_FOR_OPEN_SYMBOL.containsKey(openSymbol)) {
            throw new IllegalArgumentException(String.format("No close symbol found for input: '%s'", openSymbol));
        }

        return CLOSE_SYMBOL_FOR_OPEN_SYMBOL.get(openSymbol);
    }
}
