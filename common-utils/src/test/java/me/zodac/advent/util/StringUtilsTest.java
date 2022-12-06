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

package me.zodac.advent.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import me.zodac.advent.pojo.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link StringUtils}.
 */
class StringUtilsTest {

    @Test
    void whenBisect_givenStringOfEvenLength_thenPairOfHalvesIsReturned() {
        final String input = "abcdef";
        final Pair<String, String> output = StringUtils.bisect(input);
        assertThat(output)
            .isEqualTo(Pair.of("abc", "def"));
    }

    @Test
    void whenBisect_givenBlankStringOfEvenLength_thenPairOfHalvesIsReturned() {
        final String input = "    ";
        final Pair<String, String> output = StringUtils.bisect(input);
        assertThat(output)
            .isEqualTo(Pair.of("  ", "  "));
    }

    @Test
    void whenBisect_givenStringOfOddLength_thenExceptionIsThrown() {
        final String input = "abcdefg";
        assertThatThrownBy(() -> StringUtils.bisect(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cannot bisect input of length: 7");
    }

    @Test
    void whenBisect_givenEmptyStringOfOddLength_thenExceptionIsThrown() {
        final String input = "     ";
        assertThatThrownBy(() -> StringUtils.bisect(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cannot bisect input of length: 5");
    }

    @Test
    void whenBisect_givenEmptyString_thenExceptionIsThrown() {
        final String input = "";
        assertThatThrownBy(() -> StringUtils.bisect(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Input cannot be null or empty");
    }

    @Test
    void whenBisect_givenNullString_thenExceptionIsThrown() {
        final String input = null;
        assertThatThrownBy(() -> StringUtils.bisect(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Input cannot be null or empty");
    }

    @Test
    void whenCollectNumbersInOrder_givenSingleIntegerAsString_thenSingleValueIsReturned() {
        final String input = "1";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .containsExactly(1);
    }

    @Test
    void whenCollectNumbersInOrder_givenMultipleIntegersAsString_thenAllValuesAreReturned() {
        final String input = "1 23 456";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .containsExactly(1, 23, 456);
    }

    @Test
    void whenCollectNumbersInOrder_givenNegativeInteger_thenAllValuesIncludingNegativesAreReturned() {
        final String input = "1 -23 456";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .containsExactly(1, -23, 456);
    }

    @Test
    void whenCollectNumbersInOrder_givenMultipleIntegersAndWordsAsString_thenAllIntegerValuesAreReturned() {
        final String input = "1 and 23 and 456";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .containsExactly(1, 23, 456);
    }

    @Test
    void whenCollectNumbersInOrder_givenMultipleIntegersAndLongAsString_thenLongValueIsNotReturned() {
        final String input = "1 23 456 9999999999999999";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .containsExactly(1, 23, 456);
    }

    @Test
    void whenCollectNumbersInOrder_givenNoIntegersInString_thenEmptyListIsReturned() {
        final String input = "No numbers here!";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCollectNumbersInOrder_givenEmptyString_thenEmptyListIsReturned() {
        final String input = "";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCollectNumbersInOrder_givenBlankString_thenEmptyListIsReturned() {
        final String input = " ";
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCollectNumbersInOrder_givenNullString_thenEmptyListIsReturned() {
        final String input = null;
        final List<Integer> output = StringUtils.collectIntegersInOrder(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCommonChars_givenTwoStrings_andBothHasSingleCommonCharacter_thenCommonCharacterIsReturned() {
        final String first = "abcd";
        final String second = "aefg";

        final Set<Character> output = StringUtils.commonChars(first, second);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder('a');
    }

    @Test
    void whenCommonChars_givenThreeStrings_andEachHasSingleCommonCharacter_thenCommonCharacterIsReturned() {
        final String first = "abcd";
        final String second = "aefg";
        final String third = "ahij";

        final Set<Character> output = StringUtils.commonChars(first, second, third);
        assertThat(output)
            .hasSize(1)
            .containsExactlyInAnyOrder('a');
    }

    @Test
    void whenCommonChars_givenThreeStrings_andEachHasMultipleCommonCharacters_thenCommonCharactersAreReturned() {
        final String first = "abcdz";
        final String second = "aefgz";
        final String third = "ahijz";

        final Set<Character> output = StringUtils.commonChars(first, second, third);
        assertThat(output)
            .hasSize(2)
            .containsExactlyInAnyOrder('a', 'z');
    }

    @Test
    void whenCommonChars_givenThreeStrings_andThereAreNoCommonCharacters_thenEmptySetIsReturned() {
        final String first = "abc";
        final String second = "def";
        final String third = "ghi";

        final Set<Character> output = StringUtils.commonChars(first, second, third);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenCommonChars_givenOneString_thenExceptionIsThrownReturned() {
        final String first = "abc";

        assertThatThrownBy(() -> StringUtils.commonChars(first))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Must have at least two strings to compare");
    }

    @Test
    void whenCommonChars_givenEmptyStringInput_thenExceptionIsThrown() {
        final String first = "abc";
        final String second = "def";
        final String third = "ghi";
        final String fourth = "";
        final String fifth = "jkl";

        assertThatThrownBy(() -> StringUtils.commonChars(first, second, third, fourth, fifth))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null or blank");
    }

    @Test
    void whenCommonChars_givenBlankStringInput_thenExceptionIsThrown() {
        final String first = "abc";
        final String second = "def";
        final String third = "ghi";
        final String fourth = " ";
        final String fifth = "jkl";

        assertThatThrownBy(() -> StringUtils.commonChars(first, second, third, fourth, fifth))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null or blank");
    }

    @Test
    void whenCommonChars_givenNullStringInput_thenExceptionIsThrown() {
        final String first = "abc";
        final String second = "def";
        final String third = "ghi";
        final String fourth = null;
        final String fifth = "jkl";

        assertThatThrownBy(() -> StringUtils.commonChars(first, second, third, fourth, fifth))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input cannot be null or blank");
    }

    @Test
    void whenContainsAll_givenValidSuperString_andValidSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andMultipleValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "bcd", "def"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringMatchesSuperString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdef"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andSubStringIsSuperStringOfSuperString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdefghij"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andNoSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andEmptySubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {""};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAll_givenValidSuperString_andBlankSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {" "};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenEmptySuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = "";
        final String[] subStrings = {"abc", "def"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAll_givenBlankSuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = " ";
        final String[] subStrings = {"abc", "def"};

        final boolean output = StringUtils.containsAllCharacters(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andOneValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleValidSubStrings_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "cde", "efg"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleInvalidSubStrings_andOneValidSubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abc", "ghi", "jkl", "mno"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andInvalidSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"ghi"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andMultipleInvalidSubStrings_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"ghi", "jkl", "mno"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andSubStringMatchesSuperString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdef"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andSubStringIsSuperStringOfSuperString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {"abcdefghij"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andNoSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andEmptySubString_thenTrueIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {""};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsAny_givenValidSuperString_andBlankSubString_thenFalseIsReturned() {
        final String superString = "abcdef";
        final String[] subStrings = {" "};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenEmptySuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = "";
        final String[] subStrings = {"abc", "def"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsAny_givenBlankSuperString_andValidSubString_thenFalseIsReturned() {
        final String superString = " ";
        final String[] subStrings = {"abc", "def"};

        final boolean output = StringUtils.containsAny(superString, subStrings);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenStringWithDuplicates_thenTrueIsReturned() {
        final String input = "abca";
        final boolean output = StringUtils.containsDuplicates(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenContainsDuplicates_givenStringWithNoDuplicates_thenFalseIsReturned() {
        final String input = "abc";
        final boolean output = StringUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenEmptyString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.containsDuplicates(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenContainsDuplicates_givenNullString_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.containsDuplicates(input);
        assertThat(output)
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
    void whenFindFullyFirstUpperCaseWord_givenStringWithSingleUpperCaseWord_thenWordIsReturned() {
        final String input = "THIS is uppercase";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isPresent()
            .hasValue("THIS");
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenOnlySingleUpperCaseWord_thenInputIsReturned() {
        final String input = "THIS";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isPresent()
            .hasValue(input);
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenMultipleUpperCaseWord_thenFirstWordIsReturned() {
        final String input = "THIS is UPPERCASE";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isPresent()
            .hasValue("THIS");
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenAllLowerCaseWord_thenEmptyOptionalIsThrown() {
        final String input = "this is not uppercase";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenNoFullyUpperCaseWord_thenEmptyOptionalIsThrown() {
        final String input = "This Is Not Uppercase";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenEmptyString_thenEmptyOptionalIsThrown() {
        final String input = "";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenBlankString_thenEmptyOptionalIsThrown() {
        final String input = " ";
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenFindFullyFirstUpperCaseWord_givenNullString_thenEmptyOptionalIsThrown() {
        final String input = null;
        final Optional<String> output = StringUtils.findFirstFullyUpperCaseWord(input);
        assertThat(output)
            .isEmpty();
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
    void whenHasRepeatedCharacterPairWithNoOverlap_givenStringWithPairAndNoOverlap_thenTrueIsReturned() {
        final String input = "abcdab";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenStringWithPairAndNoOtherCharacters_thenTrueIsReturned() {
        final String input = "abab";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenStringWithRepeatedCharacterPairAndNoOverlap_thenTrueIsReturned() {
        final String input = "aabcdaafgh";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenStringWithWithPairOverlapping_thenFalseIsReturned() {
        final String input = "aaa";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenStringWithNoPairs_thenFalseIsReturned() {
        final String input = "abcdef";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenEmptyString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenHasRepeatedCharacterPairWithNoOverlap_givenNullString_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.hasRepeatedCharacterPairWithNoOverlap(input);
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
    void whenIsInteger_givenValidIntegerString_thenTrueIsReturned() {
        final String input = "123";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsInteger_givenValidNegativeIntegerString_thenTrueIsReturned() {
        final String input = "-123";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isTrue();
    }

    @Test
    void whenIsInteger_givenValidLongString_thenFalseIsReturned() {
        final String input = "9999999999999999";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenNegativeLongString_thenFalseIsReturned() {
        final String input = "-9999999999999999";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenExcessivelyLargeString_thenFalseIsReturned() {
        final String input = "99999999999999999999999999999999";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenValidFloatString_thenFalseIsReturned() {
        final String input = "3.14";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenInvalidNumericString_thenFalseIsReturned() {
        final String input = "abc";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenEmptyNumericString_thenFalseIsReturned() {
        final String input = "";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenBlankString_thenFalseIsReturned() {
        final String input = " ";
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenIsInteger_givenNullString_thenFalseIsReturned() {
        final String input = null;
        final boolean output = StringUtils.isInteger(input);
        assertThat(output)
            .isFalse();
    }

    @Test
    void whenLookAndSay_givenValidInput_thenSequenceIsApplied() {
        final String input = "132211";
        final String output = StringUtils.lookAndSay(input);
        assertThat(output)
            .isEqualTo("11132221");
    }

    @Test
    void whenLookAndSay_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String output = StringUtils.lookAndSay(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenLookAndSay_givenBlankString_thenEmptyStringIsReturned() {
        final String input = " ";
        final String output = StringUtils.lookAndSay(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenLookAndSay_givenNullString_thenEmptyStringIsReturned() {
        final String input = null;
        final String output = StringUtils.lookAndSay(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenRemoveLastCharacter_givenString_thenStringIsReturnedWithoutLastCharacter() {
        final String input = "abc";
        final String output = StringUtils.removeLastCharacter(input);
        assertThat(output)
            .isEqualTo("ab");
    }

    @Test
    void whenRemoveLastCharacter_givenBlankStringWithMultipleSpaces_thenStringIsReturnedWithoutLastCharacter() {
        final String input = "  ";
        final String output = StringUtils.removeLastCharacter(input);
        assertThat(output)
            .isEqualTo(" ");
    }

    @Test
    void whenRemoveLastCharacter_givenStringWithOneCharacter_thenEmptyStringIsReturned() {
        final String input = "a";
        final String output = StringUtils.removeLastCharacter(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenRemoveLastCharacter_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String output = StringUtils.removeLastCharacter(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenRemoveLastCharacter_givenNullString_thenEmptyStringIsReturned() {
        final String input = null;
        final String output = StringUtils.removeLastCharacter(input);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenRemoveLastCharacters_givenString_andRemovingTwoCharacters_thenStringIsReturnedWithoutLastTwoCharacters() {
        final String input = "abcd";
        final String output = StringUtils.removeLastCharacters(input, 2);
        assertThat(output)
            .isEqualTo("ab");
    }

    @Test
    void whenRemoveLastCharacters_givenStringWithBlankSpaces_andRemovingTwoCharacters_thenStringIsReturnedWithoutTwoLastCharacters() {
        final String input = "   ";
        final String output = StringUtils.removeLastCharacters(input, 2);
        assertThat(output)
            .isEqualTo(" ");
    }

    @Test
    void whenRemoveLastCharacters_givenString_andRemovingNegativeCharacters_thenExceptionIsThrown() {
        final String input = "   ";
        assertThatThrownBy(() -> StringUtils.removeLastCharacters(input, -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Must remove at least 1 character, found: -1");
    }

    @Test
    void whenRemoveLastCharacters_givenStringWithOneCharacter_andRemovingTwoCharacters_thenExceptionIsThrown() {
        final String input = "a";
        assertThatThrownBy(() -> StringUtils.removeLastCharacters(input, 2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cannot remove 2 characters from input of length: 1");
    }

    @Test
    void whenRemoveLastCharacters_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String output = StringUtils.removeLastCharacters(input, 2);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenRemoveLastCharacters_givenNullString_thenEmptyStringIsReturned() {
        final String input = null;
        final String output = StringUtils.removeLastCharacters(input, 2);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReplaceAtIndex_givenStringWithSingleSubString_andSubStringPositionIsKnown_thenSubStringIsReplaced() {
        final String input = "abcdef";
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 3);
        assertThat(output)
            .isEqualTo("abcxxx");
    }

    @Test
    void whenReplaceAtIndex_givenStringWithMultipleSubString_andFirstSubStringPositionIsKnown_thenSubStringIsReplaced() {
        final String input = "abcdefghidefjkl";
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 3);
        assertThat(output)
            .isEqualTo("abcxxxghidefjkl");
    }

    @Test
    void whenReplaceAtIndex_givenStringWithMultipleSubString_andSecondSubStringPositionIsKnown_thenSubStringIsReplaced() {
        final String input = "abcdefghidefjkl";
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 9);
        assertThat(output)
            .isEqualTo("abcdefghixxxjkl");
    }

    @Test
    void whenReplaceAtIndex_givenNegativeIndex_thenExceptionIsThrown() {
        final String input = "abcdefghi";
        assertThatThrownBy(() -> StringUtils.replaceAtIndex(input, "def", "xxx", -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected index to be between 0 and 6, found: -1");
    }

    @Test
    void whenReplaceAtIndex_givenOutOfBoundsIndex_thenExceptionIsThrown() {
        final String input = "abcdefghi";
        assertThatThrownBy(() -> StringUtils.replaceAtIndex(input, "def", "xxx", 7))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected index to be between 0 and 6, found: 7");
    }

    @Test
    void whenReplaceAtIndex_givenIncorrectIndex_thenExceptionIsThrown() {
        final String input = "abcdefghi";
        assertThatThrownBy(() -> StringUtils.replaceAtIndex(input, "def", "xxx", 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected to find subString 'def' at index 0, instead found: 'abc'");
    }

    @Test
    void whenReplaceAtIndex_givenSubStringDoesNotExist_thenExceptionIsThrown() {
        final String input = "abcdefghi";
        assertThatThrownBy(() -> StringUtils.replaceAtIndex(input, "yyy", "xxx", 3))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Expected to find subString 'yyy' at index 3, instead found: 'def'");
    }

    @Test
    void whenReplaceAtIndex_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 3);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReplaceAtIndex_givenBlankString_thenEmptyStringIsReturned() {
        final String input = " ";
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 3);
        assertThat(output)
            .isEmpty();
    }

    @Test
    void whenReplaceAtIndex_givenNullString_thenEmptyStringIsReturned() {
        final String input = null;
        final String output = StringUtils.replaceAtIndex(input, "def", "xxx", 3);
        assertThat(output)
            .isEmpty();
    }

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
    void whenSplitOnNewLines_givenStringWithThreeLines_thenThreeStringsAreReturned() {
        final String input = """
            line1
            line2
            line3""";
        final String[] output = StringUtils.splitOnNewLines(input);
        assertThat(output)
            .hasSize(3)
            .containsExactly("line1", "line2", "line3");
    }

    @Test
    void whenSplitOnNewLines_givenStringWith1Line_thenInputStringIsReturned() {
        final String input = "line1";
        final String[] output = StringUtils.splitOnNewLines(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly(input);
    }

    @Test
    void whenSplitOnNewLines_givenEmptyString_thenEmptyStringIsReturned() {
        final String input = "";
        final String[] output = StringUtils.splitOnNewLines(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly(input);
    }

    @Test
    void whenSplitOnNewLines_givenBlankString_thenBlankStringIsReturned() {
        final String input = " ";
        final String[] output = StringUtils.splitOnNewLines(input);
        assertThat(output)
            .hasSize(1)
            .containsExactly(" ");
    }

    @Test
    void whenSplitOnNewLines_givenNullString_thenEmptyStringArrayIsReturned() {
        final String input = null;
        final String[] output = StringUtils.splitOnNewLines(input);
        assertThat(output)
            .isEmpty();
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
    void whenSplitOnWhitespace_givenEmptyString_thenEmptyStringIsReturned() {
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
    void whenSplitOnWhitespace_givenNullString_thenEmptyStringArrayIsReturned() {
        final String input = null;
        final String[] output = StringUtils.splitOnWhitespace(input);
        assertThat(output)
            .isEmpty();
    }
}
