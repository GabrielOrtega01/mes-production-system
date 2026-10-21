package com.fabricaviva;

import com.fabricaviva.config.ConfiguracionPlanta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Singleton — ConfiguracionPlanta")
class SingletonTest {

    @Test
    @DisplayName("getInstancia() siempre retorna el mismo objeto")
    void mismaInstancia() {
        ConfiguracionPlanta a = ConfiguracionPlanta.getInstancia();
        ConfiguracionPlanta b = ConfiguracionPlanta.getInstancia();
        assertSame(a, b, "Deben ser la misma referencia en memoria");
    }

    @Test
    @DisplayName("Cambio en una referencia es visible en todas las demas")
    void estadoCompartido() {
        ConfiguracionPlanta a = ConfiguracionPlanta.getInstancia();
        ConfiguracionPlanta b = ConfiguracionPlanta.getInstancia();
        a.setTurnoActivo("NOCHE");
        assertEquals("NOCHE", b.getTurnoActivo(),
                "El cambio de turno debe reflejarse en cualquier referencia");
        a.setTurnoActivo("MANANA"); // restaurar
    }

    @Test
    @DisplayName("Nombre de planta no cambia (inmutable)")
    void nombrePlantaInmutable() {
        String nombre = ConfiguracionPlanta.getInstancia().getNombrePlanta();
        assertNotNull(nombre);
        assertFalse(nombre.isBlank(), "El nombre de la planta no puede estar vacio");
    }

    @Test
    @DisplayName("Umbral OEE inicial es 0.85")
    void umbralOeeDefecto() {
        ConfiguracionPlanta.getInstancia().setUmbralOee(0.85); // reset
        assertEquals(0.85, ConfiguracionPlanta.getInstancia().getUmbralOee(), 0.001);
    }
}
