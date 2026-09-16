package com.fabricaviva.ordenes;

/**
 * Patrón Factory Method (Creador).
 * Define el procedimiento para dar de alta una orden, pero deja que cada
 * subclase decida qué tipo de orden se crea.
 */
public abstract class FabricaOrden {

    public OrdenProduccion nuevaOrden(String referencia, int cantidad) {
        OrdenProduccion orden = crearOrden(referencia, cantidad);
        orden.registrar("Creada por " + getClass().getSimpleName());
        return orden;
    }

    // Método fábrica: cada subclase lo implementa a su manera.
    protected abstract OrdenProduccion crearOrden(String referencia, int cantidad);
}
