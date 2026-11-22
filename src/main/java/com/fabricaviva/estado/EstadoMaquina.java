package com.fabricaviva.estado;

/** State — contrato de todos los estados de una máquina. */
public interface EstadoMaquina {
    void iniciar(ContextoMaquina ctx);
    void pausar(ContextoMaquina ctx);
    void reportarFalla(ContextoMaquina ctx, String causa);
    void completarMantenimiento(ContextoMaquina ctx);
    String nombre();
}
