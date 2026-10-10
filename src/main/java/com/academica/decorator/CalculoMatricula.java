package com.academica.decorator;

/**
 * Patrón Decorator – Componente (interfaz objetivo).
 * Contrato para calcular el costo de matrícula y describir los ajustes aplicados.
 */
public interface CalculoMatricula {

    double calcularCosto();

    String describir();
}
