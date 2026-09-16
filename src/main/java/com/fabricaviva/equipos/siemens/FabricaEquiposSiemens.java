package com.fabricaviva.equipos.siemens;

import com.fabricaviva.equipos.ControladorMaquina;
import com.fabricaviva.equipos.FabricaEquipos;
import com.fabricaviva.equipos.SensorProduccion;

/** Fábrica concreta: toda la familia de equipos Siemens. */
public class FabricaEquiposSiemens implements FabricaEquipos {

    @Override
    public ControladorMaquina crearControlador() {
        return new ControladorSiemens();
    }

    @Override
    public SensorProduccion crearSensor() {
        return new SensorSiemens();
    }
}
