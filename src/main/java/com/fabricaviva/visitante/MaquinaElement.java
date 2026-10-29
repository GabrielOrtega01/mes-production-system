package com.fabricaviva.visitante;

/** ConcreteElement: máquina individual de producción. */
public class MaquinaElement implements ElementoProductivo {

    private final String id;
    private final double oee;
    private final double costoHora;

    public MaquinaElement(String id, double oee, double costoHora) {
        this.id        = id;
        this.oee       = oee;
        this.costoHora = costoHora;
    }

    @Override
    public void aceptar(VisitanteProduccion visitante) {
        visitante.visitarMaquina(this);
    }

    @Override public String getId()        { return id; }
    public double getOee()                 { return oee; }
    public double getCostoHora()           { return costoHora; }
}
