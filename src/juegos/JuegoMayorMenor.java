package juegos;

import java.util.Objects;
import java.util.Random;

/**
 * Juego "El mayor y menor": adivinar un número secreto entre 1 y 100
 * en un máximo de 5 intentos.
 */
public class JuegoMayorMenor implements Juego {

    public static final int RANGO_MINIMO = 1;
    public static final int RANGO_MAXIMO = 100;
    public static final int INTENTOS_MAXIMOS = 5;

    private final Consola consola;
    private final Random random;

    public JuegoMayorMenor(Consola consola, Random random) {
        this.consola = Objects.requireNonNull(consola, "La consola no puede ser null.");
        this.random = Objects.requireNonNull(random, "El generador aleatorio no puede ser null.");
    }

    @Override
    public String getNombre() {
        return "El mayor y menor";
    }

    @Override
    public void jugar() {
        int numeroSecreto = generarNumeroSecreto();

        consola.escribirLinea("=== " + getNombre() + " ===");
        consola.escribirLinea("Adivine el número secreto entre " + RANGO_MINIMO + " y " + RANGO_MAXIMO + ".");
        consola.escribirLinea("Tiene " + INTENTOS_MAXIMOS + " intentos.");

        for (int intento = 1; intento <= INTENTOS_MAXIMOS; intento++) {
            int numero = leerIntento(intento);
            Pista pista = evaluarIntento(numeroSecreto, numero);

            if (pista == Pista.ACIERTO) {
                consola.escribirLinea("¡Correcto! Adivinaste el número secreto: " + numeroSecreto + ".");
                return;
            }

            if (pista == Pista.MAYOR) {
                consola.escribirLinea("El número secreto es mayor.");
            } else {
                consola.escribirLinea("El número secreto es menor.");
            }
        }

        consola.escribirLinea("Perdiste. El número secreto era " + numeroSecreto + ".");
    }

    /**
     * Compara un número ingresado contra el número secreto.
     * Método visible para poder probar la regla de negocio de forma aislada.
     */
    public Pista evaluarIntento(int numeroSecreto, int numero) {
        if (numero == numeroSecreto) {
            return Pista.ACIERTO;
        }
        return numero < numeroSecreto ? Pista.MAYOR : Pista.MENOR;
    }

    private int generarNumeroSecreto() {
        return random.nextInt(RANGO_MAXIMO - RANGO_MINIMO + 1) + RANGO_MINIMO;
    }

    /**
     * Lee y valida un intento. Las entradas inválidas no consumen el intento:
     * se informa el error y se vuelve a pedir el número.
     */
    private int leerIntento(int numeroDeIntento) {
        while (true) {
            consola.escribir("Intento " + numeroDeIntento + " de " + INTENTOS_MAXIMOS
                    + " - Ingrese un número (" + RANGO_MINIMO + "-" + RANGO_MAXIMO + "): ");

            String linea = consola.leerLinea();
            try {
                int numero = Integer.parseInt(linea.trim());
                if (numero >= RANGO_MINIMO && numero <= RANGO_MAXIMO) {
                    return numero;
                }
                consola.escribirLinea("Entrada inválida: el número debe estar entre "
                        + RANGO_MINIMO + " y " + RANGO_MAXIMO + ".");
            } catch (NumberFormatException e) {
                consola.escribirLinea("Entrada inválida: debe ingresar un número entero.");
            }
        }
    }
}
