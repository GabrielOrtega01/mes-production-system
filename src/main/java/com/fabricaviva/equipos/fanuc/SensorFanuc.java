package com.fabricaviva.equipos.fanuc;

import com.fabricaviva.equipos.SensorProduccion;

public class SensorFanuc implements SensorProduccion {

    @Override
    public String protocolo() {
        return "FOCAS";
    }

    @Override
    public String reportarConteo(int piezas) {
        return "[FOCAS] Sensor Fanuc reporta " + piezas + " piezas";
    }
}
