package com.fabricaviva.pruebas;

import com.fabricaviva.reporteturno.DecoradorReporteTurno;
import com.fabricaviva.reporteturno.EncabezadoPlanta;
import com.fabricaviva.reporteturno.FirmaJefeTurno;
import com.fabricaviva.reporteturno.MarcaAguaBorrador;
import com.fabricaviva.reporteturno.ReporteBaseTurno;
import com.fabricaviva.reporteturno.ReporteTurno;
import com.fabricaviva.reporteturno.SeccionObservaciones;

public class PruebaDecoratorProfundizacion {

    public static void main(String[] args) {

        ReporteTurno base = new ReporteBaseTurno("MANANA", 450, 83.5);

        System.out.println("== Prueba 1: reporte base sin decoradores ==");
        System.out.println(base.generar());

        System.out.println("\n== Prueba 2: reporte oficial con encabezado y firma ==");
        ReporteTurno oficial = new FirmaJefeTurno(
                new EncabezadoPlanta(base, "FabricaViva MES"),
                "Ing. Carlos Ruiz");
        System.out.println(oficial.generar());

        System.out.println("\n== Prueba 3: reporte marcado como borrador ==");
        ReporteTurno borrador = new MarcaAguaBorrador(base);
        System.out.println(borrador.generar());

        System.out.println("\n== Prueba 4: reporte completo (encabezado + firma + observaciones) ==");
        ReporteTurno completo = new SeccionObservaciones(
                new FirmaJefeTurno(
                        new EncabezadoPlanta(base, "FabricaViva MES"),
                        "Ing. Carlos Ruiz"),
                "Paro no planificado 20 min en Linea A por mantenimiento correctivo");
        System.out.println(completo.generar());
        System.out.println("Capas: " + completo.describir());

        System.out.println("\n== Prueba 5: el orden de decoracion importa ==");
        ReporteTurno ordenA = new EncabezadoPlanta(
                new MarcaAguaBorrador(base), "FabricaViva MES");
        ReporteTurno ordenB = new MarcaAguaBorrador(
                new EncabezadoPlanta(base, "FabricaViva MES"));
        System.out.println("Orden A — encabezado envuelve borrador:");
        System.out.println(ordenA.generar());
        System.out.println("Orden B — borrador envuelve encabezado:");
        System.out.println(ordenB.generar());

        System.out.println("\n== Prueba 6: Decorator sustituible por ReporteTurno (Liskov) ==");
        ReporteTurno[] reportes = { base, oficial, borrador, completo };
        String[] etiquetas = { "Base", "Oficial", "Borrador", "Completo" };
        for (int i = 0; i < reportes.length; i++) {
            System.out.println(etiquetas[i] + " -> " + reportes[i].describir());
        }
    }
}
