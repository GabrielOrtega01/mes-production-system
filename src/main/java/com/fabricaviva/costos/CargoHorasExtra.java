package com.fabricaviva.costos;

/** Cargo del +30 % cuando la orden requiere turno en horas extra. */
public class CargoHorasExtra extends DecoradorCosto {

    public CargoHorasExtra(CalculoCostoOrden componente) {
        super(componente);
    }

    @Override
    public double calcularCosto() {
        return componente.calcularCosto() * 1.30;
    }

    @Override
    public String describir() {
        return componente.describir() + " + cargo horas extra (30%)";
    }
}
