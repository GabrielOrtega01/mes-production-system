package com.fabricaviva.visitante;

/** ConcreteVisitor: acumula costo total por hora de la estructura. */
public class VisitanteCostos implements VisitanteProduccion {

    private double costoTotal = 0;

    @Override
    public void visitarMaquina(MaquinaElement maquina) {
        costoTotal += maquina.getCostoHora();
        System.out.printf("[Costos] Maquina %s: $%.2f/h%n",
            maquina.getId(), maquina.getCostoHora());
    }

    @Override
    public void visitarLinea(LineaElement linea) {
        System.out.println("[Costos] Calculando costos de linea " + linea.getId());
    }

    public double getCostoTotal() { return costoTotal; }
}
