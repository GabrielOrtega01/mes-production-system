package com.fabricaviva.iterador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Aggregate: colección de lotes que expone dos iteradores —
 * orden de llegada (FIFO) y orden por prioridad.
 */
public class ColeccionLotes implements Iterable<LoteProduccion> {

    private final List<LoteProduccion> lotes = new ArrayList<>();

    public void agregar(LoteProduccion lote) { lotes.add(lote); }
    public int  tamano()                     { return lotes.size(); }

    /** Iterator FIFO (orden de inserción). */
    @Override
    public Iterator<LoteProduccion> iterator() {
        return lotes.iterator();
    }

    /** Iterator por prioridad ascendente (1 primero). */
    public Iterator<LoteProduccion> iteratorPorPrioridad() {
        List<LoteProduccion> ordenado = new ArrayList<>(lotes);
        ordenado.sort(Comparator.comparingInt(LoteProduccion::getPrioridad));
        return ordenado.iterator();
    }
}
