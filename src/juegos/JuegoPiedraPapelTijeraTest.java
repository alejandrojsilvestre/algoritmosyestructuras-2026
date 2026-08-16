package juegos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JuegoPiedraPapelTijeraTest {

    @Test
    @DisplayName("Piedra vence a tijera")
    void piedraVenceATijera() {
        assertEquals(ResultadoRonda.GANA_USUARIO, Eleccion.PIEDRA.contra(Eleccion.TIJERA));
    }

    @Test
    @DisplayName("Papel vence a piedra")
    void papelVenceAPiedra() {
        assertEquals(ResultadoRonda.GANA_USUARIO, Eleccion.PAPEL.contra(Eleccion.PIEDRA));
    }

    @Test
    @DisplayName("Tijera vence a papel")
    void tijeraVenceAPapel() {
        assertEquals(ResultadoRonda.GANA_USUARIO, Eleccion.TIJERA.contra(Eleccion.PAPEL));
    }

    @Test
    @DisplayName("Elecciones iguales producen empate")
    void eleccionesIgualesProducenEmpate() {
        assertEquals(ResultadoRonda.EMPATE, Eleccion.PIEDRA.contra(Eleccion.PIEDRA));
        assertEquals(ResultadoRonda.EMPATE, Eleccion.PAPEL.contra(Eleccion.PAPEL));
        assertEquals(ResultadoRonda.EMPATE, Eleccion.TIJERA.contra(Eleccion.TIJERA));
    }

    @Test
    @DisplayName("Piedra pierde contra papel")
    void piedraPierdeContraPapel() {
        assertEquals(ResultadoRonda.GANA_MAQUINA, Eleccion.PIEDRA.contra(Eleccion.PAPEL));
    }

    @Test
    @DisplayName("Juega exactamente 5 rondas")
    void jugar_juegaExactamenteCincoRondas() {
        // La máquina siempre elige piedra (0).
        ConsolaFalsa consola = new ConsolaFalsa("1", "1", "1", "1", "1");
        new JuegoPiedraPapelTijera(consola, new RandomFijo(0, 0, 0, 0, 0)).jugar();

        assertTrue(consola.contiene("Ronda 5"), "Debe llegar a la ronda 5.");
        assertFalse(consola.contiene("Ronda 6"), "No debe haber sexta ronda.");
        assertTrue(consola.contiene("Resultado final"), "Debe mostrar el resultado final.");
    }

    @Test
    @DisplayName("Cuenta el empate como una ronda más")
    void jugar_cuentaElEmpateComoRonda() {
        ConsolaFalsa consola = new ConsolaFalsa("1", "1", "1", "1", "1");
        new JuegoPiedraPapelTijera(consola, new RandomFijo(0, 0, 0, 0, 0)).jugar();

        assertEquals(5, consola.contar("Empate en esta ronda."), "Debe haber 5 empates.");
        assertTrue(consola.contiene("La partida terminó en empate."), "El resultado final debe ser empate.");
    }

    @Test
    @DisplayName("No cuenta una ronda cuando la entrada es inválida")
    void jugar_noCuentaRondaConEntradaInvalida() {
        // Primera entrada inválida; luego 5 entradas válidas (piedra).
        ConsolaFalsa consola = new ConsolaFalsa("9", "1", "1", "1", "1", "1");
        new JuegoPiedraPapelTijera(consola, new RandomFijo(0, 0, 0, 0, 0)).jugar();

        assertTrue(consola.contiene("Entrada inválida"), "Debe informar el error.");
        assertTrue(consola.contiene("Ronda 5"), "Debe llegar igualmente a la ronda 5.");
        assertFalse(consola.contiene("Ronda 6"), "La entrada inválida no debe consumir una ronda.");
    }

    @Test
    @DisplayName("Muestra la elección de la máquina y el ganador final")
    void jugar_muestraEleccionDeLaMaquinaYGanadorFinal() {
        // Usuario siempre papel (2), máquina siempre piedra (0): gana el usuario las 5 rondas.
        ConsolaFalsa consola = new ConsolaFalsa("2", "2", "2", "2", "2");
        new JuegoPiedraPapelTijera(consola, new RandomFijo(0, 0, 0, 0, 0)).jugar();

        assertTrue(consola.contiene("la máquina eligió piedra"), "Debe mostrar la elección de la máquina.");
        assertTrue(consola.contiene("¡Ganaste la partida!"), "Debe anunciar la victoria del usuario.");
        assertEquals(5, consola.contar("Ganaste esta ronda."), "Debe ganar las 5 rondas.");
    }

    @Test
    @DisplayName("Anuncia la victoria de la máquina cuando corresponde")
    void jugar_anunciaVictoriaDeLaMaquina() {
        // Usuario siempre piedra (1), máquina siempre papel (1): gana la máquina.
        ConsolaFalsa consola = new ConsolaFalsa("1", "1", "1", "1", "1");
        new JuegoPiedraPapelTijera(consola, new RandomFijo(1, 1, 1, 1, 1)).jugar();

        assertTrue(consola.contiene("La máquina ganó la partida."), "Debe anunciar la victoria de la máquina.");
    }
}
