public class Event extends Task {
    public String from;
    public String to;


    // Constructor with only description (parses /from and /to)
    public Event(String description) throws DuncanException {
        super(description);
        super.taskLetter = "[E]";
        extractFromTo(description);
    }

    // Constructor with explicit description, from, and to
    public Event(String description, boolean isDone, String from, String to) throws DuncanException {
        super(description);
        super.taskLetter = "[E]";
        if (from == null || from.trim().isEmpty() || to == null || to.trim().isEmpty()) {
            throw new DuncanException("Please specify both a start and end time!");
        }
        super.description = description;
        super.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    // Extracts 'from' and 'to' from the description
    private void extractFromTo(String description) throws DuncanException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new DuncanException("Usage: event <event description> /from <event from> /to <event to>");
        }

        String[] parts = description.split("/from", 2);
        super.description = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        this.from = timeParts[0].trim();
        this.to = timeParts.length > 1 ? timeParts[1].trim() : "";

        if (from.isEmpty() || to.isEmpty()) {
            throw new DuncanException("Please specify both a start and end time!");
        }
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + " (from: " + from + " to: " + to + ")";
    }
}
