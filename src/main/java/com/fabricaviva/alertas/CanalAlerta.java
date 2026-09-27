package com.fabricaviva.alertas;

/**
 * Patrón Bridge – rol Implementador.
 * Define cómo se entrega físicamente una alerta, sin importar su tipo.
 */
public interface CanalAlerta {

    void emitir(String codigo, String descripcion, String turno);
}
