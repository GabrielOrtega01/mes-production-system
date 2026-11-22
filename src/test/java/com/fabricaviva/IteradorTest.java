package com.fabricaviva;

import com.fabricaviva.iterador.ColeccionLotes;
import com.fabricaviva.iterador.LoteProduccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Iterator — ColeccionLotes")
class IteradorTest {

    private ColeccionLotes coleccion;
    private LoteProduccion loteA;
    private LoteProduccion loteB;
    private LoteProduccion loteC;

    @BeforeEach
    void setUp() {
        coleccion = new ColeccionLotes();
        loteA = new LoteProduccion("L001", "PIEZA-X", 100, 2);
        loteB = new LoteProduccion("L002", "PIEZA-Y", 200, 1);
        loteC = new LoteProduccion("L003", "PIEZA-Z",  50, 3);
        coleccion.agregar(loteA);
        coleccion.agregar(loteB);
        coleccion.agregar(loteC);
    }

    @Test
    @DisplayName("tamano() refleja la cantidad de lotes agregados")
    void tamanoReflejaCantidadLotes() {
        assertEquals(3, coleccion.tamano());
    }

    @Test
    @DisplayName("Iterador FIFO mantiene orden de insercion")
    void iteradorFifoMantienOrdenDeInsercion() {
        List<String> ids = new ArrayList<>();
        for (LoteProduccion l : coleccion) {
            ids.add(l.getId());
        }
        assertEquals(List.of("L001", "L002", "L003"), ids);
    }

    @Test
    @DisplayName("Iterador por prioridad ordena de menor a mayor valor numerico")
    void iteradorPrioridadOrdenaPorPrioridad() {
        List<Integer> prioridades = new ArrayList<>();
        Iterator<LoteProduccion> it = coleccion.iteratorPorPrioridad();
        while (it.hasNext()) {
            prioridades.add(it.next().getPrioridad());
        }
        assertEquals(List.of(1, 2, 3), prioridades,
            "Prioridad 1 (alta) debe aparecer primero");
    }

    @Test
    @DisplayName("Iterador FIFO no altera el iterador por prioridad")
    void iteradoresSonIndependientes() {
        for (LoteProduccion l : coleccion) { l.getId(); }

        List<String> porPrioridad = new ArrayList<>();
        Iterator<LoteProduccion> it = coleccion.iteratorPorPrioridad();
        while (it.hasNext()) porPrioridad.add(it.next().getId());

        assertEquals("L002", porPrioridad.get(0), "L002 (prioridad 1) debe ser el primero");
    }

    @Test
    @DisplayName("Coleccion vacia retorna iterador vacio")
    void coleccionVacia() {
        ColeccionLotes vacia = new ColeccionLotes();
        assertFalse(vacia.iterator().hasNext());
        assertFalse(vacia.iteratorPorPrioridad().hasNext());
    }
}
