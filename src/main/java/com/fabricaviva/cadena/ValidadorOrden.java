package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;

/** Handler — eslabón de la cadena de validación de órdenes. */
public interface ValidadorOrden {
    void setSiguiente(ValidadorOrden siguiente);
    ResultadoOrden validar(OrdenProduccion orden);
}
