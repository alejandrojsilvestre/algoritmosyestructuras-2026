package juegos;

/**
 * Opciones del juego "Piedra, papel o tijera".
 *
 * <p>Cada constante define, mediante polimorfismo, a qué opción vence,
 * evitando cadenas de {@code if} o {@code switch} para discriminar tipos.</p>
 */
public enum Eleccion {
    PIEDRA("piedra") {
        @Override
        boolean venceA(Eleccion otra) {
            return otra == TIJERA;
        }
    },
    PAPEL("papel") {
        @Override
        boolean venceA(Eleccion otra) {
            return otra == PIEDRA;
        }
    },
    TIJERA("tijera") {
        @Override
        boolean venceA(Eleccion otra) {
            return otra == PAPEL;
        }
    };

    private final String nombre;

    Eleccion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Determina el resultado de la ronda desde la perspectiva del usuario.
     * Se invoca sobre la elección del usuario pasando la elección de la máquina.
     */
    public ResultadoRonda contra(Eleccion otra) {
        if (this == otra) {
            return ResultadoRonda.EMPATE;
        }
        return venceA(otra) ? ResultadoRonda.GANA_USUARIO : ResultadoRonda.GANA_MAQUINA;
    }

    abstract boolean venceA(Eleccion otra);
}
