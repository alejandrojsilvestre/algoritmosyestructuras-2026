package juegos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @Test
    @DisplayName("Muestra las opciones y sale correctamente con la opción Salir")
    void iniciar_muestraLasOpcionesYSale() {
        JuegoEspia juego1 = new JuegoEspia("Juego uno");
        JuegoEspia juego2 = new JuegoEspia("Juego dos");
        ConsolaFalsa consola = new ConsolaFalsa("3");

        new Menu(consola, List.of(juego1, juego2)).iniciar();

        assertTrue(consola.contiene("1. Juego uno"), "Debe listar el primer juego.");
        assertTrue(consola.contiene("2. Juego dos"), "Debe listar el segundo juego.");
        assertTrue(consola.contiene("3. Salir"), "Debe listar la opción Salir.");
        assertTrue(consola.contiene("¡Hasta luego!"), "Debe despedirse al salir.");
        assertFalse(juego1.jugado, "No debe ejecutar ningún juego al salir.");
        assertFalse(juego2.jugado, "No debe ejecutar ningún juego al salir.");
    }

    @Test
    @DisplayName("Ejecuta únicamente el juego elegido")
    void iniciar_ejecutaUnicamenteElJuegoElegido() {
        JuegoEspia juego1 = new JuegoEspia("Juego uno");
        JuegoEspia juego2 = new JuegoEspia("Juego dos");
        ConsolaFalsa consola = new ConsolaFalsa("1", "3");

        new Menu(consola, List.of(juego1, juego2)).iniciar();

        assertTrue(juego1.jugado, "Debe ejecutar el primer juego.");
        assertFalse(juego2.jugado, "No debe ejecutar el segundo juego.");
    }

    @Test
    @DisplayName("Rechaza una opción inválida y vuelve a pedirla")
    void iniciar_rechazaOpcionInvalidaYVuelveAPedirla() {
        JuegoEspia juego1 = new JuegoEspia("Juego uno");
        ConsolaFalsa consola = new ConsolaFalsa("abc", "2");

        new Menu(consola, List.of(juego1)).iniciar();

        assertTrue(consola.contiene("Entrada inválida"), "Debe informar el error de opción inválida.");
        assertTrue(consola.contiene("¡Hasta luego!"), "Debe salir tras la opción válida.");
        assertFalse(juego1.jugado, "No debe ejecutar el juego.");
    }

    @Test
    @DisplayName("Vuelve al menú después de terminar una partida")
    void iniciar_vuelveAlMenuDespuesDeUnaPartida() {
        JuegoEspia juego1 = new JuegoEspia("Juego uno");
        JuegoEspia juego2 = new JuegoEspia("Juego dos");
        ConsolaFalsa consola = new ConsolaFalsa("2", "3");

        new Menu(consola, List.of(juego1, juego2)).iniciar();

        assertTrue(juego2.jugado, "Debe ejecutar el segundo juego.");
        assertEquals(2, consola.contar("=== Menú principal ==="), "Debe volver a mostrar el menú.");
    }

    @Test
    @DisplayName("Muestra la opción Notación Big O y vuelve al menú al elegirla")
    void iniciar_muestraOpcionBigOYVuelveAlMenu() {
        JuegoEspia juego1 = new JuegoEspia("Juego uno");
        JuegoEspia juego2 = new JuegoEspia("Juego dos");
        ConsolaFalsa consola = new ConsolaFalsa("3", "4");

        new Menu(consola, List.of(juego1, juego2, new ExplicacionBigO(consola))).iniciar();

        assertTrue(consola.contiene("3. Notación Big O"), "Debe listar la opción Notación Big O.");
        assertTrue(consola.contiene("4. Salir"), "Salir debe pasar a ser la opción 4.");
        assertTrue(consola.contiene(ExplicacionBigO.MENSAJE),
                "Debe mostrar el mensaje que orienta a la documentación.");
        assertTrue(consola.contiene("¡Hasta luego!"), "Debe salir al elegir la opción 4.");
        assertFalse(juego1.jugado, "No debe ejecutar el primer juego.");
        assertFalse(juego2.jugado, "No debe ejecutar el segundo juego.");
    }
}
