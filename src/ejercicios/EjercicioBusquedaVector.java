package ejercicios;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Programa de consola independiente que:
 * <ol>
 *   <li>Pide la cantidad de elementos y un rango de valores.</li>
 *   <li>Genera un vector de enteros no negativos de forma aleatoria
 *       dentro del rango inclusivo indicado.</li>
 *   <li>Muestra el vector completo en el orden generado (sin ordenarlo).</li>
 *   <li>Explica que las posiciones se cuentan desde 0.</li>
 *   <li>Pide un elemento a buscar y muestra todas las posiciones
 *       (índices base 0) donde aparece, o un mensaje si no está.</li>
 * </ol>
 *
 * <p>Ingresar {@code 0} como cantidad de elementos termina el programa.</p>
 * <p>El programa realiza una única búsqueda y finaliza.</p>
 */
public class EjercicioBusquedaVector {

    /** Valor ingresado como cantidad que termina el programa. */
    private static final int VALOR_DE_SALIDA = 0;

    /** Explicación de que las posiciones se cuentan desde 0. */
    private static final String EXPLICACION_POSICIONES =
            "Las posiciones se cuentan desde 0. Ejemplo: en el vector [10, 20, 30], "
            + "el valor 20 está en la posición 1.";

    /** Fuente de lectura de las líneas ingresadas por el usuario. */
    private final Scanner entrada;

    /** Generador de números aleatorios para el vector. */
    private final Random aleatorio;

    /**
     * Crea el programa usando la entrada estándar y un generador aleatorio nuevo.
     *
     * @param entrada fuente de lectura de las líneas ingresadas
     */
    public EjercicioBusquedaVector(Scanner entrada) {
        this(entrada, new Random());
    }

    /**
     * Crea el programa con una fuente de lectura y un generador aleatorio propios.
     *
     * @param entrada   fuente de lectura de las líneas ingresadas
     * @param aleatorio generador de números aleatorios
     */
    public EjercicioBusquedaVector(Scanner entrada, Random aleatorio) {
        this.entrada = Objects.requireNonNull(entrada, "La entrada no puede ser null.");
        this.aleatorio = Objects.requireNonNull(aleatorio, "El generador aleatorio no puede ser null.");
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        new EjercicioBusquedaVector(new Scanner(System.in)).ejecutar();
    }

    /**
     * Ejecuta el programa: pide cantidad y rango, genera el vector, lo muestra,
     * explica el índice base 0, pide el elemento a buscar, muestra el resultado
     * y termina.
     */
    public void ejecutar() {
        int cantidad = leerCantidadElementos();
        if (cantidad == VALOR_DE_SALIDA) {
            System.out.println("¡Hasta luego!");
            return;
        }

        int limiteInicial = leerLimiteInicial();
        int limiteFinal = leerLimiteFinal(limiteInicial);

        int[] vector = generarVector(cantidad, limiteInicial, limiteFinal);
        mostrarVector(vector);
        mostrarExplicacionPosiciones();

        int elemento = leerElementoABuscar();
        int[] posiciones = buscarPosiciones(vector, elemento);
        mostrarResultadoBusqueda(elemento, posiciones);
    }

    /**
     * Lee la cantidad de elementos. Acepta valores mayores o iguales a cero;
     * {@code 0} se interpreta como fin del programa.
     *
     * @return la cantidad de elementos ingresada (mayor o igual a cero)
     */
    private int leerCantidadElementos() {
        while (true) {
            int valor = leerEntero("Ingrese la cantidad de elementos (0 para salir): ");
            if (valor < 0) {
                System.out.println("Entrada inválida: la cantidad debe ser mayor o igual a 0.");
                continue;
            }
            return valor;
        }
    }

    /**
     * Lee el límite inicial del rango. Acepta valores mayores o iguales a cero.
     *
     * @return el límite inicial ingresado
     */
    private int leerLimiteInicial() {
        while (true) {
            int valor = leerEntero("Ingrese el límite inicial del rango: ");
            if (valor < 0) {
                System.out.println("Entrada inválida: el límite inicial debe ser mayor o igual a 0.");
                continue;
            }
            return valor;
        }
    }

