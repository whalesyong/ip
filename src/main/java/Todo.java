public class Todo extends Task {
    public static String taskLetter = "[T]";
    protected String description;

    public Todo(String description) throws DuncanException {
        super(description);
        String[] words = description.split(" ", 2);
        if (words.length <= 1) {
            throw new DuncanException("Usage: todo <task description>");
        }
        this.description = words[1];
    }

    @Override
    public String toString() {
        return taskLetter + getStatusIcon() + " " + description;
    }
}
