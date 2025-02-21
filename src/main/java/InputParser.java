import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class InputParser {
    /*
    * Input Parser class for Duncan. Takes in a scanner, and continuously
    * asks user for input.
    * */
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
            "event", 6
    );

    public String userInput;
    Scanner scanner;
    List taskList;
    TextFileSaver fileSaver;
//-------- constructor  ---------------------------

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

    public static String getFirstWord(String input){
        String[] parts = input.split("\\s+", 2); // Limit to 2 parts
        return parts[0];
    }
    public static String filterFirstWord(String input){
        String[] parts = input.split(" ", 2);
        return parts[1];
    }

    public void startQueryLoop() throws DuncanException {

        boolean isRunning = true;
        while (isRunning) {

            //gets the first word
            userInput = scanner.nextLine().trim().toLowerCase();
            TextUI.printHorizontalLine();
            String firstWord = userInput.split(" ")[0];

            switch (firstWord) {
            case "bye":
                isRunning = false;

                // write to file
                fileSaver.writeTextFile(taskList);
                break;
            case "list":
                taskList.showTasks();
                break;
            case "mark":
                // mark a task as done!
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(KEYWORD_LENGTH_DICT
                            .get(firstWord)));
                    taskList.markTask(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number!" + e.getMessage());
                }
                break;
            case "unmark":
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(KEYWORD_LENGTH_DICT
                            .get(firstWord)));
                    taskList.unmarkTask(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number!");
                }

                break;
            case "todo":
                try{
                    taskList.addTask(filterFirstWord(userInput), "T");
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                } catch (DuncanException e) {

                }
                break;
            case "deadline":
                try{
                    taskList.addTask(filterFirstWord(userInput), "D");
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                } catch (DuncanException e) {
                    System.out.println(e.message);
                }
                break;
            case "event":
                try{
                    taskList.addTask(filterFirstWord(userInput), "E");
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                } catch (DuncanException e){
                    System.out.println( e.message);
                }
                break;
            default:
                // not a valid input.
                System.out.println("what are u saying bro");

            }
        }

        //loop has ended
        System.out.println("Bye. Hope to see you again soon!");
        TextUI.printHorizontalLine();

    }
}
