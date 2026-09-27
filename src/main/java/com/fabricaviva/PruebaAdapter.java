package com.fabricaviva;

import com.fabricaviva.integracion.AdaptadorErp;
import com.fabricaviva.integracion.RegistroInternoMes;
import com.fabricaviva.integracion.RegistroProduccion;
import com.fabricaviva.integracion.SistemaErpLegado;

public class PruebaAdapter {

    public static void main(String[] args) {

        System.out.println("== Prueba 1: registro interno MES (implementacion nativa) ==");
        RegistroInternoMes mesNativo = new RegistroInternoMes();
        mesNativo.cargarConteo("REF-A220", 312);
        int cantidadMes = mesNativo.leerCantidadProducida("REF-A220");
        System.out.println("Cantidad producida (MES): " + cantidadMes + " piezas");
        mesNativo.registrarCierreTurno("MANANA", cantidadMes);

        System.out.println("\n== Prueba 2: adaptador ERP conectado al mismo contrato ==");
        SistemaErpLegado erpLegado = new SistemaErpLegado();
        RegistroProduccion adaptador = new AdaptadorErp(erpLegado);
        int cantidadErp = adaptador.leerCantidadProducida("REF-A220");
        System.out.println("Cantidad producida (ERP): " + cantidadErp + " piezas");
        adaptador.registrarCierreTurno("MANANA", cantidadErp);

        System.out.println("\n== Prueba 3: el cliente no distingue entre implementaciones ==");
        RegistroProduccion[] fuentes = { mesNativo, adaptador };
        String[] nombres = { "RegistroInternoMes", "AdaptadorErp     " };
        for (int i = 0; i < fuentes.length; i++) {
            int piezas = fuentes[i].leerCantidadProducida("REF-A220");
            System.out.println("Fuente: " + nombres[i] + " -> piezas: " + piezas);
        }
    }
}
