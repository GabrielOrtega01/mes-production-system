package com.academica.decorator;

/**
 * Patrón Decorator – Decorador concreto.
 * Aplica descuento del 10 % por tener un hermano matriculado.
 */
public class DescuentoHermanos extends DecoradorMatricula {

    public DescuentoHermanos(CalculoMatricula componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 0.90;
    }

    @Override
    public String describir() {
        return componente.describir() + " + Descuento hermanos (-10%)";
    }
}
