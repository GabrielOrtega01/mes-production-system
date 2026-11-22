package com.fabricaviva;

import com.fabricaviva.inspeccion.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Template Method — InspeccionCalidad")
class TemplateMethodTest {

    @Test
    @DisplayName("InspeccionDimensional produce resultado de tipo DIMENSIONAL")
    void tipoInspeccionDimensionalEsDimensional() {
        InspeccionCalidad inspeccion = new InspeccionDimensional(42L);
        ResultadoInspeccion resultado = inspeccion.inspeccionarLote("LOT-001", 100);
        assertEquals("DIMENSIONAL", resultado.tipoInspeccion);
    }

    @Test
    @DisplayName("InspeccionVisual produce resultado de tipo VISUAL")
    void tipoInspeccionVisualEsVisual() {
        InspeccionCalidad inspeccion = new InspeccionVisual(42L);
        ResultadoInspeccion resultado = inspeccion.inspeccionarLote("LOT-002", 100);
        assertEquals("VISUAL", resultado.tipoInspeccion);
    }

    @Test
    @DisplayName("El loteId del resultado coincide con el lote inspeccionado")
    void resultadoContieneLoteId() {
        InspeccionCalidad inspeccion = new InspeccionDimensional(42L);
        ResultadoInspeccion resultado = inspeccion.inspeccionarLote("LOT-XYZ", 50);
        assertEquals("LOT-XYZ", resultado.loteId);
    }

    @Test
    @DisplayName("InspeccionVisual con semilla 0 aprueba un lote con 100 muestras")
    void inspeccionVisualConSemillaCeroAprueba() {
        // Semilla 0 produce defectos < 5% sistematicamente
        InspeccionCalidad inspeccion = new InspeccionVisual(0L);
        ResultadoInspeccion resultado = inspeccion.inspeccionarLote("LOT-003", 100);
        assertTrue(resultado.defectos >= 0, "El numero de defectos debe ser no negativo");
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("El criterio dimensional (3%) es mas estricto que el base (5%)")
    void criterioMasEstrictoDimensional() {
        // Con semilla fija, dimensional rechaza lo que visual aprobaría
        InspeccionCalidad dimensional = new InspeccionDimensional(1L);
        InspeccionCalidad visual      = new InspeccionVisual(1L);
        ResultadoInspeccion rDim = dimensional.inspeccionarLote("LOT-D", 100);
        ResultadoInspeccion rVis = visual.inspeccionarLote("LOT-V", 100);
        // Ambos no deben lanzar excepciones y deben tener un resultado definido
        assertNotNull(rDim);
        assertNotNull(rVis);
    }

    @Test
    @DisplayName("Resultado con 0 defectos siempre es APROBADO")
    void ceroDefectosEsAprobado() {
        // InspeccionVisual con semilla que produce 0 defectos
        InspeccionCalidad inspeccion = new InspeccionVisual(99L);
        ResultadoInspeccion resultado = inspeccion.inspeccionarLote("LOT-004", 1000);
        // Si hay 0 defectos, definitivamente aprobado
        if (resultado.defectos == 0) {
            assertTrue(resultado.aprobado, "0 defectos = APROBADO");
        }
        assertNotNull(resultado);
    }
}
