package com.fabricaviva.pruebas;

import com.fabricaviva.proxy.AccesoReportes;
import com.fabricaviva.proxy.ProxyReportesSeguro;
import com.fabricaviva.proxy.RolUsuario;
import com.fabricaviva.reportes.MetricasTurno;
import com.fabricaviva.reportes.ReporteDetalladoBuilder;
import com.fabricaviva.reportes.ReporteOee;

public class PruebaProxy {

    static int ok = 0, total = 0;

    static void afirmar(String desc, boolean cond) {
        total++;
        if (cond) { ok++; System.out.println("  [OK]    " + desc); }
        else       {       System.out.println("  [FALLO] " + desc); }
    }

    public static void main(String[] args) {
        MetricasTurno metricas = new MetricasTurno(480, 60, 0.5, 800, 780);

        System.out.println("== Prueba 1: acceso autorizado (JEFE_TURNO) ==");
        ProxyReportesSeguro proxyJefe = new ProxyReportesSeguro(RolUsuario.JEFE_TURNO);
        afirmar("Real no inicializado antes del primer acceso", !proxyJefe.estaInicializado());
        ReporteOee r1 = proxyJefe.construirReporte(
                new ReporteDetalladoBuilder(), metricas, "CNC-01", "Turno A");
        afirmar("Reporte generado sin excepcion", r1 != null);
        afirmar("Lazy init: real creado tras primer acceso autorizado",
                proxyJefe.estaInicializado());
        afirmar("Bitacora registra el acceso", !proxyJefe.getBitacora().isEmpty());

        System.out.println("\n== Prueba 2: acceso denegado (OPERADOR) ==");
        ProxyReportesSeguro proxyOp = new ProxyReportesSeguro(RolUsuario.OPERADOR);
        boolean excepcion = false;
        try {
            proxyOp.construirReporte(
                    new ReporteDetalladoBuilder(), metricas, "CNC-01", "Turno A");
        } catch (SecurityException e) {
            excepcion = true;
            System.out.println("  " + e.getMessage());
        }
        afirmar("SecurityException lanzada para OPERADOR", excepcion);
        afirmar("Real NO inicializado tras acceso denegado", !proxyOp.estaInicializado());

        System.out.println("\n== Prueba 3: acceso autorizado (SUPERVISOR) ==");
        ProxyReportesSeguro proxySup = new ProxyReportesSeguro(RolUsuario.SUPERVISOR);
        ReporteOee r2 = proxySup.construirReporte(
                new ReporteDetalladoBuilder(), metricas, "LINEA-2", "Turno B");
        afirmar("SUPERVISOR puede generar reporte", r2 != null);
        System.out.printf("  OEE generado: %.2f %%%n", r2.getOee() * 100);

        System.out.println("\n== Prueba 4: acceso denegado (AUDITOR) ==");
        ProxyReportesSeguro proxyAud = new ProxyReportesSeguro(RolUsuario.AUDITOR);
        boolean excepcionAud = false;
        try {
            proxyAud.construirReporte(
                    new ReporteDetalladoBuilder(), metricas, "CNC-01", "Turno A");
        } catch (SecurityException e) {
            excepcionAud = true;
        }
        afirmar("SecurityException lanzada para AUDITOR", excepcionAud);

        System.out.println("\n================================================");
        System.out.println("  Pruebas exitosas: " + ok + " / " + total);
        System.out.println("================================================");
    }
}
