package com.fabricaviva.estado;

/** Context: máquina cuyo comportamiento depende del estado actual. */
public class ContextoMaquina {

    private EstadoMaquina estado;
    private final String id;

    public ContextoMaquina(String id) {
        this.id     = id;
        this.estado = new EstadoIdle();
        System.out.println("[" + id + "] Estado inicial: " + estado.nombre());
    }

    public void setEstado(EstadoMaquina nuevoEstado) {
        System.out.println("[" + id + "] Transicion: "
                           + estado.nombre() + " → " + nuevoEstado.nombre());
        this.estado = nuevoEstado;
    }

    public void iniciar()                        { estado.iniciar(this); }
    public void pausar()                         { estado.pausar(this); }
    public void reportarFalla(String causa)      { estado.reportarFalla(this, causa); }
    public void completarMantenimiento()         { estado.completarMantenimiento(this); }

    public String getEstadoActual() { return estado.nombre(); }
    public String getId()           { return id; }
}
