package com.fabricaviva.pruebas;

import com.fabricaviva.iterador.*;

public class PruebaIterador {
    public static void main(String[] args) {
        ColeccionLotes coleccion = new ColeccionLotes();
        coleccion.agregar(new LoteProduccion("L001", "REF-A", 200, 2));
        coleccion.agregar(new LoteProduccion("L002", "REF-B", 50,  1));
        coleccion.agregar(new LoteProduccion("L003", "REF-C", 300, 2));
        coleccion.agregar(new LoteProduccion("L004", "REF-D", 10,  1));

        System.out.println("=== Iteracion natural (insertion order) ===");
        for (LoteProduccion lote : coleccion) {
            System.out.println("  " + lote);
        }

        System.out.println("\n=== Iteracion por prioridad (ALTA primero) ===");
        coleccion.iteratorPorPrioridad().forEachRemaining(l -> System.out.println("  " + l));
    }
}
