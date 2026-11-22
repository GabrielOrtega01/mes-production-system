package com.fabricaviva.memento;

/**
 * Originator: configuración activa del turno.
 * Puede guardar y restaurar su estado a través de InstantaneTurno.
 */
public class ConfiguracionTurno {

    private String turno;
    private double metaOee;
    private int    capacidadMaxima;

    public ConfiguracionTurno(String turno, double metaOee, int capacidadMaxima) {
        this.turno           = turno;
        this.metaOee         = metaOee;
        this.capacidadMaxima = capacidadMaxima;
    }

    public InstantaneTurno guardar() {
        System.out.println("[Originator] Guardando estado: " + resumen());
        return new InstantaneTurno(turno, metaOee, capacidadMaxima);
    }

    public void restaurar(InstantaneTurno snap) {
        this.turno           = snap.getTurno();
        this.metaOee         = snap.getMetaOee();
        this.capacidadMaxima = snap.getCapacidadMaxima();
        System.out.println("[Originator] Estado restaurado: " + resumen());
    }

    public void setMetaOee(double v)          { this.metaOee = v; }
    public void setCapacidadMaxima(int v)     { this.capacidadMaxima = v; }
    public void setTurno(String v)            { this.turno = v; }

    public String getTurno()           { return turno; }
    public double getMetaOee()         { return metaOee; }
    public int    getCapacidadMaxima() { return capacidadMaxima; }

    public String resumen() {
        return "turno=" + turno + " metaOEE=" + metaOee + " cap=" + capacidadMaxima;
    }
}
