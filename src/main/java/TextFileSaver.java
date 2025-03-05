import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

// class for reading and writing from and to a .txt file.
public class TextFileSaver {
    String directoryPath = "./data";
    String fileName = "/duncan.txt";
    String fullFilePath = directoryPath + File.separator + fileName ;
    final String SEPARATOR = "|";
    private String boolToNumber(boolean inputBoolean) {
        return inputBoolean ? "1" : "0";
    }


    public TextFileSaver() {
        // when called, make sure that the directory ./data exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //ensure file duncan.txt exists
        File dataFile = new File(directoryPath + File.separator + fileName);
        if (!dataFile.exists()) {
            try{
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file "+ dataFile.getAbsolutePath());
            }
        }
    }

    public void writeTextFile(List list) throws DuncanException {
        ArrayList<Task> taskList = list.getTasks();
        // Open the writer once, before the loop
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fullFilePath))) {
            for (Task task : taskList) {
                StringBuilder taskInfo = new StringBuilder();
                taskInfo.append(task.taskLetter)
                        .append(SEPARATOR)
                        .append(boolToNumber(task.isDone()))
                        .append(SEPARATOR)
                        .append(task.description);
                // add task specific fields (by, from to)
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline)task;
                    taskInfo.append(SEPARATOR)
                            .append(deadline.getBy().toString());
                } else if (task instanceof Event) {
                    Event event = (Event)task;
                    taskInfo.append(SEPARATOR)
                            .append(event.getFrom() + "--" + event.getTo().toString());
                }
                //write task info
                writer.write(taskInfo.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DuncanException("You messed up: "+ e.getMessage());
        }
    }

    public List readTextFile() throws DuncanException, FileNotFoundException {
        List taskList = new List();
        boolean isFileEmpty = true; // Flag to check if any tasks are read

        try (BufferedReader reader = new BufferedReader(new FileReader(fullFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                isFileEmpty = false; // File has at least one line

                String[] taskInfo = line.split(Pattern.quote(SEPARATOR));

                if (taskInfo.length < 3) {
                    throw new DuncanException("Missing field in line: " + line);
                }

                // Get info from task
                String taskLetter = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String description = taskInfo[2];

                Task task = null;

                // Assign task instance based on letter
                if (taskLetter.equals("[T]")) {
                    task = new Todo(description, isDone);
                } else if (taskLetter.equals("[D]")) {
                    if (taskInfo.length < 4) {
                        throw new DuncanException("Missing field in line: " + line);
                    }
                    String by = taskInfo[3];
                    task = new Deadline(description, isDone, by);
                } else if (taskLetter.equals("[E]")) {
                    if (taskInfo.length < 4) {
                        throw new DuncanException("Missing 'from' and 'to' fields in line: " + line);
                    }
                    String[] times = taskInfo[3].split("--");
                    if (times.length < 2) {
                        throw new DuncanException("Missing 'from' and 'to' fields in line: " + line);
                    }
                    String from = times[0];
                    String to = times[1];
                    task = new Event(description, isDone, from, to);
                }

                if (task != null) {
                    taskList.addTask(task);
                } else {
                    throw new DuncanException("No task found for task letter: " + taskLetter);
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DuncanException("You messed up: " + e.getMessage());
        }

        // If no tasks were read (i.e., file was empty), return a new List
    }



}
