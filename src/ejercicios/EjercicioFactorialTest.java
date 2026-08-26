package ejercicios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas de los casos de uso de {@link EjercicioFactorial}.
 *
 * <p>Capturan la salida estándar para verificar el comportamiento completo del
 * programa (camino feliz, bordes y errores).</p>
 */
class EjercicioFactorialTest {

    @Test
    @DisplayName("Calcula el factorial de 0 como 1")
    void factorialDeCeroEsUno() {
        String salida = ejecutarConEntrada("0", "-1");

        assertTrue(salida.contains("El factorial de 0 es: 1"),
                "0! debe ser 1, salida: " + salida);
    }

    @Test
    @DisplayName("Calcula el factorial de 1 como 1")
    void factorialDeUnoEsUno() {
        String salida = ejecutarConEntrada("1", "-1");

        assertTrue(salida.contains("El factorial de 1 es: 1"),
                "1! debe ser 1, salida: " + salida);
    }

    @Test
    @DisplayName("Calcula el factorial de 5 como 120")
    void factorialDeCincoEsCientoVeinte() {
        String salida = ejecutarConEntrada("5", "-1");

        assertTrue(salida.contains("El factorial de 5 es: 120"),
                "5! debe ser 120, salida: " + salida);
    }

    @Test
    @DisplayName("Calcula factoriales grandes sin desbordamiento usando BigInteger")
    void factorialDeVeinteSinDesbordamiento() {
        String salida = ejecutarConEntrada("20", "-1");

        assertTrue(salida.contains("El factorial de 20 es: 2432902008176640000"),
                "20! no cabe en int/long y debe mostrarse exacto, salida: " + salida);
    }

    @Test
    @DisplayName("Un número negativo distinto de -1 muestra error y vuelve a pedir")
    void numeroNegativoDistintoDeMenosUnoVuelveAPedir() {
        String salida = ejecutarConEntrada("-3", "5", "-1");

        assertTrue(salida.contains("Entrada inválida: debe ingresar un número "
                + "mayor o igual a 0 (o -1 para salir)."), "Debe informar el error, salida: " + salida);
        assertTrue(salida.contains("El factorial de 5 es: 120"),
                "Debe aceptar el número válido posterior, salida: " + salida);
    }

    @Test
    @DisplayName("Una entrada no numérica muestra error y vuelve a pedir")
    void textoNoNumericoVuelveAPedir() {
        String salida = ejecutarConEntrada("abc", "5", "-1");

        assertTrue(salida.contains("Entrada inválida: debe ingresar un número entero."),
                "Debe informar el error de texto, salida: " + salida);
        assertTrue(salida.contains("El factorial de 5 es: 120"),
                "Debe aceptar el número válido posterior, salida: " + salida);
    }

    @Test
    @DisplayName("Ingresar -1 muestra la despedida y termina")
    void menosUnoTerminaConDespedida() {
        String salida = ejecutarConEntrada("-1");

        assertTrue(salida.contains("¡Hasta luego!"), "Debe mostrar la despedida, salida: " + salida);
        assertEquals(false, salida.contains("El factorial de"),
                "No debe calcular factoriales al salir, salida: " + salida);
    }

    @Test
    @DisplayName("Repite el cálculo en bucle con varias entradas válidas")
    void repiteElCalculoEnBucle() {
        String salida = ejecutarConEntrada("2", "3", "-1");

        assertTrue(salida.contains("El factorial de 2 es: 2"), "salida: " + salida);
        assertTrue(salida.contains("El factorial de 3 es: 6"), "salida: " + salida);
    }

    @Test
    @DisplayName("Rechaza una entrada null en el constructor")
    void constructorConEntradaNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> new EjercicioFactorial(null));
    }

    /**
     * Ejecuta el programa con las líneas de entrada dadas y devuelve todo el
     * texto que se escribió en la salida estándar.
     *
     * @param lineas líneas que devolverá el {@link Scanner} en orden
     * @return la salida estándar capturada durante la ejecución
     */
    private String ejecutarConEntrada(String... lineas) {
        String entrada = String.join(System.lineSeparator(), lineas);
        Scanner scanner = new Scanner(entrada);

        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream salidaCapturada = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        System.setOut(salidaCapturada);
        try {
            new EjercicioFactorial(scanner).ejecutar();
        } finally {
            System.setOut(salidaOriginal);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
