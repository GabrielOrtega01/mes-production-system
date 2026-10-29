package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;

/** Verifica que la línea tenga capacidad para procesar la orden. */
public class ValidadorCapacidad extends ValidadorBase {

    private final int capacidadMaxima;

    public ValidadorCapacidad(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public ResultadoOrden validar(OrdenProduccion orden) {
        if (orden.getCantidad() > capacidadMaxima) {
            return ResultadoOrden.rechazo(
                "Capacidad de linea insuficiente: max=" + capacidadMaxima
                + ", pedido=" + orden.getCantidad(),
                "ValidadorCapacidad");
        }
        System.out.println("[Capacidad] OK — capacidad=" + capacidadMaxima);
        return pasarAlSiguiente(orden);
    }
}
