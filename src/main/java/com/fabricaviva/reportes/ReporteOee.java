package com.fabricaviva.reportes;

import java.util.Locale;

/** Producto del patrón Builder: el reporte que recibe cada usuario. */
public class ReporteOee {

    private String equipo;
    private String turno;
    private double disponibilidad;
    private double rendimiento;
    private double calidad;
    private double oee;
    private String desglose;

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

    public double getCalidad() {
        return calidad;
    }

    public void setCalidad(double calidad) {
        this.calidad = calidad;
    }

    public double getOee() {
        return oee;
    }

    public void setOee(double oee) {
        this.oee = oee;
    }

    public void setDesglose(String desglose) {
        this.desglose = desglose;
    }

    @Override
    public String toString() {
        String base = String.format(Locale.US, "Reporte OEE - %s (turno %s): %.2f%%", equipo, turno, oee * 100);
        return desglose == null ? base : base + "\n" + desglose;
    }
}
