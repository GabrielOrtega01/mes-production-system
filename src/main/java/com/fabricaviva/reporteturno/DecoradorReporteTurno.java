package com.fabricaviva.reporteturno;

public abstract class DecoradorReporteTurno implements ReporteTurno {

    protected final ReporteTurno componente;

    protected DecoradorReporteTurno(ReporteTurno componente) {
        this.componente = componente;
    }
}
