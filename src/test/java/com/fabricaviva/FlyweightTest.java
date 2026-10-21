package com.fabricaviva;

import com.fabricaviva.materiales.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Flyweight — FabricaTipoMaterial")
class FlyweightTest {

    private FabricaTipoMaterial fabrica;

    @BeforeEach
    void setUp() {
        fabrica = FabricaTipoMaterial.getInstance();
    }

    @Test
    @DisplayName("Mismo codigo retorna la misma instancia del flyweight")
    void mismoCodigoMismaInstancia() {
        TipoMaterialFlyweight a = fabrica.getMaterial("ACERO", "Acero Inox", 7.9, "kg/cm3");
        TipoMaterialFlyweight b = fabrica.getMaterial("ACERO", "Acero Inox", 7.9, "kg/cm3");
        assertSame(a, b, "El flyweight debe reusar la instancia del pool");
    }

    @Test
    @DisplayName("Codigos distintos producen instancias distintas")
    void codigosDistintosInstanciasDistintas() {
        TipoMaterialFlyweight aluminio = fabrica.getMaterial("ALU", "Aluminio", 2.7, "kg/cm3");
        TipoMaterialFlyweight cobre    = fabrica.getMaterial("COB", "Cobre",    8.9, "kg/cm3");
        assertNotSame(aluminio, cobre);
    }

    @Test
    @DisplayName("El pool crece solo cuando se agrega un codigo nuevo")
    void poolCreceSoloConNuevoCodigo() {
        int antes = fabrica.getTamanioPool();
        fabrica.getMaterial("MAT-TEST-99", "Test", 1.0, "u");
        fabrica.getMaterial("MAT-TEST-99", "Test", 1.0, "u"); // segunda vez: hit
        assertEquals(antes + 1, fabrica.getTamanioPool(),
                "El pool solo debe crecer al agregar un codigo nuevo");
    }

    @Test
    @DisplayName("FabricaTipoMaterial es singleton")
    void fabricaEsSingleton() {
        assertSame(FabricaTipoMaterial.getInstance(), FabricaTipoMaterial.getInstance());
    }
}
