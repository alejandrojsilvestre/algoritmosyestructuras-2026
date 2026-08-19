package juegos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Consola de prueba: responde con entradas predefinidas y captura las salidas.
 * Permite verificar el comportamiento de los juegos sin teclado ni consola real.
 */
class ConsolaFalsa implements Consola {

    private final Deque<String> entradas;
    private final List<String> salidas = new ArrayList<>();

    /**
     * Crea una consola falsa que devolverá las entradas indicadas en orden.
     *
     * @param entradas líneas que devolverá {@link #leerLinea()}
     */
    ConsolaFalsa(String... entradas) {
        this.entradas = new ArrayDeque<>(List.of(entradas));
    }

    @Override
    public String leerLinea() {
        if (entradas.isEmpty()) {
            throw new IllegalStateException("No hay más entradas simuladas.");
        }
        return entradas.poll();
    }

    @Override
    public void escribir(String mensaje) {
        salidas.add(mensaje);
    }

    @Override
    public void escribirLinea(String mensaje) {
        salidas.add(mensaje + System.lineSeparator());
    }

    /** Indica si toda la salida capturada contiene el texto dado. */
    boolean contiene(String texto) {
        return salidaCompleta().contains(texto);
    }

    /** Cuenta cuántas veces aparece el texto dado en la salida capturada. */
    int contar(String texto) {
        int total = 0;
        String completa = salidaCompleta();
        int indice = 0;
        while ((indice = completa.indexOf(texto, indice)) != -1) {
            total++;
            indice += texto.length();
        }
        return total;
    /** Devuelve toda la salida capturada como un único texto. */
    }

    String salidaCompleta() {
        return String.join("", salidas);
    }
}
