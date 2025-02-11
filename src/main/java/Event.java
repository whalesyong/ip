public class Event extends Task {
    String from;
    String to;

    public String taskLetter = "[E]";
    protected String description;
    //TODO: remove /from and /by parts from description.
    public Event(String description) {
        super(description);
        from = description.split("/from")[1].split("/to")[0].trim();
        to = description.split("/to")[1].trim();
        this.description = description.split("/from")[0];
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + "(from: " + from + " to: " + to + ")";
    }
}
