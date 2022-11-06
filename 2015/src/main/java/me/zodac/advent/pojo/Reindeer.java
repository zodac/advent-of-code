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

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple class representing a reindeer that can fly at a known velocity (in km/s) for a given {@link Duration}, then must rest (and not fly) for
 * another {@link Duration}.
 *
 * @param flyingVelocity the velocity of the {@link Reindeer} in km/s, when not resting
 * @param flyingDuration the {@link Duration} that the {@link Reindeer} can fly before resting
 * @param restDuration   the {@link Duration} that the {@link Reindeer} must rest before flying again
 */
public record Reindeer(int flyingVelocity, Duration flyingDuration, Duration restDuration) {

    // Regex pattern with named capture group
    private static final Pattern REINDEER_PATTERN = Pattern.compile(
        ".* can fly (?<velocity>\\d+) km/s for (?<flyingDuration>\\d+) seconds, but then must rest for (?<restDuration>\\d+) seconds.");

    /**
     * Creates a {@link Reindeer} from a {@link CharSequence} in the format:
     * <pre>
     *     [name] can fly [flyingVelocity] km/s for [flyingDuration] seconds, but then must rest for [restDuration] seconds.
     * </pre>
     *
     * @param input the {@link CharSequence} to parse
     * @return the {@link Reindeer}
     */
    public static Reindeer parse(final CharSequence input) {
        final Matcher matcher = REINDEER_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find match in input string: " + input);
        }

        final int flyingVelocity = Integer.parseInt(matcher.group("velocity"));
        final Duration flyingDuration = Duration.ofSeconds(Integer.parseInt(matcher.group("flyingDuration")));
        final Duration restDuration = Duration.ofSeconds(Integer.parseInt(matcher.group("restDuration")));

        return new Reindeer(flyingVelocity, flyingDuration, restDuration);
    }

    /**
     * Calculates the distance that the {@link Reindeer} can travel in the provided {@link Duration} of time, in KM.
     *
     * @param travelDuration the {@link Duration} the {@link Reindeer} will travel
     * @return the total distance covered, in KM
     */
    public long calculateDistance(final Duration travelDuration) {
        final long fullCycleDuration = flyingDuration.toSeconds() + restDuration.toSeconds();
        final long numberOfFullCycles = travelDuration.toSeconds() / fullCycleDuration;
        final long remainingSeconds = travelDuration.toSeconds() % fullCycleDuration;

        final long totalDistanceOfFullCycles = numberOfFullCycles * flyingVelocity * flyingDuration.toSeconds();

        // Remaining time might be a single second, might be almost the full flyingDuration and restDuration
        // Since we know it is only a partial cycle, it will only fly a maximum of flyingDuration
        final long remainingFlyingTime = Math.min(remainingSeconds, flyingDuration.toSeconds());
        final long distanceOfLastCycle = flyingVelocity * remainingFlyingTime;

        return totalDistanceOfFullCycles + distanceOfLastCycle;
    }
}
