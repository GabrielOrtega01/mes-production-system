package com.fabricaviva.reporte;

public class MarcaAguaBorrador extends DecoradorReporteTurno {

    public MarcaAguaBorrador(ReporteTurno componente) {
        super(componente);
    }

    @Override
    public String generar() {
        return "[BORRADOR — NO VALIDO PARA ARCHIVO]\n" + componente.generar();
    }

    @Override
    public String describir() {
        return componente.describir() + " + MarcaAguaBorrador";
    }
}
