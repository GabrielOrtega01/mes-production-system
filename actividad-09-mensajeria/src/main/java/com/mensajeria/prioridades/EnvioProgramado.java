package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayList;
import java.util.List;

/**
 * Prioridad agregada al final del ejercicio: sale por el ultimo canal registrado
 * y admite dos intentos. Se incluye para demostrar la restriccion 3: no hubo que
 * modificar ningun canal ni ninguna otra estrategia para crearla.
 */
public class EnvioProgramado extends EstrategiaEnvio {

    private static final int MAX_INTENTOS = 2;

    public EnvioProgramado(List<CanalMensajeria> canales) {
        super(canales);
    }

    @Override
    public Prioridad prioridad() {
        return Prioridad.PROGRAMADA;
    }

    @Override
    public List<ResultadoEnvio> enviar(Mensaje mensaje) {
        List<ResultadoEnvio> resultados = new ArrayList<>();
        List<CanalMensajeria> disponibles = canalesDisponibles();
        if (!disponibles.isEmpty()) {
            resultados.add(intentar(disponibles.get(disponibles.size() - 1), mensaje, MAX_INTENTOS));
        }
        return resultados;
    }
}
