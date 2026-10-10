package com.fabricaviva.produccion;

import java.util.ArrayList;
import java.util.List;

/** Composite concreto: estación de trabajo que agrupa máquinas. */
public class Estacion implements UnidadProduccion {

    private final String nombre;
    private final List<UnidadProduccion> hijos = new ArrayList<>();

    public Estacion(String nombre) { this.nombre = nombre; }

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
        System.out.printf("%s[Estación] %s%n", prefijo, nombre);
        hijos.forEach(h -> h.mostrar(prefijo + "  "));
    }
}
