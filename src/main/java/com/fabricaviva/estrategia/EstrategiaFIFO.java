package com.fabricaviva.estrategia;

import com.fabricaviva.ordenes.OrdenProduccion;
import java.util.ArrayList;
import java.util.List;

/** ConcreteStrategy: ordena en el orden en que llegaron (FIFO). */
public class EstrategiaFIFO implements EstrategiaOrdenamiento {

    @Override
    public List<OrdenProduccion> ordenar(List<OrdenProduccion> ordenes) {
        return new ArrayList<>(ordenes); // sin cambio — orden de llegada
    }

    @Override
    public String nombre() { return "FIFO"; }
}
