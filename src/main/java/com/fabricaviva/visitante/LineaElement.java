package com.fabricaviva.visitante;

import java.util.ArrayList;
import java.util.List;

/** ConcreteElement: línea de producción compuesta de máquinas. */
public class LineaElement implements ElementoProductivo {

    private final String id;
    private final List<MaquinaElement> maquinas = new ArrayList<>();

    public LineaElement(String id) {
        this.id = id;
    }

    public void agregar(MaquinaElement m) { maquinas.add(m); }

    @Override
    public void aceptar(VisitanteProduccion visitante) {
        visitante.visitarLinea(this);
        for (MaquinaElement m : maquinas) {
            m.aceptar(visitante);
        }
    }

    @Override public String getId()                       { return id; }
    public List<MaquinaElement> getMaquinas()             { return maquinas; }
}
