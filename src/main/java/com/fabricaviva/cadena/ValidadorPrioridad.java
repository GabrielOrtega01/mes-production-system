package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.ordenes.Prioridad;

/** Rechaza órdenes de baja prioridad cuando la planta está en modo saturado. */
public class ValidadorPrioridad extends ValidadorBase {

    private final boolean modoSaturado;

    public ValidadorPrioridad(boolean modoSaturado) {
        this.modoSaturado = modoSaturado;
    }

    @Override
    public ResultadoOrden validar(OrdenProduccion orden) {
        if (modoSaturado && orden.getPrioridad() == Prioridad.NORMAL) {
            return ResultadoOrden.rechazo(
                "Planta en modo saturado: solo se aceptan ordenes de alta prioridad",
                "ValidadorPrioridad");
        }
        System.out.println("[Prioridad] OK — prioridad=" + orden.getPrioridad());
        return pasarAlSiguiente(orden);
    }
}
