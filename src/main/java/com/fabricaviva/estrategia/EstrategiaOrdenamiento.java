package com.fabricaviva.estrategia;

import com.fabricaviva.ordenes.OrdenProduccion;
import java.util.List;

/** Strategy — algoritmo de priorización de órdenes. */
public interface EstrategiaOrdenamiento {
    List<OrdenProduccion> ordenar(List<OrdenProduccion> ordenes);
    String nombre();
}
