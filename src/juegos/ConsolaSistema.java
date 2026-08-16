package juegos;

import java.io.PrintStream;
import java.util.Scanner;

/** Implementación de {@link Consola} que usa la entrada y salida estándar. */
public class ConsolaSistema implements Consola {

    private final Scanner scanner;
    private final PrintStream salida;

    public ConsolaSistema() {
        this(new Scanner(System.in), System.out);
    }

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
