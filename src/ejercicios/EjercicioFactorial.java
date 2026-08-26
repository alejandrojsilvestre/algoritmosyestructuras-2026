package ejercicios;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

/**
 * Programa de consola independiente que:
 * <ol>
 *   <li>Pide un número entero {@code n}.</li>
 *   <li>Calcula su factorial mediante una función recursiva,
 *       usando {@link BigInteger} para evitar desbordamientos.</li>
 *   <li>Muestra el resultado por pantalla.</li>
 *   <li>Repite la operación en bucle hasta que el usuario ingresa
 *       {@code -1} para salir.</li>
 * </ol>
 *
 * <p>El valor {@code 0} es un cálculo válido ({@code 0! = 1}), por lo que el
 * centinela de salida es {@code -1}.</p>
 */
public class EjercicioFactorial {

    /** Valor ingresado que termina el programa. */
    private static final int VALOR_DE_SALIDA = -1;

    /** Fuente de lectura de las líneas ingresadas por el usuario. */
    private final Scanner entrada;

    /**
     * Crea el programa usando la entrada estándar.
     *
     * @param entrada fuente de lectura de las líneas ingresadas
     */
    public EjercicioFactorial(Scanner entrada) {
        this.entrada = Objects.requireNonNull(entrada, "La entrada no puede ser null.");
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        new EjercicioFactorial(new Scanner(System.in)).ejecutar();
    }

    /**
     * Ejecuta el bucle principal: pide un número, calcula su factorial,
     * muestra el resultado y repite hasta que se ingrese {@code -1}.
     */
    public void ejecutar() {
        while (true) {
            int numero = leerNumero();
            if (numero == VALOR_DE_SALIDA) {
                System.out.println("¡Hasta luego!");
                return;
            }
            mostrarFactorial(numero);
        }
    }

    /**
     * Lee un número entero. Acepta valores mayores o iguales a cero y el
     * centinela {@code -1}; cualquier otro valor negativo se rechaza y se
     * vuelve a pedir.
     *
     * @return el número ingresado (mayor o igual a cero, o {@code -1})
     */
    private int leerNumero() {
        while (true) {
            int valor = leerEntero("Ingrese un número entero (-1 para salir): ");
            if (valor == VALOR_DE_SALIDA) {
                return valor;
            }
            if (valor < 0) {
                System.out.println("Entrada inválida: debe ingresar un número "
                        + "mayor o igual a 0 (o -1 para salir).");
                continue;
            }
            return valor;
        }
    }

    /**
     * Lee un número entero, volviendo a pedir ante entradas que no lo sean.
     *
     * @param mensaje mensaje de solicitud que se muestra al usuario
     * @return el entero ingresado
     */
    private int leerEntero(String mensaje) {
        while (true) {
            // Se fuerza el volcado del búfer para que el mensaje se vea antes de leer.
            System.out.print(mensaje);
            System.out.flush();
            String linea = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: debe ingresar un número entero.");
            }
        }
    }

    /**
     * Calcula el factorial de {@code n} de forma recursiva usando
     * {@link BigInteger}, por lo que no sufre desbordamientos para valores
     * grandes de {@code n}.
     *
     * @param n número entero mayor o igual a cero
     * @return el factorial de {@code n}
     * @throws IllegalArgumentException si {@code n} es negativo
     */
    private BigInteger factorial(int n) {

        System.out.println(n);
        
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El factorial no está definido para números negativos: " + n);
        }
        // Caso base: 0! = 1 y 1! = 1. El centinela -1 nunca llega hasta aquí
        // porque leerNumero() lo intercepta antes de calcular.
        if (n <= 1) {
            return BigInteger.ONE;
        }
        // Paso recursivo: n! = n * (n - 1)!. Se usa BigInteger en la
        // multiplicación para que el resultado no desborde con valores grandes.
        return BigInteger.valueOf(n).multiply(factorial(n-1));
    }

    private int factorialInt(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El factorial no está definido para números negativos: " + n);
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorialInt(n - 1);
    }

    private int factorialIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El factorial no está definido para números negativos: " + n);
        }
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    /**
     * Muestra el factorial del número ingresado.
     *
     * @param numero número cuyo factorial se calcula
     */
    private void mostrarFactorial(int numero) {
        System.out.println("El factorial de " + numero + " es: " + factorial(numero));
    }
}
