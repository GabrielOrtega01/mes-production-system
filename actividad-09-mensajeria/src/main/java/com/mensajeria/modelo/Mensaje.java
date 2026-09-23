package com.mensajeria.modelo;

/** Notificacion que la plataforma debe entregar. */
public class Mensaje {

    private final String asunto;
    private final String cuerpo;
    private final Destinatario destinatario;

    public Mensaje(String asunto, String cuerpo, Destinatario destinatario) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }
}
