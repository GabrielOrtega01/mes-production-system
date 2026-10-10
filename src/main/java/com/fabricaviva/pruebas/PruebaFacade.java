package com.fabricaviva.pruebas;

import com.fabricaviva.fachada.ControlTurnoFacade;
import com.fabricaviva.integracion.RegistroInternoMes;

public class PruebaFacade {

    public static void main(String[] args) {

        RegistroInternoMes registro = new RegistroInternoMes();
        ControlTurnoFacade facade = new ControlTurnoFacade(registro);

        System.out.println("== Prueba 1: iniciar turno (Singleton + Bridge) ==");
        facade.iniciarTurno("MANANA");

        System.out.println("\n== Prueba 2: crear ordenes de produccion (Factory Method) ==");
        facade.crearOrden("REF-3001", 300, false);
        facade.crearOrden("REF-3002", 150, true);

        System.out.println("\n== Prueba 3: consultar capacidad de la planta (Composite) ==");
        facade.consultarCapacidadTotal();

        System.out.println("\n== Prueba 4: calcular costos con ajustes (Decorator) ==");
        facade.calcularCostoConAjustes("ORD-9001", 500_000.0, true,  false);
        facade.calcularCostoConAjustes("ORD-9002", 800_000.0, true,  true);
        facade.calcularCostoConAjustes("ORD-9003", 200_000.0, false, false);

        System.out.println("\n== Prueba 5: cerrar turno (Adapter + Bridge) ==");
        facade.cerrarTurno("MANANA", 450);
    }
}
