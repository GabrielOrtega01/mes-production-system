package com.fabricaviva.equipos;

/** Producto abstracto A: el controlador que arranca la máquina. */
public interface ControladorMaquina {

    String protocolo();

    String iniciarCiclo(String referenciaOrden);
}
