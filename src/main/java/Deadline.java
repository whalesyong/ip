import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of {@link Task} representing a task with a deadline.
 * Contains functionality for setting, parsing, and formatting deadlines for tasks.
 */
public class Deadline extends Task {

    //constant variables for symbols and string literals
    private static final String DEADLINE_FORMAT = " (by: %s)";

    //format to a LocalDate object
    private CustomDateTime by;

    //getters and setters
    public CustomDateTime getBy() { return by; }
    public void setBy(CustomDateTime by) { this.by = by; }

    /**
     * Constructs a {@link Deadline} task with a description and deadline parsed from the description.
     *
     * @param description The task description which should contain the deadline in "/by" format.
     * @throws DuncanException If the description format is invalid or the date-time cannot be parsed.
     */
    public Deadline(String description) throws DuncanException {
        super(description);
        super.taskLetter = "[D]";
        String[] parts = extractInfo(description);
        super.description = parts[0];
        this.by = DateTimeParser.parseDateTimeString(parts[1]);
    }
    /**
     * Constructs a {@link Deadline} task from a description, done status, and a deadline string.
     *
     * @param description The task description.
     * @param isDone The completion status of the task.
     * @param by The deadline string (to be parsed into a {@link CustomDateTime}).
     * @throws DuncanException If the deadline string is invalid or missing.
     */
    public Deadline(String description, boolean isDone, String by) throws DuncanException {
        super(description);
        super.taskLetter = "[D]";
        if (by == null || by.trim().isEmpty()) {
            throw new DuncanException(ErrorCode.USAGE_ERR_DEADLINE_MISSING); //no deadline provided
        }
        super.description = description;
        super.isDone = isDone;
        this.by = DateTimeParser.parseDateTimeString(by);
    }

    // helper method to extract the 'by' field from the description
    private String[] extractInfo(String description) throws DuncanException {
        String[] words = description.split(" ");
        if (words.length <= 2) {
            throw new DuncanException(ErrorCode.USAGE_ERR_DEADLINE_INVALID);
        }
        String[] parts = description.split("/by", 2);
        if (parts.length > 1) {
            String extractedBy = parts[1].trim();
            if (extractedBy.isEmpty()) {
                throw new DuncanException(ErrorCode.USAGE_ERR_DEADLINE_MISSING);
            }
            return parts;
        } else {
            throw new DuncanException(ErrorCode.USAGE_ERR_DEADLINE_INVALID);
        }
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + String.format(DEADLINE_FORMAT, by);
    }
}
