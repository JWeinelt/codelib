package de.codeblocksmc.codelib.api.util;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
    public static LocalDateTime addTimeSpan(String timeSpan) {
        LocalDateTime now = LocalDateTime.now();
        return addTimeSpan(now, timeSpan);
    }

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
                    dateTime = dateTime.plusWeeks(value); // Wochen in Tage umrechnen
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
                    throw new IllegalArgumentException("Ungültige Zeitspanne: " + unit);
            }
        }

        return dateTime;
    }
}