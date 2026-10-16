package com.fabricaviva.materiales;

/** ConcreteFlyweight: instancia inmutable compartida entre miles de lotes. */
public final class TipoMaterialCompartido implements TipoMaterialFlyweight {

    private final String codigo;
    private final String nombre;
    private final double densidad;
    private final String unidad;

    // constructor package-private: sólo FabricaTipoMaterial puede crear instancias
    TipoMaterialCompartido(String codigo, String nombre,
                           double densidad, String unidad) {
        this.codigo   = codigo;
        this.nombre   = nombre;
        this.densidad = densidad;
        this.unidad   = unidad;
    }

    @Override public String getCodigo()   { return codigo; }
    @Override public String getNombre()   { return nombre; }
    @Override public double getDensidad() { return densidad; }
    @Override public String getUnidad()   { return unidad; }

    @Override
    public String describir() {
        return codigo + " – " + nombre
                + " (" + densidad + " g/cm³, " + unidad + ")";
    }
}
