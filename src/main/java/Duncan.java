import java.util.Scanner;

public class Duncan {
    public static void printHorizontalLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo =
                          " ██████╗ ██╗   ██╗███╗   ██╗ ██████╗ █████╗ ███╗   ██╗\n"
                        + " ██╔══██╗██║   ██║████╗  ██║██╔════╝██╔══██╗████╗  ██║\n"
                        + " ██║  ██║██║   ██║██╔██╗ ██║██║     ███████║██╔██╗ ██║\n"
                        + " ██║  ██║██║   ██║██║╚██╗██║██║     ██╔══██║██║╚██╗██║\n"
                        + " ██████╔╝╚██████╔╝██║ ╚████║╚██████╗██║  ██║██║ ╚████║\n"
                        + "  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═══╝";

        System.out.println("Hi! I'm \n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List taskList = new List();

        while (true){
            userInput = scanner.nextLine().trim();
            printHorizontalLine();

            if (userInput.equalsIgnoreCase("bye")){
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                taskList.showTasks();
            } else {
                taskList.addTask(userInput);
            }

            printHorizontalLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}