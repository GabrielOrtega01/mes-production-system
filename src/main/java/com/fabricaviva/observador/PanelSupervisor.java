package com.fabricaviva.observador;

import java.util.ArrayList;
import java.util.List;

/** ConcreteObserver: muestra alertas en el panel del supervisor. */
public class PanelSupervisor implements ObservadorMaquina {

    private final String nombre;
    private final double umbralOee;
    private final List<String> alertas = new ArrayList<>();

    public PanelSupervisor(String nombre, double umbralOee) {
        this.nombre    = nombre;
        this.umbralOee = umbralOee;
    }

    @Override
    public void actualizar(String idMaquina, String evento, double oee) {
        String msg;
        if ("PARADA_NO_PLANIFICADA".equals(evento)) {
            msg = "[" + nombre + "] PARADA en " + idMaquina;
        } else if (oee < umbralOee) {
            msg = "[" + nombre + "] OEE bajo en " + idMaquina
                  + ": " + String.format("%.0f%%", oee * 100)
                  + " (umbral " + String.format("%.0f%%", umbralOee * 100) + ")";
        } else {
            msg = "[" + nombre + "] " + idMaquina + " OK — OEE "
                  + String.format("%.0f%%", oee * 100);
        }
        alertas.add(msg);
        System.out.println(msg);
    }

    public List<String> getAlertas() { return alertas; }
}
