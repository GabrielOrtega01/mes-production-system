package com.fabricaviva;

import com.fabricaviva.calidad.PuestoInspeccion;
import com.fabricaviva.config.ConfiguracionPlanta;
import com.fabricaviva.produccion.LineaProduccion;

public class PruebaSingleton {

    public static void main(String[] args) {
        System.out.println("== Prueba 1: se pide la configuracion tres veces ==");
        ConfiguracionPlanta a = ConfiguracionPlanta.getInstancia();
        ConfiguracionPlanta b = ConfiguracionPlanta.getInstancia();
        ConfiguracionPlanta c = ConfiguracionPlanta.getInstancia();

        System.out.println("\n== Prueba 2: las tres referencias son el mismo objeto ==");
        System.out.println("hashCode a: " + a.hashCode());
        System.out.println("hashCode b: " + b.hashCode());
        System.out.println("hashCode c: " + c.hashCode());
        System.out.println("a == b ? " + (a == b) + " | b == c ? " + (b == c));

        System.out.println("\n== Prueba 3: dos modulos comparten la misma configuracion ==");
        LineaProduccion linea = new LineaProduccion("CNC-01");
        PuestoInspeccion inspeccion = new PuestoInspeccion();
        System.out.println(linea.producirPieza(1));
        System.out.println(inspeccion.evaluar(0.91));

        System.out.println("\nSupervision cambia a turno NOCHE y sube la meta de OEE a 0.93...");
        a.setTurnoActivo("NOCHE");
        a.setUmbralOee(0.93);

        System.out.println(linea.producirPieza(2));
        System.out.println(inspeccion.evaluar(0.91));
    }
}
