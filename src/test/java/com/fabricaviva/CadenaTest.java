package com.fabricaviva;

import com.fabricaviva.cadena.*;
import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.ordenes.Prioridad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Chain of Responsibility — ValidadorOrden")
class CadenaTest {

    @Test
    @DisplayName("Orden valida pasa todos los eslabones y es aprobada")
    void ordenAprobadaConRecursosAdecuados() {
        GestorOrdenes gestor = new GestorOrdenes(100, 50, false);
        OrdenProduccion orden = new OrdenProduccion("OP-001", 30, Prioridad.NORMAL);
        ResultadoOrden resultado = gestor.validar(orden);
        assertTrue(resultado.aprobada, "Debe ser aprobada con stock=100, cap=50, cantidad=30");
    }

    @Test
    @DisplayName("Orden rechazada cuando cantidad supera el stock disponible")
    void rechazadaPorStockInsuficiente() {
        GestorOrdenes gestor = new GestorOrdenes(20, 50, false);
        OrdenProduccion orden = new OrdenProduccion("OP-002", 25, Prioridad.NORMAL);
        ResultadoOrden resultado = gestor.validar(orden);
        assertFalse(resultado.aprobada);
        assertEquals("ValidadorStock", resultado.validadorQueRechazo);
    }

    @Test
    @DisplayName("Orden rechazada cuando cantidad supera la capacidad de la linea")
    void rechazadaPorCapacidadInsuficiente() {
        GestorOrdenes gestor = new GestorOrdenes(100, 10, false);
        OrdenProduccion orden = new OrdenProduccion("OP-003", 15, Prioridad.NORMAL);
        ResultadoOrden resultado = gestor.validar(orden);
        assertFalse(resultado.aprobada);
        assertEquals("ValidadorCapacidad", resultado.validadorQueRechazo);
    }

    @Test
    @DisplayName("Orden NORMAL rechazada en modo saturado")
    void rechazadaPorPrioridadEnModoSaturado() {
        GestorOrdenes gestor = new GestorOrdenes(100, 100, true);
        OrdenProduccion orden = new OrdenProduccion("OP-004", 10, Prioridad.NORMAL);
        ResultadoOrden resultado = gestor.validar(orden);
        assertFalse(resultado.aprobada);
        assertEquals("ValidadorPrioridad", resultado.validadorQueRechazo);
    }

    @Test
    @DisplayName("Orden ALTA pasa aunque planta este en modo saturado")
    void ordenAltaAprobadaEnModoSaturado() {
        GestorOrdenes gestor = new GestorOrdenes(100, 100, true);
        OrdenProduccion orden = new OrdenProduccion("OP-005", 10, Prioridad.ALTA);
        ResultadoOrden resultado = gestor.validar(orden);
        assertTrue(resultado.aprobada);
    }

    @Test
    @DisplayName("ResultadoOrden.ok() tiene aprobada=true y sin validadorQueRechazo")
    void resultadoOkTieneAprobadaTrue() {
        ResultadoOrden r = ResultadoOrden.ok();
        assertTrue(r.aprobada);
        assertNull(r.validadorQueRechazo);
    }
}
