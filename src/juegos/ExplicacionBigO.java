package juegos;

import java.util.Objects;

/**
 * Opción del menú que orienta al usuario hacia la documentación de la
 * notación Big O.
 *
 * <p>No ejecuta un juego: al ser elegida, imprime un único mensaje que indica
 * en qué archivo se encuentra la explicación (docs/algoritmos.md) y regresa al
 * menú. Se implementa como {@link Juego} para integrarse al menú sin modificar
 * {@link Menu} (OCP).</p>
 */
public class ExplicacionBigO implements Juego {

    /** Mensaje que orienta al usuario hacia la documentación. */
    public static final String MENSAJE =
            "La explicación de la notación Big O está en el archivo algoritmos.md.";

    private final Consola consola;

    /**
     * Crea la opción con su consola de salida.
     *
     * @param consola consola donde se imprimirá el mensaje
     */
    public ExplicacionBigO(Consola consola) {
        this.consola = Objects.requireNonNull(consola, "La consola no puede ser null.");
    }

    @Override
    public String getNombre() {
        return "Notación Big O";
    }

    @Override
    public void jugar() {
        consola.escribirLinea(MENSAJE);
    }
}
