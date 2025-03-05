public class TextUI {
    public static final String DUNCAN_LOGO =
                      " ██████╗ ██╗   ██╗███╗   ██╗ ██████╗ █████╗ ███╗   ██╗\n"
                    + " ██╔══██╗██║   ██║████╗  ██║██╔════╝██╔══██╗████╗  ██║\n"
                    + " ██║  ██║██║   ██║██╔██╗ ██║██║     ███████║██╔██╗ ██║\n"
                    + " ██║  ██║██║   ██║██║╚██╗██║██║     ██╔══██║██║╚██╗██║\n"
                    + " ██████╔╝╚██████╔╝██║ ╚████║╚██████╗██║  ██║██║ ╚████║\n"
                    + "  ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═══╝";

    public static final String HLINE = "___________________________________________________________";


    public static void printHorizontalLine(){
        System.out.println(HLINE);
    }
    public static void printLogo(){
        System.out.println(DUNCAN_LOGO);
    }
    public void showError(String message){
        System.out.println("sum ting wong at: "+ message);
    }

}
