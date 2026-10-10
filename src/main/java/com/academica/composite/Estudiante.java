package com.academica.composite;

/**
 * Patrón Composite – Hoja.
 * Unidad atómica de la jerarquía académica. No contiene otros elementos.
 */
public class Estudiante implements ElementoAcademico {

    private final String nombre;
    private final double promedio;

    public Estudiante(String nombre, double promedio) {
        this.nombre   = nombre;
        this.promedio = promedio;
    }

    @Override
    public double getPromedio() {
        return promedio;
    }

    @Override
    public int getTotalEstudiantes() {
        return 1;
    }

    @Override
    public void mostrar(String prefijo) {
        System.out.printf("%sEstudiante: %s  (promedio: %.1f)%n", prefijo, nombre, promedio);
    }
}
