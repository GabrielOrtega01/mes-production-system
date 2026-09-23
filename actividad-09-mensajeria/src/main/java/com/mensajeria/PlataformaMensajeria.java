package com.mensajeria;

import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import com.mensajeria.prioridades.EstrategiaEnvio;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Cliente del puente. Recibe las estrategias ya construidas, de modo que no
 * conoce ni las prioridades concretas ni los canales: solo enruta el mensaje.
 */
public class PlataformaMensajeria {

    private final Map<Prioridad, EstrategiaEnvio> estrategias = new EnumMap<>(Prioridad.class);

    public void registrar(EstrategiaEnvio estrategia) {
        estrategias.put(estrategia.prioridad(), estrategia);
    }

    public List<ResultadoEnvio> notificar(Mensaje mensaje, Prioridad prioridad) {
        EstrategiaEnvio estrategia = estrategias.get(prioridad);
        if (estrategia == null) {
            throw new IllegalArgumentException("No hay estrategia registrada para " + prioridad);
        }
        return estrategia.enviar(mensaje);
    }

    public List<Prioridad> prioridadesRegistradas() {
        return List.copyOf(estrategias.keySet());
    }
}
