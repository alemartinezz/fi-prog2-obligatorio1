
package obligatorio_1;

import utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Alejandro Martinez - 270450
 */
public class Saltar extends Partida {

    // Atributos de clase
    public char[] sequence;
    public char[][] table;
    public Scanner scanner;
    private int saltos_disponibles = 4;

    // Constructor
    public Saltar(Jugador jugador) {
        super(jugador);
        this.scanner = new Scanner(System.in);
        this.sequence = new char[]{'R', 'A', 'V', 'M'};
        this.table = new char[11][4];
    }

    // Metodo para empezar el juego
    public void start() {

        // Presentacion
        System.out.println( Utils.RED +"\n" +
                "░██████╗░█████╗░██╗░░░░░████████╗░█████╗░██████╗░\n" +
                "██╔════╝██╔══██╗██║░░░░░╚══██╔══╝██╔══██╗██╔══██╗\n" +
                "╚█████╗░███████║██║░░░░░░░░██║░░░███████║██████╔╝\n" +
                "░╚═══██╗██╔══██║██║░░░░░░░░██║░░░██╔══██║██╔══██╗\n" +
                "██████╔╝██║░░██║███████╗░░░██║░░░██║░░██║██║░░██║\n" +
                "╚═════╝░╚═╝░░╚═╝╚══════╝░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝" + Utils.RESET);
        Utils.blank_line();
        System.out.println("El juego \"Saltar\" consiste en un tablero de 11 filas y 4 columnas. El tablero tiene un área base (sin puntuación, con 6 filas)\n" +
                "y el área con puntos. En las primeras 4 filas del área de base se ubican 16 fichas: 4 rojas, 4 azules, 4 verdes y 4 amarillas.\n" +
                "No se repite color por columna ni por fila. ");
        System.out.println("El juego termina cuando no se pueden hacer más saltos y, o, quedan solamente 2 fichas en\n" +
                "el área base. El puntaje obtenido depende de la ubicación de las fichas.");
        System.out.println(Utils.YELLOW + "Configuración del tablero: Predeterminada." + Utils.RESET);

        poblarTablero();
        juego();
    }

    private void poblarTablero(){
        this.table[0] = new char[]{'M', 'V', 'R', 'A'};
        this.table[1] = new char[]{'V', 'R', 'A', 'M'};
        this.table[2] = new char[]{'R', 'A', 'M', 'V'};
        this.table[3] = new char[]{'A', 'M', 'V', 'R'};
    }

    private void juego() {

        // El juego termina cuando no se pueden hacer más saltos y, o, quedan solamente 2 fichas en el área base
        for (int pos = 0; (calcularSaltosDisponibles() > 0) && (cantAreaBase()) > 2; pos++) {

            // Si la posicion se pasa de 3, volver a empezar en la lista de colores
            if (pos > 3){
                pos = 0;
            }
            char color = this.sequence[pos];

            // Actualizar columnas disponibles para ese color
            List<String> opciones_validas = new ArrayList<>(){{
                add("1");add("2");add("3");add("4");add("x");
            }};

            // Pedir ingreso de la columna dentro de las columnas disponibles
            String input;
            do{
                String color_a_mostrar = colorAMostrar(color);
                Utils.blank_line();
                System.out.println("Le toca mover al color: " + color_a_mostrar);
                System.out.println("Las columnas disponibles son: ");

                // Iterar en las columnas disponibles
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println(Utils.RED + "x"+ Utils.RESET + " - Terminar Juego.");
                Utils.blank_line();

                System.out.print(Utils.YELLOW + "Ingrese una opción: " + Utils.RESET);
                input = scanner.nextLine();
            }while(!Utils.validateOption(input, opciones_validas));

            // Mandar la columna seleccionada o el fin del juego
            if(input.matches(Utils.regex_digit)){

                // Si efectuar el movimiento no se puede hacer, pasar a otro color en el for
                int columna_seleccionada = Character.getNumericValue(input.charAt(0) - 1);

                if(!efectuarMovimiento(columna_seleccionada, color)){
                    pos--;
                    continue;
                }
            }else{
                if (finDelJuego()) {
                    break;
                } else {
                    pos --;
                };
            }
        }
    }

    private boolean efectuarMovimiento(int columna_seleccionada, char color){
        int fila_origen;
        int filas_a_mover;
        int fila_destino;

        // a) una ficha salta siempre hacia adelante, en su misma columna, tantas posiciones como
        //fichas que haya en esa fila de partida del salto (incluyendo la propia ficha).
        fila_origen = buscarFilaEnColumna(columna_seleccionada, color);
        filas_a_mover = fichasEnFila(fila_origen);
        fila_destino = filas_a_mover + fila_origen;

        // Ver si el movimiento se puede hacer
        if(fila_destino > this.table.length){
            System.out.println("No se puede mover a ese lugar, pasando al siguente color.\n");
            saltos_disponibles -= 1;
            return false;
        }

        // b) la posición de destino debe estar vacía
        if (this.table[fila_destino][columna_seleccionada] != '\0') {
            System.out.println("Esa posición ya está ocupada! Seleccione otra columna.\n");
            return false;
        }

        // c) en el área de base, no puede haber dos fichas del mismo color en la misma fila (en el
        // área con puntos sí puede haber).
        if (fila_destino <= 5){
            if (!checkearFilaApta(fila_destino, color)) {
                System.out.println("Ese nivel ya contiene un " + color + "\n");
                return false;
            }
        }

        // (d) la ficha más adelantada del color considerado en el tablero no puede avanzar solamente
        // una posición.
        if (filas_a_mover == 1){
            if (fichaMasAdelantada(fila_origen, color)){
                System.out.println("No se puede mover a ese lugar, seleccione otra columna.\n");
                return false;
            }
        }

        this.table[fila_destino][columna_seleccionada] = color;
        this.table[fila_origen][columna_seleccionada] = '\0';
        System.out.println(Utils.GREEN + Utils.CHECK_EMOJI +" Movimiento efectuado"+ Utils.RESET);

        return true;
    }

