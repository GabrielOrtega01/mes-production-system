package co.edu.uts.mes.config;

/**
 * PATRON SINGLETON (creacional).
 *
 * Proposito: garantizar que exista UNA SOLA instancia de la configuracion de
 * la planta y ofrecer un punto de acceso global a ella.
 *
 * Por que aqui: en un MES el turno activo y el umbral de OEE son datos unicos
 * de la planta. Si cada modulo creara su propia configuracion, la linea de
 * produccion y el puesto de inspeccion podrian estar trabajando con turnos
 * distintos y el sistema perderia consistencia.
 *
 * Los tres elementos obligatorios del patron estan marcados abajo como (1), (2) y (3).
 */
public class ConfiguracionPlanta {

    // (1) Referencia estatica privada: la unica instancia que existira.
    private static ConfiguracionPlanta instancia;

    private final String nombrePlanta;
    private String turnoActivo;
    private double umbralOee;

    // (2) Constructor PRIVADO: nadie fuera de esta clase puede hacer "new".
    private ConfiguracionPlanta() {
        this.nombrePlanta = "Planta Bucaramanga";
        this.turnoActivo = "MANANA";
        this.umbralOee = 0.85;
        System.out.println("  [ConfiguracionPlanta] >>> Se creo la instancia UNICA <<<");
    }

    // (3) Metodo estatico publico: unica via para obtener la instancia.
    public static ConfiguracionPlanta getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionPlanta();
        }
        return instancia;
    }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public String getTurnoActivo() {
        return turnoActivo;
    }

    public void setTurnoActivo(String turnoActivo) {
        this.turnoActivo = turnoActivo;
    }

    public double getUmbralOee() {
        return umbralOee;
    }

    public void setUmbralOee(double umbralOee) {
        this.umbralOee = umbralOee;
    }
}
