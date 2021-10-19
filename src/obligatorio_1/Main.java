package obligatorio_1;

import utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<String> opciones_validas;
    private static List<Jugador> jugadores;
    private static List<Partida> bitacora;

    public static void main(String[] args) {
        jugadores = new ArrayList<Jugador>();
        jugadores.add(new Jugador("Alehandro", 24, "Ale"));
        bitacora = new ArrayList<Partida>();
        opciones_validas = new ArrayList<>(){
            {
                add("1");
                add("2");
                add("3");
                add("x");
            }};
        menuPrincipal();
    }

    private static void menuPrincipal(){
        String input;
        do{
            Utils.blank_line();
            System.out.println(Utils.BLUE +
                    "   d888888o.       ,o888888o.     8 8888          8 8888 8888888 8888888888   .8.          8 888888888o.    8 8888     ,o888888o.       d888888o.   \n" +
                    " .`8888:' `88.  . 8888     `88.   8 8888          8 8888       8 8888        .888.         8 8888    `88.   8 8888  . 8888     `88.   .`8888:' `88. \n" +
                    " 8.`8888.   Y8 ,8 8888       `8b  8 8888          8 8888       8 8888       :88888.        8 8888     `88   8 8888 ,8 8888       `8b  8.`8888.   Y8 \n" +
                    " `8.`8888.     88 8888        `8b 8 8888          8 8888       8 8888      . `88888.       8 8888     ,88   8 8888 88 8888        `8b `8.`8888.     \n" +
                    "  `8.`8888.    88 8888         88 8 8888          8 8888       8 8888     .8. `88888.      8 8888.   ,88'   8 8888 88 8888         88  `8.`8888.    \n" +
                    "   `8.`8888.   88 8888         88 8 8888          8 8888       8 8888    .8`8. `88888.     8 888888888P'    8 8888 88 8888         88   `8.`8888.   \n" +
                    "    `8.`8888.  88 8888        ,8P 8 8888          8 8888       8 8888   .8' `8. `88888.    8 8888`8b        8 8888 88 8888        ,8P    `8.`8888.  \n" +
                    "8b   `8.`8888. `8 8888       ,8P  8 8888          8 8888       8 8888  .8'   `8. `88888.   8 8888 `8b.      8 8888 `8 8888       ,8P 8b   `8.`8888. \n" +
                    "`8b.  ;8.`8888  ` 8888     ,88'   8 8888          8 8888       8 8888 .888888888. `88888.  8 8888   `8b.    8 8888  ` 8888     ,88'  `8b.  ;8.`8888 \n" +
                    " `Y8888P ,88P'     `8888888P'     8 888888888888  8 8888       8 8888.8'       `8. `88888. 8 8888     `88.  8 8888     `8888888P'     `Y8888P ,88P'  " + Utils.RESET);
            Utils.blank_line();

            System.out.println("""
            1 - Registrar Jugador
            2 - Ver Bitácora
            3 - Seleccionar Jugador
            """);
            System.out.println(Utils.RED + "x"+ Utils.RESET + " - Salir\n");

            System.out.print(Utils.YELLOW + "Ingrese una opción: " + Utils.RESET);
            input = scanner.next();

        }while(!Utils.validateOption(input, opciones_validas));

        if(input.equalsIgnoreCase("x")){
            Utils.end_program();
        }else{
            opcionSeleccionada(Character.getNumericValue(input.charAt(0)));
        }
    }

    private static void opcionSeleccionada(int opcion){
        if (opcion == 1){
            registrarJugador();
        }else if(opcion == 2){
            mostrarBitacora();
        }else if (opcion == 3){
            seleccionarJugador();
        }
        menuPrincipal();
    }

    private static void registrarJugador(){

        // Cargar los aliases en uso
        List<String> alias_en_uso = new ArrayList<>();
        for(Jugador jugador : jugadores){
            alias_en_uso.add(jugador.getAlias());
        }

        Utils.clear_console(); Utils.blank_line();
        System.out.println(Utils.RED + "__  ___ __   _______  __     __  ___            __      __  __  __  \n" +
                "|__)|__ / _`|/__`||__)/  \\   |  \\|__       ||  |/ _` /\\ |  \\/  \\|__) \n" +
                "|  \\|___\\__>|.__/||  \\\\__/   |__/|___   \\__/\\__/\\__>/~~\\|__/\\__/|  \\ \n" + Utils.RESET);

        System.out.print("Ingrese el nombre: ");
        String nombre;
        while(scanner.hasNextInt()){
            System.out.println(scanner.nextInt() + " no es un nombre válido...\n");
            System.out.print("Ingrese el nombre: ");
        }
        nombre = scanner.nextLine();
        scanner.next();

        int edad;
        do {
            System.out.print("Ingrese la edad: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" no es una edad válida.\n\n", input);
                System.out.print("Ingrese la edad: ");
            }
            edad = scanner.nextInt();
        }while (edad <= 5 || edad >= 120);


        boolean flag = false;
        scanner.nextLine();
        String alias;
        do {
            System.out.print("Ingrese el alias: ");
            alias = scanner.nextLine();
            flag = !(alias.contains(" ") || alias_en_uso.contains(alias) || alias.equals(""));
            if (!flag) {
                System.out.printf("\"%s\" no es un alias válido.\n\n", alias);
            }
        } while (!flag);

        
        Jugador jugador = new Jugador(nombre, edad, alias);
        jugadores.add(jugador);

        Utils.blank_line();
        System.out.println(Utils.GREEN + Utils.CHECK_EMOJI +" Jugador Registrado.\n" + Utils.RESET);
        Utils.pressEnterKeyToContinue();
    }

    private static void seleccionarJugador(){

        Utils.blank_line();
        System.out.println(Utils.RED + "**     _                               _____                             \n" +
                " ___/__) ,                /)        (, /               /)              \n" +
                "(, /       _  _/_ _     _(/  _        /      _   _   _(/ _____   _  _  \n" +
                "  /    _(_/_)_(__(_(_  (_(__(/_   ___/__(_(_(_/_(_(_(_(_(_)/ (__(/_/_)_\n" +
                " (_____                         /   /      .-/                         \n" +
                "        )                      (__ /      (_/                          " + Utils.RESET);
        Utils.blank_line();

        if(jugadores.size() == 0){
            Utils.blank_line();
            System.out.println("Actualmente no hay ningún jugador registrado.\n");
            Utils.pressEnterKeyToContinue();
            return;
        }

        int index = 1;
        for(Jugador j:jugadores){
            System.out.println(index + " - " + j.getAlias());
            index++;
        }

        Utils.blank_line();
        System.out.println(Utils.RED + "x" + Utils.RESET + " - Salir.\n" );
        System.out.print(Utils.YELLOW + "Elija un jugador: ");

        int jugador;
        while(!scanner.hasNextInt()){
            System.out.println("Ingrese un jugador válido.");
        }
        jugador = scanner.nextInt();

        Jugador j = jugadores.get(jugador - 1);
        j.menu();
    }

    private static void mostrarBitacora(){
        Utils.clear_console();
        Utils.blank_line();
        System.out.println("__  _  _____  __    ___  __   ___   __   \n" +
                "|  \\| ||_   _|/  \\  / _/ /__\\ | _ \\ /  \\  \n" +
                "| -<| |  | | | /\\ || \\__| \\/ || v /| /\\ | \n" +
                "|__/|_|  |_| |_||_| \\__/ \\__/ |_|_\\|_||_|");
        Utils.blank_line();

        if(jugadores.size() == 0){
            Utils.blank_line();
            System.out.println("Actualmente no hay ningún registro en la bitácora...\n" );
            Utils.pressEnterKeyToContinue();
        }else{
            for (Partida partida: bitacora){
                System.out.println(partida.getJugador().getAlias() + partida.getPuntaje());
            }
        }


    }

    public static void agregarPartida(Partida partida){
        bitacora.add(partida);
    }

}
