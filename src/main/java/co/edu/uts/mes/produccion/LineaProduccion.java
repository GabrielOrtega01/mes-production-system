package co.edu.uts.mes.produccion;

import co.edu.uts.mes.config.ConfiguracionPlanta;

/** Cliente 1 del Singleton: la linea consulta el turno activo para etiquetar piezas. */
public class LineaProduccion {

    private final String codigo;

    public LineaProduccion(String codigo) {
        this.codigo = codigo;
    }

    public String producirPieza(int numero) {
        ConfiguracionPlanta config = ConfiguracionPlanta.getInstancia();
        return "Pieza " + numero + " producida en " + codigo
                + " | planta: " + config.getNombrePlanta()
                + " | turno: " + config.getTurnoActivo();
    }
}
