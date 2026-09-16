package com.fabricaviva.equipos;

/**
 * Cliente del Abstract Factory. Recibe una fábrica y arma sus equipos con ella;
 * nunca menciona Siemens ni Fanuc.
 */
public class EstacionTrabajo {

    private final String nombre;
    private final ControladorMaquina controlador;
    private final SensorProduccion sensor;

    public EstacionTrabajo(String nombre, FabricaEquipos fabrica) {
        this.nombre = nombre;
        this.controlador = fabrica.crearControlador();
        this.sensor = fabrica.crearSensor();
    }

    public void procesarOrden(String referenciaOrden, int piezas) {
        System.out.println(nombre + ":");
        System.out.println("  " + controlador.iniciarCiclo(referenciaOrden));
        System.out.println("  " + sensor.reportarConteo(piezas));
    }

    public boolean equiposCompatibles() {
        return controlador.protocolo().equals(sensor.protocolo());
    }
}
