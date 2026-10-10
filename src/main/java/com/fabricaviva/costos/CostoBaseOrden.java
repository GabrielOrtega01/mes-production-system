package com.fabricaviva.costos;

/** Componente concreto: costo base de una orden de producción. */
public class CostoBaseOrden implements CalculoCostoOrden {

    private final String codigoOrden;
    private final double costoBase;

    public CostoBaseOrden(String codigoOrden, double costoBase) {
        this.codigoOrden = codigoOrden;
        this.costoBase   = costoBase;
    }

    @Override
    public double calcularCosto() {
        return costoBase;
    }

    @Override
    public String describir() {
        return String.format("Orden %s — costo base: $%.2f", codigoOrden, costoBase);
    }
}
