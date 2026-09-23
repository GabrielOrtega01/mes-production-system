package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayList;
import java.util.List;

/** Critica: sale de inmediato por todos los canales disponibles y reintenta si alguno falla. */
public class EnvioCritico extends EstrategiaEnvio {

    private static final int MAX_INTENTOS = 3;

    public EnvioCritico(List<CanalMensajeria> canales) {
        super(canales);
    }

    @Override
    public Prioridad prioridad() {
        return Prioridad.CRITICA;
    }

    @Override
    public List<ResultadoEnvio> enviar(Mensaje mensaje) {
        List<ResultadoEnvio> resultados = new ArrayList<>();
        for (CanalMensajeria canal : canalesDisponibles()) {
            resultados.add(intentar(canal, mensaje, MAX_INTENTOS));
        }
        return resultados;
    }
}
