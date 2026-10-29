package com.fabricaviva.visitante;

/** Element: cualquier componente del MES que acepta visitantes. */
public interface ElementoProductivo {
    void aceptar(VisitanteProduccion visitante);
    String getId();
}
