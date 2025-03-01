import java.util.ArrayList;

public class List {
    private ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    //initialising task with taskLetter
    public void addTask(String description, String taskLetter) throws DuncanException {
        Task newTask = null;
        switch (taskLetter) {
        case "T":
            // create a todo task
            newTask = new Todo(description);
            tasks.add(newTask);
            break;
        case "D":
            //create a deadline
            newTask = new Deadline(description);
            tasks.add(newTask);
            break;
        case "E":
            //create an event
            newTask = new Event(description);
            tasks.add(newTask);
            break;
        default:
            throw new DuncanException("Invalid task letter");
        }
        //print confirmation message
        System.out.println(
                "You have an extra thing to do now: \n"
                + newTask.taskLetter
                + newTask.getStatusIcon()
                + newTask.description
                + ((newTask instanceof Deadline)
                        ? " (" + ((Deadline) newTask).by + ")"
                        : ""
                )
                + ((newTask instanceof Event)
                        ? " ( " + ((Event) newTask).from + " to " + ((Event) newTask).to + ")"
                        : ""
                )
        );
    }

    public void addTask(Task task) throws DuncanException {
        tasks.add(task);
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
    }

    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: \n" + tasks.get(index - 1));
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

    public void deleteTask(int index){
        if (isValidIndex(index)){
            System.out.println("Okie, I've deleted this task: \n" + tasks.get(index-1));
            tasks.remove(index-1);

        } else {
            System.out.println("Invalid task number!");
        }
    }

    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
