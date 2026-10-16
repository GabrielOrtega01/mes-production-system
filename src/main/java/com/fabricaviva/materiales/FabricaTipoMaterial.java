package com.fabricaviva.materiales;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** FlyweightFactory + Singleton: gestiona el pool de tipos de material. */
public class FabricaTipoMaterial {

    private static final FabricaTipoMaterial INSTANCIA = new FabricaTipoMaterial();
    private final Map<String, TipoMaterialCompartido> pool = new HashMap<>();

    private FabricaTipoMaterial() {}

    public static FabricaTipoMaterial getInstance() { return INSTANCIA; }

    public TipoMaterialFlyweight getMaterial(String codigo, String nombre,
                                              double densidad, String unidad) {
        return pool.computeIfAbsent(codigo,
            k -> new TipoMaterialCompartido(k, nombre, densidad, unidad));
    }

    public int getTamanioPool() { return pool.size(); }

    public Map<String, TipoMaterialCompartido> getPool() {
        return Collections.unmodifiableMap(pool);
    }
}
