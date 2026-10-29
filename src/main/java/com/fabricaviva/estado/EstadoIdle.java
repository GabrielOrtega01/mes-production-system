package com.fabricaviva.estado;

public class EstadoIdle implements EstadoMaquina {
    @Override public void iniciar(ContextoMaquina ctx)   { ctx.setEstado(new EstadoProduciendo()); }
    @Override public void pausar(ContextoMaquina ctx)    { System.out.println("[" + ctx.getId() + "] Ya está inactiva."); }
    @Override public void reportarFalla(ContextoMaquina ctx, String causa) { ctx.setEstado(new EstadoFalla(causa)); }
    @Override public void completarMantenimiento(ContextoMaquina ctx) { System.out.println("[" + ctx.getId() + "] Sin mantenimiento pendiente."); }
    @Override public String nombre() { return "IDLE"; }
}
