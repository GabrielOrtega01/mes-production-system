package com.academica.pruebas;

import com.academica.decorator.BecaMerito;
import com.academica.decorator.CalculoMatricula;
import com.academica.decorator.DescuentoHermanos;
import com.academica.decorator.DescuentoProntoPago;
import com.academica.decorator.MatriculaBase;
import com.academica.decorator.RecargoIntersemestral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Patron Decorator - Calculo de matricula")
public class PruebaDecorator {

    private static final double BASE = 5_000_000.0;
    private static final double DELTA = 0.01;

    @Test
    @DisplayName("Solo la matricula base sin decoradores")
    void soloBase() {
        CalculoMatricula m = new MatriculaBase();
        assertEquals(BASE, m.calcularCosto(), DELTA);
        assertTrue(m.describir().contains("base"));
    }

    @Test
    @DisplayName("Base + beca de merito (-30%)")
    void baseConBecaMerito() {
        CalculoMatricula m = new BecaMerito(new MatriculaBase());
        assertEquals(BASE * 0.70, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("Base + descuento hermanos (-10%)")
    void baseConDescuentoHermanos() {
        CalculoMatricula m = new DescuentoHermanos(new MatriculaBase());
        assertEquals(BASE * 0.90, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("Base + descuento pronto pago (-5%)")
    void baseConDescuentoProntoPago() {
        CalculoMatricula m = new DescuentoProntoPago(new MatriculaBase());
        assertEquals(BASE * 0.95, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("Base + recargo intersemestral (+15%)")
    void baseConRecargoIntersemestral() {
        CalculoMatricula m = new RecargoIntersemestral(new MatriculaBase());
        assertEquals(BASE * 1.15, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("Dos decoradores: BecaMerito->DescuentoHermanos vs orden inverso")
    void dosDecoradoresDistintoOrden() {
        // Orden A: primero beca, luego hermanos
        CalculoMatricula ordenA = new DescuentoHermanos(
                                      new BecaMerito(new MatriculaBase()));
        // Orden B: primero hermanos, luego beca
        CalculoMatricula ordenB = new BecaMerito(
                                      new DescuentoHermanos(new MatriculaBase()));

        double costoA = BASE * 0.70 * 0.90;
        double costoB = BASE * 0.90 * 0.70;

        assertEquals(costoA, ordenA.calcularCosto(), DELTA);
        assertEquals(costoB, ordenB.calcularCosto(), DELTA);
        // Con multiplicadores porcentuales la conmutatividad garantiza igual resultado
        assertEquals(ordenA.calcularCosto(), ordenB.calcularCosto(), DELTA,
                "El orden de decoradores multiplicativos no altera el costo final");
    }

    @Test
    @DisplayName("Tres decoradores encadenados: beca + hermanos + pronto pago")
    void tresDecoradores() {
        CalculoMatricula m = new DescuentoProntoPago(
                                 new DescuentoHermanos(
                                     new BecaMerito(new MatriculaBase())));
        double esperado = BASE * 0.70 * 0.90 * 0.95;
        assertEquals(esperado, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("Recargo intersemestral combinado con descuento por beca")
    void recargoConBeca() {
        CalculoMatricula m = new RecargoIntersemestral(
                                 new BecaMerito(new MatriculaBase()));
        double esperado = BASE * 0.70 * 1.15;
        assertEquals(esperado, m.calcularCosto(), DELTA);
    }

    @Test
    @DisplayName("describir() acumula la descripcion de cada capa")
    void describirAcumula() {
        CalculoMatricula m = new DescuentoHermanos(new BecaMerito(new MatriculaBase()));
        String desc = m.describir();
        assertTrue(desc.contains("base"),      "Debe mencionar el costo base");
        assertTrue(desc.contains("merito"),    "Debe mencionar la beca de merito");
        assertTrue(desc.contains("hermanos"),  "Debe mencionar el descuento hermanos");
    }
}
