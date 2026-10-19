package com.fabricaviva.reporteturno;

public class ReporteBaseTurno implements ReporteTurno {

    private final String codigoTurno;
    private final int piezasProducidas;
    private final double oee;

    public ReporteBaseTurno(String codigoTurno, int piezasProducidas, double oee) {
        this.codigoTurno = codigoTurno;
        this.piezasProducidas = piezasProducidas;
        this.oee = oee;
    }

    @Override
    public String generar() {
        return String.format("Turno: %s | Piezas producidas: %d | OEE: %.1f%%",
                codigoTurno, piezasProducidas, oee);
    }

    @Override
    public String describir() {
        return "ReporteBaseTurno";
    }
}
