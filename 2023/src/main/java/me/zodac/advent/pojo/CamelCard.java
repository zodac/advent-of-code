/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2023 zodac.me
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import me.zodac.advent.util.StringUtils;

/**
 * Class defining a game of Camel Cards, where each game includes a hand of cards, a wager, and some definition of the game.
 *
 * @param type
 * @param hand
 * @param wager
 * @param withJokers
 */
public record CamelCard(Type type, String hand, long wager, boolean withJokers) implements Comparable<CamelCard> {

    private static final int NUMBER_OF_CARDS_IN_HAND = 5;
    private static final Map<Character, Integer> VALUE_FOR_FACE_CARD = Map.of(
        'A', 14,
        'K', 13,
        'Q', 12,
        'T', 10
    );
    private static final Pattern JOKER_MATCHER = Pattern.compile("J");

    public enum Type {
        FIVE_OF_A_KIND(6, List.of(5L)),
        FOUR_OF_A_KIND(5, List.of(4L, 1L)),
        FULL_HOUSE(4, List.of(3L, 2L)),
        THREE_OF_A_KIND(3, List.of(3L, 1L, 1L)),
        TWO_PAIR(2, List.of(2L, 2L, 1L)),
        ONE_PAIR(1, List.of(2L, 1L, 1L, 1L)),
        HIGH_CARD(0, List.of(1L, 1L, 1L, 1L, 1L));

        private final int strength;
        private final List<Long> expectedCardFrequencies;

        Type(final int strength, final List<Long> expectedCardFrequencies) {
            this.strength = strength;
            this.expectedCardFrequencies = expectedCardFrequencies;
        }

        public static Type getByHand(final String hand) {
            final List<Long> cardFrequencies = StringUtils.characterFrequency(hand);

            return Arrays.stream(values())
                .filter(type -> type.expectedCardFrequencies.equals(cardFrequencies))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid %s: '%s'", Type.class.getSimpleName(), hand)));
        }
    }

    public static CamelCard parse(final String input, final boolean withJokers) {
        final String[] inputTokens = StringUtils.splitOnWhitespace(input);
        final String hand = inputTokens[0].trim();
        final char mostOccurringCard = StringUtils.mostOccurringCharacter(hand);

        if (withJokers) {
            if (hand.equals("JJJJJ")) {
                return new CamelCard(Type.FIVE_OF_A_KIND, hand, Long.parseLong(inputTokens[1]), true);
            } else if (hand.contains("J")) {
                if (mostOccurringCard == 'J') {
                    final char secondMostOccurringCard = StringUtils.mostOccurringCharacter(JOKER_MATCHER.matcher(hand).replaceAll(""));
                    final String handWithoutJokers = JOKER_MATCHER.matcher(hand).replaceAll(String.valueOf(secondMostOccurringCard));
                    return new CamelCard(Type.getByHand(handWithoutJokers), hand, Long.parseLong(inputTokens[1]), true);
                } else {
                    final String handWithoutJokers = JOKER_MATCHER.matcher(hand).replaceAll(String.valueOf(mostOccurringCard));
                    return new CamelCard(Type.getByHand(handWithoutJokers), hand, Long.parseLong(inputTokens[1]), true);
                }
            }
        }

        return new CamelCard(Type.getByHand(hand), hand, Long.parseLong(inputTokens[1]), withJokers);
    }

    @Override
    public int compareTo(final CamelCard other) {
        if (type != other.type) {
            return Integer.compare(type.strength, other.type.strength);
        }

        // Same hand type, compare card by card
        for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++) {
            final char thisHandCard = hand.charAt(i);
            final char otherHandCard = other.hand.charAt(i);

            if (thisHandCard == otherHandCard) {
                continue;
            }

            return Integer.compare(getValueOfCard(thisHandCard), getValueOfCard(otherHandCard));
        }

        // Equal hands
        return 0;
    }

    private int getValueOfCard(final char card) {
        if (withJokers && card == 'J') {
            return 1;
        } else if (card == 'J') {
            return 11;
        } else {
            return VALUE_FOR_FACE_CARD.getOrDefault(card, Character.getNumericValue(card));
        }
    }
}
