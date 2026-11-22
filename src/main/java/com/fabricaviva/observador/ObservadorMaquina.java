package com.fabricaviva.observador;

/** Observer — contrato del suscriptor. */
public interface ObservadorMaquina {
    void actualizar(String idMaquina, String evento, double valorOee);
}
