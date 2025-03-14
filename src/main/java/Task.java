import java.util.Arrays;


/**
 * Abstract class representing a task. This class serves as the base for all task types.
 * Subclasses must implement specific task-related functionality.
 */

public abstract class Task {
    /*
     Abstract class implementation of all tasks. Must be overriden
     */


    //attributes
    protected String description;
    protected boolean isDone = false;
    public String taskLetter;
    //constructors
    public Task(String description) throws DuncanException {

        this.description = description;
    }

    //helper methods
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }

}