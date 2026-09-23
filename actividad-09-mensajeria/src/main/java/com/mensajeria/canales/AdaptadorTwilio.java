package com.mensajeria.canales;

import com.mensajeria.canales.proveedores.TwilioApi;
import com.mensajeria.modelo.Mensaje;

/**
 * Adaptador del proveedor de SMS. Twilio solo acepta texto plano y responde con
 * un codigo HTTP, asi que el adaptador arma el texto y traduce el codigo a una
 * excepcion propia del sistema.
 */
public class AdaptadorTwilio implements CanalMensajeria {

    private static final int CODIGO_CREADO = 201;

    private final TwilioApi api;
    private final String numeroEmisor;

    public AdaptadorTwilio(TwilioApi api, String numeroEmisor) {
        this.api = api;
        this.numeroEmisor = numeroEmisor;
    }

    @Override
    public String nombre() {
        return "SMS";
    }

    @Override
    public boolean disponible() {
        return true;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        String texto = mensaje.getAsunto() + ": " + mensaje.getCuerpo();
        int codigo = api.sendSms(numeroEmisor, mensaje.getDestinatario().getTelefono(), texto);
        if (codigo != CODIGO_CREADO) {
            throw new EnvioFallidoException("Twilio respondio con codigo " + codigo);
        }
    }
}
