package juegos;

import java.util.Objects;
import java.util.Random;

/**
 * Juego "Piedra, papel o tijera" contra la máquina.
 *
 * <p>Se juegan exactamente 5 rondas; los empates cuentan como ronda y,
 * al final, gana quien acumule más victorias (puede haber empate final).</p>
 */
public class JuegoPiedraPapelTijera implements Juego {

    public static final int TOTAL_RONDAS = 5;

    private final Consola consola;
    private final Random random;

    public JuegoPiedraPapelTijera(Consola consola, Random random) {
        this.consola = Objects.requireNonNull(consola, "La consola no puede ser null.");
        this.random = Objects.requireNonNull(random, "El generador aleatorio no puede ser null.");
    }

    @Override
    public String getNombre() {
        return "Piedra, papel o tijera";
    }

    @Override
    public void jugar() {
        consola.escribirLinea("=== " + getNombre() + " ===");
        consola.escribirLinea("Jugaremos " + TOTAL_RONDAS + " rondas. Gana quien acumule más victorias.");

        int victoriasUsuario = 0;
        int victoriasMaquina = 0;

        for (int ronda = 1; ronda <= TOTAL_RONDAS; ronda++) {
            Eleccion eleccionUsuario = leerEleccion(ronda);
            Eleccion eleccionMaquina = eleccionAleatoria();

            consola.escribirLinea("Ronda " + ronda + ": elegiste " + eleccionUsuario.getNombre()
                    + " y la máquina eligió " + eleccionMaquina.getNombre() + ".");

            ResultadoRonda resultado = eleccionUsuario.contra(eleccionMaquina);
            switch (resultado) {
                case GANA_USUARIO -> {
                    victoriasUsuario++;
                    consola.escribirLinea("Ganaste esta ronda.");
                }
                case GANA_MAQUINA -> {
                    victoriasMaquina++;
                    consola.escribirLinea("Perdiste esta ronda.");
                }
                case EMPATE -> consola.escribirLinea("Empate en esta ronda.");
            }
        }

        mostrarResultadoFinal(victoriasUsuario, victoriasMaquina);
    }

    private void mostrarResultadoFinal(int victoriasUsuario, int victoriasMaquina) {
        consola.escribirLinea("Resultado final: " + victoriasUsuario + " victorias tuyas contra "
                + victoriasMaquina + " de la máquina.");

        if (victoriasUsuario > victoriasMaquina) {
            consola.escribirLinea("¡Ganaste la partida!");
        } else if (victoriasMaquina > victoriasUsuario) {
            consola.escribirLinea("La máquina ganó la partida.");
        } else {
            consola.escribirLinea("La partida terminó en empate.");
        }
    }

    private Eleccion eleccionAleatoria() {
        Eleccion[] opciones = Eleccion.values();
        return opciones[random.nextInt(opciones.length)];
    }

    /** Lee y valida la elección del usuario; las entradas inválidas no cuentan como ronda. */
    private Eleccion leerEleccion(int ronda) {
        while (true) {
            consola.escribir("Ronda " + ronda + " de " + TOTAL_RONDAS
                    + " - Elija: 1 = piedra, 2 = papel, 3 = tijera: ");

            String linea = consola.leerLinea();
            try {
                int opcion = Integer.parseInt(linea.trim());
                switch (opcion) {
                    case 1 -> {
                        return Eleccion.PIEDRA;
                    }
                    case 2 -> {
                        return Eleccion.PAPEL;
                    }
                    case 3 -> {
                        return Eleccion.TIJERA;
                    }
                    default -> consola.escribirLinea("Entrada inválida: ingrese 1, 2 o 3.");
                }
            } catch (NumberFormatException e) {
                consola.escribirLinea("Entrada inválida: ingrese 1, 2 o 3.");
            }
        }
    }
}
