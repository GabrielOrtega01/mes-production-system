package com.mensajeria.modelo;

/**
 * Datos de contacto de una persona. Cada canal toma de aqui lo que necesita:
 * el correo, el telefono, el token del dispositivo o la URL del sistema externo.
 */
public class Destinatario {

    private final String nombre;
    private final String email;
    private final String telefono;
    private final String tokenDispositivo;
    private final String urlWebhook;

    public Destinatario(String nombre, String email, String telefono,
                        String tokenDispositivo, String urlWebhook) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.tokenDispositivo = tokenDispositivo;
        this.urlWebhook = urlWebhook;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTokenDispositivo() {
        return tokenDispositivo;
    }

    public String getUrlWebhook() {
        return urlWebhook;
    }
}
