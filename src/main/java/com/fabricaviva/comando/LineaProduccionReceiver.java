package com.fabricaviva.comando;

/** Receiver: línea de producción que ejecuta la lógica real. */
public class LineaProduccionReceiver {

    private final String nombre;
    private boolean activa = false;
    private int piezasProducidas = 0;

    public LineaProduccionReceiver(String nombre) {
        this.nombre = nombre;
    }

    public void iniciar(String referencia, int cantidad) {
        activa = true;
        System.out.println("[" + nombre + "] Iniciando orden " + referencia + " x" + cantidad);
    }

    public void pausar(String referencia) {
        activa = false;
        System.out.println("[" + nombre + "] Orden " + referencia + " pausada");
    }

    public void reanudar(String referencia) {
        activa = true;
        System.out.println("[" + nombre + "] Orden " + referencia + " reanudada");
    }

    public void cerrarTurno(String turno, int piezas) {
        this.piezasProducidas += piezas;
        activa = false;
        System.out.println("[" + nombre + "] Turno " + turno + " cerrado — " + piezas + " piezas");
    }

    public void reabrirTurno(String turno) {
        activa = true;
        System.out.println("[" + nombre + "] Turno " + turno + " reabierto (deshacer cierre)");
    }

    public boolean isActiva()          { return activa; }
    public int getPiezasProducidas()   { return piezasProducidas; }
}
