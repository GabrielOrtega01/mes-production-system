package com.fabricaviva.costos;

/** Descuento del −10 % para clientes con contrato preferente. */
public class DescuentoClientePreferente extends DecoradorCosto {

    public DescuentoClientePreferente(CalculoCostoOrden componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 0.90;
    }

    @Override
    public String describir() {
        return componente.describir() + " - descuento cliente preferente (10%)";
    }
}
