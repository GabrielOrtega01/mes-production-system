package com.fabricaviva.estrategia;

import com.fabricaviva.ordenes.OrdenProduccion;
import java.util.List;

/** Context: usa la estrategia activa para ordenar la cola de producción. */
public class PlanificadorOrdenes {

    private EstrategiaOrdenamiento estrategia;

    public PlanificadorOrdenes(EstrategiaOrdenamiento estrategia) {
        this.estrategia = estrategia;
    }

    /** Cambia el algoritmo en tiempo de ejecución (hot-swap). */
    public void setEstrategia(EstrategiaOrdenamiento estrategia) {
        this.estrategia = estrategia;
        System.out.println("[Planificador] Estrategia cambiada a: " + estrategia.nombre());
    }

    public List<OrdenProduccion> planificar(List<OrdenProduccion> ordenes) {
        System.out.println("[Planificador] Ordenando " + ordenes.size()
                           + " ordenes con estrategia: " + estrategia.nombre());
        List<OrdenProduccion> resultado = estrategia.ordenar(ordenes);
        resultado.forEach(o -> System.out.println("  " + o));
        return resultado;
    }
}
