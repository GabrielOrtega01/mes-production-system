package com.fabricaviva.costos;

/** Descuento del −15 % por orden de alto volumen (≥ 500 piezas). */
public class DescuentoVolumen extends DecoradorCosto {

    public DescuentoVolumen(CalculoCostoOrden componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 0.85;
    }

    @Override
    public String describir() {
        return componente.describir() + " - descuento volumen (15%)";
    }
}
