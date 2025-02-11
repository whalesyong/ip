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
            "mark", 4,
            "unmark", 6
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
                    System.out.println("Please provide a valid task number!");
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
            default:
                taskList.addTask(userInput);

            }
        }

        //loop has ended
        System.out.println("Bye. Hope to see you again soon!");
        TextUI.printHorizontalLine();

    }
}
