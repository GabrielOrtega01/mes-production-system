package com.fabricaviva.pruebas;

import com.fabricaviva.memento.*;

public class PruebaMemento {
    public static void main(String[] args) {
        ConfiguracionTurno cfg = new ConfiguracionTurno("Manana", 0.85, 500);
        HistorialTurnos historial = new HistorialTurnos();

        System.out.println("Estado inicial: " + cfg.resumen());

        historial.guardar(cfg);
        cfg.setMetaOee(0.90);
        cfg.setCapacidadMaxima(600);
        System.out.println("Modificado: " + cfg.resumen());

        historial.guardar(cfg);
        cfg.setTurno("Tarde");
        cfg.setMetaOee(0.80);
        System.out.println("Modificado 2: " + cfg.resumen());

        System.out.println("\n=== Deshacer ===");
        historial.deshacer(cfg);
        System.out.println("Tras primer deshacer: " + cfg.resumen());

        historial.deshacer(cfg);
        System.out.println("Tras segundo deshacer: " + cfg.resumen());

        historial.deshacer(cfg);
    }
}
