# FábricaViva — Sistema de Control de Producción (MES)

**Asignatura:** Patrones de Software
**Programa:** Ingeniería de Sistemas — Unidades Tecnológicas de Santander (UTS)
**Docente:** Eliecer Montero Ojeda

**Integrantes:**
- Gabriel Augusto Ortega Martínez
- Jhonathan David Rojas Molina

**Video (patrón Singleton):** https://www.youtube.com/watch?v=ALtJFTv_e2M

---

## El proyecto

Buscamos desarrollar un sistema que permita controlar y analizar el proceso de producción desde el software, organizando la información y facilitando la toma de decisiones. El sistema está escrito en **Java** y se trabaja en Visual Studio Code.

Módulos:
- Planificación y programación de producción
- Control de calidad y trazabilidad
- Integración con máquinas CNC y robots
- Análisis de OEE (Eficiencia General de los Equipos)

## Patrones creacionales implementados

| Patrón | Situación de la planta que resuelve | Clase principal | Prueba |
|---|---|---|---|
| Singleton | Un único turno activo y una única meta de OEE compartidos por todos los módulos | `config/ConfiguracionPlanta` | `PruebaSingleton` |
| Factory Method | Pedidos normales y urgentes que se preparan de forma distinta | `ordenes/FabricaOrden` | `PruebaFactoryMethod` |
| Builder | El mismo cálculo de OEE entregado resumido o detallado | `reportes/GeneradorReportes` | `PruebaBuilder` |
| Abstract Factory | Equipos de marcas distintas que no se pueden mezclar | `equipos/FabricaEquipos` | `PruebaAbstractFactory` |
| Prototype | Fichas técnicas de piezas frecuentes usadas como plantilla | `prototipo/EspecificacionPieza` | `PruebaPrototype` |

## Estructura

```
src/main/java/com/fabricaviva/
├── config/        Singleton: ConfiguracionPlanta
├── produccion/    LineaProduccion (cliente del Singleton)
├── calidad/       PuestoInspeccion (cliente del Singleton)
├── ordenes/       Factory Method: FabricaOrden, FabricaOrdenEstandar, FabricaOrdenUrgente, ModuloOrdenes
├── reportes/      Builder: ReporteOeeBuilder, constructores concretos, GeneradorReportes
├── equipos/       Abstract Factory: FabricaEquipos, productos y familias siemens/ y fanuc/
├── prototipo/     Prototype: Prototipo, EspecificacionPieza, CatalogoEspecificaciones
└── Prueba*.java   Una prueba ejecutable por patrón
docs/uml/          Diagramas de clases en PlantUML (01 a 05)
```

## Cómo ejecutar

**Visual Studio Code** (con *Extension Pack for Java*): abrir la carpeta del proyecto, ir a *Run and Debug* (`Ctrl+Shift+D`) y elegir la prueba del patrón. No necesita Maven.

**Por consola**, desde la raíz del proyecto:

```bash
javac -encoding UTF-8 -d target/classes $(find src/main/java -name "*.java")
```

```bash
java -cp target/classes com.fabricaviva.PruebaSingleton
```

Cambiando la última palabra por `PruebaFactoryMethod`, `PruebaBuilder`, `PruebaAbstractFactory` o `PruebaPrototype` se ejecuta cada patrón.

## Diagramas

Los archivos `.puml` de `docs/uml/` se pueden pegar directamente en [PlantText](https://www.planttext.com/) para visualizarlos.

## Referencias

- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (2003). *Patrones de diseño: Elementos de software orientado a objetos reutilizable*. Pearson Educación.
- Medina Veloz, G., Luna Rosas, F. J., Muñoz Arteaga, J., & Martínez Romo, J. C. (2008). Integrando mediante patrones de software una estrategia fuzzy-logic, en un servicio de balanceo dinámico de carga bajo CORBA. *Conciencia Tecnológica*, (35), 12-20.
- MESA International. (1997). *MES explained: A high level vision* (White Paper N.º 6). Manufacturing Execution Systems Association.
- Nakajima, S. (1988). *Introduction to TPM: Total productive maintenance*. Productivity Press.
