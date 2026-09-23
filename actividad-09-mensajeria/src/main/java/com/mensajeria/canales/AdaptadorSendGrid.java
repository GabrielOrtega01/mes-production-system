package com.mensajeria.canales;

import com.mensajeria.canales.proveedores.SendGridApi;
import com.mensajeria.modelo.Mensaje;

/** Adaptador del proveedor de correo: traduce el mensaje a asunto + cuerpo HTML. */
public class AdaptadorSendGrid implements CanalMensajeria {

    private final SendGridApi api;

    public AdaptadorSendGrid(SendGridApi api) {
        this.api = api;
    }

    @Override
    public String nombre() {
        return "EMAIL";
    }

    @Override
    public boolean disponible() {
        return true;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        String correo = mensaje.getDestinatario().getEmail();
        if (correo == null || correo.isBlank()) {
            throw new EnvioFallidoException("El destinatario no tiene correo registrado");
        }
        String html = "<p>" + mensaje.getCuerpo() + "</p>";
        String idMensaje = api.send(correo, mensaje.getAsunto(), html);
        if (idMensaje == null) {
            throw new EnvioFallidoException("SendGrid no devolvio identificador");
        }
    }
}
