class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
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
        return getStatusIcon() + " " + description;
    }
}