package com.fabricaviva.inspeccion;

import java.util.Random;

/** ConcreteClass: inspección visual con cámara de visión artificial. */
public class InspeccionVisual extends InspeccionCalidad {

    private final Random rng;

    public InspeccionVisual() { this.rng = new Random(42); }
    public InspeccionVisual(long seed) { this.rng = new Random(seed); }

    @Override protected String tipo() { return "VISUAL"; }

    @Override
    protected void prepararInstrumentos() {
        System.out.println("  Iniciando camara de vision artificial...");
    }

    @Override
    protected int contarDefectos(int muestras) {
        int d = (int)(muestras * (rng.nextDouble() * 0.04));
        System.out.println("  Defectos detectados por camara: " + d + "/" + muestras);
        return d;
    }
}
