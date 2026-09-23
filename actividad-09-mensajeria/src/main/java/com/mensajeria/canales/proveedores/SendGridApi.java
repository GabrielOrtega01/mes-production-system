package com.mensajeria.canales.proveedores;

/** API externa simulada de SendGrid: trabaja con asunto y cuerpo HTML. */
public class SendGridApi {

    private String ultimoAsunto;
    private String ultimoHtml;

    public String send(String toEmail, String subject, String htmlBody) {
        this.ultimoAsunto = subject;
        this.ultimoHtml = htmlBody;
        return "sg-msg-" + Math.abs((toEmail + subject).hashCode());
    }

    public String getUltimoAsunto() {
        return ultimoAsunto;
    }

    public String getUltimoHtml() {
        return ultimoHtml;
    }
}
