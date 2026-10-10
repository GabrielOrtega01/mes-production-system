package com.fabricaviva.produccion;

/** Hoja del Composite: máquina individual con capacidad fija. */
public class Maquina implements UnidadProduccion {

    private final String nombre;
    private final double capacidad;

    public Maquina(String nombre, double capacidad) {
        this.nombre    = nombre;
        this.capacidad = capacidad;
    }

    @Override
    public double getCapacidadTotal() {
        return capacidad;
    }

    @Override
    public int getTotalMaquinas() {
        return 1;
    }

    @Override
    public void mostrar(String prefijo) {
        System.out.printf("%s[Máquina] %s — %.0f pz/h%n", prefijo, nombre, capacidad);
    }

    public String getNombre() { return nombre; }
}
