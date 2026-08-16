package juegos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JuegoMayorMenorTest {

    @Test
    @DisplayName("Devuelve ACIERTO cuando el número coincide con el secreto")
    void evaluarIntento_devuelveAciertoCuandoElNumeroCoincide() {
        JuegoMayorMenor juego = new JuegoMayorMenor(new ConsolaFalsa(), new RandomFijo(0));
        assertEquals(Pista.ACIERTO, juego.evaluarIntento(42, 42));
    }

    @Test
    @DisplayName("Devuelve MAYOR cuando el número ingresado es menor que el secreto")
    void evaluarIntento_devuelveMayorCuandoElNumeroEsMenor() {
        JuegoMayorMenor juego = new JuegoMayorMenor(new ConsolaFalsa(), new RandomFijo(0));
        assertEquals(Pista.MAYOR, juego.evaluarIntento(50, 30));
    }

    @Test
    @DisplayName("Devuelve MENOR cuando el número ingresado es mayor que el secreto")
    void evaluarIntento_devuelveMenorCuandoElNumeroEsMayor() {
        JuegoMayorMenor juego = new JuegoMayorMenor(new ConsolaFalsa(), new RandomFijo(0));
        assertEquals(Pista.MENOR, juego.evaluarIntento(50, 70));
    }

    @Test
    @DisplayName("Muestra victoria cuando el usuario acierta")
    void jugar_muestraVictoriaCuandoAcierta() {
        ConsolaFalsa consola = new ConsolaFalsa("42");
        new JuegoMayorMenor(consola, new RandomFijo(41)).jugar(); // secreto = 42

        assertTrue(consola.contiene("¡Correcto!"), "Debe mostrarse el mensaje de victoria.");
        assertFalse(consola.contiene("Intento 2"), "No debe pedir más intentos tras acertar.");
    }

    @Test
    @DisplayName("Muestra derrota y el número secreto cuando se agotan los 5 intentos")
    void jugar_muestraDerrotaYNumeroSecretoCuandoNoAcierta() {
        ConsolaFalsa consola = new ConsolaFalsa("1", "2", "3", "4", "5");
        new JuegoMayorMenor(consola, new RandomFijo(41)).jugar(); // secreto = 42

        assertTrue(consola.contiene("Perdiste."), "Debe mostrarse el mensaje de derrota.");
        assertTrue(consola.contiene("42"), "Debe mostrarse el número secreto.");
        assertTrue(consola.contiene("Intento 5"), "Debe permitir exactamente 5 intentos.");
    }

    @Test
    @DisplayName("No descuenta intentos cuando la entrada no es un número entero")
    void jugar_noDescuentaIntentoConEntradaNoNumerica() {
        ConsolaFalsa consola = new ConsolaFalsa("abc", "42");
        new JuegoMayorMenor(consola, new RandomFijo(41)).jugar();

        assertTrue(consola.contiene("debe ingresar un número entero"), "Debe informar el error.");
        assertTrue(consola.contiene("¡Correcto!"), "Debe aceptar el intento válido posterior.");
        assertFalse(consola.contiene("Intento 2"), "La entrada inválida no debe consumir un intento.");
    }

    @Test
    @DisplayName("No descuenta intentos cuando el número está fuera del rango")
    void jugar_noDescuentaIntentoConNumeroFueraDeRango() {
        ConsolaFalsa consola = new ConsolaFalsa("0", "42");
        new JuegoMayorMenor(consola, new RandomFijo(41)).jugar();

        assertTrue(consola.contiene("debe estar entre"), "Debe informar el error de rango.");
        assertTrue(consola.contiene("¡Correcto!"), "Debe aceptar el intento válido posterior.");
        assertFalse(consola.contiene("Intento 2"), "La entrada inválida no debe consumir un intento.");
    }

    @Test
    @DisplayName("Acepta los límites del rango (1 y 100) como intentos válidos")
    void jugar_aceptaLosLimitesDelRango() {
        // Número secreto = 1
        ConsolaFalsa consolaMinimo = new ConsolaFalsa("1");
        new JuegoMayorMenor(consolaMinimo, new RandomFijo(0)).jugar();
        assertTrue(consolaMinimo.contiene("¡Correcto!"));

        // Número secreto = 100
        ConsolaFalsa consolaMaximo = new ConsolaFalsa("100");
        new JuegoMayorMenor(consolaMaximo, new RandomFijo(99)).jugar();
        assertTrue(consolaMaximo.contiene("¡Correcto!"));
    }
}
