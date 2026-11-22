package com.fabricaviva;

import com.fabricaviva.memento.ConfiguracionTurno;
import com.fabricaviva.memento.HistorialTurnos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Memento — ConfiguracionTurno / HistorialTurnos")
class MementoTest {

    private ConfiguracionTurno config;
    private HistorialTurnos historial;

    @BeforeEach
    void setUp() {
        config    = new ConfiguracionTurno("MANANA", 0.85, 200);
        historial = new HistorialTurnos();
    }

    @Test
    @DisplayName("Guardar y restaurar recupera el estado original")
    void guardarYRestaurarEstado() {
        historial.guardar(config);
        config.setMetaOee(0.70);
        config.setCapacidadMaxima(100);

        historial.deshacer(config);

        assertEquals(0.85, config.getMetaOee(), 0.001);
        assertEquals(200,  config.getCapacidadMaxima());
    }

    @Test
    @DisplayName("El historial crece con cada guardado")
    void historialCreceConGuardados() {
        assertEquals(0, historial.tamano());
        historial.guardar(config);
        assertEquals(1, historial.tamano());
        historial.guardar(config);
        assertEquals(2, historial.tamano());
    }

    @Test
    @DisplayName("Deshacer multiples veces restaura estados en orden LIFO")
    void deshacerMultiplesVecesRestauraPorPilas() {
        historial.guardar(config);
        config.setMetaOee(0.70);

        historial.guardar(config);
        config.setMetaOee(0.60);

        historial.deshacer(config);
        assertEquals(0.70, config.getMetaOee(), 0.001, "Debe restaurar al segundo guardado");

        historial.deshacer(config);
        assertEquals(0.85, config.getMetaOee(), 0.001, "Debe restaurar al primer guardado");
    }

    @Test
    @DisplayName("Deshacer con historial vacio no lanza excepcion")
    void deshacerEnHistorialVacioNoFalla() {
        assertDoesNotThrow(() -> historial.deshacer(config));
        assertEquals(0.85, config.getMetaOee(), 0.001, "El estado no debe cambiar");
    }

    @Test
    @DisplayName("El turno guardado en el memento se restaura correctamente")
    void turnoSeRestauraCorrecto() {
        historial.guardar(config);
        config.setTurno("NOCHE");
        historial.deshacer(config);
        assertEquals("MANANA", config.getTurno());
    }
}
