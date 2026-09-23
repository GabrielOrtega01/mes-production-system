package com.mensajeria.pruebas;

/** Utilidad minima de aserciones para las pruebas ejecutables. */
public class Verificacion {

    private int total;
    private int fallos;

    public void comprobar(String descripcion, boolean condicion, Object observado) {
        total++;
        if (!condicion) {
            fallos++;
        }
        System.out.println((condicion ? "  [OK]    " : "  [FALLA] ") + descripcion
                + " -> " + observado);
    }

    public void titulo(String texto) {
        System.out.println();
        System.out.println("== " + texto + " ==");
    }

    public void resumen() {
        System.out.println();
        System.out.println("Resultado: " + (total - fallos) + " de " + total + " comprobaciones correctas");
        if (fallos > 0) {
            System.exit(1);
        }
    }
}
