package com.fabricaviva.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/** Caretaker: almacena la pila de instantáneas sin conocer su contenido. */
public class HistorialTurnos {

    private final Deque<InstantaneTurno> pila = new ArrayDeque<>();

    public void guardar(ConfiguracionTurno cfg) {
        pila.push(cfg.guardar());
    }

    public void deshacer(ConfiguracionTurno cfg) {
        if (pila.isEmpty()) {
            System.out.println("[Historial] No hay estados anteriores.");
            return;
        }
        cfg.restaurar(pila.pop());
    }

    public int tamano() { return pila.size(); }
}
