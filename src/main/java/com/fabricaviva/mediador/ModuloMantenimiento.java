package com.fabricaviva.mediador;

/** ConcreteColleague: módulo de mantenimiento. */
public class ModuloMantenimiento implements ColaboradorMes {

    private final CentralMes central;

    public ModuloMantenimiento(CentralMes central) {
        this.central = central;
        central.registrar(this);
    }

    @Override public String getNombre() { return "MANTENIMIENTO"; }

    @Override
    public void recibirMensaje(String origen, String mensaje) {
        System.out.println("  [MANTENIMIENTO] <- " + origen + ": " + mensaje);
        if (mensaje.contains("FALLA")) {
            central.enviar("MANTENIMIENTO", "Tecnico asignado para revision");
        }
    }

    public void reportarFalla(String maquina, String causa) {
        central.enviar("MANTENIMIENTO", "FALLA en " + maquina + ": " + causa);
    }
}
