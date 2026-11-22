package com.fabricaviva.estado;

public class EstadoFalla implements EstadoMaquina {

    private final String causa;

    public EstadoFalla(String causa) {
        this.causa = causa;
        System.out.println("[FALLA] Causa: " + causa);
    }

    @Override public void iniciar(ContextoMaquina ctx)   { System.out.println("[" + ctx.getId() + "] No se puede iniciar: falla activa — " + causa); }
    @Override public void pausar(ContextoMaquina ctx)    { System.out.println("[" + ctx.getId() + "] Ya detenida por falla."); }
    @Override public void reportarFalla(ContextoMaquina ctx, String c) { System.out.println("[" + ctx.getId() + "] Falla adicional registrada: " + c); }
    @Override public void completarMantenimiento(ContextoMaquina ctx)  { ctx.setEstado(new EstadoIdle()); }
    @Override public String nombre() { return "FALLA"; }
}
