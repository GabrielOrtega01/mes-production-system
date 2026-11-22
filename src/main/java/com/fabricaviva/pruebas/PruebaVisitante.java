package com.fabricaviva.pruebas;

import com.fabricaviva.visitante.*;

public class PruebaVisitante {
    public static void main(String[] args) {
        MaquinaElement m1 = new MaquinaElement("CNC-01", 0.88, 45.0);
        MaquinaElement m2 = new MaquinaElement("CNC-02", 0.72, 45.0);
        MaquinaElement m3 = new MaquinaElement("SOLD-01", 0.91, 30.0);

        LineaElement linea = new LineaElement("L-01");
        linea.agregar(m1);
        linea.agregar(m2);
        linea.agregar(m3);

        System.out.println("=== Visitante OEE ===");
        VisitanteOee visitOee = new VisitanteOee();
        linea.aceptar(visitOee);
        System.out.printf("OEE promedio linea: %.1f%%%n", visitOee.getOeePromedio() * 100);

        System.out.println("\n=== Visitante Costos ===");
        VisitanteCostos visitCostos = new VisitanteCostos();
        linea.aceptar(visitCostos);
        System.out.printf("Costo total/hora: $%.2f%n", visitCostos.getCostoTotal());
    }
}
