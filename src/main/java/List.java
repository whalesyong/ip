import java.util.ArrayList;

class List {
    private ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Okie, I've added this task: \n" + Deadline.taskLetter + " " + newTask.description );
    }

    public void addDeadline(String description){
        Deadline newDeadline = new Deadline(description);
        tasks.add(newDeadline);
        System.out.println("Okie, I've added this task: \n" + Deadline.taskLetter + " " + newDeadline.description );
    }

    public void addEvent(String description) {
        Event newEvent = new Event(description);
        tasks.add(newEvent);
        System.out.println("Okie, I've added this task: \n" + Event.taskLetter + " " + newEvent.description ) ;
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        //System.out.println("size of list: " + tasks.size());
    }

    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: " + tasks.get(index - 1));
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index - 1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet: " + tasks.get(index - 1));
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
    }
}
