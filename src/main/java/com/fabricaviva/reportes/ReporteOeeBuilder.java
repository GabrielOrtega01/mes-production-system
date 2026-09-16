package com.fabricaviva.reportes;

/**
 * Patrón Builder (Constructor).
 * Los pasos de cálculo son iguales para todos los reportes y viven aquí una
 * sola vez. Cada constructor concreto solo decide cómo presentar el resultado.
 */
public abstract class ReporteOeeBuilder {

    protected ReporteOee reporte;

    public void reiniciar() {
        reporte = new ReporteOee();
    }

    public void establecerEquipoYTurno(String equipo, String turno) {
        reporte.setEquipo(equipo);
        reporte.setTurno(turno);
    }

    // Disponibilidad = tiempo que la máquina trabajó / tiempo planificado
    public void calcularDisponibilidad(MetricasTurno metricas) {
        reporte.setDisponibilidad(metricas.getTiempoOperativoMin() / metricas.getTiempoPlanificadoMin());
    }

    // Rendimiento = tiempo ideal para las piezas hechas / tiempo que la máquina trabajó
    public void calcularRendimiento(MetricasTurno metricas) {
        double tiempoIdeal = metricas.getTiempoCicloIdealMin() * metricas.getPiezasProducidas();
        reporte.setRendimiento(tiempoIdeal / metricas.getTiempoOperativoMin());
    }

    // Calidad = piezas buenas / piezas producidas
    public void calcularCalidad(MetricasTurno metricas) {
        reporte.setCalidad((double) metricas.getPiezasBuenas() / metricas.getPiezasProducidas());
    }

    public void calcularOeeFinal() {
        reporte.setOee(reporte.getDisponibilidad() * reporte.getRendimiento() * reporte.getCalidad());
    }

    // Único paso que cambia según el tipo de reporte.
    public abstract void agregarDesglose();

    public ReporteOee obtenerReporte() {
        return reporte;
    }
}
