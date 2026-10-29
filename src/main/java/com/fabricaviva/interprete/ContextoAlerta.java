package com.fabricaviva.interprete;

import java.util.HashMap;
import java.util.Map;

/** Context: almacena los valores del entorno de la planta. */
public class ContextoAlerta {

    private final Map<String, Double> variables = new HashMap<>();

    public void set(String variable, double valor) {
        variables.put(variable, valor);
    }

    public double get(String variable) {
        return variables.getOrDefault(variable, 0.0);
    }
}
