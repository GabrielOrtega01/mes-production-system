package com.mensajeria.pruebas;

import com.mensajeria.canales.AdaptadorFcm;
import com.mensajeria.canales.AdaptadorSendGrid;
import com.mensajeria.canales.AdaptadorTwilio;
import com.mensajeria.canales.AdaptadorWebhook;
import com.mensajeria.canales.EnvioFallidoException;
import com.mensajeria.canales.proveedores.FcmApi;
import com.mensajeria.canales.proveedores.SendGridApi;
import com.mensajeria.canales.proveedores.TwilioApi;
import com.mensajeria.canales.proveedores.WebhookHttpClient;
import com.mensajeria.modelo.Destinatario;
import com.mensajeria.modelo.Mensaje;

/** Comprueba que cada adaptador traduce el mensaje al formato propio del proveedor. */
public class PruebaAdapter {

    public static void main(String[] args) {
        Verificacion v = new Verificacion();

        SendGridApi sendGrid = new SendGridApi();
        TwilioApi twilio = new TwilioApi();
        FcmApi fcm = new FcmApi(0);
        WebhookHttpClient webhook = new WebhookHttpClient();

        Destinatario destinatario = new Destinatario("Ana Gomez", "ana@empresa.com",
                "+573001112233", "tok-abc-123", "https://erp.empresa.com/hooks/alertas");
        Mensaje mensaje = new Mensaje("Alerta de sistema", "La linea 3 se detuvo", destinatario);

        v.titulo("Cada proveedor recibe el mensaje en su propio formato");

        new AdaptadorSendGrid(sendGrid).enviar(mensaje);
        v.comprobar("SendGrid recibio asunto y cuerpo HTML",
                "Alerta de sistema".equals(sendGrid.getUltimoAsunto())
                        && sendGrid.getUltimoHtml().equals("<p>La linea 3 se detuvo</p>"),
                sendGrid.getUltimoHtml());

        new AdaptadorTwilio(twilio, "+573000000000").enviar(mensaje);
        v.comprobar("Twilio recibio texto plano",
                "Alerta de sistema: La linea 3 se detuvo".equals(twilio.getUltimoTexto()),
                twilio.getUltimoTexto());

        new AdaptadorFcm(fcm).enviar(mensaje);
        v.comprobar("FCM recibio un mapa con title y body",
                "Alerta de sistema".equals(fcm.getUltimosDatos().get("title"))
                        && "La linea 3 se detuvo".equals(fcm.getUltimosDatos().get("body")),
                fcm.getUltimosDatos().toString());

        new AdaptadorWebhook(webhook).enviar(mensaje);
        v.comprobar("El webhook recibio un JSON",
                webhook.getUltimoJson().startsWith("{") && webhook.getUltimoJson().contains("asunto"),
                webhook.getUltimoJson());

        v.titulo("Los errores de cada proveedor se traducen a un unico tipo del sistema");

        Destinatario incompleto = new Destinatario("Luis Paez", "", "", "tok-x",
                "http://inseguro.local/hook");
        Mensaje otro = new Mensaje("Recordatorio", "Su turno inicia a las 6:00", incompleto);

        v.comprobar("Correo vacio produce EnvioFallidoException",
                falla(() -> new AdaptadorSendGrid(sendGrid).enviar(otro)),
                "excepcion del sistema, no del proveedor");
        v.comprobar("Telefono vacio produce EnvioFallidoException",
                falla(() -> new AdaptadorTwilio(twilio, "+573000000000").enviar(otro)),
                "excepcion del sistema, no del proveedor");
        v.comprobar("URL sin https produce EnvioFallidoException",
                falla(() -> new AdaptadorWebhook(webhook).enviar(otro)),
                "excepcion del sistema, no del proveedor");

        v.resumen();
    }

    private static boolean falla(Runnable envio) {
        try {
            envio.run();
            return false;
        } catch (EnvioFallidoException esperado) {
            return true;
        }
    }
}
