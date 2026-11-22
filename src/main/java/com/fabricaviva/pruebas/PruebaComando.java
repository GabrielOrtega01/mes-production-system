package com.fabricaviva.pruebas;

import com.fabricaviva.comando.*;

public class PruebaComando {
    public static void main(String[] args) {
        LineaProduccionReceiver linea = new LineaProduccionReceiver("L-01");
        GestorComandos gestor = new GestorComandos();

        gestor.ejecutar(new ComandoIniciarOrden(linea, "OP-100", 250));
        gestor.ejecutar(new ComandoCerrarTurno(linea, "T-Manana", 250));

        System.out.println("\n=== Deshaciendo ultimo comando ===");
        gestor.deshacer();

        System.out.println("\n=== Deshaciendo otro comando ===");
        gestor.deshacer();
    }
}
