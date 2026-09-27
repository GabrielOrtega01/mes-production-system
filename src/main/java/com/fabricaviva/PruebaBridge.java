package com.fabricaviva;

import com.fabricaviva.alertas.AlertaCalidad;
import com.fabricaviva.alertas.AlertaMantenimiento;
import com.fabricaviva.alertas.AlertaParada;
import com.fabricaviva.alertas.AlertaProduccion;
import com.fabricaviva.alertas.BitacoraAlerta;
import com.fabricaviva.alertas.ConsolaAlerta;

public class PruebaBridge {

    public static void main(String[] args) {

        ConsolaAlerta consola = new ConsolaAlerta();
        BitacoraAlerta bitacora = new BitacoraAlerta();

        System.out.println("== Prueba 1: alerta de mantenimiento por consola ==");
        AlertaProduccion mant = new AlertaMantenimiento(consola, "MANANA");
        mant.disparar("Motor CNC-03 requiere lubricacion");

        System.out.println("\n== Prueba 2: alerta de calidad por consola ==");
        AlertaProduccion cal = new AlertaCalidad(consola, "MANANA");
        cal.disparar("Tasa de rechazo supera 3% en linea L-02");

        System.out.println("\n== Prueba 3: parada de linea registrada en bitacora ==");
        AlertaProduccion parada = new AlertaParada(bitacora, "TARDE");
        parada.disparar("L-01 detenida por falla mecanica");

        System.out.println("\n== Prueba 4: mismo tipo de alerta, canal diferente (Bridge desacopla) ==");
        AlertaProduccion mantBitacora = new AlertaMantenimiento(bitacora, "NOCHE");
        mantBitacora.disparar("Revision preventiva CNC-07");
        System.out.println("Registros en bitacora: " + bitacora.getRegistros().size());
    }
}
