package com.fabricaviva.equipos.siemens;

import com.fabricaviva.equipos.ControladorMaquina;

public class ControladorSiemens implements ControladorMaquina {

    @Override
    public String protocolo() {
        return "OPC-UA";
    }

    @Override
    public String iniciarCiclo(String referenciaOrden) {
        return "[OPC-UA] Siemens inicia ciclo de la orden " + referenciaOrden;
    }
}
