package com.fabricaviva;

import com.fabricaviva.equipos.EstacionTrabajo;
import com.fabricaviva.equipos.fanuc.FabricaEquiposFanuc;
import com.fabricaviva.equipos.siemens.FabricaEquiposSiemens;

public class PruebaAbstractFactory {

    public static void main(String[] args) {
        System.out.println("== Prueba 1: cada estacion recibe la familia de equipos de una marca ==");
        EstacionTrabajo torno = new EstacionTrabajo("Estacion Torno", new FabricaEquiposSiemens());
        EstacionTrabajo fresa = new EstacionTrabajo("Estacion Fresadora", new FabricaEquiposFanuc());

        torno.procesarOrden("REF-1001", 250);
        fresa.procesarOrden("REF-2002", 120);

        System.out.println("\n== Prueba 2: controlador y sensor siempre hablan el mismo protocolo ==");
        System.out.println("Estacion Torno compatible ? " + torno.equiposCompatibles());
        System.out.println("Estacion Fresadora compatible ? " + fresa.equiposCompatibles());

        System.out.println("\n== Prueba 3: cambiar de proveedor solo exige cambiar la fabrica ==");
        EstacionTrabajo tornoRenovado = new EstacionTrabajo("Estacion Torno (renovada)", new FabricaEquiposFanuc());
        tornoRenovado.procesarOrden("REF-1001", 250);
        System.out.println("Estacion Torno (renovada) compatible ? " + tornoRenovado.equiposCompatibles());
    }
}
