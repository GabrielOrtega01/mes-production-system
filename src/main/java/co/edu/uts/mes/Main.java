package co.edu.uts.mes;

import co.edu.uts.mes.calidad.PuestoInspeccion;
import co.edu.uts.mes.config.ConfiguracionPlanta;
import co.edu.uts.mes.produccion.LineaProduccion;

/**
 * Demostracion del patron Singleton en el MES.
 * Ejecutar esta clase muestra las tres evidencias del patron.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println(" PRUEBA 1: el constructor se ejecuta UNA sola vez");
        System.out.println("=================================================");
        System.out.println("Primera llamada a getInstancia():");
        ConfiguracionPlanta a = ConfiguracionPlanta.getInstancia();
        System.out.println("Segunda llamada a getInstancia():");
        ConfiguracionPlanta b = ConfiguracionPlanta.getInstancia();
        System.out.println("Tercera llamada a getInstancia():");
        ConfiguracionPlanta c = ConfiguracionPlanta.getInstancia();
        System.out.println("(el mensaje de creacion aparecio una sola vez)");

        System.out.println();
        System.out.println("=================================================");
        System.out.println(" PRUEBA 2: las tres variables son el MISMO objeto");
        System.out.println("=================================================");
        System.out.println("hashCode de a : " + a.hashCode());
        System.out.println("hashCode de b : " + b.hashCode());
        System.out.println("hashCode de c : " + c.hashCode());
        System.out.println("a == b ? " + (a == b));
        System.out.println("b == c ? " + (b == c));

        System.out.println();
        System.out.println("=================================================");
        System.out.println(" PRUEBA 3: el cambio se ve desde TODO el sistema");
        System.out.println("=================================================");
        LineaProduccion linea = new LineaProduccion("LINEA-01");
        PuestoInspeccion inspeccion = new PuestoInspeccion();

        System.out.println("--- Antes de cambiar el turno ---");
        System.out.println(linea.producirPieza(1));
        System.out.println(inspeccion.evaluar(0.91));

        System.out.println();
        System.out.println(">> Supervision cambia el turno a NOCHE y sube el umbral a 0.95");
        ConfiguracionPlanta.getInstancia().setTurnoActivo("NOCHE");
        ConfiguracionPlanta.getInstancia().setUmbralOee(0.95);

        System.out.println();
        System.out.println("--- Despues del cambio ---");
        System.out.println(linea.producirPieza(2));
        System.out.println(inspeccion.evaluar(0.91));
        System.out.println();
        System.out.println("Ningun objeto se volvio a crear: los dos modulos leen");
        System.out.println("la misma instancia y por eso ven el cambio al instante.");
    }
}
