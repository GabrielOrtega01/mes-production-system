package com.fabricaviva.pruebas;

import com.fabricaviva.interprete.*;

public class PruebaInterprete {
    public static void main(String[] args) {
        ExpresionAlerta alertaOee  = new ExpresionOEE(0.75);
        ExpresionAlerta alertaTemp = new ExpresionTemperatura(85.0);
        ExpresionAlerta alertaCritica = new ExpresionAnd(alertaOee, alertaTemp);
        ExpresionAlerta alertaCualquiera = new ExpresionOr(alertaOee, alertaTemp);

        System.out.println("Regla critica:    " + alertaCritica.descripcion());
        System.out.println("Regla cualquiera: " + alertaCualquiera.descripcion());

        ContextoAlerta ctx = new ContextoAlerta();

        ctx.set("oee", 0.70);
        ctx.set("temperatura", 90.0);
        System.out.println("\nOEE=70%, Temp=90C");
        System.out.println("  Alerta critica:    " + alertaCritica.interpretar(ctx));
        System.out.println("  Alerta cualquiera: " + alertaCualquiera.interpretar(ctx));

        ctx.set("oee", 0.80);
        ctx.set("temperatura", 70.0);
        System.out.println("\nOEE=80%, Temp=70C");
        System.out.println("  Alerta critica:    " + alertaCritica.interpretar(ctx));
        System.out.println("  Alerta cualquiera: " + alertaCualquiera.interpretar(ctx));

        ctx.set("oee", 0.65);
        ctx.set("temperatura", 70.0);
        System.out.println("\nOEE=65%, Temp=70C");
        System.out.println("  Alerta critica:    " + alertaCritica.interpretar(ctx));
        System.out.println("  Alerta cualquiera: " + alertaCualquiera.interpretar(ctx));
    }
}
