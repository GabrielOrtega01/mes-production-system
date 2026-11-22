package com.fabricaviva.mediador;

/** ConcreteColleague: módulo de calidad. */
public class ModuloCalidad implements ColaboradorMes {

    private final CentralMes central;

    public ModuloCalidad(CentralMes central) {
        this.central = central;
        central.registrar(this);
    }

    @Override public String getNombre() { return "CALIDAD"; }

    @Override
    public void recibirMensaje(String origen, String mensaje) {
        System.out.println("  [CALIDAD] <- " + origen + ": " + mensaje);
        if (mensaje.contains("FALLA")) {
            central.enviar("CALIDAD", "Iniciando inspeccion de lote afectado");
        }
    }

    public void reportarDefectos(String lote, int pct) {
        central.enviar("CALIDAD", "Defectos en " + lote + ": " + pct + "%");
    }
}
