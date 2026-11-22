package com.fabricaviva.observador;

import java.util.ArrayList;
import java.util.List;

/** ConcreteObserver: persiste todos los eventos en una bitácora. */
public class BitacoraEventos implements ObservadorMaquina {

    private final List<String> registros = new ArrayList<>();

    @Override
    public void actualizar(String idMaquina, String evento, double oee) {
        String linea = evento + " | " + idMaquina
                       + " | oee=" + String.format("%.3f", oee);
        registros.add(linea);
        System.out.println("[BITACORA] " + linea);
    }

    public List<String> getRegistros() { return registros; }
}
