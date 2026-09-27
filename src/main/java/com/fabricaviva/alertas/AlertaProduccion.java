package com.fabricaviva.alertas;

/**
 * Patrón Bridge – Abstracción.
 * Define qué se reporta (tipo de alerta) y delega cómo se entrega al canal.
 * Abstracción e implementación pueden variar de forma independiente.
 */
public abstract class AlertaProduccion {

    protected final CanalAlerta canal;
    protected final String turno;

    public AlertaProduccion(CanalAlerta canal, String turno) {
        this.canal = canal;
        this.turno = turno;
    }

    public abstract void disparar(String descripcion);
}
