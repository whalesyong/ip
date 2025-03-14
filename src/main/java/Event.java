import java.time.LocalDateTime;
/**
 * Represents an event with a start time and end time. Inherits from {@link Task}.
 * Provides functionality for setting, parsing, and displaying event details.
 */
public class Event extends Task {
    // Constant for time format
    private static final String TIME_RANGE_FORMAT = " (from: %s to: %s)";

    private CustomDateTime from;
    private CustomDateTime to;

    //getters and setters
    public CustomDateTime getFrom() { return from; }
    public CustomDateTime getTo() { return to; }
    public void setFrom(CustomDateTime from) { this.from = from; }
    public void setTo(CustomDateTime to) { this.to = to; }


    // Constructor with only description (parses /from and /to)
    /**
     * Constructs an {@link Event} with the specified description, and parses the start and end times.
     *
     * @param description The description of the event, including "/from" and "/to" fields.
     * @throws DuncanException If there are errors in parsing or invalid fields.
     */
    public Event(String description) throws DuncanException {
        super(description);
        super.taskLetter = "[E]";
        extractFromTo(description);
    }

    // Constructor with explicit description, from, and to
    /**
     * Constructs an {@link Event} with the specified description, completion status, and start and end times.
     *
     * @param description The description of the event.
     * @param isDone The completion status of the event.
     * @param from The start time as a string.
     * @param to The end time as a string.
     * @throws DuncanException If there are errors in parsing or invalid fields.
     */
    public Event(String description, boolean isDone, String from, String to) throws DuncanException {
        super(description);
        super.taskLetter = "[E]";
        if (from == null || from.trim().isEmpty() || to == null || to.trim().isEmpty()) {
            throw new DuncanException(ErrorCode.USAGE_ERR_EVENT_INVALID);
        }
        super.description = description;
        super.isDone = isDone;
        this.from = DateTimeParser.parseDateTimeString(from);
        this.to = DateTimeParser.parseDateTimeString(to);
    }

    // Extracts 'from' and 'to' from the description
    private void extractFromTo(String description) throws DuncanException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new DuncanException(ErrorCode.USAGE_ERR_EVENT_INVALID);
        }

        String[] parts = description.split("/from", 2);
        super.description = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        String fromStr = timeParts[0].trim();
        String toStr = timeParts.length > 1 ? timeParts[1].trim() : "";

        if (fromStr.isEmpty() || toStr.isEmpty()) {
            throw new DuncanException(ErrorCode.USAGE_ERR_EVENT_TIME_MISSING);
        }

        this.from = DateTimeParser.parseDateTimeString(fromStr);
        this.to = DateTimeParser.parseDateTimeString(toStr);
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description +
                String.format(TIME_RANGE_FORMAT, from, to);
    }
}
