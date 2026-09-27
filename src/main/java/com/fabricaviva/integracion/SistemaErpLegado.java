package com.fabricaviva.integracion;

/**
 * Patrón Adapter – rol Adaptado (Adaptee).
 * ERP heredado con API incompatible con el contrato del MES.
 * No puede modificarse porque pertenece a un proveedor externo.
 */
public class SistemaErpLegado {

    public String consultarVolumen(String codigoProducto) {
        System.out.println("Consultando ERP... respuesta: \"312 piezas\"");
        return "312 piezas";
    }

    public void cerrarJornada(String codigoProducto, int volumen, String observacion) {
        System.out.println("ERP: jornada cerrada -> " + codigoProducto
                + " | vol=" + volumen + " | obs=" + observacion);
    }
}
