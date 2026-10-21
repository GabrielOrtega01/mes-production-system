package com.fabricaviva;

import com.fabricaviva.produccion.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Composite — Planta / Estacion / Maquina")
class CompositeTest {

    @Test
    @DisplayName("Maquina hoja reporta su propia capacidad")
    void maquinaReportaCapacidad() {
        Maquina m = new Maquina("Torno CNC-01", 120);
        assertEquals(120.0, m.getCapacidadTotal(), 0.01);
    }

    @Test
    @DisplayName("Maquina hoja cuenta como 1 maquina")
    void maquinaEsUna() {
        assertEquals(1, new Maquina("Torno", 80).getTotalMaquinas());
    }

    @Test
    @DisplayName("Planta suma capacidades de todas las maquinas hijas")
    void plantaSumaCapacidades() {
        Maquina m1 = new Maquina("Torno",     120);
        Maquina m2 = new Maquina("Fresadora",  80);
        Maquina m3 = new Maquina("Soldadora",  60);

        Estacion est = new Estacion("Mecanizado");
        est.agregar(m1);
        est.agregar(m2);

        Planta planta = new Planta("FabricaViva");
        planta.agregar(est);
        planta.agregar(m3);

        assertEquals(260.0, planta.getCapacidadTotal(), 0.01);
    }

    @Test
    @DisplayName("Planta cuenta el total de maquinas en toda la jerarquia")
    void plantaCuentaMaquinas() {
        Maquina m1 = new Maquina("A", 100);
        Maquina m2 = new Maquina("B", 100);
        Maquina m3 = new Maquina("C", 100);

        Estacion est = new Estacion("Linea");
        est.agregar(m1);
        est.agregar(m2);

        Planta planta = new Planta("Test");
        planta.agregar(est);
        planta.agregar(m3);

        assertEquals(3, planta.getTotalMaquinas());
    }

    @Test
    @DisplayName("Quitar una maquina reduce la capacidad total")
    void quitarReduceCapacidad() {
        Maquina m1 = new Maquina("A", 100);
        Maquina m2 = new Maquina("B",  50);
        Planta planta = new Planta("Test");
        planta.agregar(m1);
        planta.agregar(m2);
        planta.quitar(m2);
        assertEquals(100.0, planta.getCapacidadTotal(), 0.01);
    }
}
