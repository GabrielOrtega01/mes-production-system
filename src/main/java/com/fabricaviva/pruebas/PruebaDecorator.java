package com.fabricaviva.pruebas;

import com.fabricaviva.costos.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifica que el patrón Decorator permita combinar ajustes de costo
 * de órdenes de producción sin modificar las clases existentes.
 */
class PruebaDecorator {

    private static final double COSTO_BASE = 1_000_000.0;
    private static final double DELTA      = 0.01;

    private CalculoCostoOrden base;

    @BeforeEach
    void setUp() {
        base = new CostoBaseOrden("OP-2024-001", COSTO_BASE);
    }

    @Test
    void costoBaseSinDecoradores() {
        assertEquals(COSTO_BASE, base.calcularCosto(), DELTA);
    }

    @Test
    void recargoPrioritarioAumentaCosto25Porciento() {
        CalculoCostoOrden orden = new RecargoPrioritario(base);
        assertEquals(COSTO_BASE * 1.25, orden.calcularCosto(), DELTA);
    }

    @Test
    void cargoHorasExtraAumentaCosto30Porciento() {
        CalculoCostoOrden orden = new CargoHorasExtra(base);
        assertEquals(COSTO_BASE * 1.30, orden.calcularCosto(), DELTA);
    }

    @Test
    void descuentoVolumenReduceCosto15Porciento() {
        CalculoCostoOrden orden = new DescuentoVolumen(base);
        assertEquals(COSTO_BASE * 0.85, orden.calcularCosto(), DELTA);
    }

    @Test
    void descuentoClientePreferenteReduceCosto10Porciento() {
        CalculoCostoOrden orden = new DescuentoClientePreferente(base);
        assertEquals(COSTO_BASE * 0.90, orden.calcularCosto(), DELTA);
    }

    @Test
    void dosDecoradoresEncadenados_prioritarioYHorasExtra() {
        // 1 000 000 * 1.25 * 1.30 = 1 625 000
        CalculoCostoOrden orden = new CargoHorasExtra(new RecargoPrioritario(base));
        assertEquals(COSTO_BASE * 1.25 * 1.30, orden.calcularCosto(), DELTA);
    }

    @Test
    void dosDecoradoresEnDistintoOrden_conmutatividad() {
        // Con factores multiplicativos el orden no altera el resultado
        CalculoCostoOrden ordenAB = new DescuentoVolumen(new RecargoPrioritario(base));
        CalculoCostoOrden ordenBA = new RecargoPrioritario(new DescuentoVolumen(base));
        assertEquals(ordenAB.calcularCosto(), ordenBA.calcularCosto(), DELTA);
    }

    @Test
    void tresDecoradoresEncadenados() {
        // prioritario + horasExtra + volumen
        CalculoCostoOrden orden = new DescuentoVolumen(
                                    new CargoHorasExtra(
                                      new RecargoPrioritario(base)));
        double esperado = COSTO_BASE * 1.25 * 1.30 * 0.85;
        assertEquals(esperado, orden.calcularCosto(), DELTA);
    }

    @Test
    void describir_acumulaDescripcionDeCadaCapa() {
        CalculoCostoOrden orden = new RecargoPrioritario(
                                    new DescuentoClientePreferente(base));
        String desc = orden.describir();
        assertTrue(desc.contains("OP-2024-001"));
        assertTrue(desc.contains("cliente preferente"));
        assertTrue(desc.contains("prioritario"));
    }
}
