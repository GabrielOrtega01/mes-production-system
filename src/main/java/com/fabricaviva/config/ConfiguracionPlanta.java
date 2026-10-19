package com.fabricaviva.config;

/**
 * Patrón Singleton.
 * La planta tiene un solo turno activo y una sola meta de OEE en cada momento,
 * así que esta configuración debe existir una única vez y ser compartida por
 * todos los módulos.
 */
public class ConfiguracionPlanta {

    // (1) Inicialización temprana: la JVM crea la instancia al cargar la clase,
    //     antes de que cualquier hilo pueda llamar getInstancia(). Thread-safe sin synchronized.
    private static final ConfiguracionPlanta INSTANCIA = new ConfiguracionPlanta();

    private final String nombrePlanta;
    private String turnoActivo;
    private double umbralOee;

    // (2) Constructor privado: ninguna otra clase puede usar "new".
    private ConfiguracionPlanta() {
        this.nombrePlanta = "FabricaViva - Planta Bucaramanga";
        this.turnoActivo = "MANANA";
        this.umbralOee = 0.85;
        System.out.println("Creando la unica instancia de ConfiguracionPlanta...");
    }

    // (3) Método estático: único punto de acceso a la instancia.
    public static ConfiguracionPlanta getInstancia() {
        return INSTANCIA;
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
