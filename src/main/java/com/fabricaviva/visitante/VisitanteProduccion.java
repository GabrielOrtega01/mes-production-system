package com.fabricaviva.visitante;

/** Visitor: define una operación para cada tipo de elemento. */
public interface VisitanteProduccion {
    void visitarMaquina(MaquinaElement maquina);
    void visitarLinea(LineaElement linea);
}
