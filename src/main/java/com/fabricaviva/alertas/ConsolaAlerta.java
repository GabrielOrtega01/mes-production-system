package com.fabricaviva.alertas;

/**
 * Patrón Bridge – Implementador Concreto.
 * Muestra la alerta por la salida estándar (consola del operario).
 */
public class ConsolaAlerta implements CanalAlerta {

    @Override
    public void emitir(String codigo, String descripcion, String turno) {
        System.out.println("[ALERTA] " + codigo + " | " + descripcion + " | Turno: " + turno);
    }
}
