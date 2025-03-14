import java.util.ArrayList;

/**
 * The List class manages a collection of tasks, allowing the addition, modification,
 * deletion, and display of tasks. It supports operations such as marking, unmarking,
 * and finding tasks based on keywords.
 */
public class List {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new List object with an empty list of tasks.
     */
    public List() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list based on the description and task letter.
     * The task letter determines the type of task (e.g., Todo, Deadline, Event).
     *
     * @param description The description of the task.
     * @param taskLetter A single character indicating the type of task (T, D, E).
     * @throws DuncanException If the task letter is invalid.
     */
    public void addTask(String description, String taskLetter) throws DuncanException {
        Task newTask = switch (taskLetter) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description);
            case "E" -> new Event(description);
            default -> throw new DuncanException(ErrorCode.INVALID_TASK_LETTER);
        };

        tasks.add(newTask);
        System.out.println("You have an extra thing to do now:\n"  + formatTaskMessage(newTask));
    }

    /**
     * Formats the task message to be printed after adding a new task.
     * Includes details such as the task letter, status icon, and specific task details.
     *
     * @param task The task to format.
     * @return A formatted string representing the task.
     */
    private String formatTaskMessage(Task task) {
        StringBuilder message = new StringBuilder(task.taskLetter + task.getStatusIcon() + " " + task.description);

        if (task instanceof Deadline deadline) {
            message.append(" (").append(deadline.getBy()).append(")");
        } else if (task instanceof Event event) {
            message.append(" (").append(event.getFrom()).append(" to ").append(event.getTo()).append(")");
        }

        return message.toString();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @throws DuncanException If there is an error adding the task.
     */
    public void addTask(Task task) throws DuncanException {
        tasks.add(task);
    }

    /**
     * Displays all tasks in the list.
     * If the list is empty, prints a message indicating that no tasks are available.
     */
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

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param index The index of the task to mark as done.
     * @throws DuncanException If the task index is invalid.
     */
    public void markTask(int index) throws DuncanException {
        if (isValidIndex(index)) {
            tasks.get(index - 1).setDone(true);
            System.out.println("Great! I've marked this task as done: \n" + tasks.get(index - 1));
        } else {
            throw new DuncanException(ErrorCode.INVALID_INDEX_ERR, index);
        }
    }

    /**
     * Unmarks a task, setting it as not done based on its index in the list.
     *
     * @param index The index of the task to unmark.
     * @throws DuncanException If the task index is invalid.
     */
    public void unmarkTask(int index) throws DuncanException {
        if (isValidIndex(index)) {
            tasks.get(index - 1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet: " + tasks.get(index - 1));
        } else {
            throw new DuncanException(ErrorCode.INVALID_INDEX_ERR, index);
        }
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index The index of the task to delete.
     * @throws DuncanException If the task index is invalid.
     */
    public void deleteTask(int index) throws DuncanException {
        if (isValidIndex(index)) {
            System.out.println("Wow, you have one less thing to do now: \n" + tasks.get(index-1));
            tasks.remove(index-1);
        } else {
            throw new DuncanException(ErrorCode.INVALID_INDEX_ERR, index);
        }
    }

    /**
     * Checks if the given index is valid (within the bounds of the task list).
     *
     * @param index The index to check.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     * Prints the matching tasks and a message if no tasks are found.
     *
     * @param keyword The keyword to search for.
     */
    public void findKeyword(String keyword) {
        boolean hasEntry = false;
        System.out.println("Finding tasks with keyword: " + keyword);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + "." + tasks.get(i));
                if (!hasEntry) { hasEntry = true; }
            }
        }

        if (!hasEntry) {
            System.out.println("No tasks with the keyword: " + keyword);
        }
    }
}