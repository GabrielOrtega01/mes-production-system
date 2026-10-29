package com.fabricaviva.interprete;

/** NonTerminalExpression: ambas sub-expresiones deben ser verdaderas. */
public class ExpresionAnd implements ExpresionAlerta {

    private final ExpresionAlerta izq;
    private final ExpresionAlerta der;

    public ExpresionAnd(ExpresionAlerta izq, ExpresionAlerta der) {
        this.izq = izq;
        this.der = der;
    }

    @Override
    public boolean interpretar(ContextoAlerta ctx) {
        return izq.interpretar(ctx) && der.interpretar(ctx);
    }

    @Override
    public String descripcion() {
        return "(" + izq.descripcion() + " AND " + der.descripcion() + ")";
    }
}
