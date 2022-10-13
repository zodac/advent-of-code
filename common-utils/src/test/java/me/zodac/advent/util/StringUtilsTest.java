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

package me.zodac.advent.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link StringUtils}.
 */
class StringUtilsTest {

    @Test
    void whenSort_givenUnsortedString_thenSortedStringIsReturned() {
        final String input = "fedcba";
        final String output = StringUtils.sort(input);
        assertThat(output)
            .isEqualTo("abcdef");
    }

    @Test
    void whenSort_givenSortedString_thenStringIsReturnedWithoutChange() {
        final String input = "abcdef";
        final String output = StringUtils.sort(input);
        assertThat(output)
            .isEqualTo(input);
    }

    @Test
    void whenSort_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String output = StringUtils.sort(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenSort_givenBlankString_thenEmptyStringIsReturned() {
        final String input = " ";
        final String output = StringUtils.sort(input);
        assertThat(output)
            .isEqualTo(input)
            .isBlank();
    }

    @Test
    void whenSplitOnWhitespace_givenStringWithoutWhitespace_thenStringIsReturned() {
        final String input = "abc";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly(input);
    }

    @Test
    void whenSplitOnWhitespace_givenStringWithOneWhitespace_thenTwoStringsAreReturned() {
        final String input = "abc def";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(2)
            .containsExactly("abc", "def");
    }

    @Test
    void whenSplitOnWhitespace_givenStringWithOneWhitespaceOfMultipleSpaces_thenTwoStringsAreReturned() {
        final String input = "abc   def";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(2)
            .containsExactly("abc", "def");
    }

    @Test
    void whenSplitOnWhitespace_givenStringWithMultipleWhitespace_thenMultipleStringsAreReturned() {
        final String input = "a bc def ghij";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(4)
            .containsExactly("a", "bc", "def", "ghij");
    }

    @Test
    void whenSplitOnWhitespace_givenEmptyString_thenStringIsReturned() {
        final String input = "";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly(input);
    }

    @Test
    void whenSplitOnWhitespace_givenBlankString_thenEmptyStringIsReturned() {
        final String input = " ";
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly("");
    }

    @Test
    void whenContainsAll_givenValidSuperString_andValidSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andMultipleValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "bcd", "def"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringMatchesSuperString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdef"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringIsSuperStringOfSuperString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdefghij"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andNoSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = new String[0];

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andEmptySubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {""};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andBlankSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {" "};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenEmptySuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = "";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenBlankSuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = " ";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAll(superString, subStrings);

        assertThat(result)
            .isFalse();
    }
}
