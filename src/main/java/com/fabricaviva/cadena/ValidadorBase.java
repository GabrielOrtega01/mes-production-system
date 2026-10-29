package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;

/** ConcreteHandler base: encadena el siguiente eslabón. */
public abstract class ValidadorBase implements ValidadorOrden {

    private ValidadorOrden siguiente;

    @Override
    public void setSiguiente(ValidadorOrden siguiente) {
        this.siguiente = siguiente;
    }

    protected ResultadoOrden pasarAlSiguiente(OrdenProduccion orden) {
        if (siguiente != null) {
            return siguiente.validar(orden);
        }
        return ResultadoOrden.ok();
    }
}
