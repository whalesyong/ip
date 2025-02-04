import java.util.ArrayList;



class List {
    private ArrayList<String> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}