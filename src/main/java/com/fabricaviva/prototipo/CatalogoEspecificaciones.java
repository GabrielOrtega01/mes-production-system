package com.fabricaviva.prototipo;

import java.util.HashMap;
import java.util.Map;

/** Guarda las especificaciones originales y entrega siempre una copia. */
public class CatalogoEspecificaciones {

    private final Map<String, EspecificacionPieza> prototipos = new HashMap<>();

    public void registrar(String clave, EspecificacionPieza prototipo) {
        prototipos.put(clave, prototipo);
    }

    public EspecificacionPieza obtenerCopia(String clave) {
        EspecificacionPieza prototipo = prototipos.get(clave);
        if (prototipo == null) {
            throw new IllegalArgumentException("No existe la especificacion: " + clave);
        }
        return prototipo.clonar();
    }
}
