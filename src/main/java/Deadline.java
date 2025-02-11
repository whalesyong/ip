public class Deadline extends Task{
    public String taskLetter = "[D]";
    public String by;
    protected String description;
    //TODO: remove the /by part from the description
    public Deadline(String description) {
        super(description);
        String[] parts = description.split("/by", 2);
        if (parts.length > 1){
            by = parts[1].trim();
        }
        this.description = parts[0];
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + "(by: " + by+")" ;
    }
}
