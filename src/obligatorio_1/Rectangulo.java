package obligatorio_1;
/**
 * @author Alejandro Martinez - 270450
 */

public class Rectangulo extends Partida{

    public char[][] matriz_saltar;

    public Rectangulo(Jugador jugador) {
        super(jugador);
        this.matriz_saltar = new char[20][20];
    }

    private void calcularPuntos() {

    }
}
