package juegos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** Consola de prueba: responde con entradas predefinidas y captura las salidas. */
class ConsolaFalsa implements Consola {

    private final Deque<String> entradas;
    private final List<String> salidas = new ArrayList<>();

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

    boolean contiene(String texto) {
        return salidaCompleta().contains(texto);
    }

    int contar(String texto) {
        int total = 0;
        String completa = salidaCompleta();
        int indice = 0;
        while ((indice = completa.indexOf(texto, indice)) != -1) {
            total++;
            indice += texto.length();
        }
        return total;
    }

    String salidaCompleta() {
        return String.join("", salidas);
    }
}
