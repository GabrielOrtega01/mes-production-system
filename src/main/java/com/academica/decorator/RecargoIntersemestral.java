package com.academica.decorator;

/**
 * Patrón Decorator – Decorador concreto.
 * Aplica recargo del 15 % por matrícula en período intersemestral.
 */
public class RecargoIntersemestral extends DecoradorMatricula {

    public RecargoIntersemestral(CalculoMatricula componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 1.15;
    }

    @Override
    public String describir() {
        return componente.describir() + " + Recargo intersemestral (+15%)";
    }
}
