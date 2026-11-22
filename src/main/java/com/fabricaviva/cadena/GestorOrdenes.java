package com.fabricaviva.cadena;

import com.fabricaviva.ordenes.OrdenProduccion;

/** Client: construye la cadena y lanza la validación. */
public class GestorOrdenes {

    private final ValidadorOrden cabeza;

    public GestorOrdenes(int stock, int capacidad, boolean modoSaturado) {
        ValidadorStock    v1 = new ValidadorStock(stock);
        ValidadorCapacidad v2 = new ValidadorCapacidad(capacidad);
        ValidadorPrioridad v3 = new ValidadorPrioridad(modoSaturado);

        v1.setSiguiente(v2);
        v2.setSiguiente(v3);

        this.cabeza = v1;
    }

    public ResultadoOrden validar(OrdenProduccion orden) {
        System.out.println("\n-- Validando: " + orden);
        ResultadoOrden r = cabeza.validar(orden);
        System.out.println("   Resultado: " + r);
        return r;
    }
}
