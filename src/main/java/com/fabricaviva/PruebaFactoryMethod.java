package com.fabricaviva;

import com.fabricaviva.ordenes.FabricaOrden;
import com.fabricaviva.ordenes.FabricaOrdenEstandar;
import com.fabricaviva.ordenes.FabricaOrdenUrgente;
import com.fabricaviva.ordenes.ModuloOrdenes;
import com.fabricaviva.ordenes.OrdenProduccion;

public class PruebaFactoryMethod {

    public static void main(String[] args) {
        System.out.println("== Prueba 1: cada fabrica decide que orden crear ==");
        FabricaOrden estandar = new FabricaOrdenEstandar();
        FabricaOrden urgente = new FabricaOrdenUrgente();

        OrdenProduccion o1 = estandar.nuevaOrden("REF-1001", 500);
        OrdenProduccion o2 = urgente.nuevaOrden("REF-2002", 120);
        System.out.println(o1 + " | bitacora: " + o1.getBitacora());
        System.out.println(o2 + " | bitacora: " + o2.getBitacora());

        System.out.println("\n== Prueba 2: ModuloOrdenes elige la fabrica sin conocer las clases concretas ==");
        ModuloOrdenes modulo = new ModuloOrdenes();
        OrdenProduccion o3 = modulo.crear("REF-3003", 80, true);
        OrdenProduccion o4 = modulo.crear("REF-4004", 300, false);
        System.out.println("crear(urgente=true)  -> " + o3 + " | bitacora: " + o3.getBitacora());
        System.out.println("crear(urgente=false) -> " + o4 + " | bitacora: " + o4.getBitacora());
        System.out.println("Ordenes registradas en el modulo: " + modulo.getOrdenes().size());
    }
}
