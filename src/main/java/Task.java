import java.util.Arrays;
class Task {
    protected String description;
    private boolean isDone;
    public String taskLetter = "[T]";
    public Task(String description) {
        String[] words = description.split(" ");
        this.description = String.join(" ",Arrays.copyOfRange(words, 1, words.length) );
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean done) {
        isDone = done;
    }



    @Override
    public String toString() {
        return taskLetter + getStatusIcon() + " " + description;
    }
}