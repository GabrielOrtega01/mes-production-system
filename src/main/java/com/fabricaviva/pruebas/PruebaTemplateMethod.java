package com.fabricaviva.pruebas;

import com.fabricaviva.inspeccion.*;

public class PruebaTemplateMethod {
    public static void main(String[] args) {
        InspeccionCalidad visual     = new InspeccionVisual();
        InspeccionCalidad dimensional = new InspeccionDimensional();

        System.out.println("=== Inspeccion Visual ===");
        ResultadoInspeccion r1 = visual.inspeccionarLote("LOTE-A01", 100);
        System.out.println(r1);

        System.out.println("\n=== Inspeccion Dimensional ===");
        ResultadoInspeccion r2 = dimensional.inspeccionarLote("LOTE-B02", 50);
        System.out.println(r2);
    }
}
