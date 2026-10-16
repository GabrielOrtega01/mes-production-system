package com.fabricaviva.materiales;

/** Flyweight: estado intrínseco de un tipo de material en la planta. */
public interface TipoMaterialFlyweight {
    String getCodigo();
    String getNombre();
    double getDensidad();   // g/cm³
    String getUnidad();
    String describir();
}
