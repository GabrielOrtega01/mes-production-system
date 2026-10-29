package com.fabricaviva.comando;

public class ComandoCerrarTurno implements ComandoProduccion {

    private final LineaProduccionReceiver linea;
    private final String turno;
    private final int piezas;

    public ComandoCerrarTurno(LineaProduccionReceiver linea,
                               String turno, int piezas) {
        this.linea  = linea;
        this.turno  = turno;
        this.piezas = piezas;
    }

    @Override public void ejecutar()  { linea.cerrarTurno(turno, piezas); }
    @Override public void deshacer()  { linea.reabrirTurno(turno); }
    @Override public String descripcion() {
        return "CerrarTurno(" + turno + ", " + piezas + " piezas)";
    }
}
