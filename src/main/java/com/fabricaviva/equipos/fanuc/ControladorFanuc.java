package com.fabricaviva.equipos.fanuc;

import com.fabricaviva.equipos.ControladorMaquina;

public class ControladorFanuc implements ControladorMaquina {

    @Override
    public String protocolo() {
        return "FOCAS";
    }

    @Override
    public String iniciarCiclo(String referenciaOrden) {
        return "[FOCAS] Fanuc inicia ciclo de la orden " + referenciaOrden;
    }
}
