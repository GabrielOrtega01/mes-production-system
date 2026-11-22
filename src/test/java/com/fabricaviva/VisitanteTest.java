package com.fabricaviva;

import com.fabricaviva.visitante.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Visitor — VisitanteOee / VisitanteCostos")
class VisitanteTest {

    private MaquinaElement maquinaA;
    private MaquinaElement maquinaB;
    private LineaElement linea;

    @BeforeEach
    void setUp() {
        maquinaA = new MaquinaElement("MCN-A", 0.90, 150.0);
        maquinaB = new MaquinaElement("MCN-B", 0.70, 200.0);
        linea    = new LineaElement("LINEA-1");
        linea.agregar(maquinaA);
        linea.agregar(maquinaB);
    }

    @Test
    @DisplayName("VisitanteOee calcula el promedio correcto de OEE")
    void oeePromedioCalculaCorrecto() {
        VisitanteOee visitante = new VisitanteOee();
        maquinaA.aceptar(visitante);
        maquinaB.aceptar(visitante);
        assertEquals(0.80, visitante.getOeePromedio(), 0.001,
            "Promedio de 0.90 y 0.70 debe ser 0.80");
    }

    @Test
    @DisplayName("VisitanteCostos suma correctamente los costos por hora")
    void costoTotalSumaTodasMaquinas() {
        VisitanteCostos visitante = new VisitanteCostos();
        maquinaA.aceptar(visitante);
        maquinaB.aceptar(visitante);
        assertEquals(350.0, visitante.getCostoTotal(), 0.001,
            "150 + 200 = 350 por hora");
    }

    @Test
    @DisplayName("LineaElement aplica el visitante a todas las maquinas anidadas")
    void lineaVisitaMaquinasAnidadas() {
        VisitanteOee visitante = new VisitanteOee();
        linea.aceptar(visitante);
        assertEquals(0.80, visitante.getOeePromedio(), 0.001,
            "El visitante debe recorrer ambas maquinas de la linea");
    }

    @Test
    @DisplayName("VisitanteOee en coleccion vacia retorna OEE 0")
    void oeePromedioEnColeccionVaciaEsCero() {
        VisitanteOee visitante = new VisitanteOee();
        assertEquals(0.0, visitante.getOeePromedio(), 0.001);
    }

    @Test
    @DisplayName("VisitanteCostos en linea suma costos de todas las maquinas")
    void costosEnLineaSumaTodo() {
        VisitanteCostos visitante = new VisitanteCostos();
        linea.aceptar(visitante);
        assertEquals(350.0, visitante.getCostoTotal(), 0.001);
    }
}