    private int buscarFilaEnColumna(int columna, char c) {
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i][columna] == c) {
                return i;
            }
        }
        return -1;
    }

    private int fichasEnFila(int fila) {
        int fichas = 0;
        for (int col = 0; col < this.table[fila].length; col++) {
            if(this.table[fila][col] != '\0'){
                fichas++;
            }
        }
        return fichas;
    }

    private boolean checkearFilaApta(int fila_seleccionada, char color) {
        boolean flag = true;
        for (int col = 0; col < this.table[fila_seleccionada].length; col++) {
            if (this.table[fila_seleccionada][col] == color) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private boolean fichaMasAdelantada(int fila_origen, char color){
        boolean flag = false;
        for (int fila = fila_origen + 1; fila < this.table.length && !flag; fila++) {
            for (int col = 0; col < this.table[fila].length; col++) {
                if(this.table[fila][col] != color){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private void mostrarTablero(){
        Utils.blank_line();
        System.out.println("+-+-+-+-+");
        for (int fila = this.table.length - 1; fila >= 0; fila--) {
            for (int col = 0; col < this.table[fila].length; col++) {

                char color_a_mostrar = this.table[fila][col];
                if (color_a_mostrar == 'A'){
                    System.out.print("|" + Utils.BLUE + '#' + Utils.RESET);
                }else if(color_a_mostrar == 'M'){
                    System.out.print("|" + Utils.YELLOW + '#' + Utils.RESET);
                }else if(color_a_mostrar == 'V'){
                    System.out.print("|" + Utils.GREEN + '#' + Utils.RESET);
                }else if (color_a_mostrar == 'R'){
                    System.out.print("|" + Utils.RED + '#' + Utils.RESET);
                }else{
                    System.out.print("|" + " ");
                }
            }
            System.out.print("|\n");
            System.out.println("+-+-+-+-+");
        }
        Utils.blank_line();
    }

    private boolean finDelJuego(){
        // Mostrar disposición final en el tablero
        Utils.blank_line();
        System.out.print("Disposición final del tablero:");
        mostrarTablero();

        // Calcular puntuación final
        calcularPuntos();
        System.out.println(Utils.RED + "Puntuación TOTAL: " + Utils.RESET + this.puntaje);
        System.out.println(Utils.YELLOW + Utils.BOOK_EMOJI + " Agregando a bitácora..." + Utils.RESET);
        System.out.println(Utils.GREEN + Utils.CHECK_EMOJI + " Puntuación registrada." + Utils.RESET);

        // Preguntar si quiere jugar otra vez
        Utils.blank_line();
        System.out.println("Desea volver a jugar? s / N");
        String si_o_no = scanner.nextLine();
        return !si_o_no.equalsIgnoreCase("s");
    }

    private int cantAreaBase(){
        int long_area_base = 0;
        for(int fila = 0; fila < this.table.length && fila <= 3; fila ++){
            for(int col = 0; col < this.table[fila].length; col++){
                long_area_base += 1;
            }
        }
        return long_area_base;
    }

    private int calcularSaltosDisponibles(){
        return 12;
    }

    private void calcularPuntos() {
        int resultado = 0;
        for (int fila = 6; fila < this.table.length; fila++){
            for(int col = 0; col < this.table[fila].length; col++){
                if(table[fila][col] != '\0'){
                    if(fila == 6){
                        resultado += 10;
                    }else if(fila == 7){
                        resultado += 20;
                    }else if (fila == 8){
                        resultado += 30;
                    } else if(fila == 9){
                        resultado += 40;
                    }else{
                        resultado += 60;
                    }
                }
            }
        }
        this.puntaje = resultado;
    }

    private String colorAMostrar(char color){
        String color_a_mostrar;
        if (color == 'R'){
            color_a_mostrar = Utils.RED + "Rojo" + Utils.RESET;
        }else if (color == 'V'){
            color_a_mostrar = Utils.GREEN + "Verde" + Utils.RESET;
        }else if(color == 'M'){
            color_a_mostrar = Utils.YELLOW + "Amarillo" + Utils.RESET;
        }else{
            color_a_mostrar = Utils.BLUE + "Azul" + Utils.RESET;
        }
        return color_a_mostrar;
    }
}

