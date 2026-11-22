package com.fabricaviva.interprete;

/** TerminalExpression: dispara alerta si la temperatura supera el umbral. */
public class ExpresionTemperatura implements ExpresionAlerta {

    private final double umbralMaximo;

    public ExpresionTemperatura(double umbralMaximo) {
        this.umbralMaximo = umbralMaximo;
    }

    @Override
    public boolean interpretar(ContextoAlerta ctx) {
        return ctx.get("temperatura") > umbralMaximo;
    }

    @Override
    public String descripcion() {
        return "Temperatura > " + umbralMaximo + "°C";
    }
}
