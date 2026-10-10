# ADR 0009: Decorator (profundización) y Facade en FábricaViva MES

## Estado
Aceptado

## Contexto
El MES necesita generar reportes de turno en distintos formatos (básico para operador,
oficial con encabezado y firma para dirección, borrador para revisión, con observaciones
para calidad). Modelar esto con herencia produce 2^n subclases.

Además, el operador de turno debe coordinar seis subsistemas (Singleton de configuración,
Factory Method de órdenes, Composite de planta, Adapter de registro, Bridge de alertas y
Decorator de costos) para realizar operaciones cotidianas como iniciar turno, crear orden o
cerrar turno. Sin una fachada, el cliente debe conocer y orquestar todos estos módulos.

## Alternativas consideradas
1. Herencia pura para reportes: `ReporteConEncabezado`, `ReporteConEncabezadoYFirma`, etc.
   → explosión combinatoria (2^4 = 16 clases para 4 extensiones).
2. `if/else` por tipo de reporte en un método `generar()` → viola Open/Closed.
3. Cliente orquesta directamente los seis subsistemas → acoplamiento alto y código frágil.
4. **Decorator + Facade** (elegida).

## Decisión
- **Decorator profundización** (`com.fabricaviva.reporte`): interfaz `ReporteTurno` con
  `ReporteBaseTurno` como componente concreto y cuatro decoradores independientes:
  `EncabezadoPlanta`, `FirmaJefeTurno`, `MarcaAguaBorrador`, `SeccionObservaciones`.
  Cada decorador añade una sola responsabilidad; el orden se decide en runtime.

- **Facade** (`com.fabricaviva.fachada`): `ControlTurnoFacade` expone cinco operaciones
  de alto nivel (`iniciarTurno`, `crearOrden`, `consultarCapacidadTotal`,
  `calcularCostoConAjustes`, `cerrarTurno`) que coordinan internamente los seis
  subsistemas existentes. El cliente (operador de turno) no necesita conocer ningún
  subsistema directamente.

## Consecuencias
- **Positivas**: extensibilidad sin modificación (nuevo decorador no toca los existentes),
  desacoplamiento del cliente respecto a subsistemas, punto único de cambio si el
  subsistema evoluciona.
- **Negativas**: mayor número de clases, el orden de decoración importa y debe
  documentarse, riesgo de que `ControlTurnoFacade` acumule demasiadas
  responsabilidades si el sistema crece.

## Referencias
- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of
  Reusable Object-Oriented Software*. Addison-Wesley. Decorator: pp. 175–184;
  Facade: pp. 185–193.
