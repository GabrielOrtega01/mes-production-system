package com.fabricaviva.reportes;

/**
 * Director del patrón Builder: siempre sigue la misma secuencia de pasos,
 * sin saber qué tipo de reporte resultará.
 */
public class GeneradorReportes {

    public ReporteOee construirReporte(ReporteOeeBuilder builder, MetricasTurno metricas,
                                       String equipo, String turno) {
        builder.reiniciar();
        builder.establecerEquipoYTurno(equipo, turno);
        builder.calcularDisponibilidad(metricas);
        builder.calcularRendimiento(metricas);
        builder.calcularCalidad(metricas);
        builder.calcularOeeFinal();
        builder.agregarDesglose();
        return builder.obtenerReporte();
    }
}
