package com.fabricaviva.reportes;

/** Para el supervisor: solo le interesa el OEE final. */
public class ReporteResumidoBuilder extends ReporteOeeBuilder {

    @Override
    public void agregarDesglose() {
        // El resumen no lleva desglose.
    }
}
