package com.fabricaviva.costos;

/** Recargo del +25 % por orden prioritaria de producción. */
public class RecargoPrioritario extends DecoradorCosto {

    public RecargoPrioritario(CalculoCostoOrden componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 1.25;
    }

    @Override
    public String describir() {
        return componente.describir() + " + recargo prioritario (25%)";
    }
}
