package com.fabricaviva.observador;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject (Observable): sensor que reporta el OEE de una máquina
 * y notifica a todos sus observadores cuando el valor cambia.
 */
public class SensorMaquina {

    private final String idMaquina;
    private double oeeActual = 1.0;
    private final List<ObservadorMaquina> observadores = new ArrayList<>();

    public SensorMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void agregarObservador(ObservadorMaquina o) {
        observadores.add(o);
    }

    public void quitarObservador(ObservadorMaquina o) {
        observadores.remove(o);
    }

    /** Registra nueva lectura y notifica si el OEE cambia. */
    public void reportarOee(double oee) {
        if (Double.compare(oee, oeeActual) != 0) {
            this.oeeActual = oee;
            notificar("OEE_ACTUALIZADO");
        }
    }

    /** Señal de parada no planificada — siempre notifica. */
    public void reportarParada() {
        notificar("PARADA_NO_PLANIFICADA");
    }

    private void notificar(String evento) {
        for (ObservadorMaquina o : observadores) {
            o.actualizar(idMaquina, evento, oeeActual);
        }
    }

    public String getIdMaquina() { return idMaquina; }
    public double getOeeActual()  { return oeeActual; }
}
