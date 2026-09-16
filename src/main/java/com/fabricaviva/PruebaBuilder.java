package com.fabricaviva;

import com.fabricaviva.reportes.GeneradorReportes;
import com.fabricaviva.reportes.MetricasTurno;
import com.fabricaviva.reportes.ReporteDetalladoBuilder;
import com.fabricaviva.reportes.ReporteOee;
import com.fabricaviva.reportes.ReporteResumidoBuilder;

public class PruebaBuilder {

    public static void main(String[] args) {
        // Turno de 480 min con 24 min de paradas, ciclo ideal de 0.5 min,
        // 820 piezas producidas de las cuales 804 salieron buenas.
        MetricasTurno metricas = new MetricasTurno(480, 24, 0.5, 820, 804);
        GeneradorReportes generador = new GeneradorReportes();

        ReporteOee resumen = generador.construirReporte(
                new ReporteResumidoBuilder(), metricas, "CNC-01", "MANANA");
        ReporteOee detallado = generador.construirReporte(
                new ReporteDetalladoBuilder(), metricas, "CNC-01", "MANANA");

        System.out.println("== Reporte resumido (supervisor) ==");
        System.out.println(resumen);

        System.out.println("\n== Reporte detallado (analista) ==");
        System.out.println(detallado);

        System.out.println("\nMismo OEE en ambos reportes ? " + (resumen.getOee() == detallado.getOee()));
    }
}
