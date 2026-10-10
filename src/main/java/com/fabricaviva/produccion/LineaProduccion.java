package com.fabricaviva.produccion;

import com.fabricaviva.config.ConfiguracionPlanta;

/** Primer módulo que usa la configuración: etiqueta cada pieza con el turno. */
public class LineaProduccion {

    private final String codigo;

    public LineaProduccion(String codigo) {
        this.codigo = codigo;
    }

    public String producirPieza(int numero) {
        ConfiguracionPlanta config = ConfiguracionPlanta.getInstancia();
        return "LineaProduccion " + codigo + " -> pieza " + numero
                + " fabricada en turno " + config.getTurnoActivo();
    }
}
