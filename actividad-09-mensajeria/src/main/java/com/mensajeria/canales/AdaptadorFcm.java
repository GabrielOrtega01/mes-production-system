package com.mensajeria.canales;

import com.mensajeria.canales.proveedores.FcmApi;
import com.mensajeria.modelo.Mensaje;
import java.util.LinkedHashMap;
import java.util.Map;

/** Adaptador del proveedor de notificaciones push: traduce el mensaje a un mapa de datos. */
public class AdaptadorFcm implements CanalMensajeria {

    private final FcmApi api;

    public AdaptadorFcm(FcmApi api) {
        this.api = api;
    }

    @Override
    public String nombre() {
        return "PUSH";
    }

    @Override
    public boolean disponible() {
        return true;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        Map<String, String> datos = new LinkedHashMap<>();
        datos.put("title", mensaje.getAsunto());
        datos.put("body", mensaje.getCuerpo());

        Map<String, Object> respuesta = api.push(mensaje.getDestinatario().getTokenDispositivo(), datos);
        if (!Integer.valueOf(1).equals(respuesta.get("success"))) {
            throw new EnvioFallidoException("FCM respondio: " + respuesta.get("error"));
        }
    }
}
