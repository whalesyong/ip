import java.util.Arrays;

// super class for different tasks
class Task {
    protected String description;
    private boolean isDone = false;

    public Task(String description) throws DuncanException {

        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean done) {
        isDone = done;
    }




}