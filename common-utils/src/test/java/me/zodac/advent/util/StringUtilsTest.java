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

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andMultipleValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "bcd", "def"};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringMatchesSuperString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdef"};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringIsSuperStringOfSuperString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdefghij"};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andNoSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = new String[0];

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andEmptySubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {""};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andBlankSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {" "};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenEmptySuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = "";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenBlankSuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = " ";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAllCharacters(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andOneValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "cde", "efg"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleInvalidSubStrings_andOneValidSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "ghi", "jkl", "mno"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andInvalidSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"ghi"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleInvalidSubStrings_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"ghi", "jkl", "mno"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andSubStringMatchesSuperString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdef"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andSubStringIsSuperStringOfSuperString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdefghij"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andNoSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = new String[0];

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andEmptySubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {""};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andBlankSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {" "};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenEmptySuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = "";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenBlankSuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = " ";
        final String[] subStrings = {"abc", "def"};

        final boolean result = StringUtils.containsAny(superString, subStrings);

        assertThat(result)
            .isFalse();
    }

    @Test
    void whenCountVowels_givenStringWithNoVowels_thenZeroIsReturned() {
        final String input = "bcdf";
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenCountVowels_givenStringWithMultipleVowels_thenCountIsReturned() {
        final String input = "abcdef";
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isEqualTo(2L);
    }

    @Test
    void whenCountVowels_givenStringWithRepeatedVowels_thenCombinedCountIsReturned() {
        final String input = "aabcdeef";
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isEqualTo(4L);
    }

    @Test
    void whenCountVowels_givenEmptyString_thenZeroIsReturned() {
        final String input = "";
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenCountVowels_givenBlankString_thenZeroIsReturned() {
        final String input = " ";
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenCountVowels_givenNullString_thenZeroIsReturned() {
        final String input = null;
        final long output = StringUtils.countVowels(input);
        assertThat(output)
            .isZero();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenStringWithSingleRepeat_thenTrueIsReturned() {
        final String input = "abccdef";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenStringWithMultipleRepeatsOfDifferentCharacters_thenTrueIsReturned() {
        final String input = "aabccdef";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenStringWithMultipleRepeatsOfSameCharacters_thenTrueIsReturned() {
        final String input = "aabcdefaa";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenStringWithSingleRepeatButNotInOrder_thenFalseIsReturned() {
        final String input = "abcdefa";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenStringWithNoRepeats_thenFalseIsReturned() {
        final String input = "abcdef";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenEmptyString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterInOrder_givenNullStringRepeats_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.hasRepeatedCharacterInOrder(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasSandwichCharacters_givenStringWithSingleSandwich_thenTrueIsReturned() {
        final String input = "aba";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasSandwichCharacters_givenStringWithSingleSandwichAtEndOfString_thenTrueIsReturned() {
        final String input = "abcdefgf";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasSandwichCharacters_givenStringWithMultipleSandwichs_thenTrueIsReturned() {
        final String input = "ababa";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasSandwichCharacters_givenStringWithSingleSandwichOfSameCharacters_thenTrueIsReturned() {
        final String input = "aaa";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasSandwichCharacters_givenStringWithNoRepeats_thenFalseIsReturned() {
        final String input = "abcdef";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasSandwichCharacters_givenEmptyString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasSandwichCharacters_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasSandwichCharacters_givenNullString_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.hasSandwichCharacters(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenStringWithPairAndNoOverlap_thenTrueIsReturned() {
        final String input = "abcdab";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenStringWithPairAndNoOtherCharacters_thenTrueIsReturned() {
        final String input = "abab";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenStringWithRepeatedCharacterPairAndNoOverlap_thenTrueIsReturned() {
        final String input = "aabcdaafgh";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenStringWithWithPairOverlapping_thenFalseIsReturned() {
        final String input = "aaa";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenStringWithNoPairs_thenFalseIsReturned() {
        final String input = "abcdef";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenEmptyString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasCharacterPairRepeatWithNoOverlap_givenNullString_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.hasCharacterPairRepeatWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }
}
