package com.fabricaviva;

import com.fabricaviva.fachada.ControlTurnoFacade;
import com.fabricaviva.integracion.*;
import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.ordenes.Prioridad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Facade — ControlTurnoFacade")
class FacadeTest {

    private ControlTurnoFacade facade;

    @BeforeEach
    void setUp() {
        facade = new ControlTurnoFacade(new AdaptadorErp(new SistemaErpLegado()));
    }

    @Test
    @DisplayName("iniciarTurno() actualiza el turno activo en ConfiguracionPlanta")
    void iniciarTurnoActualizaConfig() {
        facade.iniciarTurno("NOCHE");
        assertEquals("NOCHE",
                com.fabricaviva.config.ConfiguracionPlanta.getInstancia().getTurnoActivo());
    }

    @Test
    @DisplayName("crearOrden() devuelve una orden con la referencia solicitada")
    void crearOrdenConReferencia() {
        OrdenProduccion orden = facade.crearOrden("REF-TEST", 50, false);
        assertEquals("REF-TEST", orden.getReferencia());
    }

    @Test
    @DisplayName("crearOrden urgente crea orden con prioridad URGENTE")
    void crearOrdenUrgenteEsUrgente() {
        OrdenProduccion orden = facade.crearOrden("REF-URG", 10, true);
        assertEquals(Prioridad.ALTA, orden.getPrioridad());
    }

    @Test
    @DisplayName("consultarCapacidadTotal() retorna > 0 con la planta interna")
    void capacidadTotalPositiva() {
        double cap = facade.consultarCapacidadTotal();
        assertTrue(cap > 0, "La planta interna del facade debe tener capacidad positiva");
    }

    @Test
    @DisplayName("calcularCostoConAjustes con solo base = costo base exacto")
    void costoSoloBase() {
        double costo = facade.calcularCostoConAjustes("OP-001", 800.0, false, false);
        assertEquals(800.0, costo, 0.01);
    }

    @Test
    @DisplayName("calcularCostoConAjustes prioritario + horasExtra = 800 * 1.25 * 1.30")
    void costoConAmbosAjustes() {
        double costo = facade.calcularCostoConAjustes("OP-002", 800.0, true, true);
        assertEquals(800.0 * 1.25 * 1.30, costo, 0.01);
    }

    @Test
    @DisplayName("cerrarTurno() no lanza excepcion")
    void cerrarTurnoSinExcepcion() {
        facade.iniciarTurno("MANANA");
        assertDoesNotThrow(() -> facade.cerrarTurno("MANANA", 300));
    }
}
