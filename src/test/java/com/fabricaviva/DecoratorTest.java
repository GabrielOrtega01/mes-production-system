package com.fabricaviva;

import com.fabricaviva.costos.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Decorator — CalculoCostoOrden")
class DecoratorTest {

    @Test
    @DisplayName("CostoBaseOrden retorna el costo base sin modificar")
    void costoBaseSinDecorar() {
        CalculoCostoOrden c = new CostoBaseOrden("OP-001", 1000.0);
        assertEquals(1000.0, c.calcularCosto(), 0.01);
    }

    @Test
    @DisplayName("RecargoPrioritario aplica +25% sobre el costo base")
    void recargoPrioritario() {
        CalculoCostoOrden c = new RecargoPrioritario(new CostoBaseOrden("OP-002", 1000.0));
        assertEquals(1250.0, c.calcularCosto(), 0.01);
    }

    @Test
    @DisplayName("CargoHorasExtra aplica +30% acumulado")
    void cargoHorasExtra() {
        CalculoCostoOrden c = new CargoHorasExtra(new CostoBaseOrden("OP-003", 1000.0));
        assertEquals(1300.0, c.calcularCosto(), 0.01);
    }

    @Test
    @DisplayName("DescuentoVolumen aplica -15% acumulado")
    void descuentoVolumen() {
        CalculoCostoOrden c = new DescuentoVolumen(new CostoBaseOrden("OP-004", 1000.0));
        assertEquals(850.0, c.calcularCosto(), 0.01);
    }

    @Test
    @DisplayName("Cadena completa: base + recargo + horasExtra + descuento = 1381.25")
    void cadenaCostoCompleta() {
        CalculoCostoOrden c = new CostoBaseOrden("OP-005", 1000.0);
        c = new RecargoPrioritario(c);  // *1.25 = 1250
        c = new CargoHorasExtra(c);     // *1.30 = 1625
        c = new DescuentoVolumen(c);    // *0.85 = 1381.25
        assertEquals(1381.25, c.calcularCosto(), 0.01);
    }

    @Test
    @DisplayName("describir() incluye la referencia de la orden")
    void describirMencionaOrden() {
        CalculoCostoOrden c = new RecargoPrioritario(new CostoBaseOrden("OP-006", 500.0));
        assertTrue(c.describir().contains("OP-006"),
                "La descripcion debe incluir el codigo de la orden");
    }
}
