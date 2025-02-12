public class Event extends Task {
    String from;
    String to;

    public static String taskLetter = "[E]";
    protected String description;

    //TODO: remove /from and /by parts from description.
    public Event(String description) throws DuncanException {
        super(description);
        String[] words = description.split(" ");
        if (words.length <= 3) {
            throw new DuncanException("Usage: event <event description> /from <event from> /to <event to>");
        }

        from = description.split("/from")[1].split("/to")[0].trim();
        to = description.split("/to", 2)[1].trim();


        if ( from.isEmpty() || to.isEmpty()) {
            throw new DuncanException("Please specify a start and end time!");
        }
        this.description = description.split("/from")[0];
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + " (from: " + from + " to: " + to + ")";
    }
}
