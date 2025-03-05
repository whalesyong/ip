import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeParser {

    //formats dates only
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


    //handles both date AND time, and just date if there is no time in the string
    public static CustomDateTime parseDateTimeString(String dateStr) throws DuncanException {
        dateStr = dateStr.trim();
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return new CustomDateTime(LocalDateTime.parse(dateStr, formatter));
            } catch (DateTimeParseException ignored) {
            }
        }

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return new CustomDateTime(LocalDate.parse(dateStr, formatter).atStartOfDay());
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new DuncanException("Invalid date/time format:" + dateStr);
    }

    public static CustomDate parseRelativeDate(String relativeStr) {
        LocalDate today = LocalDate.now();
        String lower = relativeStr.toLowerCase();

        return switch (lower) {
            case "today" -> new CustomDate(today.atStartOfDay());
            case "tomorrow" -> new CustomDate(today.plusDays(1).atStartOfDay());
            case "yesterday" -> new CustomDate(today.minusDays(1).atStartOfDay());
            case "day after tomorrow" -> new CustomDate(today.plusDays(2).atStartOfDay());
            case "day before yesterday" -> new CustomDate(today.minusDays(2).atStartOfDay());
            default -> new CustomDate(parseFlexibleWeekday(lower, today).atStartOfDay());
        };
    }

    // method to parse weekdays from a string e.g. "next monday"
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
