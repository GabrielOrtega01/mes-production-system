package com.fabricaviva.pruebas;

import com.fabricaviva.cadena.*;
import com.fabricaviva.ordenes.*;

public class PruebaCadena {
    public static void main(String[] args) {
        GestorOrdenes gestor = new GestorOrdenes(400, 350, false);

        gestor.validar(new OrdenProduccion("REF-OK",  200, Prioridad.NORMAL));
        gestor.validar(new OrdenProduccion("REF-SIN", 500, Prioridad.ALTA));
        gestor.validar(new OrdenProduccion("REF-CAP", 360, Prioridad.NORMAL));

        System.out.println("\n=== Modo saturado activo ===");
        GestorOrdenes gestorSat = new GestorOrdenes(400, 350, true);
        gestorSat.validar(new OrdenProduccion("REF-BAJA", 100, Prioridad.NORMAL));
        gestorSat.validar(new OrdenProduccion("REF-ALTA", 100, Prioridad.ALTA));
    }
}
