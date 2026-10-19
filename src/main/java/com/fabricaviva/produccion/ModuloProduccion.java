package com.fabricaviva.produccion;

import com.fabricaviva.config.ConfiguracionPlanta;

/** Módulo cliente del Singleton: etiqueta cada pieza con el turno activo. */
public class ModuloProduccion {

    private final String codigo;

    public ModuloProduccion(String codigo) {
        this.codigo = codigo;
    }

    public String producirPieza(int numero) {
        ConfiguracionPlanta config = ConfiguracionPlanta.getInstancia();
        return "ModuloProduccion " + codigo + " -> pieza " + numero
                + " fabricada en turno " + config.getTurnoActivo();
    }
}
