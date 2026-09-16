package com.fabricaviva.prototipo;

/**
 * Patrón Prototype.
 * Declara la operación para clonarse (Gamma et al., 2003, p. 111).
 */
public interface Prototipo<T> {

    T clonar();
}
