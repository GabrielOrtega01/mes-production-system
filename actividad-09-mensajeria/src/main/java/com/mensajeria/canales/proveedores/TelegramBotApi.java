package com.mensajeria.canales.proveedores;

/**
 * API externa simulada de Telegram. Se agrega al final del ejercicio para
 * demostrar la restriccion 4: un canal nuevo no obliga a tocar las prioridades.
 */
public class TelegramBotApi {

    private String ultimoTexto;

    public boolean sendMessage(long chatId, String text, String parseMode) {
        this.ultimoTexto = text;
        return chatId != 0;
    }

    public String getUltimoTexto() {
        return ultimoTexto;
    }
}
