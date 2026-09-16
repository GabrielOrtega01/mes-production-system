package com.fabricaviva.equipos.fanuc;

import com.fabricaviva.equipos.ControladorMaquina;
import com.fabricaviva.equipos.FabricaEquipos;
import com.fabricaviva.equipos.SensorProduccion;

/** Fábrica concreta: toda la familia de equipos Fanuc. */
public class FabricaEquiposFanuc implements FabricaEquipos {

    @Override
    public ControladorMaquina crearControlador() {
        return new ControladorFanuc();
    }

    @Override
    public SensorProduccion crearSensor() {
        return new SensorFanuc();
    }
}
