package com.fabricaviva.inspeccion;

public class ResultadoInspeccion {
    public final String loteId;
    public final String tipoInspeccion;
    public final int    defectos;
    public final boolean aprobado;

    public ResultadoInspeccion(String loteId, String tipo, int defectos, boolean aprobado) {
        this.loteId          = loteId;
        this.tipoInspeccion  = tipo;
        this.defectos        = defectos;
        this.aprobado        = aprobado;
    }
}
