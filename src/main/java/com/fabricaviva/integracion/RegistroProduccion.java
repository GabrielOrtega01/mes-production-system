package com.fabricaviva.integracion;

/**
 * Patrón Adapter – interfaz Objetivo (Target).
 * Contrato que el MES FábricaViva utiliza para leer y cerrar registros
 * de producción, independientemente del sistema subyacente.
 */
public interface RegistroProduccion {

    int leerCantidadProducida(String referencia);

    void registrarCierreTurno(String turno, int piezas);
}
