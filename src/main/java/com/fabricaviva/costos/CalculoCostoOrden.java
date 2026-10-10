package com.fabricaviva.costos;

/**
 * Componente del Decorator: contrato para calcular el costo de una
 * orden de producción con ajustes combinables (Gamma et al., 2003, p. 175).
 */
public interface CalculoCostoOrden {
    /** Costo total de la orden tras aplicar todos los ajustes. */
    double calcularCosto();

    /** Descripción auditada de los ajustes aplicados. */
    String describir();
}
