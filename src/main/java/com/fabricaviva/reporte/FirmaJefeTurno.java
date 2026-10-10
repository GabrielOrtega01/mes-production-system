package com.fabricaviva.reporte;

public class FirmaJefeTurno extends DecoradorReporteTurno {

    private final String nombreJefe;

    public FirmaJefeTurno(ReporteTurno componente, String nombreJefe) {
        super(componente);
        this.nombreJefe = nombreJefe;
    }

    @Override
    public String generar() {
        return componente.generar() + "\nFirma Jefe de Turno: " + nombreJefe;
    }

    @Override
    public String describir() {
        return componente.describir() + " + FirmaJefeTurno";
    }
}
