package com.fabricaviva.pruebas;

import com.fabricaviva.estrategia.*;
import com.fabricaviva.ordenes.*;
import java.util.*;

public class PruebaEstrategia {
    public static void main(String[] args) {
        List<OrdenProduccion> ordenes = Arrays.asList(
            new OrdenProduccion("REF-C", 300, Prioridad.NORMAL),
            new OrdenProduccion("REF-A", 100, Prioridad.ALTA),
            new OrdenProduccion("REF-B", 200, Prioridad.NORMAL)
        );

        PlanificadorOrdenes planificador = new PlanificadorOrdenes(new EstrategiaFIFO());

        System.out.println("=== FIFO ===");
        planificador.setEstrategia(new EstrategiaFIFO());
        planificador.planificar(ordenes).forEach(o -> System.out.println("  " + o));

        System.out.println("\n=== Por prioridad ===");
        planificador.setEstrategia(new EstrategiaPrioridad());
        planificador.planificar(ordenes).forEach(o -> System.out.println("  " + o));

        System.out.println("\n=== Por volumen ===");
        planificador.setEstrategia(new EstrategiaVolumen());
        planificador.planificar(ordenes).forEach(o -> System.out.println("  " + o));
    }
}
