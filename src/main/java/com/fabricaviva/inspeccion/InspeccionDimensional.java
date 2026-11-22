package com.fabricaviva.inspeccion;

import java.util.Random;

/** ConcreteClass: inspección dimensional con calibrador digital. */
public class InspeccionDimensional extends InspeccionCalidad {

    private final Random rng;

    public InspeccionDimensional() { this.rng = new Random(7); }
    public InspeccionDimensional(long seed) { this.rng = new Random(seed); }

    @Override protected String tipo() { return "DIMENSIONAL"; }

    @Override
    protected void prepararInstrumentos() {
        System.out.println("  Calibrando micrometro digital...");
    }

    @Override
    protected int contarDefectos(int muestras) {
        int d = (int)(muestras * (0.03 + rng.nextDouble() * 0.05));
        System.out.println("  Piezas fuera de tolerancia: " + d + "/" + muestras);
        return d;
    }

    /** Hook sobrescrito: criterio mas estricto para dimensional (< 3 %). */
    @Override
    protected boolean evaluarResultado(int defectos, int muestras) {
        return defectos * 1.0 / muestras < 0.03;
    }
}
