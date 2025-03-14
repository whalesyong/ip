import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TextUI {
    public static final String DUNCAN_LOGO =
                      " ██████╗ ██╗   ██╗███╗   ██╗ ██████╗ █████╗ ███╗   ██╗\n"
                    + " ██╔══██╗██║   ██║████╗  ██║██╔════╝██╔══██╗████╗  ██║\n"
                    + " ██║  ██║██║   ██║██╔██╗ ██║██║     ███████║██╔██╗ ██║\n"
                    + " ██║  ██║██║   ██║██║╚██╗██║██║     ██╔══██║██║╚██╗██║\n"
                    + " ██████╔╝╚██████╔╝██║ ╚████║╚██████╗██║  ██║██║ ╚████║\n"
                    + "  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═══╝";

    public static final String HLINE = "___________________________________________________________";
    public static final String USER_INDENT = ">> ";
    public static void printUserIndent(){ System.out.print(USER_INDENT); }
    public static void printHorizontalLine(){
        System.out.println(HLINE);
    }
    public static void printLogo(){
        System.out.println(DUNCAN_LOGO);
    }

    public static void greetUser(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Hi there! It is now: " + formattedTime );

    }

    public static void sayBye(){
        System.out.println("Going so fast? :( I'll see you soon then!");
        printHorizontalLine();
    }
}
