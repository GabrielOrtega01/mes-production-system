package com.fabricaviva.mediador;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteMediator: hub de comunicación del MES.
 * Los módulos solo se conocen entre sí a través de la central.
 */
public class CentralMes {

    private final List<ColaboradorMes> modulos = new ArrayList<>();

    public void registrar(ColaboradorMes modulo) {
        modulos.add(modulo);
    }

    /**
     * Enruta el mensaje del emisor a todos los demás módulos.
     * Lógica de enrutamiento centralizada y fácil de cambiar.
     */
    public void enviar(String origen, String mensaje) {
        System.out.println("[CENTRAL] " + origen + " → \"" + mensaje + "\"");
        for (ColaboradorMes m : modulos) {
            if (!m.getNombre().equals(origen)) {
                m.recibirMensaje(origen, mensaje);
            }
        }
    }
}
