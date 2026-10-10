package com.academica.decorator;

/**
 * Patrón Decorator – Decorador concreto.
 * Aplica descuento del 30 % por beca de mérito académico.
 */
public class BecaMerito extends DecoradorMatricula {

    public BecaMerito(CalculoMatricula componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 0.70;
    }

    @Override
    public String describir() {
        return componente.describir() + " + Beca merito (-30%)";
    }
}
