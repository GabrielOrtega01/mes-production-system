package com.fabricaviva.integracion;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Adapter – implementación nativa del MES.
 * Sirve como punto de comparación: el cliente llama al mismo contrato
 * sin necesitar el adaptador.
 */
public class RegistroInternoMes implements RegistroProduccion {

    private final Map<String, Integer> conteos = new HashMap<>();

    public void cargarConteo(String referencia, int cantidad) {
        conteos.put(referencia, cantidad);
    }

    @Override
    public int leerCantidadProducida(String referencia) {
        return conteos.getOrDefault(referencia, 0);
    }

    @Override
    public void registrarCierreTurno(String turno, int piezas) {
        System.out.println("Cierre de turno registrado internamente: " + turno + " | " + piezas + " piezas");
    }
}
