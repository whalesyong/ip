import java.util.Scanner;

public class Duncan {

    public static void printHorizontalLine(){
        System.out.println("-------------------------------------------------------------");
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

        //keep reading until user types "bye"
        Scanner scanner = new Scanner(System.in);
        String userInput ;

        while (true){
            System.out.print("You: ");
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")){
                break;
            }

            //function to give output.
            printOutput(userInput);
        }

        // Greet user and exit for now
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();

    }

     private static void printOutput(String userInput){
        printHorizontalLine();
        System.out.println("Duncan: "+ userInput);
        printHorizontalLine();
    }
}
