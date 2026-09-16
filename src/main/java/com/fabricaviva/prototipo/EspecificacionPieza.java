package com.fabricaviva.prototipo;

import java.util.ArrayList;
import java.util.List;

/** Prototipo concreto: la ficha técnica de una pieza que la planta fabrica seguido. */
public class EspecificacionPieza implements Prototipo<EspecificacionPieza> {

    private final String codigo;
    private final String material;
    private double tolerancia;
    private final List<String> pasosControlCalidad;

    public EspecificacionPieza(String codigo, String material, double tolerancia) {
        this.codigo = codigo;
        this.material = material;
        this.tolerancia = tolerancia;
        this.pasosControlCalidad = new ArrayList<>();
    }

    // Constructor de copia: la lista se copia completa para que cada clon sea independiente.
    private EspecificacionPieza(EspecificacionPieza original) {
        this.codigo = original.codigo;
        this.material = original.material;
        this.tolerancia = original.tolerancia;
        this.pasosControlCalidad = new ArrayList<>(original.pasosControlCalidad);
    }

    @Override
    public EspecificacionPieza clonar() {
        return new EspecificacionPieza(this);
    }

    public void agregarPasoControlCalidad(String paso) {
        pasosControlCalidad.add(paso);
    }

    public double getTolerancia() {
        return tolerancia;
    }

    public int getCantidadControles() {
        return pasosControlCalidad.size();
    }

    public void setTolerancia(double tolerancia) {
        this.tolerancia = tolerancia;
    }

    @Override
    public String toString() {
        return codigo + " (" + material + ", tolerancia " + tolerancia + " mm) - controles: "
                + pasosControlCalidad;
    }
}
