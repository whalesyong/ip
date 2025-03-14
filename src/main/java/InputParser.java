import java.util.Map;
import java.util.Scanner;
import java.io.*;


/**
 * The InputParser class handles user input, continuously prompting for input
 * and processing commands. It interacts with the task list and performs various actions
 * like marking, unmarking, deleting tasks, and adding new ones.
 */
public class InputParser {

//-------- attributes ---------------------------
    //Maps keywords to their associated length
    final Map<String, Integer> KEYWORD_LENGTH_DICT
            = Map.of(
            "bye", 3,
            "list", 4,
            "mark", 5,
            "unmark", 7,
            "todo", 5,
            "deadline", 9,
            "event", 6,
            "delete", 7
    );

    public String userInput;
    Scanner scanner;
    List taskList;
    TextFileSaver fileSaver;
//-------- constructor  ---------------------------

    /**
     * Constructs an InputParser object, initializing the scanner and loading
     * the task list from the data file if it exists.
     *
     * @param scanner The scanner used to read user input.
     */
    public InputParser(Scanner scanner) {
        this.scanner = scanner;

        //check if data file exists
        File dataFile = new File("./data/duncan.txt");
        fileSaver = new TextFileSaver();
        if (dataFile.exists()) {
            try{
                taskList = fileSaver.readTextFile();

            } catch (DuncanException e) {
                System.out.println("Error reading duncan.txt, please check the file!! Error: " + e.getMessage());

            } catch (FileNotFoundException e) {
                System.out.println("duncan.txt not found, please check the file!! Error: " + e.getMessage());
            }
        } else {
            taskList = new List();
        }

    }

//-------- methods  ---------------------------


    //public methods
    public static String getFirstWord(String input){
        String[] parts = input.split(" ", 2); // Limit to 2 parts
        return parts[0];
    }
    public static String filterFirstWord(String input) throws DuncanException {

        String[] parts = input.split(" ", 2);
        if (parts.length <= 1 ) {
            throw new DuncanException(ErrorCode.INVALID_CMD_ARGS_ERR, parts[0]);

        }
        return parts[1];
    }

    /**
     * Starts the input query loop, processing user commands until "bye" is entered.
     * Handles task additions, modifications, and deletions.
     *
     * @throws DuncanException If there is an error processing a command.
     */
    public void startQueryLoop() throws DuncanException {
        boolean isRunning = true;
        while (isRunning) {
            TextUI.printUserIndent();
            userInput = scanner.nextLine().trim().toLowerCase();
            TextUI.printHorizontalLine();
            String firstWord = userInput.split(" ")[0];
            try {
                switch (firstWord) {
                case "bye":
                    isRunning = false;
                    fileSaver.writeTextFile(taskList);
                    break;
                case "find":
                    taskList.findKeyword(filterFirstWord(userInput));
                    break;
                case "list":
                    taskList.showTasks();
                    break;
                case "delete", "mark", "unmark":
                    handleTaskModification(firstWord);
                    break;
                case "todo", "deadline", "event":
                    handleTaskAddition(firstWord);
                    break;
                default:
                    System.out.println("Not a command: '" + firstWord + "'! stop.");
                }
            } catch (DuncanException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //private methods
    private void handleTaskModification(String firstWord) {
        try {
            int taskNumber;
            String taskNumberString = userInput.substring(KEYWORD_LENGTH_DICT.get(firstWord));

            try{
                taskNumber = Integer.parseInt(taskNumberString);
            } catch (NumberFormatException e) {
                System.out.println("Not a number: " + taskNumberString);
                return;
            }

            switch (firstWord) {
            case "delete":
                taskList.deleteTask(taskNumber);
                break;
            case "mark":
                taskList.markTask(taskNumber);
                break;
            case "unmark":
                taskList.unmarkTask(taskNumber);
                break;
            }
        } catch (DuncanException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleTaskAddition(String taskType) {
        try {
            taskList.addTask(filterFirstWord(userInput), taskType.substring(0, 1).toUpperCase());
            System.out.println("You have " + taskList.size() + " tasks.");
        } catch (DuncanException e) {
            System.out.println(e.getMessage());
        }
    }

}
