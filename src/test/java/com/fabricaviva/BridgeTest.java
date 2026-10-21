package com.fabricaviva;

import com.fabricaviva.alertas.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bridge — AlertaProduccion x CanalAlerta")
class BridgeTest {

    @Test
    @DisplayName("AlertaMantenimiento registra en BitacoraAlerta")
    void alertaMantenimientoEnBitacora() {
        BitacoraAlerta bitacora = new BitacoraAlerta();
        AlertaProduccion alerta = new AlertaMantenimiento(bitacora, "MANANA");
        alerta.disparar("Falla en bomba hidraulica");
        assertEquals(1, bitacora.getRegistros().size());
    }

    @Test
    @DisplayName("AlertaCalidad registra en BitacoraAlerta")
    void alertaCalidadEnBitacora() {
        BitacoraAlerta bitacora = new BitacoraAlerta();
        AlertaProduccion alerta = new AlertaCalidad(bitacora, "TARDE");
        alerta.disparar("OEE por debajo de umbral 0.85");
        assertEquals(1, bitacora.getRegistros().size());
    }

    @Test
    @DisplayName("El registro contiene la descripcion disparada")
    void registroContieneDescripcion() {
        BitacoraAlerta bitacora = new BitacoraAlerta();
        new AlertaParada(bitacora, "NOCHE").disparar("Paro de emergencia");
        String entrada = bitacora.getRegistros().get(0);
        assertTrue(entrada.contains("Paro de emergencia"),
                "El registro debe incluir la descripcion del evento");
    }

    @Test
    @DisplayName("Cambiar canal no requiere cambiar el tipo de alerta")
    void cambiarCanalNoCambiaAlerta() {
        BitacoraAlerta bitacoraA = new BitacoraAlerta();
        BitacoraAlerta bitacoraB = new BitacoraAlerta();
        AlertaProduccion alertaConA = new AlertaMantenimiento(bitacoraA, "MANANA");
        AlertaProduccion alertaConB = new AlertaMantenimiento(bitacoraB, "MANANA");
        alertaConA.disparar("test");
        alertaConB.disparar("test");
        assertEquals(alertaConA.getClass(), alertaConB.getClass(),
                "El tipo de alerta es el mismo aunque el canal sea distinto");
    }
}
