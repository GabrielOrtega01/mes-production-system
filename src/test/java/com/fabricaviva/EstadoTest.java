package com.fabricaviva;

import com.fabricaviva.estado.ContextoMaquina;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("State — ContextoMaquina / EstadoMaquina")
class EstadoTest {

    @Test
    @DisplayName("Estado inicial de la maquina es IDLE")
    void estadoInicialEsIdle() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-10");
        assertEquals("IDLE", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("iniciar() transiciona de IDLE a PRODUCIENDO")
    void iniciarCambiaAProduciendo() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-11");
        maquina.iniciar();
        assertEquals("PRODUCIENDO", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("reportarFalla() en PRODUCIENDO transiciona a FALLA")
    void fallaEnProduciendoCambiaAFalla() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-12");
        maquina.iniciar();
        maquina.reportarFalla("Sobrecalentamiento");
        assertEquals("FALLA", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("completarMantenimiento() en FALLA regresa a IDLE")
    void mantenimientoCompleto_vuelveAIdle() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-13");
        maquina.iniciar();
        maquina.reportarFalla("Sensor defectuoso");
        maquina.completarMantenimiento();
        assertEquals("IDLE", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("pausar() en PRODUCIENDO regresa a IDLE")
    void pausarEnProduciendoRegresaAIdle() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-14");
        maquina.iniciar();
        maquina.pausar();
        assertEquals("IDLE", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("pausar() en IDLE no lanza excepcion")
    void pausarEnIdleNoFalla() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-15");
        assertDoesNotThrow(maquina::pausar);
        assertEquals("IDLE", maquina.getEstadoActual());
    }

    @Test
    @DisplayName("iniciar() en FALLA no cambia el estado")
    void iniciarEnFallaMantieneFalla() {
        ContextoMaquina maquina = new ContextoMaquina("MCN-16");
        maquina.reportarFalla("Error electrico");
        assertDoesNotThrow(() -> maquina.iniciar());
        assertEquals("FALLA", maquina.getEstadoActual(),
            "No se puede iniciar produccion con una falla activa");
    }
}
