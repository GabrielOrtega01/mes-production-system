package com.fabricaviva.estrategia;

import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.ordenes.Prioridad;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** ConcreteStrategy: ALTA antes que NORMAL; mismo nivel en orden de llegada. */
public class EstrategiaPrioridad implements EstrategiaOrdenamiento {

    @Override
    public List<OrdenProduccion> ordenar(List<OrdenProduccion> ordenes) {
        List<OrdenProduccion> copia = new ArrayList<>(ordenes);
        copia.sort(Comparator.comparing(
            o -> o.getPrioridad() == Prioridad.ALTA ? 0 : 1));
        return copia;
    }

    @Override
    public String nombre() { return "PRIORIDAD"; }
}
