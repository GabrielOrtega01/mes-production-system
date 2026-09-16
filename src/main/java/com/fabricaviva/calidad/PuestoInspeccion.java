package com.fabricaviva.calidad;

import com.fabricaviva.config.ConfiguracionPlanta;

/** Segundo módulo que usa la configuración: compara el OEE con la meta. */
public class PuestoInspeccion {

    public String evaluar(double oeeMedido) {
        ConfiguracionPlanta config = ConfiguracionPlanta.getInstancia();
        String resultado = oeeMedido >= config.getUmbralOee() ? "APROBADO" : "NO APROBADO";
        return "PuestoInspeccion -> OEE " + oeeMedido
                + " (meta " + config.getUmbralOee()
                + ", turno " + config.getTurnoActivo() + "): " + resultado;
    }
}
