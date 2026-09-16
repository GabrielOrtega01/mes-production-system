package com.fabricaviva.reportes;

import java.util.Locale;

/** Para el analista de mejora continua: necesita ver dónde se pierde eficiencia. */
public class ReporteDetalladoBuilder extends ReporteOeeBuilder {

    @Override
    public void agregarDesglose() {
        reporte.setDesglose(String.format(Locale.US,
                "Disponibilidad: %.2f%% | Rendimiento: %.2f%% | Calidad: %.2f%%",
                reporte.getDisponibilidad() * 100,
                reporte.getRendimiento() * 100,
                reporte.getCalidad() * 100));
    }
}
