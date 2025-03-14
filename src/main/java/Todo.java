public class Todo extends Task {

    // Constructor with only description
    public Todo(String description) throws DuncanException {

        super(description);
        super.taskLetter = "[T]";
        super.description = validateDescription(description);
    }

    // Constructor with description and isDone
    public Todo(String description, boolean isDone) throws DuncanException {
        super(description);
        super.taskLetter = "[T]";
        super.description = validateDescription(description);
        super.isDone = isDone; // Set completion status
    }

    // Validates the description
    private String validateDescription(String description) throws DuncanException {
        if (description == null || description.trim().isEmpty()) {
            throw new DuncanException(ErrorCode.USAGE_ERR_TODO);
        }
        return description.trim();
    }

    @Override
    public String toString() {
        return taskLetter + getStatusIcon() + " " + description;
    }
}
