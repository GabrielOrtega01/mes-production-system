package com.academica.decorator;

/**
 * Patrón Decorator – Decorador concreto.
 * Aplica descuento del 5 % por pago antes de la fecha límite.
 */
public class DescuentoProntoPago extends DecoradorMatricula {

    public DescuentoProntoPago(CalculoMatricula componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 0.95;
    }

    @Override
    public String describir() {
        return componente.describir() + " + Descuento pronto pago (-5%)";
    }
}
