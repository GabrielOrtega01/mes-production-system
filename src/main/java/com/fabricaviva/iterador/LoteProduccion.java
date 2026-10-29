package com.fabricaviva.iterador;

/** Elemento de la colección: un lote de producción. */
public class LoteProduccion {
    private final String id;
    private final String referencia;
    private final int    cantidad;
    private final int    prioridad; // 1=alta … 3=baja

    public LoteProduccion(String id, String referencia, int cantidad, int prioridad) {
        this.id          = id;
        this.referencia  = referencia;
        this.cantidad    = cantidad;
        this.prioridad   = prioridad;
    }

    public String getId()          { return id; }
    public String getReferencia()  { return referencia; }
    public int    getCantidad()    { return cantidad; }
    public int    getPrioridad()   { return prioridad; }

    @Override
    public String toString() {
        return "Lote[" + id + " | " + referencia + " x" + cantidad + " | P" + prioridad + "]";
    }
}
