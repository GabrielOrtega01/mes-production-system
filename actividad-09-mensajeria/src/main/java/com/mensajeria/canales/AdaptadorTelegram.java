package com.mensajeria.canales;

import com.mensajeria.canales.proveedores.TelegramBotApi;
import com.mensajeria.modelo.Mensaje;

/**
 * Canal agregado al final del ejercicio. Su existencia demuestra la restriccion 4:
 * no hubo que modificar ninguna estrategia de prioridad para incorporarlo.
 */
public class AdaptadorTelegram implements CanalMensajeria {

    private final TelegramBotApi api;
    private final long chatId;

    public AdaptadorTelegram(TelegramBotApi api, long chatId) {
        this.api = api;
        this.chatId = chatId;
    }

    @Override
    public String nombre() {
        return "TELEGRAM";
    }

    @Override
    public boolean disponible() {
        return true;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        String texto = "*" + mensaje.getAsunto() + "*\n" + mensaje.getCuerpo();
        if (!api.sendMessage(chatId, texto, "Markdown")) {
            throw new EnvioFallidoException("Telegram rechazo el chat " + chatId);
        }
    }
}
