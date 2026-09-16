package com.fabricaviva;

import com.fabricaviva.prototipo.CatalogoEspecificaciones;
import com.fabricaviva.prototipo.EspecificacionPieza;

public class PruebaPrototype {

    public static void main(String[] args) {
        EspecificacionPieza ejeBase = new EspecificacionPieza("EJE-INOX-20", "Acero inoxidable", 0.05);
        ejeBase.agregarPasoControlCalidad("Verificar diametro");
        ejeBase.agregarPasoControlCalidad("Verificar acabado superficial");

        CatalogoEspecificaciones catalogo = new CatalogoEspecificaciones();
        catalogo.registrar("EJE-INOX-20", ejeBase);

        EspecificacionPieza copia1 = catalogo.obtenerCopia("EJE-INOX-20");
        EspecificacionPieza copia2 = catalogo.obtenerCopia("EJE-INOX-20");

        // Un cliente pide el eje con tolerancia mas estricta y una prueba adicional.
        copia2.setTolerancia(0.02);
        copia2.agregarPasoControlCalidad("Ensayo de dureza");

        System.out.println("== Prueba 1: el catalogo entrega copias listas para usar ==");
        System.out.println("Prototipo base: " + ejeBase);
        System.out.println("Copia 1       : " + copia1);
        System.out.println("Copia 2       : " + copia2);

        System.out.println("\n== Prueba 2: son objetos distintos en memoria ==");
        System.out.println("copia1 == copia2 ? " + (copia1 == copia2));
        System.out.println("copia1 == ejeBase ? " + (copia1 == ejeBase));

        System.out.println("\n== Prueba 3: modificar la copia 2 no altera al prototipo ni a la copia 1 ==");
        System.out.println("Prototipo base: tolerancia " + ejeBase.getTolerancia()
                + ", controles " + ejeBase.getCantidadControles());
        System.out.println("Copia 1       : tolerancia " + copia1.getTolerancia()
                + ", controles " + copia1.getCantidadControles());
        System.out.println("Copia 2       : tolerancia " + copia2.getTolerancia()
                + ", controles " + copia2.getCantidadControles());
    }
}
