package com.fabricaviva.proxy;

import com.fabricaviva.reportes.GeneradorReportes;
import com.fabricaviva.reportes.MetricasTurno;
import com.fabricaviva.reportes.ReporteOee;
import com.fabricaviva.reportes.ReporteOeeBuilder;

/** RealSubject: delega la construcción del reporte al GeneradorReportes existente. */
public class GeneradorReportesReal implements AccesoReportes {

    private final GeneradorReportes generador = new GeneradorReportes();

    @Override
    public ReporteOee construirReporte(ReporteOeeBuilder builder,
                                       MetricasTurno metricas,
                                       String equipo, String turno) {
        return generador.construirReporte(builder, metricas, equipo, turno);
    }
}
