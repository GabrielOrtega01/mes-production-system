package com.fabricaviva.ordenes;

/** Pedidos urgentes que pasan por delante del plan vigente. */
public class FabricaOrdenUrgente extends FabricaOrden {

    @Override
    protected OrdenProduccion crearOrden(String referencia, int cantidad) {
        OrdenProduccion orden = new OrdenProduccion(referencia, cantidad, Prioridad.ALTA);
        orden.registrar("Marcada urgente: pasa delante del plan vigente");
        return orden;
    }
}
