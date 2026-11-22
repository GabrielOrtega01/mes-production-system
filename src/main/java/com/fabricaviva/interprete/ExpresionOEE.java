package com.fabricaviva.interprete;

/** TerminalExpression: dispara alerta si OEE cae bajo el umbral. */
public class ExpresionOEE implements ExpresionAlerta {

    private final double umbralMinimo;

    public ExpresionOEE(double umbralMinimo) {
        this.umbralMinimo = umbralMinimo;
    }

    @Override
    public boolean interpretar(ContextoAlerta ctx) {
        return ctx.get("oee") < umbralMinimo;
    }

    @Override
    public String descripcion() {
        return "OEE < " + (umbralMinimo * 100) + "%";
    }
}
