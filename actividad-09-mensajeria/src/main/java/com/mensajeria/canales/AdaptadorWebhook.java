package com.mensajeria.canales;

import com.mensajeria.canales.proveedores.WebhookHttpClient;
import com.mensajeria.modelo.Mensaje;

/** Adaptador del webhook generico: traduce el mensaje a un JSON y lo publica por HTTP. */
public class AdaptadorWebhook implements CanalMensajeria {

    private static final String RESPUESTA_OK = "HTTP 204";

    private final WebhookHttpClient cliente;

    public AdaptadorWebhook(WebhookHttpClient cliente) {
        this.cliente = cliente;
    }

    @Override
    public String nombre() {
        return "WEBHOOK";
    }

    @Override
    public boolean disponible() {
        return true;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        String json = "{"
                + "\"asunto\":\"" + mensaje.getAsunto() + "\","
                + "\"cuerpo\":\"" + mensaje.getCuerpo() + "\","
                + "\"destinatario\":\"" + mensaje.getDestinatario().getNombre() + "\"}";
        String respuesta = cliente.post(mensaje.getDestinatario().getUrlWebhook(), json);
        if (!RESPUESTA_OK.equals(respuesta)) {
            throw new EnvioFallidoException("El webhook respondio " + respuesta);
        }
    }
}
