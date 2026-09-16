package com.fabricaviva.ordenes;

import java.util.ArrayList;
import java.util.List;

/** Producto que crean las fábricas: una orden de producción. */
public class OrdenProduccion {

    private final String referencia;
    private final int cantidad;
    private final Prioridad prioridad;
    private final List<String> bitacora = new ArrayList<>();

    public OrdenProduccion(String referencia, int cantidad, Prioridad prioridad) {
        this.referencia = referencia;
        this.cantidad = cantidad;
        this.prioridad = prioridad;
    }

    public void registrar(String evento) {
        bitacora.add(evento);
    }

    public String getReferencia() {
        return referencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public List<String> getBitacora() {
        return bitacora;
    }

    @Override
    public String toString() {
        return "OrdenProduccion{referencia=" + referencia + ", cantidad=" + cantidad
                + ", prioridad=" + prioridad + "}";
    }
}
