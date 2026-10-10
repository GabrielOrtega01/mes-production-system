package com.academica.decorator;

/**
 * Patrón Decorator – Decorador abstracto.
 * Mantiene la referencia al componente envuelto y delega a él.
 * Cada subclase aplica un único ajuste sobre el costo resultante.
 */
public abstract class DecoradorMatricula implements CalculoMatricula {

    protected final CalculoMatricula componente;

    public DecoradorMatricula(CalculoMatricula componente) {
        this.componente = componente;
    }
}
