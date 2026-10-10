package com.academica.composite;

/**
 * Patrón Composite – Componente.
 * Contrato uniforme que permite tratar hojas y composites de igual manera.
 */
public interface ElementoAcademico {

    double getPromedio();

    int getTotalEstudiantes();

    void mostrar(String prefijo);
}
