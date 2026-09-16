package com.fabricaviva.ordenes;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente del Factory Method. Solo conoce la abstracción FabricaOrden;
 * no sabe cómo se arma por dentro cada tipo de orden.
 */
public class ModuloOrdenes {

    private final FabricaOrden fabricaEstandar = new FabricaOrdenEstandar();
    private final FabricaOrden fabricaUrgente = new FabricaOrdenUrgente();
    private final List<OrdenProduccion> ordenes = new ArrayList<>();

    public OrdenProduccion crear(String referencia, int cantidad, boolean urgente) {
        FabricaOrden fabrica = urgente ? fabricaUrgente : fabricaEstandar;
        OrdenProduccion orden = fabrica.nuevaOrden(referencia, cantidad);
        ordenes.add(orden);
        return orden;
    }

    public List<OrdenProduccion> getOrdenes() {
        return ordenes;
    }
}
