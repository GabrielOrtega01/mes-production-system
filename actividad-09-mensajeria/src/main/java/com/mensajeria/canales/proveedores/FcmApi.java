package com.mensajeria.canales.proveedores;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * API externa simulada de Firebase Cloud Messaging: recibe un mapa de datos y
 * responde con otro mapa. Puede configurarse para fallar las primeras llamadas
 * y asi representar un proveedor inestable.
 */
public class FcmApi {

    private int fallosPendientes;
    private Map<String, String> ultimosDatos;

    public FcmApi(int fallosPendientes) {
        this.fallosPendientes = fallosPendientes;
    }

    public Map<String, Object> push(String deviceToken, Map<String, String> data) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        if (fallosPendientes > 0) {
            fallosPendientes--;
            respuesta.put("success", 0);
            respuesta.put("error", "UNAVAILABLE");
            return respuesta;
        }
        this.ultimosDatos = data;
        respuesta.put("success", 1);
        respuesta.put("message_id", "fcm-" + Math.abs(deviceToken.hashCode()));
        return respuesta;
    }

    public Map<String, String> getUltimosDatos() {
        return ultimosDatos;
    }
}
