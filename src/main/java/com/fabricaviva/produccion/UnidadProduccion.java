package com.fabricaviva.produccion;

/**
 * Componente del Composite: contrato uniforme para hojas y contenedores
 * de la jerarquía de unidades de producción (Gamma et al., 2003, p. 163).
 */
public interface UnidadProduccion {
    /** Capacidad nominal total en piezas/hora. */
    double getCapacidadTotal();

    /** Número total de máquinas individuales en el subárbol. */
    int getTotalMaquinas();

    /** Visualiza la estructura con sangría. */
    void mostrar(String prefijo);
}
