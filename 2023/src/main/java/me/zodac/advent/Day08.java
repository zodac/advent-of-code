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

package me.zodac.advent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.zodac.advent.util.MathUtils;

/**
 * Solution for 2023, Day 8.
 *
 * @see <a href="https://adventofcode.com/2023/day/8">[2023: 08] Haunted Wasteland</a>
 */
public final class Day08 {

    private Day08() {

    }

    private static final String START_NODE_NAME_PART_1 = "AAA";
    private static final String END_NODE_NAME_PART_1 = "ZZZ";
    private static final String START_NODE_SUFFIX_PART_2 = "A";
    private static final String END_NODE_SUFFIX_PART_2 = "Z";
    private static final Pattern NODE_PATTERN = Pattern.compile("(?<name>[12A-Z]{3}) = \\((?<left>[12A-Z]{3}), (?<right>[12A-Z]{3})\\)");

    private record Node(String name, String left, String right) {

    }

    /**
     * Part 1.
     *
     * @param values the input values
     * @return the part 1 result
     */
    public static long part1(final List<String> values) {
        final Map<String, Node> nodesByName = new HashMap<>();

        for (final String value : values.subList(2, values.size())) {
            final Matcher matcher = NODE_PATTERN.matcher(value);

            if (!matcher.find()) {
                throw new IllegalArgumentException("Unable to find match in input: " + value);
            }

            final String name = matcher.group("name");
            final String left = matcher.group("left");
            final String right = matcher.group("right");

            final Node node = new Node(name, left, right);
            nodesByName.put(node.name, node);
        }

        final String instructions = values.getFirst();
        final Node startNode = getNode(nodesByName, START_NODE_NAME_PART_1);

        return followInstructions(startNode, instructions, nodesByName);
    }

    private static int followInstructions(final Node startNode, final CharSequence instructions, final Map<String, Node> nodesByName) {
        Node current = startNode;
        int count = 0;

        while (!END_NODE_NAME_PART_1.equals(current.name)) {
            final int instructionIndex = count % instructions.length();
            final char currentInstruction = instructions.charAt(instructionIndex);
            current = currentInstruction == 'L' ? getNode(nodesByName, current.left) : getNode(nodesByName, current.right);
            count++;
        }
        return count;
    }

    private static int followInstructions2(final Node startNode, final CharSequence instructions, final Map<String, Node> nodesByName) {
        Node current = startNode;
        int count = 0;

        while (!current.name.endsWith(END_NODE_SUFFIX_PART_2)) {
            final int instructionIndex = count % instructions.length();
            final char currentInstruction = instructions.charAt(instructionIndex);
            current = currentInstruction == 'L' ? getNode(nodesByName, current.left) : getNode(nodesByName, current.right);
            count++;
        }
        return count;
    }

    private static Node getNode(final Map<String, Node> nodesByName, final String wantedNode) {
        if (!nodesByName.containsKey(wantedNode)) {
            throw new IllegalArgumentException(String.format("Unable to find node with name '%s'", wantedNode));
        }

        return nodesByName.get(wantedNode);
    }

    /**
     * Part 2.
     *
     * @param values the input values
     * @return the part 2 result
     */
    public static long part2(final List<String> values) {
        final Map<String, Node> nodesByName = new HashMap<>();
        final Collection<Node> startNodes = new ArrayList<>();

        for (final String value : values.subList(2, values.size())) {
            final Matcher matcher = NODE_PATTERN.matcher(value);

            if (!matcher.find()) {
                throw new IllegalArgumentException("Unable to find match in input: " + value);
            }

            final String name = matcher.group("name");
            final String left = matcher.group("left");
            final String right = matcher.group("right");

            final Node node = new Node(name, left, right);
            nodesByName.put(node.name, node);

            if (!node.name().isEmpty() && node.name().charAt(node.name().length() - 1) == 'A') {
                startNodes.add(node);
            }
        }

        final String instructions = values.getFirst();
        final Collection<Long> counts = new ArrayList<>();

        for (final Node startNode : startNodes) {
            final long countForNode = followInstructions2(startNode, instructions, nodesByName);
            counts.add(countForNode);
        }

        return MathUtils.lowestCommonMultiple(counts);
    }
}
