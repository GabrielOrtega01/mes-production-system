package com.fabricaviva.reportes;

/** Datos crudos que la planta registra durante un turno. */
public class MetricasTurno {

    private final double tiempoPlanificadoMin;
    private final double tiempoParadasMin;
    private final double tiempoCicloIdealMin;
    private final int piezasProducidas;
    private final int piezasBuenas;

    public MetricasTurno(double tiempoPlanificadoMin, double tiempoParadasMin,
                         double tiempoCicloIdealMin, int piezasProducidas, int piezasBuenas) {
        this.tiempoPlanificadoMin = tiempoPlanificadoMin;
        this.tiempoParadasMin = tiempoParadasMin;
        this.tiempoCicloIdealMin = tiempoCicloIdealMin;
        this.piezasProducidas = piezasProducidas;
        this.piezasBuenas = piezasBuenas;
    }

    public double getTiempoPlanificadoMin() {
        return tiempoPlanificadoMin;
    }

    public double getTiempoOperativoMin() {
        return tiempoPlanificadoMin - tiempoParadasMin;
    }

    public double getTiempoCicloIdealMin() {
        return tiempoCicloIdealMin;
    }

    public int getPiezasProducidas() {
        return piezasProducidas;
    }

    public int getPiezasBuenas() {
        return piezasBuenas;
    }
}
