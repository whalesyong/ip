import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    //constant variables for symbols and string literals
    private static final String DEADLINE_FORMAT = " (by: %s)";

    //format to a LocalDate object
    private CustomDateTime by;

    //getters and setters
    public CustomDateTime getBy() { return by; }
    public void setBy(CustomDateTime by) { this.by = by; }

    // constructors
    public Deadline(String description) throws DuncanException {
        super(description);
        super.taskLetter = "[D]";
        String[] parts = extractInfo(description);
        super.description = parts[0];
        System.out.println("0:" + parts[0] + "1:" + parts[1]);
        this.by = DateTimeParser.parseDateTimeString(parts[1]);
    }
    //constructor for initialising from file
    public Deadline(String description, boolean isDone, String by) throws DuncanException {
        super(description);
        super.taskLetter = "[D]";
        if (by == null || by.trim().isEmpty()) {
            throw new DuncanException("No deadline provided.");
        }
        super.description = description;
        super.isDone = isDone;
        this.by = DateTimeParser.parseDateTimeString(by);
    }

    // helper method to extract the 'by' field from the description
    private String[] extractInfo(String description) throws DuncanException {
        String[] words = description.split(" ");
        if (words.length <= 2) {
            throw new DuncanException("Usage: deadline <description> /by <deadline by>");
        }
        String[] parts = description.split("/by", 2);
        if (parts.length > 1) {
            String extractedBy = parts[1].trim();
            if (extractedBy.isEmpty()) {
                throw new DuncanException("Please specify a deadline!");
            }
            return parts;
        } else {
            throw new DuncanException("Usage: deadline <description> /by <deadline by>");
        }
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + String.format(DEADLINE_FORMAT, by);
    }
}
