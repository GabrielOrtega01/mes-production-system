package com.fabricaviva.memento;

/** Memento: cápsula inmutable del estado de un turno. */
public class InstantaneTurno {

    private final String turno;
    private final double metaOee;
    private final int    capacidadMaxima;

    InstantaneTurno(String turno, double metaOee, int capacidadMaxima) {
        this.turno           = turno;
        this.metaOee         = metaOee;
        this.capacidadMaxima = capacidadMaxima;
    }

    String getTurno()           { return turno; }
    double getMetaOee()         { return metaOee; }
    int    getCapacidadMaxima() { return capacidadMaxima; }

    @Override
    public String toString() {
        return "Instantanea[turno=" + turno + ", metaOEE=" + metaOee
               + ", cap=" + capacidadMaxima + "]";
    }
}
