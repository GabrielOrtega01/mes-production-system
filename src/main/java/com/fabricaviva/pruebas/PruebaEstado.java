package com.fabricaviva.pruebas;

import com.fabricaviva.estado.*;

public class PruebaEstado {
    public static void main(String[] args) {
        ContextoMaquina maquina = new ContextoMaquina("CNC-07");

        System.out.println("Estado inicial: " + maquina.getEstadoActual());
        maquina.iniciar();
        System.out.println("Tras iniciar: " + maquina.getEstadoActual());

        maquina.reportarFalla("Temperatura alta");
        System.out.println("Tras falla: " + maquina.getEstadoActual());

        maquina.completarMantenimiento();
        System.out.println("Tras mantenimiento: " + maquina.getEstadoActual());

        System.out.println("\n=== Transicion invalida (pausar en Idle) ===");
        maquina.pausar();
    }
}
