package com.fabricaviva.estado;

public class EstadoProduciendo implements EstadoMaquina {
    @Override public void iniciar(ContextoMaquina ctx)   { System.out.println("[" + ctx.getId() + "] Ya está produciendo."); }
    @Override public void pausar(ContextoMaquina ctx)    { ctx.setEstado(new EstadoIdle()); }
    @Override public void reportarFalla(ContextoMaquina ctx, String causa) { ctx.setEstado(new EstadoFalla(causa)); }
    @Override public void completarMantenimiento(ContextoMaquina ctx) { System.out.println("[" + ctx.getId() + "] Sin mantenimiento activo."); }
    @Override public String nombre() { return "PRODUCIENDO"; }
}
