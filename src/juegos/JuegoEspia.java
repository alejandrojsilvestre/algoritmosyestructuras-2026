package juegos;

/**
 * Juego de prueba (espía) que registra si fue ejecutado.
 * Se usa en las pruebas del menú para verificar qué opción se eligió.
 */
class JuegoEspia implements Juego {

    private final String nombre;
    boolean jugado = false;

    /** Crea el espía con el nombre que mostrará en el menú. */
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
