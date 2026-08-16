package juegos;

import java.util.List;
import java.util.Objects;

/**
 * Menú principal del sistema.
 *
 * <p>Presenta la lista de juegos disponibles y la opción de salir. Depende de la
 * abstracción {@link Juego}, por lo que agregar un juego nuevo no exige modificar
 * esta clase (OCP).</p>
 */
public class Menu {

    private final Consola consola;
    private final List<Juego> juegos;

    public Menu(Consola consola, List<Juego> juegos) {
        this.consola = Objects.requireNonNull(consola, "La consola no puede ser null.");
        this.juegos = List.copyOf(Objects.requireNonNull(juegos, "La lista de juegos no puede ser null."));
        if (this.juegos.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un juego en el menú.");
        }
    }

    /** Muestra el menú en bucle hasta que el usuario elige salir. */
    public void iniciar() {
        int opcionSalir = juegos.size() + 1;

        boolean salir = false;
        while (!salir) {
            mostrarOpciones(opcionSalir);

            consola.escribir("Elija una opción: ");
            String linea = consola.leerLinea();
            Integer opcion = leerOpcion(linea, opcionSalir);

            if (opcion == null) {
                consola.escribirLinea("Entrada inválida: ingrese un número entre 1 y " + opcionSalir + ".");
                continue;
            }

            if (opcion == opcionSalir) {
                consola.escribirLinea("¡Hasta luego!");
                salir = true;
            } else {
                consola.escribirLinea("");
                juegos.get(opcion - 1).jugar();
            }
        }
    }

    private void mostrarOpciones(int opcionSalir) {
        consola.escribirLinea("=== Menú principal ===");
        for (int i = 0; i < juegos.size(); i++) {
            consola.escribirLinea((i + 1) + ". " + juegos.get(i).getNombre());
        }
        consola.escribirLinea(opcionSalir + ". Salir");
    }

    private Integer leerOpcion(String linea, int opcionSalir) {
        try {
            int opcion = Integer.parseInt(linea.trim());
            if (opcion >= 1 && opcion <= opcionSalir) {
                return opcion;
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