    /**
     * Lee el límite final del rango. Debe ser mayor o igual a cero y
     * estrictamente mayor que el límite inicial.
     *
     * @param limiteInicial límite inicial ya validado
     * @return el límite final ingresado
     */
    private int leerLimiteFinal(int limiteInicial) {
        while (true) {
            int valor = leerEntero("Ingrese el límite final del rango: ");
            if (valor < 0) {
                System.out.println("Entrada inválida: el límite final debe ser mayor o igual a 0.");
                continue;
            }
            if (valor <= limiteInicial) {
                System.out.println("Entrada inválida: el límite final debe ser mayor que el inicial.");
                continue;
            }
            return valor;
        }
    }

    /**
     * Lee el elemento a buscar. Acepta valores mayores o iguales a cero.
     *
     * @return el elemento a buscar ingresado
     */
    private int leerElementoABuscar() {
        while (true) {
            int valor = leerEntero("Ingrese el elemento a buscar: ");
            if (valor < 0) {
                System.out.println("Entrada inválida: el elemento a buscar debe ser mayor o igual a 0.");
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
     * Genera un vector de enteros aleatorios dentro del rango inclusivo
     * {@code [limiteInicial, limiteFinal]}.
     *
     * @param cantidad       cantidad de elementos del vector
     * @param limiteInicial  extremo inferior del rango (incluido)
     * @param limiteFinal    extremo superior del rango (incluido)
     * @return el vector generado
     */
    private int[] generarVector(int cantidad, int limiteInicial, int limiteFinal) {
        int[] vector = new int[cantidad];
        // El ancho siempre es >= 2 porque limiteFinal > limiteInicial ya fue validado,
        // por lo que nextInt nunca recibe un límite no positivo.
        int ancho = limiteFinal - limiteInicial + 1;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = aleatorio.nextInt(ancho) + limiteInicial;
        }
        return vector;
    }

    /**
     * Muestra el vector completo, en el orden en que fue generado y sin ordenar.
     *
     * @param vector vector a mostrar
     */
    private void mostrarVector(int[] vector) {
        StringBuilder resultado = new StringBuilder("Vector generado: [");
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) {
                resultado.append(", ");
            }
            resultado.append(vector[i]);
        }
        resultado.append("]");
        System.out.println(resultado);
    }

    /**
     * Muestra la explicación de que las posiciones se cuentan desde 0.
     */
    private void mostrarExplicacionPosiciones() {
        System.out.println(EXPLICACION_POSICIONES);
    }

    /**
     * Busca todas las posiciones (índices base 0) donde aparece el elemento
     * dentro del vector desordenado, mediante un recorrido lineal.
     *
     * @param vector   vector sobre el que se busca
     * @param elemento elemento a buscar
     * @return arreglo con las posiciones donde aparece; vacío si no aparece
     */
    private int[] buscarPosiciones(int[] vector, int elemento) {
        int apariciones = 0;
        for (int valor : vector) {
            if (valor == elemento) {
                apariciones++;
            }
        }

        int[] posiciones = new int[apariciones];
        int indice = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == elemento) {
                posiciones[indice] = i;
                indice++;
            }
        }
        return posiciones;
    }

    /**
     * Muestra el resultado de la búsqueda: todas las posiciones donde aparece
     * el elemento, o un mensaje si no se encuentra.
     *
     * @param elemento  elemento buscado
     * @param posiciones posiciones donde aparece el elemento
     */
    private void mostrarResultadoBusqueda(int elemento, int[] posiciones) {
        if (posiciones.length == 0) {
            System.out.println("El elemento " + elemento + " no se encuentra en el vector");
            return;
        }

        if (posiciones.length == 1) {
            System.out.println("El elemento " + elemento + " se encuentra en la posición: " + posiciones[0]);
            return;
        }

        StringBuilder resultado = new StringBuilder(
                "El elemento " + elemento + " se encuentra en las posiciones: ");
        for (int i = 0; i < posiciones.length; i++) {
            if (i > 0) {
                resultado.append(", ");
            }
            resultado.append(posiciones[i]);
        }
        System.out.println(resultado);
    }
}
