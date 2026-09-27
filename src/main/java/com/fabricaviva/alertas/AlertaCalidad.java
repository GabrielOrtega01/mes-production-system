package com.fabricaviva.alertas;

/**
 * Patrón Bridge – Abstracción Refinada.
 * Alerta de desviación en los indicadores de calidad de la línea.
 */
public class AlertaCalidad extends AlertaProduccion {

    public AlertaCalidad(CanalAlerta canal, String turno) {
        super(canal, turno);
    }

    @Override
    public void disparar(String descripcion) {
        canal.emitir("CAL-001", "[CALIDAD] " + descripcion, turno);
    }
}
