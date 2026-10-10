# ADR 0008: Uso de Composite y Decorator en FábricaViva MES

## Estado

Aceptado

## Contexto

El módulo de gestión de planta del sistema necesita resolver dos problemas estructurales:

1. **Jerarquía de unidades de producción**: la planta organiza sus recursos en
   máquinas individuales agrupadas en estaciones, las estaciones en líneas de
   producción y las líneas en la planta completa. Un cliente (por ejemplo, el
   módulo de capacidad) debe poder calcular la capacidad total y el número de
   máquinas de cualquier nodo —hoja o contenedor— sin conocer su tipo concreto.

2. **Cálculo del costo de una orden de producción con ajustes combinables**: el
   costo base de una orden puede verse afectado por recargos (orden prioritaria
   +25 %, horas extra +30 %) y descuentos (alto volumen −15 %, cliente preferente
   −10 %) en cualquier combinación. Con herencia directa, n ajustes requerirían
   2^n subclases para cubrir todas las combinaciones.

## Decisión

Se adoptan **Composite** para la jerarquía de unidades de producción y
**Decorator** para el cálculo del costo de órdenes.

- `UnidadProduccion` es la interfaz compartida por `Maquina` (hoja) y los
  composites `Estacion`, `LineaProduccion` y `Planta`.
- `CalculoCostoOrden` es la interfaz compartida por `CostoBaseOrden` (componente
  concreto) y `DecoradorCosto` (decorador abstracto del que derivan
  `RecargoPrioritario`, `CargoHorasExtra`, `DescuentoVolumen` y
  `DescuentoClientePreferente`).

## Alternativas consideradas

| Alternativa | Problema |
|---|---|
| **Herencia pura** | Requiere 2^n subclases para n ajustes de costo; añadir un nuevo ajuste exige modificar la jerarquía existente. |
| **if/else por tipo** | Viola el principio Abierto/Cerrado: agregar un nuevo tipo de unidad o ajuste obliga a modificar el código cliente. |
| **Strategy para costos** | Permite intercambiar un algoritmo de costo, pero no componer varios simultáneamente. |
| **Composite + Decorator** *(elegida)* | Composite logra tratamiento uniforme recursivo; Decorator permite componer ajustes sin explosión combinatoria. |

## Consecuencias

**Positivas**
- El cliente llama `getCapacidadTotal()` y `getTotalMaquinas()` sin instanceof.
- Agregar un nuevo nivel de jerarquía (p. ej. `Departamento`) es crear una clase
  que implementa `UnidadProduccion`, sin tocar el código existente (OCP).
- Agregar un nuevo ajuste de costo es crear un decorador que extiende
  `DecoradorCosto`, sin modificar `CostoBaseOrden` ni los demás decoradores (OCP + SRP).
- Los decoradores son combinables: n ajustes producen n clases, no 2^n.

**Negativas**
- El número de clases aumenta: 5 para Composite y 6 para Decorator.
- El orden de decoración importa cuando los ajustes no son conmutativos. En esta
  implementación todos son multiplicadores porcentuales, por lo que la
  conmutatividad se preserva; si se introdujera un ajuste absoluto (p. ej.
  −$50.000 fijos), el orden sí importaría y debería documentarse por caso de uso.
- La depuración de cadenas largas de decoradores puede ser menos directa.

## Referencias

- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (2003). *Patrones de diseño:
  Elementos de software orientado a objetos reutilizable*. Pearson / Addison-Wesley.
  Composite: p. 163; Decorator: p. 175.
