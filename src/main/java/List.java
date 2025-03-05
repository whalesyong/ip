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
        Task newTask = switch (taskLetter) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description);
            case "E" -> new Event(description);
            default -> throw new DuncanException("Invalid task letter");
        };

        tasks.add(newTask);
        System.out.println("You have an extra thing to do now:\n" + formatTaskMessage(newTask));
    }

    private String formatTaskMessage(Task task) {
        StringBuilder message = new StringBuilder(task.taskLetter + task.getStatusIcon() + task.description);

        if (task instanceof Deadline deadline) {
            message.append(" (").append(deadline.by).append(")");
        } else if (task instanceof Event event) {
            message.append(" (").append(event.from).append(" to ").append(event.to).append(")");
        }

        return message.toString();
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

    public void findKeyword(String keyword) {
        boolean hasEntry = false;
        System.out.println("Finding tasks with keyword: " + keyword);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }

        if (!hasEntry) {
            System.out.println("No tasks with the keyword: " + keyword);
        }
    }
}
