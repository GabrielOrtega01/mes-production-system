package com.fabricaviva.produccion;

import java.util.ArrayList;
import java.util.List;

/** Composite concreto raíz: planta que agrupa líneas de producción. */
public class Planta implements UnidadProduccion {

    private final String nombre;
    private final List<UnidadProduccion> hijos = new ArrayList<>();

    public Planta(String nombre) { this.nombre = nombre; }

    public void agregar(UnidadProduccion u) { hijos.add(u); }
    public void quitar(UnidadProduccion u)  { hijos.remove(u); }

    @Override
    public double getCapacidadTotal() {
        return hijos.stream().mapToDouble(UnidadProduccion::getCapacidadTotal).sum();
    }

    @Override
    public int getTotalMaquinas() {
        return hijos.stream().mapToInt(UnidadProduccion::getTotalMaquinas).sum();
    }

    @Override
    public void mostrar(String prefijo) {
        System.out.printf("%s[Planta] %s%n", prefijo, nombre);
        hijos.forEach(h -> h.mostrar(prefijo + "  "));
    }
}
