package com.fabricaviva.interprete;

/** NonTerminalExpression: al menos una sub-expresión debe ser verdadera. */
public class ExpresionOr implements ExpresionAlerta {

    private final ExpresionAlerta izq;
    private final ExpresionAlerta der;

    public ExpresionOr(ExpresionAlerta izq, ExpresionAlerta der) {
        this.izq = izq;
        this.der = der;
    }

    @Override
    public boolean interpretar(ContextoAlerta ctx) {
        return izq.interpretar(ctx) || der.interpretar(ctx);
    }

    @Override
    public String descripcion() {
        return "(" + izq.descripcion() + " OR " + der.descripcion() + ")";
    }
}
