package com.fabricaviva.alertas;

/**
 * Patrón Bridge – Abstracción Refinada.
 * Alerta de parada no planificada de una línea o equipo de producción.
 */
public class AlertaParada extends AlertaProduccion {

    public AlertaParada(CanalAlerta canal, String turno) {
        super(canal, turno);
    }

    @Override
    public void disparar(String descripcion) {
        canal.emitir("PAR-001", "[PARADA] " + descripcion, turno);
    }
}
