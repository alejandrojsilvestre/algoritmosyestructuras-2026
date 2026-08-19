package juegos;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Implementación de {@link Consola} que usa la entrada y salida estándar
 * ({@code System.in} y {@code System.out}).
 */
public class ConsolaSistema implements Consola {

    /** Fuente de lectura de las líneas ingresadas por el usuario. */
    private final Scanner scanner;

    /** Destino donde se escriben los mensajes. */
    private final PrintStream salida;

    /** Crea una consola conectada a la entrada y salida estándar. */
    public ConsolaSistema() {
        this(new Scanner(System.in), System.out);
    }

    /**
     * Crea una consola con fuentes propias, útil para redirigir la entrada/salida.
     *
     * @param scanner fuente de lectura de líneas
     * @param salida  destino de escritura de los mensajes
     */
    public ConsolaSistema(Scanner scanner, PrintStream salida) {
        this.scanner = scanner;
        this.salida = salida;
    }

    @Override
    public String leerLinea() {
        return scanner.nextLine();
    }

    @Override
    public void escribir(String mensaje) {
        salida.print(mensaje);
    }

    @Override
    public void escribirLinea(String mensaje) {
        salida.println(mensaje);
    }
}
