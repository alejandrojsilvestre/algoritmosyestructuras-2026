package juegos;

/** Juego de prueba que registra si fue ejecutado. */
class JuegoEspia implements Juego {

    private final String nombre;
    boolean jugado = false;

    JuegoEspia(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void jugar() {
        jugado = true;
    }
}
