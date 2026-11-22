package com.fabricaviva.inspeccion;

/**
 * Template Method — esqueleto fijo del proceso de inspección;
 * las subclases implementan los pasos específicos.
 */
public abstract class InspeccionCalidad {

    /** Método plantilla: define el orden invariable de la inspección. */
    public final ResultadoInspeccion inspeccionarLote(String loteId, int muestras) {
        System.out.println("=== Inspeccion " + tipo() + " — Lote " + loteId + " ===");
        prepararInstrumentos();
        int defectos = contarDefectos(muestras);
        boolean aprobado = evaluarResultado(defectos, muestras);
        registrarResultado(loteId, defectos, aprobado);
        return new ResultadoInspeccion(loteId, tipo(), defectos, aprobado);
    }

    protected abstract String tipo();
    protected abstract void   prepararInstrumentos();
    protected abstract int    contarDefectos(int muestras);

    /** Hook con implementación por defecto: < 5 % de defectos. */
    protected boolean evaluarResultado(int defectos, int muestras) {
        return defectos * 1.0 / muestras < 0.05;
    }

    private void registrarResultado(String lote, int defectos, boolean ok) {
        System.out.println("  Resultado: " + (ok ? "APROBADO" : "RECHAZADO")
                           + " — defectos=" + defectos);
    }
}
