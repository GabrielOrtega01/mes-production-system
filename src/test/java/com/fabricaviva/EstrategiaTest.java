package com.fabricaviva;

import com.fabricaviva.estrategia.*;
import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.ordenes.Prioridad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Strategy — PlanificadorOrdenes / EstrategiaOrdenamiento")
class EstrategiaTest {

    private List<OrdenProduccion> ordenesBase() {
        return Arrays.asList(
            new OrdenProduccion("OP-A", 10, Prioridad.NORMAL),
            new OrdenProduccion("OP-B", 20, Prioridad.ALTA),
            new OrdenProduccion("OP-C", 15, Prioridad.NORMAL)
        );
    }

    @Test
    @DisplayName("EstrategiaFIFO mantiene el orden original de insercion")
    void fifoMantieneOrdenOriginal() {
        List<OrdenProduccion> ordenes = ordenesBase();
        List<OrdenProduccion> resultado = new EstrategiaFIFO().ordenar(ordenes);
        assertEquals("OP-A", resultado.get(0).getReferencia());
        assertEquals("OP-B", resultado.get(1).getReferencia());
        assertEquals("OP-C", resultado.get(2).getReferencia());
    }

    @Test
    @DisplayName("EstrategiaPrioridad coloca las ordenes ALTA al inicio")
    void prioridadPoneAltasPrimero() {
        List<OrdenProduccion> ordenes = ordenesBase();
        List<OrdenProduccion> resultado = new EstrategiaPrioridad().ordenar(ordenes);
        assertEquals(Prioridad.ALTA, resultado.get(0).getPrioridad(),
            "La primera orden debe ser ALTA");
    }

    @Test
    @DisplayName("EstrategiaPrioridad no pierde ninguna orden")
    void prioridadMantieneTotalOrdenes() {
        List<OrdenProduccion> ordenes = ordenesBase();
        List<OrdenProduccion> resultado = new EstrategiaPrioridad().ordenar(ordenes);
        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("PlanificadorOrdenes usa FIFO por defecto")
    void planificadorUsaFifoPorDefecto() {
        PlanificadorOrdenes planificador = new PlanificadorOrdenes(new EstrategiaFIFO());
        List<OrdenProduccion> ordenes = ordenesBase();
        List<OrdenProduccion> resultado = planificador.planificar(ordenes);
        assertEquals("OP-A", resultado.get(0).getReferencia());
    }

    @Test
    @DisplayName("PlanificadorOrdenes permite cambiar estrategia en runtime")
    void planificadorCambiaEstrategiaEnRuntime() {
        PlanificadorOrdenes planificador = new PlanificadorOrdenes(new EstrategiaFIFO());
        planificador.setEstrategia(new EstrategiaPrioridad());
        List<OrdenProduccion> ordenes = ordenesBase();
        List<OrdenProduccion> resultado = planificador.planificar(ordenes);
        assertEquals(Prioridad.ALTA, resultado.get(0).getPrioridad(),
            "Tras el cambio a prioridad, la primera orden debe ser ALTA");
    }

    @Test
    @DisplayName("EstrategiaFIFO.nombre() retorna 'FIFO'")
    void nombreEstrategiaFifo() {
        assertEquals("FIFO", new EstrategiaFIFO().nombre());
    }

    @Test
    @DisplayName("EstrategiaPrioridad.nombre() retorna 'PRIORIDAD'")
    void nombreEstrategiaPrioridad() {
        assertEquals("PRIORIDAD", new EstrategiaPrioridad().nombre());
    }
}
