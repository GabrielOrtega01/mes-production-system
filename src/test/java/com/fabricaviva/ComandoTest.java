package com.fabricaviva;

import com.fabricaviva.comando.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Command — GestorComandos / ComandoProduccion")
class ComandoTest {

    private LineaProduccionReceiver linea;
    private GestorComandos gestor;

    @BeforeEach
    void setUp() {
        linea  = new LineaProduccionReceiver("LINEA-A");
        gestor = new GestorComandos();
    }

    @Test
    @DisplayName("Ejecutar IniciarOrden activa la linea de produccion")
    void ejecutarIniciarActivaLinea() {
        gestor.ejecutar(new ComandoIniciarOrden(linea, "REF-100", 50));
        assertTrue(linea.isActiva(), "La linea debe quedar activa tras iniciar la orden");
    }

    @Test
    @DisplayName("Deshacer IniciarOrden detiene la linea (pausa)")
    void deshacerIniciarDetieneLinea() {
        gestor.ejecutar(new ComandoIniciarOrden(linea, "REF-200", 30));
        assertTrue(linea.isActiva());
        gestor.deshacer();
        assertFalse(linea.isActiva(), "La linea debe quedar inactiva tras deshacer");
    }

    @Test
    @DisplayName("El historial crece con cada comando ejecutado")
    void historialCreceConCadaComando() {
        assertEquals(0, gestor.tamanoHistorial());
        gestor.ejecutar(new ComandoIniciarOrden(linea, "REF-300", 10));
        assertEquals(1, gestor.tamanoHistorial());
        gestor.ejecutar(new ComandoCerrarTurno(linea, "MANANA", 100));
        assertEquals(2, gestor.tamanoHistorial());
    }

    @Test
    @DisplayName("El historial decrece al deshacer")
    void historialDecreceAlDeshacer() {
        gestor.ejecutar(new ComandoIniciarOrden(linea, "REF-400", 20));
        gestor.ejecutar(new ComandoCerrarTurno(linea, "TARDE", 80));
        assertEquals(2, gestor.tamanoHistorial());
        gestor.deshacer();
        assertEquals(1, gestor.tamanoHistorial());
    }

    @Test
    @DisplayName("Deshacer con historial vacio no lanza excepcion")
    void deshacerEnHistorialVacioNoFalla() {
        assertDoesNotThrow(() -> gestor.deshacer());
        assertEquals(0, gestor.tamanoHistorial());
    }

    @Test
    @DisplayName("descripcion() del comando contiene la referencia de la orden")
    void descripcionContieneReferencia() {
        ComandoProduccion cmd = new ComandoIniciarOrden(linea, "REF-500", 15);
        assertTrue(cmd.descripcion().contains("REF-500"));
    }
}
