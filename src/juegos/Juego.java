package juegos;

/**
 * Contrato que debe cumplir todo juego del sistema.
 *
 * <p>Permite que el menú trate a cualquier juego de forma uniforme (polimorfismo)
 * y que agregar un juego nuevo no requiera modificar el menú (OCP).</p>
 */
public interface Juego {

    /** Nombre visible del juego en el menú. */
    String getNombre();

    /** Ejecuta una partida completa del juego. */
    void jugar();
}
