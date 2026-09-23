package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayList;
import java.util.List;

/** Normal: un solo canal, un solo intento. */
public class EnvioNormal extends EstrategiaEnvio {

    public EnvioNormal(List<CanalMensajeria> canales) {
        super(canales);
    }

    @Override
    public Prioridad prioridad() {
        return Prioridad.NORMAL;
    }

    @Override
    public List<ResultadoEnvio> enviar(Mensaje mensaje) {
        List<ResultadoEnvio> resultados = new ArrayList<>();
        List<CanalMensajeria> disponibles = canalesDisponibles();
        if (!disponibles.isEmpty()) {
            resultados.add(intentar(disponibles.get(0), mensaje, 1));
        }
        return resultados;
    }
}
