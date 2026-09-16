package com.fabricaviva.equipos;

/**
 * Patrón Abstract Factory.
 * Crea la familia completa de equipos de un mismo fabricante, de modo que el
 * controlador y el sensor de una estación siempre hablen el mismo protocolo.
 */
public interface FabricaEquipos {

    ControladorMaquina crearControlador();

    SensorProduccion crearSensor();
}
