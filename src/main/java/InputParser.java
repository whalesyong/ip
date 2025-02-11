import java.util.Map;
import java.util.Scanner;

public class InputParser {
    /*
    * Input Parser class for Duncan. Takes in a scanner, and continuously
    * asks user for input.
    * */

    //Maps keywords to their associated length
    final Map<String, Integer> keywordLengthDict = Map.of(
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
    List taskList = new List();
    public InputParser(Scanner scanner) {
        this.scanner = scanner;
    }



    public void startQueryLoop(){

        boolean isRunning = true;
        while (isRunning) {

            //gets the first word
            userInput = scanner.nextLine().trim().toLowerCase();
            TextUI.printHorizontalLine();
            String firstWord = userInput.split(" ")[0];

            switch (firstWord) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                taskList.showTasks();
                break;
            case "mark":
                // mark a task as done!
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(keywordLengthDict.get(firstWord)));
                    taskList.markTask(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number!" + e.getMessage());
                }
                break;
            case "unmark":
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(keywordLengthDict.get(firstWord)));
                    taskList.unmarkTask(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid task number!");
                }

                break;
            case "todo":
                try{
                    taskList.addTask(userInput);
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;
            case "deadline":
                try{
                    taskList.addDeadline(userInput);
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;
            case "event":
                try{
                    taskList.addEvent(userInput);
                    System.out.println("You have " + taskList.size() + " tasks.");
                } catch (NumberFormatException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
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
