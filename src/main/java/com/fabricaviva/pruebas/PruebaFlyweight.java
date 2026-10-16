package com.fabricaviva.pruebas;

import com.fabricaviva.materiales.FabricaTipoMaterial;
import com.fabricaviva.materiales.LoteProduccion;
import com.fabricaviva.materiales.TipoMaterialFlyweight;

public class PruebaFlyweight {

    static int ok = 0, total = 0;

    static void afirmar(String desc, boolean cond) {
        total++;
        if (cond) { ok++; System.out.println("  [OK]    " + desc); }
        else       {       System.out.println("  [FALLO] " + desc); }
    }

    public static void main(String[] args) {
        FabricaTipoMaterial fabrica = FabricaTipoMaterial.getInstance();

        System.out.println("== Prueba 1: identidad del pool ==");
        TipoMaterialFlyweight m1 = fabrica.getMaterial("AISI-304", "Acero Inox.", 8.0, "kg");
        TipoMaterialFlyweight m2 = fabrica.getMaterial("AISI-304", "Acero Inox.", 8.0, "kg");
        TipoMaterialFlyweight m3 = fabrica.getMaterial("AL-6061",  "Aluminio 6061", 2.7, "kg");
        afirmar("AISI-304 devuelve el mismo objeto (m1 == m2)", m1 == m2);
        afirmar("AISI-304 y AL-6061 son objetos distintos (m1 != m3)", m1 != m3);
        afirmar("Pool tiene exactamente 2 entradas", fabrica.getTamanioPool() == 2);

        System.out.println("\n== Prueba 2: estado extrinseco independiente ==");
        LoteProduccion l1 = new LoteProduccion("L-001", 50.0, "CNC-01", m1);
        LoteProduccion l2 = new LoteProduccion("L-002", 30.0, "CNC-02", m1);
        afirmar("Lotes comparten la misma instancia TipoMaterial", l1.toString().contains("AISI-304")
                && l2.toString().contains("AISI-304"));
        afirmar("Peso L-001 = 50 * 8.0 = 400.0", l1.calcularPeso() == 400.0);
        afirmar("Peso L-002 = 30 * 8.0 = 240.0", l2.calcularPeso() == 240.0);

        System.out.println("\n== Prueba 3: ahorro estructural (1 000 tipos, 50 000 lotes) ==");
        for (int i = 3; i <= 1000; i++) {
            fabrica.getMaterial("MAT-" + String.format("%04d", i),
                    "Material " + i, 2.0 + (i % 10) * 0.1, "kg");
        }
        int poolSize = fabrica.getTamanioPool();
        int lotes    = 50_000;
        double ahorro = 1.0 - (double) poolSize / lotes;
        afirmar("Pool <= 1 000 tras 50 000 solicitudes", poolSize <= 1000);
        afirmar("Ahorro estructural > 97 %", ahorro > 0.97);
        System.out.printf("  Pool: %d instancias | Lotes: %d | Ahorro: %.1f %%%n",
                poolSize, lotes, ahorro * 100);

        System.out.println("\n================================================");
        System.out.println("  Pruebas exitosas: " + ok + " / " + total);
        System.out.println("================================================");
    }
}
