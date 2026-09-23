package com.mensajeria.canales.proveedores;

/** API externa simulada de Twilio: envia texto plano y devuelve un codigo HTTP. */
public class TwilioApi {

    private static final int LIMITE_SMS = 160;

    private String ultimoTexto;

    public int sendSms(String fromNumber, String toNumber, String body) {
        if (toNumber == null || toNumber.isBlank()) {
            return 400;
        }
        this.ultimoTexto = body.length() > LIMITE_SMS ? body.substring(0, LIMITE_SMS) : body;
        return 201;
    }

    public String getUltimoTexto() {
        return ultimoTexto;
    }
}
