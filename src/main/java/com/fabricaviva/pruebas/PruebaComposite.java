package com.fabricaviva.pruebas;

import com.fabricaviva.produccion.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifica que el patrón Composite permita tratar de forma uniforme
 * las hojas (Maquina) y los contenedores (Estacion, LineaProduccion, Planta).
 */
class PruebaComposite {

    private Maquina tornoCNC;
    private Maquina fresadora;
    private Maquina soldadora;
    private Estacion estacion1;
    private Estacion estacion2;
    private LineaFabricacion linea;
    private Planta planta;

    @BeforeEach
    void setUp() {
        tornoCNC  = new Maquina("Torno CNC",   120.0);
        fresadora = new Maquina("Fresadora",    80.0);
        soldadora = new Maquina("Soldadora",   100.0);

        estacion1 = new Estacion("Mecanizado");
        estacion1.agregar(tornoCNC);
        estacion1.agregar(fresadora);

        estacion2 = new Estacion("Ensamble");
        estacion2.agregar(soldadora);

        linea = new LineaFabricacion("Línea A");
        linea.agregar(estacion1);
        linea.agregar(estacion2);

        planta = new Planta("Planta FábricaViva");
        planta.agregar(linea);
    }

    // --- Maquina (hoja) ---

    @Test
    void maquinaIndividualReportaCapacidadPropia() {
        assertEquals(120.0, tornoCNC.getCapacidadTotal(), 0.001);
    }

    @Test
    void maquinaIndividualReportaUnaMaquina() {
        assertEquals(1, tornoCNC.getTotalMaquinas());
    }

    // --- Estacion (composite nivel 1) ---

    @Test
    void estacionSumaCapacidadDeSusHijos() {
        // 120 + 80 = 200
        assertEquals(200.0, estacion1.getCapacidadTotal(), 0.001);
    }

    @Test
    void estacionCuentaTotalMaquinas() {
        assertEquals(2, estacion1.getTotalMaquinas());
    }

    // --- LineaFabricacion (composite nivel 2) ---

    @Test
    void lineaSumaCapacidadDeSusEstaciones() {
        // (120+80) + 100 = 300
        assertEquals(300.0, linea.getCapacidadTotal(), 0.001);
    }

    @Test
    void lineaCuentaTotalMaquinasRecursivo() {
        assertEquals(3, linea.getTotalMaquinas());
    }

    // --- Planta (composite raíz) ---

    @Test
    void plantaSumaCapacidadDeTodaLaJerarquia() {
        assertEquals(300.0, planta.getCapacidadTotal(), 0.001);
    }

    @Test
    void plantaCuentaTodosLosMaquinasDeLaJerarquia() {
        assertEquals(3, planta.getTotalMaquinas());
    }

    // --- Uniformidad de trato ---

    @Test
    void clienteTrataMaquinaYPlantaMedianteMismaInterfaz() {
        UnidadProduccion hoja      = tornoCNC;
        UnidadProduccion compuesto = planta;
        // ambos responden getCapacidadTotal() sin instanceof
        assertTrue(hoja.getCapacidadTotal() > 0);
        assertTrue(compuesto.getCapacidadTotal() > hoja.getCapacidadTotal());
    }

    @Test
    void agregarMaquinaAEstacionActualizaCapacidadDeLaLinea() {
        double antes = linea.getCapacidadTotal();
        Maquina nueva = new Maquina("Prensa hidráulica", 60.0);
        estacion2.agregar(nueva);
        assertEquals(antes + 60.0, linea.getCapacidadTotal(), 0.001);
    }

    @Test
    void quitarMaquinaDeEstacionActualizaCapacidad() {
        estacion1.quitar(fresadora);
        assertEquals(120.0, estacion1.getCapacidadTotal(), 0.001);
    }

    // Permite ejecutar con: java com.fabricaviva.pruebas.PruebaComposite
    public static void main(String[] args) {
        PruebaComposite t = new PruebaComposite();
        java.util.Map<String, Runnable> pruebas = new java.util.LinkedHashMap<>();
        pruebas.put("maquinaIndividualReportaCapacidadPropia",        () -> { t.setUp(); t.maquinaIndividualReportaCapacidadPropia(); });
        pruebas.put("maquinaIndividualReportaUnaMaquina",             () -> { t.setUp(); t.maquinaIndividualReportaUnaMaquina(); });
        pruebas.put("estacionSumaCapacidadDeSusHijos",                () -> { t.setUp(); t.estacionSumaCapacidadDeSusHijos(); });
        pruebas.put("estacionCuentaTotalMaquinas",                    () -> { t.setUp(); t.estacionCuentaTotalMaquinas(); });
        pruebas.put("lineaSumaCapacidadDeSusEstaciones",              () -> { t.setUp(); t.lineaSumaCapacidadDeSusEstaciones(); });
        pruebas.put("lineaCuentaTotalMaquinasRecursivo",              () -> { t.setUp(); t.lineaCuentaTotalMaquinasRecursivo(); });
        pruebas.put("plantaSumaCapacidadDeTodaLaJerarquia",           () -> { t.setUp(); t.plantaSumaCapacidadDeTodaLaJerarquia(); });
        pruebas.put("plantaCuentaTodosLosMaquinasDeLaJerarquia",      () -> { t.setUp(); t.plantaCuentaTodosLosMaquinasDeLaJerarquia(); });
        pruebas.put("clienteTrataMaquinaYPlantaMedianteMismaInterfaz",() -> { t.setUp(); t.clienteTrataMaquinaYPlantaMedianteMismaInterfaz(); });
        pruebas.put("agregarMaquinaAEstacionActualizaCapacidadDeLaLinea", () -> { t.setUp(); t.agregarMaquinaAEstacionActualizaCapacidadDeLaLinea(); });
        pruebas.put("quitarMaquinaDeEstacionActualizaCapacidad",      () -> { t.setUp(); t.quitarMaquinaDeEstacionActualizaCapacidad(); });

        int ok = 0, fallo = 0;
        System.out.println("== PruebaComposite ==");
        for (var e : pruebas.entrySet()) {
            try {
                e.getValue().run();
                System.out.println("  PASS " + e.getKey());
                ok++;
            } catch (AssertionError | Exception ex) {
                System.out.println("  FAIL " + e.getKey() + " -> " + ex.getMessage());
                fallo++;
            }
        }
        System.out.printf("%n%d PASS | %d FAIL%n", ok, fallo);
    }
}
