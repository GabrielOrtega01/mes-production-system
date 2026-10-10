package com.academica.decorator;

/**
 * Patrón Decorator – Componente Concreto.
 * Matrícula estándar sin ajustes: costo base de $5.000.000.
 */
public class MatriculaBase implements CalculoMatricula {

    private static final double COSTO_BASE = 5_000_000.0;

    @Override
    public double calcularCosto() {
        return COSTO_BASE;
    }

    @Override
    public String describir() {
        return "Matricula base: $5.000.000";
    }
}
