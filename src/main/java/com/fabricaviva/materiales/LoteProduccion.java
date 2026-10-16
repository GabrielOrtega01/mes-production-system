package com.fabricaviva.materiales;

/**
 * Cliente Flyweight: contiene el estado extrínseco (numeroLote, cantidad,
 * maquinaOrigen) y una referencia al estado intrínseco compartido.
 */
public class LoteProduccion {

    private final String               numeroLote;
    private final double               cantidadKg;
    private final String               maquinaOrigen;
    private final TipoMaterialFlyweight tipo;

    public LoteProduccion(String numeroLote, double cantidadKg,
                          String maquinaOrigen, TipoMaterialFlyweight tipo) {
        this.numeroLote    = numeroLote;
        this.cantidadKg    = cantidadKg;
        this.maquinaOrigen = maquinaOrigen;
        this.tipo          = tipo;
    }

    /** Peso total = cantidad × densidad del material compartido. */
    public double calcularPeso() { return cantidadKg * tipo.getDensidad(); }

    @Override
    public String toString() {
        return "Lote " + numeroLote
                + " | " + tipo.describir()
                + " | " + cantidadKg + " " + tipo.getUnidad()
                + " | Máquina: " + maquinaOrigen;
    }
}
