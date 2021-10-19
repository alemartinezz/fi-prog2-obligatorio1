package obligatorio_1;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugador {

    private String nombre;
    private int edad;
    private String alias;

    private static final List<String> opciones_validas = new ArrayList<>(){{
        add("1");add("2");add("x");
    }};
    Scanner scanner = new Scanner(System.in);

    public Jugador(String nombre, int edad, String alias) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
    }

    public void menu(){

        Utils.blank_line();
        System.out.println(" ____                                                             __             \n" +
                "/\\  _`\\    __                                             __     /\\ \\            \n" +
                "\\ \\ \\L\\ \\ /\\_\\      __     ___    __  __     __     ___  /\\_\\    \\_\\ \\     ___   \n" +
                " \\ \\  _ <'\\/\\ \\   /'__`\\ /' _ `\\ /\\ \\/\\ \\  /'__`\\ /' _ `\\\\/\\ \\   /'_` \\   / __`\\ \n" +
                "  \\ \\ \\L\\ \\\\ \\ \\ /\\  __/ /\\ \\/\\ \\\\ \\ \\_/ |/\\  __/ /\\ \\/\\ \\\\ \\ \\ /\\ \\L\\ \\ /\\ \\L\\ \\\n" +
                "   \\ \\____/ \\ \\_\\\\ \\____\\\\ \\_\\ \\_\\\\ \\___/ \\ \\____\\\\ \\_\\ \\_\\\\ \\_\\\\ \\___,_\\\\ \\____/\n" +
                "    \\/___/   \\/_/ \\/____/ \\/_/\\/_/ \\/__/   \\/____/ \\/_/\\/_/ \\/_/ \\/__,_ / \\/___/ : " + this.alias);

        Utils.blank_line();

        System.out.println(Utils.GREEN + "(` _ | _  _ _.  ,_ _     ,_   .   _     \n" +
                "_)(/_|(/_(_(_|()||(/_  L|||   |L|(/_(|()\n" +
                "                             _|     _|  :" + Utils.RESET);

        String input;
        do{
            System.out.println("1 - Saltar");
            System.out.println("2 - Rectángulo");
            System.out.println(Utils.RED + "x"+ Utils.RESET + " - Salir");
            Utils.blank_line();

            System.out.print(Utils.YELLOW + "Ingrese una opción: " + Utils.RESET);
            input = scanner.nextLine();
        }while(!Utils.validateOption(input, opciones_validas));

        if(input.matches(Utils.regex_digit)){
            opcionSeleccionada(Character.getNumericValue(input.charAt(0)));
        }

    }

    public void opcionSeleccionada(int opcion){
        if(opcion == 1){
            Saltar saltar = new Saltar(this);
            saltar.start();
        }else if(opcion == 2){
            Rectangulo rectangulo = new Rectangulo(this);
            //rectangulo.start();
        }
    }

    @Override
    public String toString(){
        return "nombre: " + this.nombre + " edad: " + this.edad + " alias: " + this.alias;
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public String getAlias() {
        return alias;
    }
}
