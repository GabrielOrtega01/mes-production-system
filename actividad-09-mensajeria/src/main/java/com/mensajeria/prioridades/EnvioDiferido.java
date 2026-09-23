package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** Baja: el mensaje se encola y sale despues, cuando se procesa la cola. */
public class EnvioDiferido extends EstrategiaEnvio {

    private final Deque<Mensaje> cola = new ArrayDeque<>();

    public EnvioDiferido(List<CanalMensajeria> canales) {
        super(canales);
    }

    @Override
    public Prioridad prioridad() {
        return Prioridad.BAJA;
    }

    @Override
    public List<ResultadoEnvio> enviar(Mensaje mensaje) {
        cola.addLast(mensaje);
        return List.of(new ResultadoEnvio("COLA", true, 0,
                "Encolado para envio diferido (" + cola.size() + " pendientes)"));
    }

    public int pendientes() {
        return cola.size();
    }

    /** Vacia la cola enviando cada mensaje por un canal. */
    public List<ResultadoEnvio> procesarCola() {
        List<ResultadoEnvio> resultados = new ArrayList<>();
        List<CanalMensajeria> disponibles = canalesDisponibles();
        while (!cola.isEmpty()) {
            Mensaje mensaje = cola.removeFirst();
            if (!disponibles.isEmpty()) {
                resultados.add(intentar(disponibles.get(0), mensaje, 1));
            }
        }
        return resultados;
    }
}
