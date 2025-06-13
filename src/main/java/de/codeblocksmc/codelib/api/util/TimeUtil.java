package de.codeblocksmc.codelib.api.util;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
    public static LocalDateTime addTimeSpan(String timeSpan) {
        LocalDateTime now = LocalDateTime.now();
        return addTimeSpan(now, timeSpan);
    }

    /**
     * Adds a time span to a given {@link LocalDateTime}.
     * <p>
     * The time span is defined as a string containing multiple time units,
     * each consisting of a numeric value followed by a unit identifier.
     * Supported units are:
     * <ul>
     *     <li>{@code y} - years</li>
     *     <li>{@code M} - months</li>
     *     <li>{@code w} - weeks</li>
     *     <li>{@code d} - days</li>
     *     <li>{@code h} - hours</li>
     *     <li>{@code m} - minutes</li>
     *     <li>{@code s} - seconds</li>
     * </ul>
     * For example, {@code "1y2M3d4h"} adds 1 year, 2 months, 3 days and 4 hours to the given date.
     *
     * @param dateTime the initial {@link LocalDateTime} to which the time span will be added
     * @param timeSpan a string representation of the time span to add; must match the format {@code (\\d+)([yMwdhms])}
     * @return a new {@link LocalDateTime} with the specified time span added
     * @throws IllegalArgumentException if the time span contains an invalid unit
     */
    public static LocalDateTime addTimeSpan(LocalDateTime dateTime, String timeSpan) {
        Pattern pattern = Pattern.compile("(\\d+)([yMwdhms])");
        Matcher matcher = pattern.matcher(timeSpan);

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            char unit = matcher.group(2).charAt(0);

            switch (unit) {
                case 'y':
                    dateTime = dateTime.plusYears(value);
                    break;
                case 'M':
                    dateTime = dateTime.plusMonths(value);
                    break;
                case 'w':
                    dateTime = dateTime.plusWeeks(value);
                    break;
                case 'd':
                    dateTime = dateTime.plusDays(value);
                    break;
                case 'h':
                    dateTime = dateTime.plusHours(value);
                    break;
                case 'm':
                    dateTime = dateTime.plusMinutes(value);
                    break;
                case 's':
                    dateTime = dateTime.plusSeconds(value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time unit: " + unit);
            }
        }

        return dateTime;
    }
}