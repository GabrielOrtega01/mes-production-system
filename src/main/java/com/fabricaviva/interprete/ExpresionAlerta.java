package com.fabricaviva.interprete;

/** AbstractExpression: nodo del árbol de expresiones de alerta. */
public interface ExpresionAlerta {
    boolean interpretar(ContextoAlerta ctx);
    String descripcion();
}
