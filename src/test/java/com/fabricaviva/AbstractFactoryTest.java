package com.fabricaviva;

import com.fabricaviva.equipos.*;
import com.fabricaviva.equipos.siemens.*;
import com.fabricaviva.equipos.fanuc.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Abstract Factory — FabricaEquipos")
class AbstractFactoryTest {

    @Test
    @DisplayName("FabricaEquiposSiemens produce controlador OPC-UA")
    void siemensProtocolo() {
        FabricaEquipos fabrica = new FabricaEquiposSiemens();
        ControladorMaquina ctrl = fabrica.crearControlador();
        assertEquals("OPC-UA", ctrl.protocolo());
    }

    @Test
    @DisplayName("FabricaEquiposFanuc produce controlador con protocolo FOCAS")
    void fanucProtocolo() {
        FabricaEquipos fabrica = new FabricaEquiposFanuc();
        ControladorMaquina ctrl = fabrica.crearControlador();
        assertEquals("FOCAS", ctrl.protocolo(),
                "El controlador Fanuc usa el protocolo FOCAS");
    }

    @Test
    @DisplayName("Controlador Siemens inicia ciclo con la referencia dada")
    void siemensIniciaCiclo() {
        ControladorMaquina ctrl = new FabricaEquiposSiemens().crearControlador();
        String resultado = ctrl.iniciarCiclo("ORD-100");
        assertTrue(resultado.contains("ORD-100"), "La respuesta debe incluir la referencia de la orden");
    }

    @Test
    @DisplayName("Cada familia crea su propio tipo de sensor")
    void sensorDistintoXFamilia() {
        SensorProduccion sensorS = new FabricaEquiposSiemens().crearSensor();
        SensorProduccion sensorF = new FabricaEquiposFanuc().crearSensor();
        assertNotEquals(sensorS.getClass(), sensorF.getClass(),
                "Cada familia debe producir su propio tipo de sensor");
    }
}
