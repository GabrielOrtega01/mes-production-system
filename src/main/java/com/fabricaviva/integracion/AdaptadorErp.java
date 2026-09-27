package com.fabricaviva.integracion;

/**
 * Patrón Adapter – rol Adaptador.
 * Traduce las llamadas del MES (RegistroProduccion) al protocolo
 * del ERP legado (SistemaErpLegado) sin alterar ninguno de los dos.
 */
public class AdaptadorErp implements RegistroProduccion {

    private final SistemaErpLegado erp;

    public AdaptadorErp(SistemaErpLegado erp) {
        this.erp = erp;
    }

    @Override
    public int leerCantidadProducida(String referencia) {
        String respuesta = erp.consultarVolumen(referencia);
        return Integer.parseInt(respuesta.split(" ")[0]);
    }

    @Override
    public void registrarCierreTurno(String turno, int piezas) {
        erp.cerrarJornada(turno, piezas, "Cierre automatico MES-FabricaViva");
    }
}
