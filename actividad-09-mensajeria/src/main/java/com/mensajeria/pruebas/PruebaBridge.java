package com.mensajeria.pruebas;

import com.mensajeria.PlataformaMensajeria;
import com.mensajeria.canales.AdaptadorFcm;
import com.mensajeria.canales.AdaptadorSendGrid;
import com.mensajeria.canales.AdaptadorTwilio;
import com.mensajeria.canales.AdaptadorWebhook;
import com.mensajeria.canales.CanalMensajeria;
import com.mensajeria.canales.proveedores.FcmApi;
import com.mensajeria.canales.proveedores.SendGridApi;
import com.mensajeria.canales.proveedores.TwilioApi;
import com.mensajeria.canales.proveedores.WebhookHttpClient;
import com.mensajeria.modelo.Destinatario;
import com.mensajeria.modelo.Mensaje;
import com.mensajeria.modelo.Prioridad;
import com.mensajeria.modelo.ResultadoEnvio;
import com.mensajeria.prioridades.EnvioAlto;
import com.mensajeria.prioridades.EnvioCritico;
import com.mensajeria.prioridades.EnvioDiferido;
import com.mensajeria.prioridades.EnvioNormal;
import java.util.List;

/** Comprueba que cada prioridad usa los canales como exige el enunciado. */
public class PruebaBridge {

    public static void main(String[] args) {
        Verificacion v = new Verificacion();

        // El canal push se configura para fallar dos veces: asi se ven los reintentos.
        List<CanalMensajeria> canales = List.of(
                new AdaptadorSendGrid(new SendGridApi()),
                new AdaptadorTwilio(new TwilioApi(), "+573000000000"),
                new AdaptadorFcm(new FcmApi(2)),
                new AdaptadorWebhook(new WebhookHttpClient()));

        EnvioDiferido diferido = new EnvioDiferido(canales);
        PlataformaMensajeria plataforma = new PlataformaMensajeria();
        plataforma.registrar(new EnvioCritico(canales));
        plataforma.registrar(new EnvioAlto(canales));
        plataforma.registrar(new EnvioNormal(canales));
        plataforma.registrar(diferido);

        Destinatario destinatario = new Destinatario("Ana Gomez", "ana@empresa.com",
                "+573001112233", "tok-abc-123", "https://erp.empresa.com/hooks/alertas");
        Mensaje mensaje = new Mensaje("Alerta de sistema", "La linea 3 se detuvo", destinatario);

        v.titulo("Prioridad CRITICA: todos los canales disponibles, con reintentos");
        List<ResultadoEnvio> criticos = plataforma.notificar(mensaje, Prioridad.CRITICA);
        criticos.forEach(r -> System.out.println("       " + r));
        v.comprobar("Uso los 4 canales", criticos.size() == 4, criticos.size() + " canales");
        v.comprobar("Todos entregaron", criticos.stream().allMatch(ResultadoEnvio::isExitoso), "exitosos");
        ResultadoEnvio push = criticos.stream()
                .filter(r -> r.getCanal().equals("PUSH")).findFirst().orElseThrow();
        v.comprobar("El canal inestable se reintento hasta lograrlo", push.getIntentos() == 3,
                push.getIntentos() + " intentos");

        v.titulo("Prioridad ALTA: dos canales, sin reintentos");
        List<ResultadoEnvio> altos = plataforma.notificar(mensaje, Prioridad.ALTA);
        altos.forEach(r -> System.out.println("       " + r));
        v.comprobar("Uso exactamente 2 canales", altos.size() == 2, altos.size() + " canales");
        v.comprobar("Un solo intento por canal",
                altos.stream().allMatch(r -> r.getIntentos() == 1), "1 intento");

        v.titulo("Prioridad NORMAL: un solo canal");
        List<ResultadoEnvio> normales = plataforma.notificar(mensaje, Prioridad.NORMAL);
        normales.forEach(r -> System.out.println("       " + r));
        v.comprobar("Uso exactamente 1 canal", normales.size() == 1, normales.size() + " canal");

        v.titulo("Prioridad BAJA: se encola y sale despues");
        List<ResultadoEnvio> bajos = plataforma.notificar(mensaje, Prioridad.BAJA);
        bajos.forEach(r -> System.out.println("       " + r));
        v.comprobar("No se envio en el momento", diferido.pendientes() == 1,
                diferido.pendientes() + " pendiente");
        List<ResultadoEnvio> procesados = diferido.procesarCola();
        procesados.forEach(r -> System.out.println("       " + r));
        v.comprobar("Al procesar la cola si se entrego",
                procesados.size() == 1 && procesados.get(0).isExitoso(), "entregado");
        v.comprobar("La cola quedo vacia", diferido.pendientes() == 0,
                diferido.pendientes() + " pendientes");

        v.resumen();
    }
}
