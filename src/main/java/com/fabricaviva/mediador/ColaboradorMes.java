package com.fabricaviva.mediador;

/** Colleague — interfaz de todos los módulos coordinados por el mediador. */
public interface ColaboradorMes {
    String getNombre();
    void recibirMensaje(String origen, String mensaje);
}
