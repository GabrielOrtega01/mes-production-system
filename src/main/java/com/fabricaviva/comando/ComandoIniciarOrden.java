package com.fabricaviva.comando;

public class ComandoIniciarOrden implements ComandoProduccion {

    private final LineaProduccionReceiver linea;
    private final String referencia;
    private final int cantidad;

    public ComandoIniciarOrden(LineaProduccionReceiver linea,
                                String referencia, int cantidad) {
        this.linea      = linea;
        this.referencia = referencia;
        this.cantidad   = cantidad;
    }

    @Override public void ejecutar()  { linea.iniciar(referencia, cantidad); }
    @Override public void deshacer()  { linea.pausar(referencia); }
    @Override public String descripcion() {
        return "IniciarOrden(" + referencia + " x" + cantidad + ")";
    }
}
