public class Duncan {

    public static void printHorizontalLine(){
        System.out.println("-------------------------------------------------------------");
    }
    public static void printGreeting(){
        System.out.println("Hello! I'm Duncan.\nWhat can I do for you?");
    }
    public static void main(String[] args) {
        String logo =

                  " ██████╗ ██╗   ██╗███╗   ██╗ ██████╗ █████╗ ███╗   ██╗\n"
                + " ██╔══██╗██║   ██║████╗  ██║██╔════╝██╔══██╗████╗  ██║\n"
                + " ██║  ██║██║   ██║██╔██╗ ██║██║     ███████║██╔██╗ ██║\n"
                + " ██║  ██║██║   ██║██║╚██╗██║██║     ██╔══██║██║╚██╗██║\n"
                + " ██████╔╝╚██████╔╝██║ ╚████║╚██████╗██║  ██║██║ ╚████║\n"
                + "  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═══╝";

        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        printGreeting();
        printHorizontalLine();
        // Greet user and exit for now
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();

    }
}
