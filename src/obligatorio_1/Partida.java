package obligatorio_1;
/**
 * @author Alejandro Martinez - 270450
 */


import java.time.LocalDateTime;

public class Partida {

    protected LocalDateTime hora_comienzo;
    protected int puntaje;
    protected Jugador jugador;

    protected Partida(Jugador jugador) {
        this.hora_comienzo = LocalDateTime.now();
        this.jugador = jugador;
        Main.agregarPartida(this);
    }

    public LocalDateTime getHora_comienzo() {
        return hora_comienzo;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
