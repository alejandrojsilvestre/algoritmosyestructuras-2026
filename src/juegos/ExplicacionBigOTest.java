package juegos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExplicacionBigOTest {

    @Test
    @DisplayName("Devuelve el nombre Notación Big O")
    void getNombre_devuelveNotacionBigO() {
        ExplicacionBigO explicacion = new ExplicacionBigO(new ConsolaFalsa());

        assertEquals("Notación Big O", explicacion.getNombre());
    }

    @Test
    @DisplayName("Al ejecutarse imprime el mensaje que orienta a la documentación")
    void jugar_imprimeElMensajeQueOrientaALaDocumentacion() {
        ConsolaFalsa consola = new ConsolaFalsa();

        new ExplicacionBigO(consola).jugar();

        assertTrue(consola.contiene(ExplicacionBigO.MENSAJE),
                "Debe imprimir el mensaje que orienta a la documentación.");
    }

    @Test
    @DisplayName("Rechaza una consola nula")
    void constructor_rechazaConsolaNula() {
        assertThrows(NullPointerException.class, () -> new ExplicacionBigO(null));
    }
}
