package juegos;

import java.util.List;
import java.util.Random;

/** Generador aleatorio determinista para pruebas: devuelve los valores indicados en orden. */
class RandomFijo extends Random {

    private final List<Integer> valores;
    private int indice = 0;

    RandomFijo(Integer... valores) {
        if (valores.length == 0) {
            throw new IllegalArgumentException("Debe proveer al menos un valor.");
        }
        this.valores = List.of(valores);
    }

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
