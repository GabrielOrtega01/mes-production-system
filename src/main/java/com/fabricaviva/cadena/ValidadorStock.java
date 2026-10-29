package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;

/** Verifica que la cantidad pedida no supere el stock disponible. */
public class ValidadorStock extends ValidadorBase {

    private final int stockDisponible;

    public ValidadorStock(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public ResultadoOrden validar(OrdenProduccion orden) {
        if (orden.getCantidad() > stockDisponible) {
            return ResultadoOrden.rechazo(
                "Stock insuficiente: se piden " + orden.getCantidad()
                + " unidades pero solo hay " + stockDisponible,
                "ValidadorStock");
        }
        System.out.println("[Stock] OK — cantidad=" + orden.getCantidad());
        return pasarAlSiguiente(orden);
    }
}
