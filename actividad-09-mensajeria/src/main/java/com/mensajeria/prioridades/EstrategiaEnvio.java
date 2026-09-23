package com.mensajeria.prioridades;

import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.canales.EnvioFallidoException;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstraccion del patron Bridge.
 *
 * Define como se envia un mensaje segun su prioridad y mantiene una referencia a
 * los canales, pero solo los conoce a traves de la interfaz CanalMensajeria. Por
 * eso una prioridad nueva se agrega creando una subclase, sin tocar los canales.
 */
public abstract class EstrategiaEnvio {

    private final List<CanalMensajeria> canales;

    protected EstrategiaEnvio(List<CanalMensajeria> canales) {
        this.canales = List.copyOf(canales);
    }

    public abstract Prioridad prioridad();

    public abstract List<ResultadoEnvio> enviar(Mensaje mensaje);

    /** Canales que en este momento pueden recibir mensajes. */
    protected List<CanalMensajeria> canalesDisponibles() {
        List<CanalMensajeria> activos = new ArrayList<>();
        for (CanalMensajeria canal : canales) {
            if (canal.disponible()) {
                activos.add(canal);
            }
        }
        return activos;
    }

    /** Intenta la entrega hasta maxIntentos veces y registra como termino. */
    protected ResultadoEnvio intentar(CanalMensajeria canal, Mensaje mensaje, int maxIntentos) {
        String ultimoError = "";
        for (int intento = 1; intento <= maxIntentos; intento++) {
            try {
                canal.enviar(mensaje);
                return new ResultadoEnvio(canal.nombre(), true, intento, "Entregado");
            } catch (EnvioFallidoException error) {
                ultimoError = error.getMessage();
            }
        }
        return new ResultadoEnvio(canal.nombre(), false, maxIntentos, ultimoError);
    }
}
