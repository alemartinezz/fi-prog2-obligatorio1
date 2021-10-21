package utils;
/**
 * @author Alejandro Martinez - 270450
 */

import java.util.List;

public abstract class Utils {

    public static boolean validateInput(String input, List<String> opciones_validas){
        boolean flag = false;
        if(opciones_validas.contains(input.toLowerCase())){
            flag = true;
        }else{
            System.out.printf("\"%s\" No es una opción válida...\n\n", input);
        }
        return flag;
    }
    public static void clear_console(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void end_program(){
        clear_console();
        System.out.println("FIN.");
        System.exit(0);
    }
    public static void blank_line() {
        System.out.print("\n");
    }
    public static void pressEnterKeyToContinue() {
        System.out.print(Utils.RED + "Presione 'Enter' para volver..." + Utils.RESET);
        try {
            System.in.read();
        } catch(Exception e) {}
    }
    public static final String regex_digit = "\\d+";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String BOOK_EMOJI = "\uD83D\uDCDA";
    public static final String CHAT_EMOJI = "\uD83D\uDCAC\uFEFF";
    public static final String CHECK_EMOJI = "✔";

}
