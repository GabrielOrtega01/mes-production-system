package com.fabricaviva;

import com.fabricaviva.reportes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Builder — GeneradorReportes + ReporteOeeBuilder")
class BuilderTest {

    private GeneradorReportes director;
    private MetricasTurno metricas;

    @BeforeEach
    void setUp() {
        director = new GeneradorReportes();
        // 480 min planificados, 30 min paradas, ciclo ideal 0.5 min, 900 piezas, 882 buenas
        metricas = new MetricasTurno(480, 30, 0.5, 900, 882);
    }

    @Test
    @DisplayName("El reporte construido tiene OEE entre 0 y 1")
    void oeeEnRangoValido() {
        ReporteOeeBuilder builder = new ReporteResumidoBuilder();
        ReporteOee reporte = director.construirReporte(builder, metricas, "CNC-01", "MANANA");
        assertTrue(reporte.getOee() > 0 && reporte.getOee() <= 1.0,
                "OEE debe estar entre 0 y 1");
    }

    @Test
    @DisplayName("Disponibilidad = (planificado - paradas) / planificado")
    void disponibilidadCorrecta() {
        ReporteOeeBuilder builder = new ReporteResumidoBuilder();
        ReporteOee reporte = director.construirReporte(builder, metricas, "CNC-01", "MANANA");
        double esperada = (480.0 - 30.0) / 480.0;
        assertEquals(esperada, reporte.getDisponibilidad(), 0.001);
    }

    @Test
    @DisplayName("Calidad = piezas buenas / piezas producidas")
    void calidadCorrecta() {
        ReporteOeeBuilder builder = new ReporteResumidoBuilder();
        ReporteOee reporte = director.construirReporte(builder, metricas, "CNC-01", "MANANA");
        double esperada = 882.0 / 900.0;
        assertEquals(esperada, reporte.getCalidad(), 0.001);
    }

    @Test
    @DisplayName("OEE = disponibilidad * rendimiento * calidad")
    void oeeEsProductoDeTres() {
        ReporteOeeBuilder builder = new ReporteResumidoBuilder();
        ReporteOee reporte = director.construirReporte(builder, metricas, "CNC-01", "MANANA");
        double oeeCalculado = reporte.getDisponibilidad()
                * reporte.getRendimiento()
                * reporte.getCalidad();
        assertEquals(oeeCalculado, reporte.getOee(), 0.001);
    }
}
