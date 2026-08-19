package juegos;

import java.util.List;
import java.util.Random;

/**
 * Punto de entrada del sistema de juegos.
 *
 * <p>Construye la consola, el generador aleatorio y los juegos disponibles,
 * y arranca el menú principal.</p>
 */
public class Main {

    public static void main(String[] args) {
        Consola consola = new ConsolaSistema();
        Random random = new Random();

        List<Juego> juegos = List.of(
                new JuegoMayorMenor(consola, random),
                new JuegoPiedraPapelTijera(consola, random),
                new ExplicacionBigO(consola)
        );

        new Menu(consola, juegos).iniciar();
    }
}
