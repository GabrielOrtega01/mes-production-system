package com.academica.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Composite – Composite concreto (nivel Curso).
 * Agrupa estudiantes y delega las operaciones recursivamente.
 */
public class Curso implements ElementoAcademico {

    private final String nombre;
    private final List<ElementoAcademico> elementos = new ArrayList<>();

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ElementoAcademico e) {
        elementos.add(e);
    }

    public void quitar(ElementoAcademico e) {
        elementos.remove(e);
    }

    @Override
    public double getPromedio() {
        if (elementos.isEmpty()) return 0.0;
        return elementos.stream()
                .mapToDouble(ElementoAcademico::getPromedio)
                .average()
                .orElse(0.0);
    }

    @Override
    public int getTotalEstudiantes() {
        return elementos.stream()
                .mapToInt(ElementoAcademico::getTotalEstudiantes)
                .sum();
    }

    @Override
    public void mostrar(String prefijo) {
        System.out.printf("%sCurso: %s  (promedio: %.2f, estudiantes: %d)%n",
                prefijo, nombre, getPromedio(), getTotalEstudiantes());
        for (ElementoAcademico e : elementos) {
            e.mostrar(prefijo + "  ");
        }
    }
}
