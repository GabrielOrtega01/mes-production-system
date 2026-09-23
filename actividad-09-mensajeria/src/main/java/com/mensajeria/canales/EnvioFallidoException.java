package com.mensajeria.canales;

/** Error de entrega traducido a un tipo propio del sistema. */
public class EnvioFallidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EnvioFallidoException(String mensaje) {
        super(mensaje);
    }
}
