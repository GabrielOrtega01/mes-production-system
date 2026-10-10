package com.fabricaviva.fachada;

import com.fabricaviva.alertas.AlertaCalidad;
import com.fabricaviva.alertas.AlertaMantenimiento;
import com.fabricaviva.alertas.AlertaProduccion;
import com.fabricaviva.alertas.CanalAlerta;
import com.fabricaviva.alertas.ConsolaAlerta;
import com.fabricaviva.config.ConfiguracionPlanta;
import com.fabricaviva.costos.CalculoCostoOrden;
import com.fabricaviva.costos.CargoHorasExtra;
import com.fabricaviva.costos.CostoBaseOrden;
import com.fabricaviva.costos.RecargoPrioritario;
import com.fabricaviva.integracion.RegistroProduccion;
import com.fabricaviva.ordenes.ModuloOrdenes;
import com.fabricaviva.ordenes.OrdenProduccion;
import com.fabricaviva.produccion.Estacion;
import com.fabricaviva.produccion.LineaFabricacion;
import com.fabricaviva.produccion.Maquina;
import com.fabricaviva.produccion.Planta;

/**
 * Patrón Facade.
 * Punto de entrada único para el operador de turno: coordina Singleton,
 * Factory Method, Composite, Adapter, Bridge y Decorator sin exponer
 * la complejidad interna de cada subsistema.
 */
public class ControlTurnoFacade {

    private final ConfiguracionPlanta config;
    private final ModuloOrdenes modulo;
    private final Planta planta;
    private final RegistroProduccion registro;
    private final CanalAlerta canal;

    public ControlTurnoFacade(RegistroProduccion registro) {
        this.config = ConfiguracionPlanta.getInstancia();
        this.modulo = new ModuloOrdenes();
        this.planta = construirPlanta();
        this.registro = registro;
        this.canal = new ConsolaAlerta();
    }

    public void iniciarTurno(String turno) {
        config.setTurnoActivo(turno);
        AlertaProduccion alerta = new AlertaMantenimiento(canal, turno);
        alerta.disparar("Inicio de turno — verificacion preventiva de equipos");
        System.out.println("[FACADE] Turno " + turno + " iniciado en " + config.getNombrePlanta());
    }

    public OrdenProduccion crearOrden(String referencia, int cantidad, boolean urgente) {
        OrdenProduccion orden = modulo.crear(referencia, cantidad, urgente);
        System.out.println("[FACADE] Orden creada: " + orden);
        return orden;
    }

    public double consultarCapacidadTotal() {
        double capacidad = planta.getCapacidadTotal();
        System.out.printf("[FACADE] Capacidad total: %.0f pz/h | Maquinas: %d%n",
                capacidad, planta.getTotalMaquinas());
        return capacidad;
    }

    public double calcularCostoConAjustes(String codigo, double costoBase,
                                           boolean prioritario, boolean horasExtra) {
        CalculoCostoOrden costo = new CostoBaseOrden(codigo, costoBase);
        if (prioritario) costo = new RecargoPrioritario(costo);
        if (horasExtra)  costo = new CargoHorasExtra(costo);
        double total = costo.calcularCosto();
        System.out.printf("[FACADE] %s -> $%.2f%n", costo.describir(), total);
        return total;
    }

    public void cerrarTurno(String turno, int piezasProducidas) {
        registro.registrarCierreTurno(turno, piezasProducidas);
        AlertaProduccion alerta = new AlertaCalidad(canal, turno);
        alerta.disparar("Cierre de turno — " + piezasProducidas + " piezas registradas");
        System.out.println("[FACADE] Turno " + turno + " cerrado. Ordenes procesadas: "
                + modulo.getOrdenes().size());
    }

    private Planta construirPlanta() {
        Maquina torno     = new Maquina("Torno CNC-01",    120);
        Maquina fresadora = new Maquina("Fresadora CNC-02", 80);
        Maquina soldadora = new Maquina("Soldadora S-01",   60);

        Estacion mecanizado = new Estacion("Mecanizado");
        mecanizado.agregar(torno);
        mecanizado.agregar(fresadora);

        Estacion ensamble = new Estacion("Ensamble");
        ensamble.agregar(soldadora);

        LineaFabricacion lineaA = new LineaFabricacion("Linea A");
        lineaA.agregar(mecanizado);
        lineaA.agregar(ensamble);

        Planta p = new Planta("FabricaViva");
        p.agregar(lineaA);
        return p;
    }
}
