package com.fabricaviva;

import com.fabricaviva.integracion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adapter — AdaptadorErp")
class AdapterTest {

    private RegistroProduccion adaptador;

    @BeforeEach
    void setUp() {
        adaptador = new AdaptadorErp(new SistemaErpLegado());
    }

    @Test
    @DisplayName("leerCantidadProducida() retorna un entero positivo")
    void cantidadProducidaPositiva() {
        int cantidad = adaptador.leerCantidadProducida("REF-001");
        assertTrue(cantidad > 0, "La cantidad producida debe ser positiva");
    }

    @Test
    @DisplayName("leerCantidadProducida() parsea la respuesta del ERP legado (312)")
    void cantidadParsedaCorrecta() {
        int cantidad = adaptador.leerCantidadProducida("REF-001");
        assertEquals(312, cantidad);
    }

    @Test
    @DisplayName("registrarCierreTurno() no lanza excepcion")
    void cierreTurnoSinExcepcion() {
        assertDoesNotThrow(() -> adaptador.registrarCierreTurno("MANANA", 450));
    }

    @Test
    @DisplayName("AdaptadorErp implementa la interfaz RegistroProduccion del MES")
    void implementaInterfazMes() {
        assertInstanceOf(RegistroProduccion.class, adaptador,
                "El adaptador debe ser utilizable como RegistroProduccion");
    }
}
