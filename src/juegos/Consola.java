package juegos;

/**
 * Contrato de entrada/salida por consola.
 *
 * <p>Permite desacoplar la lógica de los juegos de la implementación concreta
 * (System.in / System.out) y facilita las pruebas automatizadas.</p>
 */
public interface Consola {

    /** Lee una línea de texto ingresada por el usuario. */
    String leerLinea();

    /** Escribe un mensaje sin salto de línea final. */
    void escribir(String mensaje);

    /** Escribe un mensaje con salto de línea final. */
    void escribirLinea(String mensaje);
}
