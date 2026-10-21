package com.fabricaviva;

import com.fabricaviva.ordenes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Factory Method — FabricaOrden")
class FactoryMethodTest {

    @Test
    @DisplayName("FabricaOrdenEstandar crea ordenes con prioridad NORMAL")
    void fabricaEstandarCreaNormal() {
        FabricaOrden fabrica = new FabricaOrdenEstandar();
        OrdenProduccion orden = fabrica.nuevaOrden("REF-001", 100);
        assertEquals(Prioridad.NORMAL, orden.getPrioridad());
    }

    @Test
    @DisplayName("FabricaOrdenUrgente crea ordenes con prioridad URGENTE")
    void fabricaUrgenteEsUrgente() {
        FabricaOrden fabrica = new FabricaOrdenUrgente();
        OrdenProduccion orden = fabrica.nuevaOrden("REF-002", 50);
        assertEquals(Prioridad.ALTA, orden.getPrioridad());
    }

    @Test
    @DisplayName("La orden retiene la referencia y cantidad solicitadas")
    void ordenConservaReferencia() {
        FabricaOrden fabrica = new FabricaOrdenEstandar();
        OrdenProduccion orden = fabrica.nuevaOrden("REF-XYZ", 200);
        assertEquals("REF-XYZ", orden.getReferencia());
        assertEquals(200, orden.getCantidad());
    }

    @Test
    @DisplayName("Dos ordenes distintas son objetos diferentes")
    void dosOrdenesDistintas() {
        FabricaOrden fabrica = new FabricaOrdenEstandar();
        OrdenProduccion o1 = fabrica.nuevaOrden("REF-001", 10);
        OrdenProduccion o2 = fabrica.nuevaOrden("REF-002", 20);
        assertNotSame(o1, o2);
    }
}
