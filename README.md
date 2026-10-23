# FábricaViva — Sistema de Ejecución de Manufactura (MES)

**Asignatura:** Patrones de Software  
**Programa:** Ingeniería de Sistemas · Décimo semestre  
**Institución:** Universitaria Tecnológica de Santander (UTS)  
**Docente:** Eliecer Montero Ojeda  

**Integrantes:**
- Gabriel Augusto Ortega Martínez
- Jhonathan David Rojas Molina

**Demo en vivo:** https://gabrielortega01.github.io/mes-production-system/  
**Video (patrón Singleton):** https://www.youtube.com/watch?v=ALtJFTv_e2M

---

## El proyecto

FábricaViva es un sistema de control y análisis de producción que aplica los **12 patrones de diseño GoF** en un contexto industrial realista. El sistema gestiona órdenes de producción, equipos, costos, reportes OEE y alertas de la planta.

Está escrito en **Java puro** (sin Maven) y cuenta con un **frontend HUD interactivo** publicado en GitHub Pages, más una **suite de 55 tests JUnit 5**.

---

## Patrones GoF implementados (12)

### Creacionales (5)

| Patrón | Solución en la planta | Clase principal |
|---|---|---|
| Singleton | Un único turno activo y meta OEE compartidos por todos los módulos | `config/ConfiguracionPlanta` |
| Factory Method | Órdenes normales y urgentes construidas de forma distinta | `ordenes/FabricaOrden` |
| Abstract Factory | Familias de equipos Siemens/Fanuc incompatibles entre sí | `equipos/FabricaEquipos` |
| Builder | El mismo OEE entregado como reporte resumido o detallado | `reportes/GeneradorReportes` |
| Prototype | Fichas técnicas de piezas usadas como plantilla clonable | `prototipo/EspecificacionPieza` |

### Estructurales (6)

| Patrón | Solución en la planta | Clase principal |
|---|---|---|
| Composite | Árbol de planta → línea → máquina con capacidad agregada | `produccion/LineaProduccion` |
| Decorator | Ajustes de costo apilables: prioritario, horas extra, descuento | `costos/DecoradorCosto` |
| Facade | 4 métodos simples que ocultan 6 subsistemas al operador | `fachada/ControlTurnoFacade` |
| Adapter | Integración con ERP legado que entrega texto plano | `integracion/AdaptadorErp` |
| Bridge | Tipos de alerta desacoplados del canal de notificación | `alertas/AlertaProduccion` |
| Flyweight | Estado intrínseco de piezas compartido en memoria | `materiales/FabricaTipoMaterial` |

### De comportamiento (1)

| Patrón | Solución en la planta | Clase principal |
|---|---|---|
| Proxy | Control de acceso por rol antes de generar reportes OEE | `proxy/ProxyReportesSeguro` |

---

## Frontend HUD

Interfaz industrial interactiva publicada en GitHub Pages:

- Grafo hexagonal de los 12 patrones GoF con conexiones animadas
- Inspector por patrón: descripción, código fuente, principio SOLID y laboratorio interactivo
- Panel de 55 tests JUnit 5 con resultados y tiempos
- Simulación de turno de producción con métricas OEE en tiempo real

---

## Tests

Suite JUnit 5 con **55 tests** para los 12 patrones (JDK 17, sin Maven):

```powershell
# Ejecutar todos los tests
.\test.ps1

# Ejecutar un suite específico
.\test.ps1 -filtro SingletonTest
```

Tiempo de ejecución: ~576ms · Resultado: 55/55 PASSED ✓

---

## Estructura del proyecto

```
src/
├── main/java/com/fabricaviva/
│   ├── config/        Singleton
│   ├── ordenes/       Factory Method
│   ├── prototipo/     Prototype
│   ├── equipos/       Abstract Factory (siemens/, fanuc/)
│   ├── reportes/      Builder
│   ├── produccion/    Composite
│   ├── costos/        Decorator
│   ├── reporteturno/  Decorator (profundización)
│   ├── fachada/       Facade
│   ├── integracion/   Adapter
│   ├── alertas/       Bridge
│   ├── materiales/    Flyweight
│   └── proxy/         Proxy
└── test/java/com/fabricaviva/
    └── *Test.java     55 tests JUnit 5

frontend/
└── index.html         HUD interactivo (GitHub Pages)

lib/
└── junit-platform-console-standalone-1.10.3.jar

docs/uml/              Diagramas PlantUML (01–12)
```

## Cómo ejecutar

**Compilar y correr (consola):**

```bash
javac -encoding UTF-8 -d target/classes $(find src/main/java -name "*.java")
java -cp target/classes com.fabricaviva.PruebaSingleton
```

**Tests:**

```powershell
.\test.ps1
```

**Frontend:** abrir https://gabrielortega01.github.io/mes-production-system/

---

## Referencias

- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (2003). *Patrones de diseño: Elementos de software orientado a objetos reutilizable*. Pearson Educación.
- Medina Veloz, G., Luna Rosas, F. J., Muñoz Arteaga, J., & Martínez Romo, J. C. (2008). Integrando mediante patrones de software una estrategia fuzzy-logic, en un servicio de balanceo dinámico de carga bajo CORBA. *Conciencia Tecnológica*, (35), 12–20.
- MESA International. (1997). *MES explained: A high level vision* (White Paper N.º 6). Manufacturing Execution Systems Association.
- Nakajima, S. (1988). *Introduction to TPM: Total productive maintenance*. Productivity Press.
