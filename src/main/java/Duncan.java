import org.w3c.dom.Text;

import java.util.Scanner;

public class Duncan {


    public static void main(String[] args) throws DuncanException {


        System.out.println("Hi! I'm \n" + TextUI.DUNCAN_LOGO);
        TextUI.printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        InputParser duncanInputParser = new InputParser(scanner);
        TextUI.greetUser();
        duncanInputParser.startQueryLoop();
        TextUI.sayBye();

    }
}
