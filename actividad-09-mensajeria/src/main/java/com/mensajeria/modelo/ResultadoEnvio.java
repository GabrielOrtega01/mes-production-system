package com.mensajeria.modelo;

/** Resultado de intentar entregar un mensaje por un canal. */
public class ResultadoEnvio {

    private final String canal;
    private final boolean exitoso;
    private final int intentos;
    private final String detalle;

    public ResultadoEnvio(String canal, boolean exitoso, int intentos, String detalle) {
        this.canal = canal;
        this.exitoso = exitoso;
        this.intentos = intentos;
        this.detalle = detalle;
    }

    public String getCanal() {
        return canal;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public int getIntentos() {
        return intentos;
    }

    public String getDetalle() {
        return detalle;
    }

    @Override
    public String toString() {
        return (exitoso ? "OK  " : "FALLO") + " | " + canal + " | intentos: " + intentos
                + " | " + detalle;
    }
}
