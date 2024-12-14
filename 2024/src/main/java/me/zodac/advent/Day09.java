/*
 * BSD Zero Clause License
 *
 * Copyright (c) 2021-2024 zodac.me
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
import java.util.List;

/**
 * Solution for 2024, Day 9.
 *
 * @see <a href="https://adventofcode.com/2024/day/9">[2024: 9] Disk Fragmenter</a>
 */
public final class Day09 {

    private Day09() {

    }

    public static long part1(final String value) {
        final List<String> expanded = expand(value);
        return calculateChecksumPartialFileMovement(expanded);
    }

    private static long calculateChecksumPartialFileMovement(final List<String> input) {
        long total = 0L;
        int forwardPointer = 0;
        int reversePointer = input.size() - 1;

        while (forwardPointer <= reversePointer) {
            final String currentValue = input.get(forwardPointer);

            if (".".equals(currentValue)) {
                String reverseValue = input.get(reversePointer);
                final long update = Long.parseLong(reverseValue) * forwardPointer;
                total += update;

                do {
                    reversePointer--;
                    reverseValue = input.get(reversePointer);
                } while (".".equals(reverseValue));
            } else {
                final long update = Long.parseLong(currentValue) * forwardPointer;
                total += update;
            }

            forwardPointer++;
        }

        return total;
    }

    // TODO: Rather than expand, create list of int with gaps in the index, w/o . values?
    private static List<String> expand(final String input) {
        final List<String> output = new ArrayList<>();
        int index = 0;
        int value = 0;

        for (final char c : input.toCharArray()) {
            final int count = Character.getNumericValue(c);

            for (int i = 0; i < count; i++) {
                if (index % 2 == 0) {
                    output.add(String.valueOf(value));
                } else {
                    output.add(".");
                }
            }

            if (index % 2 == 0) {
                value++;
            }

            index++;
        }

        return output;
    }

    public static long part2(final String value) {
        final List<String> expanded = expand(value); // TODO: Rather than expand, create list of int with gaps in the index, w/o . values?
        final List<String> sorted = sort(expanded);
        return calculateChecksumFullFileMovement(sorted);
    }

    private record Block(String value, int count) {

        public boolean isGap() {
            return ".".equals(value);
        }
    }

    private static List<String> sort(final List<String> expanded) {
        final List<Block> fileBlocks = new ArrayList<>();

        String current = expanded.getFirst();
        int count = 1;
        for (int i = 1; i < expanded.size(); i++) {
            final String next = expanded.get(i);

            if (current.equals(next)) {
                count++;
            } else {
                fileBlocks.add(new Block(current, count));

                current = next;
                count = 1;
            }
        }
        fileBlocks.add(new Block(current, count)); // Add final value


        int reversePointer = fileBlocks.size() - 1;

        while (reversePointer > 0) {
            final Block lastBlock = fileBlocks.get(reversePointer);
            if (lastBlock.isGap()) {
                reversePointer--;
                continue;
            }

            final int fileBlockSize = lastBlock.count();
            for (int i = 0; i < reversePointer; i++) {
                final Block block = fileBlocks.get(i);
                if (!block.isGap()) {
                    continue;
                }

                if (block.count() == fileBlockSize) {
                    fileBlocks.remove(i);
                    fileBlocks.add(i, lastBlock);
                    fileBlocks.remove(reversePointer);
                    fileBlocks.add(reversePointer, new Block(".", fileBlockSize));
                    break;
                } else if (block.count() > fileBlockSize) {
                    fileBlocks.remove(i);
                    fileBlocks.add(i, new Block(".", block.count - fileBlockSize));
                    fileBlocks.add(i, lastBlock);
                    reversePointer++; // add 1 to pointer since we've increased the number of elements in the list
                    fileBlocks.remove(reversePointer);
                    fileBlocks.add(reversePointer, new Block(".", fileBlockSize));
                    break;
                }
            }

            reversePointer--;
        }

        final List<String> output = new ArrayList<>();

        for (final Block block : fileBlocks) {
            for (int i = 0; i < block.count(); i++) {
                output.add(block.value());
            }
        }

        return output;
    }

//    public static void main(String[] args) {
//        final String s = "00...111...2...333.44.5555.6666.777.888899";
//        final List<String> input = s.chars().mapToObj(i -> String.valueOf((char) i)).toList();
//
//        final String sorted = String.join("", sort(input));
//        System.out.println("Act: " + "00992111777.44.333....5555.6666.....8888..");
//        System.out.println("Exp: " + sorted);
//    }

    private static long calculateChecksumFullFileMovement(final List<String> sorted) {
        long total = 0L;

        for (int i = 0; i < sorted.size(); i++) {
            String s = sorted.get(i);
            if (".".equals(s)) {
                continue;
            }

            int value = Integer.parseInt(s);
            total += (long) value * i;
        }

        return total;
    }
}
