package com.fabricaviva;

import com.fabricaviva.proxy.*;
import com.fabricaviva.reportes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Proxy — ProxyReportesSeguro")
class ProxyTest {

    private MetricasTurno metricas() {
        return new MetricasTurno(480, 30, 0.5, 900, 882);
    }

    @Test
    @DisplayName("JEFE_TURNO tiene acceso y recibe el reporte")
    void jefeTurnoAccedeCorrecto() {
        ProxyReportesSeguro proxy = new ProxyReportesSeguro(RolUsuario.JEFE_TURNO);
        ReporteOee reporte = proxy.construirReporte(
                new ReporteResumidoBuilder(), metricas(), "CNC-01", "MANANA");
        assertNotNull(reporte, "El jefe de turno debe recibir el reporte");
    }

    @Test
    @DisplayName("SUPERVISOR tiene acceso y recibe el reporte")
    void supervisorAccedeCorrecto() {
        ProxyReportesSeguro proxy = new ProxyReportesSeguro(RolUsuario.SUPERVISOR);
        assertDoesNotThrow(() ->
                proxy.construirReporte(new ReporteResumidoBuilder(), metricas(), "CNC-01", "TARDE"));
    }

    @Test
    @DisplayName("OPERADOR lanza SecurityException")
    void operadorDenegado() {
        ProxyReportesSeguro proxy = new ProxyReportesSeguro(RolUsuario.OPERADOR);
        assertThrows(SecurityException.class, () ->
                proxy.construirReporte(new ReporteResumidoBuilder(), metricas(), "CNC-01", "NOCHE"),
                "OPERADOR no debe poder generar reportes OEE");
    }

    @Test
    @DisplayName("El objeto real se crea solo tras el primer acceso autorizado (lazy init)")
    void lazyInitializacion() {
        ProxyReportesSeguro proxy = new ProxyReportesSeguro(RolUsuario.JEFE_TURNO);
        assertFalse(proxy.estaInicializado(), "El generador real no debe existir antes del primer acceso");
        proxy.construirReporte(new ReporteResumidoBuilder(), metricas(), "CNC-01", "MANANA");
        assertTrue(proxy.estaInicializado(), "El generador real debe existir despues del primer acceso");
    }

    @Test
    @DisplayName("La bitacora registra el intento denegado de OPERADOR")
    void bitacoraRegistraDenegado() {
        ProxyReportesSeguro proxy = new ProxyReportesSeguro(RolUsuario.OPERADOR);
        try { proxy.construirReporte(new ReporteResumidoBuilder(), metricas(), "X", "Y"); }
        catch (SecurityException ignored) {}
        assertFalse(proxy.getBitacora().isEmpty(), "La bitacora debe registrar el intento");
    }
}
