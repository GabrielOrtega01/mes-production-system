package com.fabricaviva;

import com.fabricaviva.mediador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Mediator — CentralMes / ColaboradorMes")
class MediadorTest {

    private CentralMes central;
    private ModuloCalidad calidad;
    private ModuloMantenimiento mantenimiento;

    @BeforeEach
    void setUp() {
        central       = new CentralMes();
        calidad       = new ModuloCalidad(central);
        mantenimiento = new ModuloMantenimiento(central);
    }

    @Test
    @DisplayName("El emisor no recibe su propio mensaje")
    void emisorNoRecibePropiaMensaje() {
        List<String> mensajes = new ArrayList<>();
        ColaboradorMes espia = new ColaboradorMes() {
            @Override public String getNombre() { return "CALIDAD"; }
            @Override public void recibirMensaje(String origen, String msg) { mensajes.add(msg); }
        };
        CentralMes c2 = new CentralMes();
        c2.registrar(espia);
        c2.enviar("CALIDAD", "Autoenvio");
        assertTrue(mensajes.isEmpty(), "Un modulo no debe recibir su propio mensaje");
    }

    @Test
    @DisplayName("Mensaje llega a todos los modulos menos al emisor")
    void mensajeLlegaAOtroModulo() {
        List<String> recibidos = new ArrayList<>();
        CentralMes c2 = new CentralMes();
        c2.registrar(new ColaboradorMes() {
            @Override public String getNombre() { return "PRODUCCION"; }
            @Override public void recibirMensaje(String origen, String msg) { recibidos.add(msg); }
        });
        c2.registrar(new ColaboradorMes() {
            @Override public String getNombre() { return "CALIDAD"; }
            @Override public void recibirMensaje(String origen, String msg) { recibidos.add(msg); }
        });
        c2.enviar("PRODUCCION", "Inicio turno");
        assertEquals(1, recibidos.size(), "Solo CALIDAD debe recibir; PRODUCCION es el emisor");
        assertEquals("Inicio turno", recibidos.get(0));
    }

    @Test
    @DisplayName("ModuloMantenimiento reporta a la central correctamente")
    void reporteMantenimientoLlegaAlResto() {
        assertDoesNotThrow(
            () -> mantenimiento.reportarFalla("MCN-01", "Sensor roto"),
            "reportarFalla no debe lanzar excepcion"
        );
    }

    @Test
    @DisplayName("ModuloCalidad reporta defectos a la central")
    void calidad_reportaDefectosAlResto() {
        assertDoesNotThrow(
            () -> calidad.reportarDefectos("LOTE-77", 8),
            "reportarDefectos no debe lanzar excepcion"
        );
    }
}
