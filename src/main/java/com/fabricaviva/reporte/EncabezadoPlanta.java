package com.fabricaviva.reporte;

public class EncabezadoPlanta extends DecoradorReporteTurno {

    private final String nombrePlanta;

    public EncabezadoPlanta(ReporteTurno componente, String nombrePlanta) {
        super(componente);
        this.nombrePlanta = nombrePlanta;
    }

    @Override
    public String generar() {
        return "=== " + nombrePlanta + " — Reporte Oficial ===\n" + componente.generar();
    }

    @Override
    public String describir() {
        return componente.describir() + " + EncabezadoPlanta";
    }
}
