package com.fabricaviva.pruebas;

import com.fabricaviva.observador.*;

public class PruebaObservador {
    public static void main(String[] args) {
        SensorMaquina sensor = new SensorMaquina("M-01");
        sensor.agregarObservador(new PanelSupervisor("Panel-A", 0.75));
        sensor.agregarObservador(new BitacoraEventos());

        System.out.println("=== Reporte de produccion normal ===");
        sensor.reportarOee(0.87);

        System.out.println("\n=== OEE bajo (debe alertar) ===");
        sensor.reportarOee(0.61);

        System.out.println("\n=== Parada de maquina ===");
        sensor.reportarParada();
    }
}
