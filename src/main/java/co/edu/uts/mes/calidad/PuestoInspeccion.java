package co.edu.uts.mes.calidad;

import co.edu.uts.mes.config.ConfiguracionPlanta;

/** Cliente 2 del Singleton: la inspeccion usa el mismo umbral y el mismo turno. */
public class PuestoInspeccion {

    public String evaluar(double oeeMedido) {
        ConfiguracionPlanta config = ConfiguracionPlanta.getInstancia();
        boolean aprueba = oeeMedido >= config.getUmbralOee();
        return "OEE medido " + oeeMedido
                + " | umbral: " + config.getUmbralOee()
                + " | turno: " + config.getTurnoActivo()
                + " | resultado: " + (aprueba ? "APROBADO" : "NO APROBADO");
    }
}
