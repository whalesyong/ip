import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for parsing date and time strings in various formats.
 * Supports flexible parsing of both exact and relative date-time strings.
 */

public class DateTimeParser {

    //formats dates only
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("M d yyyy"),
            DateTimeFormatter.ofPattern("d M yyyy"),
            DateTimeFormatter.ofPattern("d M yy"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yy"),
            DateTimeFormatter.ofPattern("M-dd-yyyy"),
            DateTimeFormatter.ofPattern("d-MM-yyyy"),
            DateTimeFormatter.ofPattern("d-MM-yy")
    );

    //formats time AND date
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("M d yyyy HHmm"),
            DateTimeFormatter.ofPattern("d M yyyy HHmm"),
            DateTimeFormatter.ofPattern("d M yy HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yy HHmm"),
            DateTimeFormatter.ofPattern("M-dd-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-MM-yy HHmm")
    );

    // List of recognized relative date terms
    private static final List<String> RELATIVE_DATE_TERMS = Arrays.asList(
            "today", "tomorrow",  "day after tomorrow",
            "next", "this", "monday", "tuesday", "wednesday",
            "thursday", "friday", "saturday", "sunday"
    );

    // Define end of day time (23:59:59.999999999)
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59, 999999999);

    // Pattern to match 24-hour time format (e.g., 1400, 0900)
    private static final Pattern TIME_PATTERN = Pattern.compile("\\s(\\d{3,4})$");

    /**
     * Parses a date-time string, returning a {@link CustomDateTime} instance.
     * This method first checks if the string contains relative date terms,
     * then attempts to parse with date-time formatters, and finally with date-only formatters.
     *
     * @param dateStr The date-time string to be parsed.
     * @return A {@link CustomDateTime} object representing the parsed date-time.
     * @throws DuncanException If the input string cannot be parsed into a valid date or date-time.
     */
    public static CustomDateTime parseDateTimeString(String dateStr) throws DuncanException {
        dateStr = dateStr.trim();

        // Check if the input contains any relative date terms
        String lowerCaseStr = dateStr.toLowerCase();
        if (RELATIVE_DATE_TERMS.stream().anyMatch(lowerCaseStr::contains)) {
            try {
                // Try to parse as a relative date
                return parseRelativeDate(dateStr);
            } catch (IllegalArgumentException ignored) {
                // If parsing as a relative date fails, continue with other methods
            }
        }
        // Try to parse with date-time formatters
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return new CustomDateTime(LocalDateTime.parse(dateStr, formatter));
            } catch (DateTimeParseException ignored) {
            }
        }
        // Try to parse with date-only formatters
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                // For date-only formats, set the time to end of day
                LocalDate date = LocalDate.parse(dateStr, formatter);
                return new CustomDateTime(LocalDateTime.of(date, END_OF_DAY));
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DuncanException(ErrorCode.INVALID_DATE_ERR, dateStr);
    }

    /**
     * Parses a relative date string (e.g., "today", "tomorrow", "next monday") and returns the corresponding {@link CustomDateTime}.
     * If a time component is provided (e.g., "tomorrow 1400"), that time is used; otherwise, the time is set to end of day.
     *
     * @param relativeStr The relative date string (e.g., "today", "tomorrow 1400", "next monday").
     * @return A {@link CustomDateTime} representing the parsed relative date with the specified time or end of day.
     */
    public static CustomDateTime parseRelativeDate(String relativeStr) {
        LocalDate today = LocalDate.now();
        String lower = relativeStr.toLowerCase();

        // Extract time component if present (e.g., "1400" from "tomorrow 1400")
        LocalTime timeComponent = END_OF_DAY;
        Matcher timeMatcher = TIME_PATTERN.matcher(relativeStr);
        String datePartOnly = relativeStr;

        if (timeMatcher.find()) {
            String timeStr = timeMatcher.group(1);
            // Parse time based on length (3 or 4 digits)
            if (timeStr.length() == 3) {
                // Format: "100" -> "01:00"
                timeComponent = LocalTime.of(Integer.parseInt(timeStr.substring(0, 1)),
                        Integer.parseInt(timeStr.substring(1)));
            } else if (timeStr.length() == 4) {
                // Format: "1400" -> "14:00"
                timeComponent = LocalTime.of(Integer.parseInt(timeStr.substring(0, 2)),
                        Integer.parseInt(timeStr.substring(2)));
            }
            // Remove the time part from the date string for further processing
            datePartOnly = relativeStr.substring(0, timeMatcher.start()).trim();
            lower = datePartOnly.toLowerCase();
        }

        LocalDate resultDate = switch (lower) {
            case "today" -> today;
            case "tomorrow" -> today.plusDays(1);
            case "day after tomorrow" -> today.plusDays(2);
            default -> parseFlexibleWeekday(lower, today);
        };

        // Return the date with the specified time or end of day
        return new CustomDateTime(LocalDateTime.of(resultDate, timeComponent));
    }

    /**
     * Parses a string representing a weekday (e.g., "next monday") and returns the corresponding {@link LocalDate}.
     *
     * @param dayStr The weekday string to be parsed (e.g., "next monday").
     * @param referenceDate The date from which the calculation should be made.
     * @return A {@link LocalDate} representing the next or current weekday based on the string.
     * @throws IllegalArgumentException If the string does not represent a valid weekday.
     */
    private static LocalDate parseFlexibleWeekday(String dayStr, LocalDate referenceDate) {
        boolean isNext = dayStr.contains("next");
        boolean isThis = dayStr.contains("this");

        for (DayOfWeek day : DayOfWeek.values()) {
            if (dayStr.toLowerCase().contains(day.toString().toLowerCase())) {
                int daysUntil = (day.getValue() - referenceDate.getDayOfWeek().getValue() + 7) % 7;

                if (isNext) {
                    daysUntil += 7;
                } else if (isThis && daysUntil == 0) {
                    return referenceDate; // happens if the day string after "this" is the current day
                }

                return referenceDate.plusDays(daysUntil == 0 ? 7 : daysUntil);
            }
        }
        throw new IllegalArgumentException("Unrecognized date description: " + dayStr);
    }
}