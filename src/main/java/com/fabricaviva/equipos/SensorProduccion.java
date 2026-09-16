package com.fabricaviva.equipos;

/** Producto abstracto B: el sensor que cuenta las piezas fabricadas. */
public interface SensorProduccion {

    String protocolo();

    String reportarConteo(int piezas);
}
