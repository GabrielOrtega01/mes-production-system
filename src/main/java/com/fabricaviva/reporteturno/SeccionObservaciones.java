package com.fabricaviva.reporteturno;

public class SeccionObservaciones extends DecoradorReporteTurno {

    private final String observaciones;

    public SeccionObservaciones(ReporteTurno componente, String observaciones) {
        super(componente);
        this.observaciones = observaciones;
    }

    @Override
    public String generar() {
        return componente.generar() + "\nObservaciones: " + observaciones;
    }

    @Override
    public String describir() {
        return componente.describir() + " + SeccionObservaciones";
    }
}
