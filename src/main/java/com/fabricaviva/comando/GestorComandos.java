package com.fabricaviva.comando;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Invoker: encola y ejecuta comandos; mantiene historial para deshacer.
 */
public class GestorComandos {

    private final Deque<ComandoProduccion> historial = new ArrayDeque<>();

    public void ejecutar(ComandoProduccion cmd) {
        cmd.ejecutar();
        historial.push(cmd);
    }

    /** Deshace el último comando ejecutado. */
    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("[Gestor] No hay comandos para deshacer.");
            return;
        }
        ComandoProduccion ultimo = historial.pop();
        System.out.println("[Gestor] Deshaciendo: " + ultimo.descripcion());
        ultimo.deshacer();
    }

    public int tamanoHistorial() { return historial.size(); }
}
