package com.fabricaviva.visitante;

/** ConcreteVisitor: calcula OEE promedio de la estructura recorrida. */
public class VisitanteOee implements VisitanteProduccion {

    private double sumaOee = 0;
    private int    cuenta  = 0;

    @Override
    public void visitarMaquina(MaquinaElement maquina) {
        sumaOee += maquina.getOee();
        cuenta++;
        System.out.printf("[OEE] Maquina %s: OEE=%.1f%%%n",
            maquina.getId(), maquina.getOee() * 100);
    }

    @Override
    public void visitarLinea(LineaElement linea) {
        System.out.println("[OEE] Recorriendo linea " + linea.getId());
    }

    public double getOeePromedio() {
        return cuenta == 0 ? 0 : sumaOee / cuenta;
    }
}
