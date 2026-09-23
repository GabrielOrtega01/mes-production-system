package com.mensajeria.canales.proveedores;

/** Cliente HTTP generico simulado: publica un JSON en una URL. */
public class WebhookHttpClient {

    private String ultimoJson;

    public String post(String url, String jsonPayload) {
        this.ultimoJson = jsonPayload;
        return url.startsWith("https://") ? "HTTP 204" : "HTTP 400";
    }

    public String getUltimoJson() {
        return ultimoJson;
    }
}
