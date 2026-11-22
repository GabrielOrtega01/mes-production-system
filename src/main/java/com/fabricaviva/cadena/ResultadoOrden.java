package com.fabricaviva.cadena;

public class ResultadoOrden {
    public final boolean aprobada;
    public final String  mensaje;
    public final String  validadorQueRechazo;

    private ResultadoOrden(boolean aprobada, String mensaje, String quien) {
        this.aprobada           = aprobada;
        this.mensaje            = mensaje;
        this.validadorQueRechazo = quien;
    }

    public static ResultadoOrden ok() {
        return new ResultadoOrden(true, "Orden aprobada.", null);
    }

    public static ResultadoOrden rechazo(String mensaje, String quien) {
        return new ResultadoOrden(false, mensaje, quien);
    }

    @Override
    public String toString() {
        return aprobada
            ? "APROBADA: " + mensaje
            : "RECHAZADA por " + validadorQueRechazo + ": " + mensaje;
    }
}
