public class Deadline extends Task{
    public static String taskLetter = "[D]";
    public String by;
    protected String description;

    public Deadline(String description) throws DuncanException {
        super(description);
        String[] words = description.split(" ");
        if (words.length <= 2) {
            throw new DuncanException("Usage: deadline <deadline description> /by <deadline by>");
        }
        String[] parts = description.split("/by", 2);
        if (parts.length > 1){
            by = parts[1].trim();
            if (by.isEmpty()){
                throw new DuncanException("Please specify a deadline!");
            }
        } else {
            System.out.println("Usage: deadline <deadline by> /by <deadline by>");
        }
        this.description = parts[0].split(" ", 2)[1].trim();
    }

    @Override
    public String toString() {
        return taskLetter + super.getStatusIcon() + " " + this.description + " (by: " + by+")" ;
    }
}
