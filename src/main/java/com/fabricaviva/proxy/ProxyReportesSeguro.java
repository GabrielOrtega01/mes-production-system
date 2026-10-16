package com.fabricaviva.proxy;

import com.fabricaviva.reportes.MetricasTurno;
import com.fabricaviva.reportes.ReporteOee;
import com.fabricaviva.reportes.ReporteOeeBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Proxy de protección + inicialización perezosa sobre AccesoReportes.
 * Verifica el rol antes de delegar; crea el objeto real sólo al primer
 * acceso autorizado.
 */
public class ProxyReportesSeguro implements AccesoReportes {

    private GeneradorReportesReal real;
    private final RolUsuario      rol;
    private final List<String>    bitacora = new ArrayList<>();

    public ProxyReportesSeguro(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public ReporteOee construirReporte(ReporteOeeBuilder builder,
                                       MetricasTurno metricas,
                                       String equipo, String turno) {
        if (!tieneAcceso()) {
            String msg = "ACCESO DENEGADO: rol " + rol
                    + " no puede generar reportes OEE.";
            bitacora.add(msg);
            throw new SecurityException(msg);
        }
        if (real == null) {
            real = new GeneradorReportesReal();   // lazy init
        }
        bitacora.add("Reporte generado – rol: " + rol
                + " | equipo: " + equipo + " | turno: " + turno);
        return real.construirReporte(builder, metricas, equipo, turno);
    }

    private boolean tieneAcceso() {
        return rol == RolUsuario.JEFE_TURNO || rol == RolUsuario.SUPERVISOR;
    }

    public List<String> getBitacora()    { return bitacora; }
    public boolean      estaInicializado() { return real != null; }
}
