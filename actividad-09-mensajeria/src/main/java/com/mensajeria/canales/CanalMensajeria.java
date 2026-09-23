package com.mensajeria.canales;

import com.mensajeria.modelo.Mensaje;

/**
 * Implementador del patron Bridge y, al mismo tiempo, interfaz objetivo (Target)
 * del patron Adapter.
 *
 * Es el unico contrato que conocen las estrategias de prioridad. Cada proveedor
 * externo se expone al sistema a traves de esta interfaz.
 */
public interface CanalMensajeria {

    String nombre();

    boolean disponible();

    /**
     * Entrega el mensaje por este canal.
     *
     * @throws EnvioFallidoException si el proveedor rechaza la entrega
     */
    void enviar(Mensaje mensaje);
}
