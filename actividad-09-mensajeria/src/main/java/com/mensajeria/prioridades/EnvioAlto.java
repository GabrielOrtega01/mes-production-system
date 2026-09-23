package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayList;
import java.util.List;

/** Alta: dos canales, un solo intento en cada uno. */
public class EnvioAlto extends EstrategiaEnvio {

    private static final int CANALES_USADOS = 2;

    public EnvioAlto(List<CanalMensajeria> canales) {
        super(canales);
    }

    @Override
    public Prioridad prioridad() {
        return Prioridad.ALTA;
    }

    @Override
    public List<ResultadoEnvio> enviar(Mensaje mensaje) {
        List<CanalMensajeria> disponibles = canalesDisponibles();
        List<ResultadoEnvio> resultados = new ArrayList<>();
        int usados = Math.min(CANALES_USADOS, disponibles.size());
        for (int i = 0; i < usados; i++) {
            resultados.add(intentar(disponibles.get(i), mensaje, 1));
        }
        return resultados;
    }
}
