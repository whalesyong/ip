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
            throw new DuncanException(ErrorCode.IO_FILE_WRITE_ERR, e.getMessage());
        }
    }

    public List readTextFile() throws DuncanException, FileNotFoundException {
        List taskList = new List();
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DuncanException(ErrorCode.IO_FILE_READ_ERR, e.getMessage());
        }
    }

    private Task parseTask(String line) throws DuncanException {
        String[] taskInfo = line.split(Pattern.quote(SEPARATOR));
        if (taskInfo.length < 3) {
            throw new DuncanException(ErrorCode.IO_INVALID_TASK_ERR ,line);
        }

        String taskLetter = taskInfo[0];
        boolean isDone = taskInfo[1].equals("1");
        String description = taskInfo[2];

        switch (taskLetter) {
        case "[T]":
            return new Todo(description, isDone);
        case "[D]":
            return parseDeadline(taskInfo, line);
        case "[E]":
            return parseEvent(taskInfo, line);
        default:
            throw new DuncanException(ErrorCode.IO_INVALID_TASK_ERR, line);
        }
    }

    private Deadline parseDeadline(String[] taskInfo, String line) throws DuncanException {
        if (taskInfo.length < 4) {
            throw new DuncanException(ErrorCode.IO_INVALID_TASK_ERR ,line);
        }
        return new Deadline(taskInfo[2], taskInfo[1].equals("1"), taskInfo[3]);
    }

    private Event parseEvent(String[] taskInfo, String line) throws DuncanException {
        if (taskInfo.length < 4) {
            throw new DuncanException(ErrorCode.IO_INVALID_TASK_ERR, line);
        }
        String[] times = taskInfo[3].split("--");
        if (times.length < 2) {
            throw new DuncanException(ErrorCode.IO_INVALID_TASK_ERR, line);
        }
        return new Event(taskInfo[2], taskInfo[1].equals("1"), times[0], times[1]);
    }



}
