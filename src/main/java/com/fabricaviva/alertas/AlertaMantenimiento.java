package com.fabricaviva.alertas;

/**
 * Patrón Bridge – Abstracción Refinada.
 * Alerta de mantenimiento preventivo o correctivo sobre un equipo de la planta.
 */
public class AlertaMantenimiento extends AlertaProduccion {

    public AlertaMantenimiento(CanalAlerta canal, String turno) {
        super(canal, turno);
    }

    @Override
    public void disparar(String descripcion) {
        canal.emitir("MAN-001", "[MANTENIMIENTO] " + descripcion, turno);
    }
}
