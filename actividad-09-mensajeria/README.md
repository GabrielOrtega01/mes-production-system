# Actividad 9 — Plataforma de mensajería con canales y prioridades

**Asignatura:** Patrones de Software — Unidades Tecnológicas de Santander (UTS)
**Docente:** Eliecer Montero Ojeda
**Integrantes:** Gabriel Augusto Ortega Martínez · Jhonathan David Rojas Molina

Solución del ejercicio: **Bridge + Adapter**. La prioridad decide cuántos canales se usan y si hay reintentos; el canal decide cómo se entrega. Las dos jerarquías crecen por separado.

## Estructura

```
src/main/java/com/mensajeria/
├── modelo/          Mensaje, Destinatario, Prioridad, ResultadoEnvio
├── prioridades/     Bridge: EstrategiaEnvio (abstracción) y sus cinco refinamientos
├── canales/         Adapter: CanalMensajeria (implementador) y los cinco adaptadores
│   └── proveedores/ APIs externas simuladas, con firmas incompatibles entre sí
├── pruebas/         PruebaBridge, PruebaAdapter, PruebaExtensibilidad
└── PlataformaMensajeria.java
docs/uml/            Diagramas PlantUML (Bridge, Adapter y secuencia)
docs/adr/            ADR-001 con la decisión de diseño
```

## Prioridades

| Prioridad | Comportamiento | Clase |
|---|---|---|
| Crítica | Todos los canales disponibles, hasta 3 intentos por canal | `EnvioCritico` |
| Alta | Dos canales, un intento cada uno | `EnvioAlto` |
| Normal | Un canal | `EnvioNormal` |
| Baja | Se encola y sale al procesar la cola | `EnvioDiferido` |
| Programada | Agregada después, para demostrar extensibilidad | `EnvioProgramado` |

## Canales

| Canal | Proveedor simulado | Firma original |
|---|---|---|
| EMAIL | SendGrid | `send(String, String, String) : String` |
| SMS | Twilio | `sendSms(String, String, String) : int` |
| PUSH | FCM | `push(String, Map) : Map` |
| WEBHOOK | HTTP genérico | `post(String, String) : String` |
| TELEGRAM | Telegram Bot | `sendMessage(long, String, String) : boolean` |

## Cómo ejecutar

**Visual Studio Code** (con *Extension Pack for Java*): *Run and Debug* (`Ctrl+Shift+D`) y elegir la prueba.

**Por consola**, desde la raíz del repositorio:

```bash
javac -encoding UTF-8 -d actividad-09-mensajeria/target/classes $(find actividad-09-mensajeria/src -name "*.java")
```

```bash
java -cp actividad-09-mensajeria/target/classes com.mensajeria.pruebas.PruebaBridge
```

Las otras dos pruebas son `com.mensajeria.pruebas.PruebaAdapter` y `com.mensajeria.pruebas.PruebaExtensibilidad`. Las tres terminan con un resumen del tipo `Resultado: N de N comprobaciones correctas` y devuelven código de salida distinto de cero si alguna falla.

## Restricciones del enunciado y dónde se verifican

| # | Restricción | Verificación |
|---|---|---|
| 1 | La lógica de prioridad no conoce los canales concretos | `PruebaExtensibilidad`, por reflexión sobre los campos de las estrategias |
| 2 | La lógica de canal no conoce las prioridades | `PruebaExtensibilidad`, por reflexión sobre campos y parámetros de los adaptadores |
| 3 | Agregar una prioridad no modifica los canales | `EnvioProgramado`, registrada sin tocar ningún adaptador |
| 4 | Agregar un canal no modifica las prioridades | `AdaptadorTelegram`: `EnvioCritico` pasa de 4 a 5 canales sin cambios |
| 5 | Las APIs de los canales son incompatibles | Las cinco firmas distintas de `canales/proveedores/` |
