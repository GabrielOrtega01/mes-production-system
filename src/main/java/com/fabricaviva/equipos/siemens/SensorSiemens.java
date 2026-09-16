package com.fabricaviva.equipos.siemens;

import com.fabricaviva.equipos.SensorProduccion;

public class SensorSiemens implements SensorProduccion {

    @Override
    public String protocolo() {
        return "OPC-UA";
    }

    @Override
    public String reportarConteo(int piezas) {
        return "[OPC-UA] Sensor Siemens reporta " + piezas + " piezas";
    }
}
