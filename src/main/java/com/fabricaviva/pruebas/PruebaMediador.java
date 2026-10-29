package com.fabricaviva.pruebas;

import com.fabricaviva.mediador.*;

public class PruebaMediador {
    public static void main(String[] args) {
        CentralMes central = new CentralMes();

        ModuloCalidad       calidad = new ModuloCalidad(central);
        ModuloMantenimiento manto   = new ModuloMantenimiento(central);

        System.out.println("=== Sensor detecta falla ===");
        central.enviar("Sensor-CNC-03", "FALLA maquina CNC-03 temperatura alta");

        System.out.println("\n=== Calidad emite advertencia ===");
        calidad.reportarDefectos("LOTE-X01", 7);
    }
}
