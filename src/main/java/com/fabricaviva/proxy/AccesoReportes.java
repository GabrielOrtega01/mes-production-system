package com.fabricaviva.proxy;

import com.fabricaviva.reportes.MetricasTurno;
import com.fabricaviva.reportes.ReporteOee;
import com.fabricaviva.reportes.ReporteOeeBuilder;

/** Subject: interfaz común para el real y el proxy de generación de reportes. */
public interface AccesoReportes {
    ReporteOee construirReporte(ReporteOeeBuilder builder, MetricasTurno metricas,
                                String equipo, String turno);
}
