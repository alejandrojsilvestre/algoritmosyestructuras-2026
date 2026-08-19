package juegos;

import java.util.List;
import java.util.Random;

/**
 * Generador aleatorio determinista para pruebas: devuelve los valores indicados
 * en orden, de modo que las pruebas sean reproducibles.
 */
class RandomFijo extends Random {

    private final List<Integer> valores;
    private int indice = 0;

    /**
     * Crea el generador con la secuencia de valores que devolverá.
     *
     * @param valores valores que devolverá {@link #nextInt(int)} en orden
     */
    RandomFijo(Integer... valores) {
        if (valores.length == 0) {
            throw new IllegalArgumentException("Debe proveer al menos un valor.");
        }
        this.valores = List.of(valores);
    }

    /**
     * Devuelve el siguiente valor de la secuencia, repitiéndola si se agota.
     *
     * @param bound límite superior exclusivo del valor
     * @return el valor configurado, validado contra {@code bound}
     */
    @Override
    public int nextInt(int bound) {
        int valor = valores.get(indice % valores.size());
        indice++;
        if (valor < 0 || valor >= bound) {
            throw new IllegalStateException("Valor " + valor + " fuera del rango [0, " + bound + ").");
        }
        return valor;
    }
}
