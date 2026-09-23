package com.mensajeria.pruebas;

import com.mensajeria.PlataformaMensajeria;
import com.mensajeria.canales.AdaptadorFcm;
import com.mensajeria.canales.AdaptadorSendGrid;
import com.mensajeria.canales.AdaptadorTelegram;
import com.mensajeria.canales.AdaptadorTwilio;
import com.mensajeria.canales.AdaptadorWebhook;
import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.canales.proveedores.FcmApi;
import com.mensajeria.canales.proveedores.SendGridApi;
import com.mensajeria.canales.proveedores.TelegramBotApi;
import com.mensajeria.canales.proveedores.TwilioApi;
import com.mensajeria.canales.proveedores.WebhookHttpClient;
import com.mensajeria.modelo.Destinatario;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import com.mensajeria.prioridades.EnvioCritico;
import com.mensajeria.prioridades.EnvioNormal;
import com.mensajeria.prioridades.EnvioProgramado;
import com.mensajeria.prioridades.EstrategiaEnvio;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/** Comprueba las cinco restricciones del enunciado. */
public class PruebaExtensibilidad {

    public static void main(String[] args) {
        Verificacion v = new Verificacion();

        List<CanalMensajeria> canales = new ArrayList<>(List.of(
                new AdaptadorSendGrid(new SendGridApi()),
                new AdaptadorTwilio(new TwilioApi(), "+573000000000"),
                new AdaptadorFcm(new FcmApi(0)),
                new AdaptadorWebhook(new WebhookHttpClient())));

        Destinatario destinatario = new Destinatario("Ana Gomez", "ana@empresa.com",
                "+573001112233", "tok-abc-123", "https://erp.empresa.com/hooks/alertas");
        Mensaje mensaje = new Mensaje("Corte de energia", "Planta sin fluido electrico", destinatario);

        v.titulo("Restriccion 4: un canal nuevo no obliga a tocar las prioridades");
        int antes = new EnvioCritico(canales).enviar(mensaje).size();
        canales.add(new AdaptadorTelegram(new TelegramBotApi(), 987654321L));
        List<ResultadoEnvio> despues = new EnvioCritico(canales).enviar(mensaje);
        v.comprobar("Antes entregaba por 4 canales", antes == 4, antes + " canales");
        v.comprobar("Con Telegram entrega por 5, con el mismo EnvioCritico",
                despues.size() == 5, despues.size() + " canales");
        v.comprobar("Telegram entrego correctamente",
                despues.stream().anyMatch(r -> r.getCanal().equals("TELEGRAM") && r.isExitoso()),
                "TELEGRAM OK");

        v.titulo("Restriccion 3: una prioridad nueva no obliga a tocar los canales");
        PlataformaMensajeria plataforma = new PlataformaMensajeria();
        plataforma.registrar(new EnvioNormal(canales));
        plataforma.registrar(new EnvioProgramado(canales));
        List<ResultadoEnvio> programado = plataforma.notificar(mensaje, Prioridad.PROGRAMADA);
        v.comprobar("La plataforma acepta la prioridad nueva sin cambios",
                plataforma.prioridadesRegistradas().contains(Prioridad.PROGRAMADA),
                plataforma.prioridadesRegistradas().toString());
        v.comprobar("La prioridad nueva entrega por un canal existente",
                programado.size() == 1 && programado.get(0).isExitoso(),
                programado.get(0).toString());

        v.titulo("Restriccion 1: la logica de prioridad no conoce canales concretos");
        List<Class<?>> estrategias = List.of(EstrategiaEnvio.class, EnvioCritico.class,
                EnvioNormal.class, EnvioProgramado.class);
        List<String> acoples = new ArrayList<>();
        for (Class<?> clase : estrategias) {
            for (Field campo : clase.getDeclaredFields()) {
                if (esCanalConcreto(campo.getType())) {
                    acoples.add(clase.getSimpleName() + "." + campo.getName());
                }
            }
        }
        v.comprobar("Ninguna estrategia guarda un canal concreto", acoples.isEmpty(),
                acoples.isEmpty() ? "solo depende de CanalMensajeria" : acoples.toString());

        v.titulo("Restriccion 2: la logica de canal no conoce las prioridades");
        List<String> fugas = new ArrayList<>();
        for (CanalMensajeria canal : canales) {
            Class<?> clase = canal.getClass();
            for (Field campo : clase.getDeclaredFields()) {
                if (esPrioridad(campo.getType())) {
                    fugas.add(clase.getSimpleName() + "." + campo.getName());
                }
            }
            for (Method metodo : clase.getDeclaredMethods()) {
                for (Class<?> parametro : metodo.getParameterTypes()) {
                    if (esPrioridad(parametro)) {
                        fugas.add(clase.getSimpleName() + "#" + metodo.getName());
                    }
                }
            }
        }
        v.comprobar("Ningun canal menciona prioridades", fugas.isEmpty(),
                fugas.isEmpty() ? "los 5 canales ignoran la prioridad" : fugas.toString());

        v.titulo("Restriccion 5: las APIs de los proveedores son incompatibles entre si");
        v.comprobar("Cada proveedor expone una firma distinta",
                firmas().size() == 5, String.join(" | ", firmas()));

        v.resumen();
    }

    private static boolean esCanalConcreto(Class<?> tipo) {
        return CanalMensajeria.class.isAssignableFrom(tipo) && !tipo.isInterface();
    }

    private static boolean esPrioridad(Class<?> tipo) {
        return tipo == Prioridad.class || EstrategiaEnvio.class.isAssignableFrom(tipo);
    }

    private static List<String> firmas() {
        List<String> firmas = new ArrayList<>();
        firmas.add("SendGridApi.send(String,String,String)");
        firmas.add("TwilioApi.sendSms(String,String,String):int");
        firmas.add("FcmApi.push(String,Map):Map");
        firmas.add("WebhookHttpClient.post(String,String):String");
        firmas.add("TelegramBotApi.sendMessage(long,String,String):boolean");
        return firmas;
    }
}
