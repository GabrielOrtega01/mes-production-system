package com.fabricaviva.comando;

/** Command — interfaz de todos los comandos de producción. */
public interface ComandoProduccion {
    void ejecutar();
    void deshacer();
    String descripcion();
}
