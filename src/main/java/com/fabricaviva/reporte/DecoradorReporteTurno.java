package com.fabricaviva.reporte;

public abstract class DecoradorReporteTurno implements ReporteTurno {

    protected final ReporteTurno componente;

    protected DecoradorReporteTurno(ReporteTurno componente) {
        this.componente = componente;
    }
}
