package com.fabricaviva.ordenes;

/** Órdenes del plan normal de producción. */
public class FabricaOrdenEstandar extends FabricaOrden {

    @Override
    protected OrdenProduccion crearOrden(String referencia, int cantidad) {
        return new OrdenProduccion(referencia, cantidad, Prioridad.NORMAL);
    }
}
