package com.fabricaviva.costos;

/** Decorador abstracto: delega al componente envuelto. */
public abstract class DecoradorCosto implements CalculoCostoOrden {

    protected final CalculoCostoOrden componente;

    protected DecoradorCosto(CalculoCostoOrden componente) {
        this.componente = componente;
    }
}
