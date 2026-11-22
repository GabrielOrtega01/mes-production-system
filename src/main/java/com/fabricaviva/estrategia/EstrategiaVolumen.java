package com.fabricaviva.estrategia;

import com.fabricaviva.ordenes.OrdenProduccion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** ConcreteStrategy: menor cantidad primero (minimizar tiempo de ciclo). */
public class EstrategiaVolumen implements EstrategiaOrdenamiento {

    @Override
    public List<OrdenProduccion> ordenar(List<OrdenProduccion> ordenes) {
        List<OrdenProduccion> copia = new ArrayList<>(ordenes);
        copia.sort(Comparator.comparingInt(OrdenProduccion::getCantidad));
        return copia;
    }

    @Override
    public String nombre() { return "MENOR_VOLUMEN_PRIMERO"; }
}
