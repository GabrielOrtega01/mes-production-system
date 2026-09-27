package com.fabricaviva.alertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Patrón Bridge – Implementador Concreto.
 * Persiste la alerta en una bitácora en memoria (simula registro persistente).
 */
public class BitacoraAlerta implements CanalAlerta {

    private final List<String> registros = new ArrayList<>();

    @Override
    public void emitir(String codigo, String descripcion, String turno) {
        String entrada = codigo + " | " + descripcion + " | Turno: " + turno;
        registros.add(entrada);
        System.out.println("[BITACORA] Registrado: " + entrada);
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
