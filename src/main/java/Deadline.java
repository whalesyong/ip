public class Deadline extends Task {
    String by;


    // constructors
    public Deadline(String description) throws DuncanException {
        super(description);
        super.taskLetter = "[D]";
        String[] parts = extractInfo(description);
        super.description = parts[0];
        System.out.println("0:" + parts[0] + "1:" + parts[1]);
        this.by = parts[1];
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
        this.by = by;
    }

    // Method to extract the 'by' field from the description
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
        return taskLetter + super.getStatusIcon() + " " + this.description + " (by: " + by + ")";
    }
}
