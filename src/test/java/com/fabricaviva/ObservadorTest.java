package com.fabricaviva;

import com.fabricaviva.observador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Observer — SensorMaquina / ObservadorMaquina")
class ObservadorTest {

    private SensorMaquina sensor;
    private PanelSupervisor panel;

    @BeforeEach
    void setUp() {
        sensor = new SensorMaquina("MCN-01");
        panel  = new PanelSupervisor("PANEL-NORTE", 0.80);
        sensor.agregarObservador(panel);
    }

    @Test
    @DisplayName("Cambio de OEE genera una alerta en el panel")
    void cambioDeOeeGeneraAlerta() {
        sensor.reportarOee(0.75);
        assertEquals(1, panel.getAlertas().size());
    }

    @Test
    @DisplayName("Alerta incluye OEE bajo cuando OEE < umbral")
    void alertaIncluyeOeeBajo() {
        sensor.reportarOee(0.70);
        String alerta = panel.getAlertas().get(0);
        assertTrue(alerta.contains("OEE bajo") || alerta.contains("MCN-01"),
            "La alerta debe mencionar la maquina o el problema de OEE");
    }

    @Test
    @DisplayName("Parada no planificada genera alerta aunque OEE no cambie")
    void paradaNotificaObservadores() {
        sensor.reportarParada();
        assertEquals(1, panel.getAlertas().size());
        assertTrue(panel.getAlertas().get(0).contains("PARADA"));
    }

    @Test
    @DisplayName("Mismo OEE reportado dos veces no genera segunda notificacion")
    void mismoOeeNoNotificaDosVeces() {
        sensor.reportarOee(0.75);
        sensor.reportarOee(0.75);
        assertEquals(1, panel.getAlertas().size(), "Sin cambio de OEE no debe haber segunda notificacion");
    }

    @Test
    @DisplayName("Observador removido no recibe mas notificaciones")
    void observadorRemovidoNoRecibeNotificaciones() {
        sensor.reportarOee(0.75);
        sensor.quitarObservador(panel);
        sensor.reportarOee(0.60);
        assertEquals(1, panel.getAlertas().size(), "El observador removido no debe recibir mas alertas");
    }

    @Test
    @DisplayName("Multiples observadores reciben la misma notificacion")
    void multiplesObservadoresReciben() {
        List<String> eventos = new ArrayList<>();
        ObservadorMaquina espiaA = (id, ev, oee) -> eventos.add("A:" + ev);
        ObservadorMaquina espiaB = (id, ev, oee) -> eventos.add("B:" + ev);
        sensor.agregarObservador(espiaA);
        sensor.agregarObservador(espiaB);
        sensor.reportarOee(0.50);
        assertEquals(3, eventos.size() + panel.getAlertas().size(),
            "Los tres observadores deben haber sido notificados (panel + 2 espias)");
    }
}
