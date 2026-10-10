# ADR 0008: Uso de Composite y Decorator en el Sistema de Gestión Académica

## Estado

Aceptado

## Contexto

El módulo académico del sistema necesita resolver dos problemas estructurales:

1. **Jerarquía académica**: la institución organiza sus estudiantes en cursos, los
   cursos en programas y los programas en facultades. El cliente (por ejemplo, un
   reporte de promedios) debe poder calcular el promedio y el total de estudiantes
   de cualquier nodo sin saber si está tratando con una hoja (Estudiante) o un
   contenedor (Curso, Programa, Facultad).

2. **Cálculo de matrícula con ajustes combinables**: el costo de matrícula admite
   descuentos (beca de mérito −30 %, hermanos matriculados −10 %, pronto pago −5 %)
   y recargos (intersemestral +15 %) que pueden aplicarse en cualquier combinación.
   Si se usara herencia directa, serían necesarias 2^n subclases para n ajustes posibles.

## Decisión

Se adoptan **Composite** para la jerarquía académica y **Decorator** para el cálculo
de matrícula.

- `ElementoAcademico` es la interfaz compartida por `Estudiante` (hoja) y por los
  composites `Curso`, `Programa` y `Facultad`.
- `CalculoMatricula` es la interfaz compartida por `MatriculaBase` (componente
  concreto) y por `DecoradorMatricula` (decorador abstracto del que derivan
  `BecaMerito`, `DescuentoHermanos`, `DescuentoProntoPago` y `RecargoIntersemestral`).

## Alternativas consideradas

| Alternativa | Problema |
|---|---|
| **Herencia pura** | Requiere 2^n subclases para n ajustes de matrícula; añadir una nueva clase de descuento exige modificar la jerarquía existente. |
| **if/else por tipo** | Viola el principio Abierto/Cerrado: agregar un nuevo tipo de elemento o ajuste obliga a modificar el código cliente. |
| **Strategy para matrícula** | Permite intercambiar algoritmos, pero no componerlos: con Strategy solo se puede aplicar una estrategia a la vez, no combinar varias. |
| **Composite + Decorator** *(elegida)* | Composite logra tratamiento uniforme recursivo; Decorator permite componer ajustes sin explosión combinatoria y sin modificar clases existentes. |

## Consecuencias

**Positivas**
- El cliente llama siempre a `getPromedio()` y `getTotalEstudiantes()` sin instanceof.
- Agregar una nueva categoría (por ejemplo, `Departamento`) es añadir una clase
  que implementa `ElementoAcademico`, sin tocar el código existente (OCP).
- Agregar un nuevo descuento es crear un nuevo decorador que extiende
  `DecoradorMatricula`, sin tocar `MatriculaBase` ni otros decoradores (OCP + SRP).
- Los decoradores son combinables: `n` ajustes producen `n` clases, no `2^n`.

**Negativas**
- El número de clases aumenta: actualmente 5 para Composite y 6 para Decorator.
- El orden de decoración importa cuando los ajustes no son conmutativos. En esta
  implementación todos son multiplicadores porcentuales sobre el valor envuelto,
  por lo que el orden no altera el costo final; sin embargo, si se introdujera un
  ajuste absoluto (por ejemplo, −$200.000 fijos), el orden sí importaría y debería
  documentarse en cada caso de uso.
- La depuración de cadenas largas de decoradores puede ser menos directa.

## Referencias

- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (2003). *Patrones de diseño:
  Elementos de software orientado a objetos reutilizable*. Pearson / Addison-Wesley.
  Composite: p. 163; Decorator: p. 175.
