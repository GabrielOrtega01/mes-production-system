package com.fabricaviva;

import com.fabricaviva.prototipo.EspecificacionPieza;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prototype — EspecificacionPieza")
class PrototypeTest {

    @Test
    @DisplayName("clonar() retorna un objeto diferente (no la misma referencia)")
    void clonarNoDaLaMismaReferencia() {
        EspecificacionPieza original = new EspecificacionPieza("P-001", "Acero", 0.05);
        EspecificacionPieza clon = original.clonar();
        assertNotSame(original, clon);
    }

    @Test
    @DisplayName("El clon tiene los mismos valores iniciales que el original")
    void clonTieneMismosDatos() {
        EspecificacionPieza original = new EspecificacionPieza("P-001", "Acero", 0.05);
        EspecificacionPieza clon = original.clonar();
        assertEquals(original.getTolerancia(), clon.getTolerancia(), 0.0001);
    }

    @Test
    @DisplayName("Modificar el clon no afecta al original")
    void clonEsIndependiente() {
        EspecificacionPieza original = new EspecificacionPieza("P-001", "Acero", 0.05);
        EspecificacionPieza clon = original.clonar();
        clon.setTolerancia(0.10);
        assertEquals(0.05, original.getTolerancia(), 0.0001,
                "La tolerancia del original no debe cambiar al modificar el clon");
    }

    @Test
    @DisplayName("La lista de pasos de calidad del clon es independiente del original")
    void listaPasosEsIndependiente() {
        EspecificacionPieza original = new EspecificacionPieza("P-001", "Acero", 0.05);
        original.agregarPasoControlCalidad("Paso A");
        EspecificacionPieza clon = original.clonar();
        clon.agregarPasoControlCalidad("Paso B");
        assertEquals(1, original.getCantidadControles(),
                "El original no debe ver los pasos agregados al clon");
        assertEquals(2, clon.getCantidadControles());
    }
}
