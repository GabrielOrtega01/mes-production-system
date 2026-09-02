# Patron Singleton — Sistema de Control de Produccion (MES)

**Asignatura:** Patrones de Software
**Programa:** Ingenieria de Sistemas — Unidades Tecnologicas de Santander (UTS)
**Docente:** Eliecer Montero Ojeda

<<<<<<< HEAD
**Video** https://www.youtube.com/watch?v=ALtJFTv_e2M
Integrantes:
- Gabriel Augusto Ortega Martínez
- Jhonathan David Rojas Molina
=======
**Integrantes:**
- Gabriel Augusto Ortega Martinez
- Jhonathan David Rojas Molina
>>>>>>> b4cda6a (Implementar patron Singleton en ConfiguracionPlanta)

---

<<<<<<< HEAD
El proyecto se basa en desarrollar un Sistema de Control de Producción (MES) utilizando Python en Visual Studio Code. La idea es crear un sistema que permita planificar y programar la producción, llevar el control de calidad y la trazabilidad de los productos, simular o gestionar la integración con máquinas CNC y robots, y finalmente analizar el OEE, que permite medir la eficiencia general de los equipos.
En pocas palabras, buscamos desarrollar un sistema que permita controlar y analizar el proceso de producción desde el software, organizando la información y facilitando la toma de decisiones.

Módulos:
- Planificación y programación de producción
- Control de calidad y trazabilidad
- Integración con máquinas CNC y robots
- Análisis de OEE
=======
## 1. El patron

**Singleton** es un patron **creacional**. Su intencion, segun Gamma et al. (1995),
es *garantizar que una clase tenga una unica instancia y proporcionar un punto de
acceso global a ella*.

## 2. Por que aplica en un MES

En un sistema de control de produccion, el **turno activo** y el **umbral de OEE**
son datos unicos de la planta. Si cada modulo creara su propia configuracion, la
linea de produccion podria estar etiquetando piezas del turno de la manana
mientras el puesto de inspeccion evalua con el turno de la noche: el sistema
perderia consistencia y la trazabilidad quedaria corrupta.

`ConfiguracionPlanta` resuelve esto: existe una sola instancia y todos los
modulos la comparten.

## 3. Implementacion

Los tres elementos obligatorios del patron, en
[ConfiguracionPlanta.java](src/main/java/co/edu/uts/mes/config/ConfiguracionPlanta.java):

| # | Elemento | Codigo |
|---|---|---|
| 1 | Atributo estatico privado | `private static ConfiguracionPlanta instancia;` |
| 2 | Constructor privado | `private ConfiguracionPlanta() { ... }` |
| 3 | Metodo estatico de acceso | `public static ConfiguracionPlanta getInstancia()` |

El constructor privado es la clave: impide que cualquier otra clase escriba
`new ConfiguracionPlanta()`. La unica forma de obtener el objeto es
`ConfiguracionPlanta.getInstancia()`.

## 4. Estructura del proyecto

```
src/main/java/co/edu/uts/mes/
├── Main.java                        <- demostracion ejecutable
├── config/ConfiguracionPlanta.java  <- EL SINGLETON
├── produccion/LineaProduccion.java  <- cliente 1
└── calidad/PuestoInspeccion.java    <- cliente 2
docs/uml/singleton.puml              <- diagrama de clases
```

Cuatro clases en total. El diagrama UML esta en
[docs/uml/singleton.puml](docs/uml/singleton.puml) (se abre con el plugin
*PlantUML Integration* de IntelliJ IDEA).

## 5. Como ejecutar

**Visual Studio Code** (requiere la extension *Extension Pack for Java*):
abrir la carpeta del proyecto, abrir `Main.java` y pulsar **Run** sobre el
metodo `main`, o `F5`. El proyecto ya trae `.vscode/settings.json` y
`.vscode/launch.json` configurados; no necesita Maven.

**IntelliJ IDEA:** abrir `Main.java` y pulsar el boton verde de ejecutar.

Por consola:

```bash
javac -d out src/main/java/co/edu/uts/mes/config/ConfiguracionPlanta.java src/main/java/co/edu/uts/mes/produccion/LineaProduccion.java src/main/java/co/edu/uts/mes/calidad/PuestoInspeccion.java src/main/java/co/edu/uts/mes/Main.java
```

```bash
java -cp out co.edu.uts.mes.Main
```

## 6. Guion para el video

**Duracion sugerida: 4 a 5 minutos.**

1. **Que es y para que sirve** (~40 s). Mostrar el README, seccion 1 y 2.
   Decir: patron creacional, una sola instancia, punto de acceso global.
   Justificar el caso: turno y umbral son unicos en la planta.

2. **El diagrama UML** (~40 s). Abrir `docs/uml/singleton.puml`. Senalar la
   auto-referencia estatica `instancia`, el constructor privado (visibilidad `-`)
   y la multiplicidad: muchos clientes `0..*` contra una unica instancia `1`.

3. **El codigo** (~90 s). Abrir `ConfiguracionPlanta.java` y senalar los tres
   elementos marcados con los comentarios `(1)`, `(2)` y `(3)`.
   *Momento fuerte:* en `LineaProduccion.java`, escribir `new ConfiguracionPlanta()`
   y mostrar que IntelliJ lo marca en rojo con el error
   *"ConfiguracionPlanta() has private access"*. Borrarlo. Eso demuestra en vivo
   que el patron protege la unicidad.

4. **La ejecucion** (~90 s). Ejecutar `Main.java` y narrar las tres pruebas:
   - Prueba 1: el mensaje de creacion aparece **una sola vez** aunque se llame
     tres veces a `getInstancia()`.
   - Prueba 2: los tres `hashCode` son identicos y `a == b` es `true`:
     es literalmente el mismo objeto en memoria.
   - Prueba 3: se cambia el turno una vez y **los dos modulos** lo ven al
     instante. La misma pieza con OEE 0.91 pasa de APROBADO a NO APROBADO
     porque el umbral cambio en la instancia compartida.

5. **Cierre** (~30 s). Mencionar la limitacion honesta: esta version *lazy* no
   es segura en entornos multihilo; en produccion se usaria `synchronized`,
   *double-checked locking* o la inicializacion temprana. Esto suele sumar
   puntos por profundidad.

## 7. Referencia

Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1995). *Design patterns:
Elements of reusable object-oriented software*. Addison-Wesley.

---

> **Nota:** el esqueleto completo del MES con los trece patrones (Facade, State,
> Command, Observer, Chain of Responsibility, Adapter, Bridge, Strategy,
> Decorator, entre otros) esta guardado en la rama `esqueleto-completo` por si se
> requiere en entregas posteriores.
>>>>>>> b4cda6a (Implementar patron Singleton en ConfiguracionPlanta)
